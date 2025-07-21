package org.example.parsers;

import org.example.Document;
import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

public class PString extends Parser<String> {
    private final String expected;

    public PString(String expected){
        this.expected = expected;
    }

    @Override
    protected ParseState<String> parse(ParseState<?> parseState) throws ParseException {
        if (parseState.getParseResult().equals(ParseResult.ERROR))
            throw new ParseException("error while parsing",parseState);

        int current = parseState.getDocument().getIndex();
        int x = 0;
        for (int i = current ; i < current +  expected.length(); i++) {
            if ((i >= parseState.getDocument().getText().length()) ||
                    !(parseState.getDocument().getText().charAt(i) == expected.charAt(x++))) {
                return new ParseState<>(ParseResult.ERROR, "Error Parsing", parseState.getDocument(), null);
            }
        }

        Document newDoc = parseState.getDocument().advance(expected.length());
        return new ParseState<>(ParseResult.OK, null, newDoc, expected);
    }
}
