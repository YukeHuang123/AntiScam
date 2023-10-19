package com.example.antiscam;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeTest {
    private static Pattern pattern = Pattern.compile("(^[@#%&|!=<>](.+))|([&|!=<>](.+))");
    private static Pattern pattern1 = Pattern.compile("(&)|(|)");

    private static Pattern pattern2 = Pattern.compile("^[@#%][!=<>][|&].*$");
    private static Pattern pattern3 = Pattern.compile("[|&](.+)");

    @Test
    public void test1() {
        String s1 = "A=3&B=4|C=ui&D=d|E";
        String s2 = "@A=3&B=4|C=ui&D=d|E";
        System.out.println(s2);
        Matcher matcher = pattern3.matcher(s2);
        if (matcher.find()) {
            System.out.println("found");
            String[] strings = s2.split("([|&])");
            for (String string : strings) {
                System.out.println(string);
            }
        } else {
            System.out.println("not found");
        }
    }

    @Test
    public void test2() {
        String s2 = "@A=3&B=4|C=ui&D=d|E";
        Pattern compile = Pattern.compile("[|&]");
        Matcher matcher = compile.matcher(s2);
        int previousEnd = 0;
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String nonMatched = s2.substring(previousEnd, end - 1);
            String match = s2.substring(start, end);
            System.out.println(matcher.groupCount() + "---" + matcher.group() + " nonMatched " + nonMatched + " match:" + match);
            previousEnd = end;
        }
        String endstr = s2.substring(previousEnd, s2.length());
        System.out.println(endstr);
    }

    @Test
    public void test3() {
        String input = "A=3&B=4|C=ui&D=d|E";

        // 定义正则表达式模式
        String pattern = "\\b[A-Za-z]+=[^&|]+\\b";

        // 创建 Pattern 对象
        Pattern regex = Pattern.compile(pattern);

        // 创建 Matcher 对象
        Matcher matcher = regex.matcher(input);

        // 存储匹配结果
        List<String> matches = new ArrayList<>();

        // 进行匹配
        while (matcher.find()) {
            String match = matcher.group();
            matches.add(match);
        }

        // 输出匹配结果
        for (String match : matches) {
            System.out.println("匹配结果: " + match);
        }
    }

    @Test
    public void codeTest3() {
        String input = "A=3&B=4|C=ui&D=d|E";

        // 使用正则表达式匹配查询语句中的条件
        Pattern pattern = Pattern.compile("([&|])");
        Matcher matcher = pattern.matcher(input);

        // 检查条件的关系
        int index = 0;
        String andStr = "";
        String orStr = "";
        while (matcher.find()) {
            String condition = input.substring(index, matcher.start());
            String operator = matcher.group(1);

            if ("&".equals(operator)) {
                andStr += condition;
                System.out.println("条件 " + condition + " 使用了 & 关系");
            } else if ("|".equals(operator)) {
                orStr += condition;
                System.out.println("条件 " + condition + " 使用了 | 关系");
            }
            index = matcher.end();
        }

        // 处理最后一个条件
        String lastCondition = input.substring(index);
        System.out.println("条件 " + lastCondition + " 没有关系符");
    }

    @Test
    public void codeTest4() {
        String input = "#=Products that Disappoint&$=product and service scams&@=comp2100@anu.edu.au|#=Products that Disappoint&$=product and service scams&@=comp2100@anu.edu.au";
        String[] strings = input.split("([&|])");
        for (String string : strings) {
            System.out.println(string);
        }
        String endstr = strings[strings.length - 1];
        System.out.println(endstr);
        String s = input.substring(0, input.length() - endstr.length() - 1);
        System.out.println(input.charAt(input.length() - endstr.length() - 1) + "::::" + s);
    }

    @Test
    public void codeTest5(){
        String input = "";
    }

}
