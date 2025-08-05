package org.example.jsonParser.types;

public class JsonInteger extends JsonData {
    private int i;

    public JsonInteger(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return "JsonInteger{" +
                "i=" + i +
                '}';
    }
}
