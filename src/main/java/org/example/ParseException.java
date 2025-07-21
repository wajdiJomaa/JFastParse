package org.example;

public class ParseException extends Exception {
    private ParseState<?> parseState;

    public ParseException(String message, ParseState<?> parseState) {
        super(message);
        this.parseState = parseState;
    }

    public ParseState<?> getParseState() {
        return parseState;
    }

    public void setParseState(ParseState<?> parseState) {
        this.parseState = parseState;
    }
}
