package org.example.parsers;

import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

import java.util.function.Function;

abstract public class Parser<T> {
    protected abstract ParseState<T> parse(ParseState<?> parseState) throws ParseException;

    public ParseState<T> parse(String text) throws ParseException {
        return parse(ParseState.init(text));
    }

    public <F> Parser<F> map(Function<T, F> fn) {
        Parser<T> parser = this;
        return new Parser<F>() {
            @Override
            protected ParseState<F> parse(ParseState<?> parseState) throws ParseException {
                ParseState<T> p = parser.parse(parseState);

                ParseState<F> newParseState = new ParseState<>();
                newParseState.setDocument(p.getDocument());
                newParseState.setMessage(p.getMessage());
                newParseState.setParseResult(p.getParseResult());
                if (!p.getParseResult().equals(ParseResult.ERROR)) {
                    newParseState.setValue(fn.apply(p.getValue()));
                }

                return newParseState;
            }
        };
    }

    public Parser<T> notNull() {
        Parser <T> parser = this;
        return new Parser<T>() {
            @Override
            protected ParseState<T> parse(ParseState<?> parseState) throws ParseException {
                ParseState<T> oldState = parser.parse(parseState);
                if (parseState.getParseResult().equals(ParseResult.ERROR)) return oldState;
                if (parser.isNull(oldState)) return new ParseState<>(ParseResult.ERROR, "Found Null value while parsing"
                ,parseState.getDocument(), null);
                return oldState;
            }
        };
    }


    protected boolean isNull (ParseState<T> p) {
        return false;
    }

}
