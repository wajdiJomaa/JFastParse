package org.example.parsers;

import org.example.ParseException;
import org.example.ParseResult;
import org.example.ParseState;

import java.util.ArrayList;
import java.util.List;

public class PSepBy<T> extends Parser<List<T>> {
    private String separator;
    private Parser<T> parser;

    public PSepBy(Parser<T> parser, String separator){
        this.separator = separator;
        this.parser = parser;
    }

    @Override
    protected ParseState<List<T>> parse(ParseState<?> parseState) throws ParseException {

        if (parseState.getParseResult().equals(ParseResult.ERROR))
            throw new ParseException("error while parsing", parseState);

        List<T> values = new ArrayList<>();
        ParseState<T> state = null;
        boolean stop = false;
        while(!stop) {
            if (state == null) {
                state = parser.parse(parseState);
                if (state.getParseResult().equals(ParseResult.ERROR)) break;
            }
            else {
                state = parser.parse(state);
                if (state.getParseResult().equals(ParseResult.ERROR))
                    return new ParseState<>(ParseResult.ERROR, "message ended unexpectedly", parseState.getDocument() ,null);
            }

            values.add(state.getValue());

            for (int i = 0; i < separator.length(); i++) {
                if (state.getDocument().getText().length() <= state.getDocument().getIndex() + i ||
                state.getDocument().getText().charAt(state.getDocument().getIndex() + i) != separator.charAt(i)) {
                    stop = true;
                    break;
                }
            }

            if (!stop)
                state = new ParseState<>(ParseResult.OK, null, state.getDocument().advance(separator.length()), null);
        }

        return new ParseState<>(ParseResult.OK, null, state.getDocument(), values);
    }

    @Override
    protected boolean isNull(ParseState<List<T>> p) {
        return p.getValue().isEmpty();
    }
}
