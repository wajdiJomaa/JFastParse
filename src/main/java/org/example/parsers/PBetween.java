package org.example.parsers;

import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

public class PBetween<T> extends Parser<T> {

    private String start;
    private String end;
    private Parser<T> parser;

    public PBetween(String start, String end, Parser<T> parser) {
        this.start = start;
        this.end = end;
        this.parser = parser;
    }

    @Override
    protected ParseState<T> parse(ParseState<?> parseState) throws ParseException {
        if (parseState.getParseResult().equals(ParseResult.ERROR))
            return new ParseState<>(parseState.getParseResult(), parseState.getMessage(), parseState.getDocument(), null);

        Parser<String> beginParser = new PString(start);
        Parser<String> endParser = new PString(end);

        ParseState<String> pState = beginParser.parse(parseState);

        if (pState.getParseResult().equals(ParseResult.ERROR))
            return new ParseState<>(ParseResult.ERROR, "Unmatched begin", parseState.getDocument(), null);

        ParseState<T> parseState1 = parser.parse(pState);

        if (parseState1.getParseResult().equals(ParseResult.ERROR))
            return new ParseState<>(ParseResult.ERROR, parseState1.getMessage(), parseState.getDocument(), null);

        pState = endParser.parse(parseState1);

        if (pState.getParseResult().equals(ParseResult.ERROR))
            return new ParseState<>(ParseResult.ERROR, "Unmatched end", parseState.getDocument(), null);


        return new ParseState<>(ParseResult.OK, null, pState.getDocument(), parseState1.getValue());
    }
}
