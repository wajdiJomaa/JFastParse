package org.example;

import org.example.parsers.*;

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


        Parser<List<Integer>> ls = new PSepBy<>(new PNumber(), ",").notNull();
        System.out.println(ls.parse("123,145,134,12,1,11,15").getValue());
//        [123, 145, 134, 12, 1, 11, 15]

        System.out.println(ls.parse("").getMessage());
//      Found Null value while parsing

    }
}
