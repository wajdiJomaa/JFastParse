package org.example;

import org.example.parsers.Parser;
import org.example.parsers.SequenceParser;
import org.example.parsers.StringParser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Parser stringParser = new StringParser("1");
        Parser stringParser2 = new StringParser("+1");

        Parser sequenceParser = new SequenceParser(stringParser, stringParser2);
        ParseState state = sequenceParser.parse("1+1")
                .map(Object::toString)
                .getParseState();



        System.out.println(state.getParseResult());
        System.out.println(state.getValue());
    }
}
