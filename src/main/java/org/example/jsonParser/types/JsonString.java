package org.example.jsonParser.types;

public class JsonString extends JsonData {
    private String s;

    public JsonString(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return "JsonString{" +
                "s='" + s + '\'' +
                '}';
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }
}
