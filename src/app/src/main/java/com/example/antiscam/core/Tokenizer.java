package com.example.antiscam.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tokenizer {

    private Token currentToken;

    public Tokenizer(String input) {
        currentToken = tokenize(input);
    }

    private Token tokenize(String input) {
        input = input.trim();
        if (input.isEmpty()) {
            currentToken = null;
            return null;
        }
        Token token;
        if (input.startsWith("@")) {
            input = input.substring(1);
            token = new Token(input, Token.Type.USERNAME);
        } else if (input.startsWith("#")) {
            input = input.substring(1);
            token = new Token(input, Token.Type.TITLE);
        } else if (input.startsWith("%")) {
            input = input.substring(1);
            token = new Token(input, Token.Type.AMOUNT);
        } else if (input.contains("!=")) {
            input = input.substring(2);
            token = new Token(input, Token.Type.NE);
        } else if (input.contains("<")) {
            input = input.substring(1);
            token = new Token(input, Token.Type.LT);
        } else if (input.contains("<=")) {
            input = input.substring(2);
            token = new Token(input, Token.Type.LE);
        } else if (input.contains(">")) {
            input = input.substring(1);
            token = new Token(input, Token.Type.GT);
        } else if (input.contains(">=")) {
            input = input.substring(2);
            token = new Token(input, Token.Type.GE);
        } else if (input.contains("=")) {
            input = input.substring(1);
            token = new Token(input, Token.Type.EQ);
        } else if (input.contains("==")) {
            input = input.substring(2);
            token = new Token(input, Token.Type.EQ);
        } else if (input.contains("|")) {
            token = new Token(input, Token.Type.OR);
        } else if (input.contains("&")) {
            token = new Token(input, Token.Type.AND);
        } else if (input.contains("&") && input.contains("|")) {
            token = new Token(input, Token.Type.COMPOUND);
        } else {
            token = new Token(input, Token.Type.STR);
        }
        return token;
    }

    public Token getNextToken() {
        return currentToken;
    }
}
