package org.example.parsers;

import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PSepBy<T> extends Parser<List<T>> {
    private List<String> separators;

    public PSepBy(String s){
        separators = new ArrayList<>();
        separators.add(s);
    }

    public PSepBy(String... s){
        separators = new ArrayList<>();
        Collections.addAll(this.separators, s);
    }

    @Override
    protected ParseState<List<T>> parse(ParseState<?> parseState) throws ParseException {

        if (parseState.getParseResult().equals(ParseResult.ERROR))
            throw new ParseException("error while parsing",parseState);

        List<T> values = new ArrayList<>();

        return new ParseState<>(ParseResult.OK, null, parseState.getDocument(), values);
    }
}
