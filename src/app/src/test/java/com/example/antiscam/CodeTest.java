package com.example.antiscam;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeTest {
    private static Pattern pattern = Pattern.compile("(^[@#%&|!=<>](.+))|([&|!=<>](.+))");
    private static Pattern pattern1 = Pattern.compile("(&)|(|)");

    @Test
    public void test1() {
//        String s1 = "A=3&B=4|C=ui&D=d|E";
        String s1 = "A=3|B=4&C=ui|D=d|E";
        String[] s2 = s1.split("&");
        for (String s : s2) {
            System.out.println("----------------" + s);
            System.out.println();
            if (s.contains("|")) {
                String[] split = s.split("\\|");
                for (String s3 : split) {
                    System.out.println(s3 + "-----------");
                }
            }

        }
    }


}
