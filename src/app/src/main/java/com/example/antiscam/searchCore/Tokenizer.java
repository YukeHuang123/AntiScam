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
        } else if (input.startsWith("@") || input.startsWith("#") || input.startsWith("%")|| input.startsWith("$")) {
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
        if (input.contains("&")) {
            type = Token.Type.AND;
        } else if (input.contains("|")) {
            type = Token.Type.OR;
        }
        return new Token(split[0], split[1], type, null);
    }


    public Token getTokens() {
        return token;
    }
}
