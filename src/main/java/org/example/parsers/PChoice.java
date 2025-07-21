package org.example.parsers;

import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PChoice<T> extends Parser<T>{
    private final List<Parser<? extends T>> parsers;

    @SafeVarargs
    public PChoice(Parser<? extends T>... parsers){
        this.parsers = new ArrayList<>();
        Collections.addAll(this.parsers, parsers);
    }

    @Override
    protected ParseState<T> parse(ParseState<?> parseState) throws ParseException {
        if (parseState.getParseResult().equals(ParseResult.ERROR))
            throw new ParseException("error while parsing",parseState);

        for (Parser<? extends T> p: parsers){
            ParseState<T> pState = (ParseState<T>) p.parse(parseState);
            if (pState.getParseResult().equals(ParseResult.OK))
                return pState;
        }
        return new ParseState<>(ParseResult.ERROR, "Error Parsing", parseState.getDocument(), null);
    }
}
