package org.example;

public class ParseState {
    private ParseResult parseResult;
    private String message;
    private Document document;
    private Object value;

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public ParseState() {
    }

    public ParseState(ParseResult parseResult, String message, Document document, Object value) {
        this.parseResult = parseResult;
        this.message = message;
        this.document = document;
        this.value = value;
    }

    public static ParseState init(String text){
        Document document = new Document(text, 0);
        ParseState parseState = new ParseState();
        parseState.setDocument(document);
        parseState.setParseResult(ParseResult.OK);

        return parseState;
    }
}
