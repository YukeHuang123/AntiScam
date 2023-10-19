package com.example.antiscam.searchCore;

import java.util.regex.Pattern;

public class Tokenizer {

    private Token token;
    private Pattern compile = Pattern.compile("[|&]");

    public Tokenizer(String input) {
        token = tokenize(input);
    }

    private Token tokenize(String input) {
        input = input.trim();
        if (input.isEmpty()) {
            return null;
        }

        if (input.contains("|") || input.contains("&")) {
            return parser3(input);
        } else if (input.startsWith("@") || input.startsWith("#") || input.startsWith("%") || input.startsWith("$")) {
            return parser1(input);
        } else {
            return new Token(input, null, Token.Type.STR, null);
        }
    }

    /**
     * parser @#%$
     *
     * @return
     */
    private Token parser1(String input) {
        if (input.startsWith("@")) {
            input = input.substring(1);
            return parser2(input, Token.Type.USEREMAIL);
        } else if (input.startsWith("#")) {
            input = input.substring(1);
            return parser2(input, Token.Type.TITLE);
        } else if (input.startsWith("%")) {
            input = input.substring(1);
            return parser2(input, Token.Type.AMOUNT);
        } else if (input.startsWith("$")) {
            input = input.substring(1);
            return parser2(input, Token.Type.SCAMTYPE);
        } else {
            return null;
        }
    }

    /**
     * parser !=<>
     *
     * @return
     */
    private Token parser2(String input, Token.Type type) {
        Token.Type type2 = Token.Type.STR;
        if (input.contains("!=")) {
            input = input.substring(2);
            type2 = Token.Type.NE;
        } else if (input.contains("<=")) {
            input = input.substring(2);
            type2 = Token.Type.LE;
        } else if (input.contains(">=")) {
            input = input.substring(2);
            type2 = Token.Type.GE;
        } else if (input.contains("==")) {
            input = input.substring(2);
            type2 = Token.Type.EQ;
        } else if (input.contains(">")) {
            input = input.substring(1);
            type2 = Token.Type.GT;
        } else if (input.contains("<")) {
            input = input.substring(1);
            type2 = Token.Type.LT;
        } else if (input.contains("=")) {
            input = input.substring(1);
            type2 = Token.Type.EQ;
        }
        return new Token(input, null, type, type2);
    }

    /**
     * parser | and &
     *
     * @return
     */
    private Token parser3(String input) {
        String[] split = input.split("([|&])");
        Token.Type type = Token.Type.STR;
        String end = split[split.length - 1];
        int index = input.length() - end.length() - 1;
        String beginStr = input.substring(0, index);
        char symbol = input.charAt(index);
        if (symbol == '&')
            type = Token.Type.AND;
        else if (symbol == '|')
            type = Token.Type.OR;
        return new Token(end, beginStr, type, null);
    }


    public Token getTokens() {
        return token;
    }
}
