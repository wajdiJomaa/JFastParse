package org.example.jsonParser.types;

import java.util.HashMap;

public class JsonObject extends JsonData {
    private HashMap<String, JsonData> map;

    public JsonObject(HashMap<String, JsonData> map) {
        this.map = map;
    }

    public HashMap<String, JsonData> getMap() {
        return map;
    }

    public void setMap(HashMap<String, JsonData> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "JsonObject{" +
                "map=" + map +
                '}';
    }
}
