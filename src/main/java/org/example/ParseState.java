package org.example;

public class ParseState<T> {
    private ParseResult parseResult;
    private String message;
    private Document document;
    private T value;

    public ParseResult getParseResult() {
        return parseResult;
    }

    public void setParseResult(ParseResult parseResult) {
        this.parseResult = parseResult;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public ParseState() {
    }

    public ParseState(ParseResult parseResult, String message, Document document, T value) {
        this.parseResult = parseResult;
        this.message = message;
        this.document = document;
        this.value = value;
    }

    public static ParseState<Object> init(String text){
        Document document = new Document(text, 0);
        ParseState<Object> parseState = new ParseState<>();
        parseState.setDocument(document);
        parseState.setParseResult(ParseResult.OK);

        return parseState;
    }
}
