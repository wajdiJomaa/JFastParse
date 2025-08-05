package org.example.jsonParser;

import org.example.ParseException;
import org.example.ParseState;
import org.example.context.ContextManager;
import org.example.jsonParser.types.*;
import org.example.parsers.*;

import java.util.HashMap;
import java.util.List;

public class JsonParser extends ContextManager {

    Parser<JsonInteger> intParser = new PNumber().map(JsonInteger::new);

    Parser<JsonBool> boolParser = new PChoice<>(new PString("true"), new PString("false"))
            .map(b -> b.equals("true") ? new JsonBool(true) : new JsonBool(false));

    Parser<JsonString> stringParser = new PBetween<>("\"", "\"", new PLetters())
            .map(JsonString::new);

    Parser<JsonList> listParser =
            new PBetween<>("[","]",
                new PSepBy<>(
                    new PChoice<>(intParser, boolParser, stringParser, lazy("listParser", JsonList.class))
                    ,",")
            ).map(JsonList::new);

    Parser<JsonObject> objectParser =
            new PBetween<>("{","}",
            new PSepBy<>(
            new PSequence<>(
                stringParser,
                new PString(":"),
                new PChoice<>(intParser, boolParser, stringParser, listParser, lazy("objectParser", JsonObject.class))
            )
            , ","
    )).map(lists -> {
        HashMap<String, JsonData> map = new HashMap<>();
        for(List<Object> l : lists) {
            map.put(((JsonString)l.get(0)).getS(), ((JsonData)l.get(2)));
        }
        return new JsonObject(map);
    });


    Parser<JsonData> jsonParser = new PChoice<>(boolParser, stringParser, intParser, listParser, objectParser);

    public JsonData parse(String file) throws ParseException {
        bind("listParser", listParser);
        bind("objectParser", objectParser);
        ParseState<JsonData> pr = jsonParser.parse(file);

        System.out.println("Parse Result: " + pr.getParseResult());
        System.out.println("Parse Message: " + pr.getMessage());

        return pr.getValue();
    }


    public static void main(String[] args) throws ParseException {
        JsonParser jsonParser = new JsonParser();
        JsonData jd = jsonParser.parse("{\"x\":[1,[2,2]]}");
        //JsonObject{map={x=JsonList{list=[JsonInteger{i=1}, JsonList{list=[JsonInteger{i=2}, JsonInteger{i=2}]}]}}}
        System.out.println(jd);
    }


}
