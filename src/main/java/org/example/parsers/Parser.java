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


}
