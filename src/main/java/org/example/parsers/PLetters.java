package org.example.parsers;

import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

public class PLetters extends Parser<String> {
    @Override
    protected ParseState<String> parse(ParseState<?> parseState) throws ParseException {
        if (parseState.getParseResult().equals(ParseResult.ERROR))
            return new ParseState<>(parseState.getParseResult(), parseState.getMessage(), parseState.getDocument(), null);

        int index = parseState.getDocument().getIndex();
        StringBuilder strRes = new StringBuilder();

        while(index < parseState.getDocument().getText().length()) {
            if ((parseState.getDocument().getText().charAt(index) >= 'a' &&
                    parseState.getDocument().getText().charAt(index) <= 'z') ||
            (parseState.getDocument().getText().charAt(index) >= 'A' &&
                    parseState.getDocument().getText().charAt(index) <= 'Z'))  {
                strRes.append(parseState.getDocument().getText().charAt(index));
                index++;
            } else break;
        }

        if (strRes.toString().isEmpty()) return new ParseState<>(ParseResult.ERROR, "no letters found", parseState.getDocument(), null);

        return new ParseState<>(ParseResult.OK, null, parseState.getDocument().advance(strRes.toString().length()), strRes.toString());

    }
}
