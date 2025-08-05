package org.example.jsonParser.types;

public class JsonBool extends JsonData {
    private boolean b;

    public JsonBool(boolean b) {
        this.b = b;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return "JsonBool{" +
                "b=" + b +
                '}';
    }
}
