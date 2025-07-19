package org.example.parsers;

import org.example.ParseResult;
import org.example.ParseState;

public class StringParser extends Parser {
    private final String expected;

    public StringParser(String expected){
        this.expected = expected;
    }

    @Override
    protected ParseState parseS(ParseState parseState) {
        if (parseState.getParseResult().equals(ParseResult.ERROR))
            return parseState;

        int current = parseState.getDocument().getIndex();
        int x = 0;
        for (int i = current ; i < current +  expected.length(); i++) {
            if ((i >= parseState.getDocument().getText().length()) ||
                    !(parseState.getDocument().getText().charAt(i) == expected.charAt(x++))) {
                return new ParseState(ParseResult.ERROR, "Error Parsing", parseState.getDocument(), null);
            }
        }

        parseState.getDocument().advance(expected.length());
        return new ParseState(ParseResult.OK, null, parseState.getDocument(), expected);
    }
}
