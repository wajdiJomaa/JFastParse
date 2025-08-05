package org.example.context;

import org.example.ParseState;
import org.example.parsers.LazyParser;
import org.example.parsers.Parser;

import java.util.HashMap;
import java.util.Map;

public abstract class ContextManager {
    private static Map<String, Parser<?>> context = new HashMap<>();

    public void bind(String ctxName, Parser<?> parser) {
        context.put(ctxName, parser);
    }

    public Parser<?> getContext(String parser){
        return context.get(parser);
    }

    public <T> LazyParser<T> lazy(String name, Class<T> type) {
        return new LazyParser<T>(name, this);
    }

}
