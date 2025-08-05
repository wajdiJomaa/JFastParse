package org.example.parsers;

import org.example.ParseException;
import org.example.ParseState;
import org.example.context.ContextManager;

public class LazyParser<T> extends Parser<T> {

    private final String parser;
    private final ContextManager contextManager;

    public LazyParser(String parser, ContextManager contextManager) {
        this.parser = parser;
        this.contextManager = contextManager;
    }

    @Override
    protected ParseState<T> parse(ParseState<?> parseState) throws ParseException {
        Parser<T> p = (Parser<T>) contextManager.getContext(parser);
        return p.parse(parseState);
    }
}
