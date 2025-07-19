package org.example;

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

    public void advance(int value){
        this.index += value;
    }
}
