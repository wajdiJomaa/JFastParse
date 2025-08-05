package org.example.parsers;

import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

public class PNumber extends Parser<Integer>{

    @Override
    protected ParseState<Integer> parse(ParseState<?> parseState) throws ParseException {
        if (parseState.getParseResult().equals(ParseResult.ERROR))
            return new ParseState<>(parseState.getParseResult(), parseState.getMessage(), parseState.getDocument(), null);

        int index = parseState.getDocument().getIndex();
        StringBuilder strRes = new StringBuilder();

        while(index < parseState.getDocument().getText().length()) {
            if (parseState.getDocument().getText().charAt(index) >= '0' &&
                    parseState.getDocument().getText().charAt(index) <= '9') {
                strRes.append(parseState.getDocument().getText().charAt(index));
                index++;
            } else break;
        }

        if (strRes.toString().isEmpty()) return new ParseState<>(ParseResult.ERROR, "no integer found", parseState.getDocument(), null);

        return new ParseState<>(ParseResult.OK, null, parseState.getDocument().advance(strRes.toString().length()), Integer.parseInt(strRes.toString()));
    }
}
