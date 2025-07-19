package org.example.parsers;

import org.example.ParseState;

import java.util.function.Function;

abstract public class Parser {
    protected ParseState parseState;

    public ParseState getParseState() {
        return parseState;
    }

    protected void setParseState(ParseState parseState) {
        this.parseState = parseState;
    }

    protected ParseState parseS(ParseState parseState){return null;};

    protected Parser parse(ParseState parseState) {
        setParseState(parseS(parseState));
        return this;
    }

    public Parser parse(String text) {
        return parse(ParseState.init(text));
    }

    public Parser map(Function<Object, Object> fn) {
        parseState.setValue(fn.apply(parseState.getValue()));
        return this;
    }


}
