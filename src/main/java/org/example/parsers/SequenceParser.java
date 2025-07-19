package org.example.parsers;

import org.example.ParseResult;
import org.example.ParseState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SequenceParser extends Parser {
    private final List<Parser> parsers;
    public SequenceParser(Parser... parsers) {
        this.parsers = new ArrayList<>();
        Collections.addAll(this.parsers, parsers);
    }

    @Override
    protected ParseState parseS(ParseState parseState) {
        if (parseState.getParseResult().equals(ParseResult.ERROR))
            return parseState;

        List<Object> values = new ArrayList<>();
        for (Parser p: parsers) {
            parseState = p.parse(parseState).getParseState();
            if (parseState.getParseResult().equals(ParseResult.ERROR))
                return new ParseState(ParseResult.ERROR, "Error Parsing", parseState.getDocument(), null);
            values.add(parseState.getValue());
        }
        return new ParseState(ParseResult.OK, null, parseState.getDocument(), values);
    }
}
