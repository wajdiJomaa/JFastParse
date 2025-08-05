package org.example.parsers;

import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PSequence<T> extends Parser<List<T>> {
    private final List<Parser<? extends T>> parsers;

    @SafeVarargs
    public PSequence(Parser<? extends T>... parsers) {
        this.parsers = new ArrayList<>();
        Collections.addAll(this.parsers, parsers);
    }

    @Override
    protected ParseState<List<T>> parse(ParseState<?> parseState) throws ParseException {
        if (parseState.getParseResult().equals(ParseResult.ERROR))
            throw new ParseException("error while parsing",parseState);

        List<T> values = new ArrayList<>();
        ParseState<T> pState = null;

        for (Parser<? extends T> p: parsers) {
            if (pState == null)
                pState = (ParseState<T>) p.parse(parseState);
            else
                pState = (ParseState<T>) p.parse(pState);

            if (parseState.getParseResult().equals(ParseResult.ERROR))
                return new ParseState<>(ParseResult.ERROR, "Error Parsing", parseState.getDocument(), null);
            values.add(pState.getValue());
        }
        return new ParseState<>(ParseResult.OK, null, pState != null ? pState.getDocument() : parseState.getDocument(), values);
    }
}
