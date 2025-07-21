package org.example;

import org.example.parsers.PChoice;
import org.example.parsers.Parser;
import org.example.parsers.PSequence;
import org.example.parsers.PString;

import java.util.List;

public class App 
{
    public static void main( String[] args ) throws ParseException {
        Parser<String> s1 = new PString("ABC");
        Parser<Integer> i1 = new PString("1").map(s -> 1);
        Parser<String> s2 = new PString("DEF");

        Parser<List<String>> s3 = new PSequence<>(s1, s2);
        Parser<List<Object>> s4 = new PSequence<>(s1, i1);

        Parser<String> s5 = new PChoice<>(s2, s1);
        System.out.println(s5.parse("ABCDEF").getValue());

        System.out.println(s1.parse("ABCD").getValue());
        System.out.println(i1.parse("1").getValue());
        System.out.println(s3.parse("ABCDEF").getValue());
        System.out.println(s4.parse("ABC1").getValue());


    }
}
