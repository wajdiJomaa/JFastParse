package org.example.jsonParser.types;

import java.util.List;

public class JsonList extends JsonData{
    private  List<JsonData> list;

    public JsonList(List<JsonData> list) {
        this.list = list;
    }

    public List<JsonData> getList() {
        return list;
    }

    public void setList(List<JsonData> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "JsonList{" +
                "list=" + list +
                '}';
    }
}
