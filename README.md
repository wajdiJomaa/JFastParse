JFastParse is a parse combinators library in java

installation:
no need for external dependancies just clone the project

Some sections are currently under construction

todo:
- add error handling
- add support to remove spaces

Json parser:

```java
public class JsonParser extends ContextManager {

    // Parses an integer value and wraps it into JsonInteger
    Parser<JsonInteger> intParser = new PNumber().map(JsonInteger::new);

    // Parses either "true" or "false", maps it to JsonBool
    Parser<JsonBool> boolParser = new PChoice<>(new PString("true"), new PString("false"))
            .map(b -> b.equals("true") ? new JsonBool(true) : new JsonBool(false));

    // Parses a string wrapped in double quotes and wraps it into JsonString
    Parser<JsonString> stringParser = new PBetween<>("\"", "\"", new PLetters())
            .map(JsonString::new);

    // Parses a list: [value1, value2, ...]
    Parser<JsonList> listParser =
            new PBetween<>("[", "]",
                    new PSepBy<>(
                            new PChoice<>(
                                    intParser,
                                    boolParser,
                                    stringParser,
                                    lazy("listParser", JsonList.class) // allow recursive lists
                            ),
                            "," // elements are comma-separated
                    )
            ).map(JsonList::new);

    // Parses an object: {"key": value, ...}
    Parser<JsonObject> objectParser =
            new PBetween<>("{", "}",
                    new PSepBy<>(
                            new PSequence<>( // parses: string ":" value
                                    stringParser,                    // key
                                    new PString(":"),                // colon
                                    new PChoice<>(                   // value can be any JSON type
                                            intParser,
                                            boolParser,
                                            stringParser,
                                            listParser,
                                            lazy("objectParser", JsonObject.class) // recursive objects
                                    )
                            ),
                            "," // key-value pairs are comma-separated
                    )
            ).map(lists -> {
                // Build a map from the parsed key-value pairs
                HashMap<String, JsonData> map = new HashMap<>();
                for (List<Object> l : lists) {
                    map.put(((JsonString) l.get(0)).getS(), ((JsonData) l.get(2)));
                }
                return new JsonObject(map);
            });

    // Top-level parser: chooses the appropriate parser based on input
    Parser<JsonData> jsonParser = new PChoice<>(boolParser, stringParser, intParser, listParser, objectParser);

    // Entry point for parsing a JSON string
    public JsonData parse(String file) throws ParseException {
        // Register recursive parsers
        bind("listParser", listParser);
        bind("objectParser", objectParser);

        // Parse the input
        ParseState<JsonData> pr = jsonParser.parse(file);

        // Print debug info
        System.out.println("Parse Result: " + pr.getParseResult());
        System.out.println("Parse Message: " + pr.getMessage());

        return pr.getValue();
    }
    
    public static void main(String[] args) throws ParseException {
        JsonParser jsonParser = new JsonParser();
        JsonData jd = jsonParser.parse("{\"x\":[1,[2,2]]}");
        //JsonObject{map={x=JsonList{list=[JsonInteger{i=1}, JsonList{list=[JsonInteger{i=2}, JsonInteger{i=2}]}]}}}
        //no support for spaces
        System.out.println(jd);
    }


}

```
