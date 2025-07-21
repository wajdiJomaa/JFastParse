package org.example;

import javax.print.Doc;

public class Document {
    private String text;
    private int index;

    public Document(String text, int index) {
        this.text = text;
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Document advance(int value){
        return new Document(text, index + value);
    }
}
