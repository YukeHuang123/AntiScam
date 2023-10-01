package com.example.antiscam;

import com.example.antiscam.bean.ScamCaseWithUser;
import com.example.antiscam.dataclass.scamCaseList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SearchTool {
    private static Pattern pattern = Pattern.compile("^[@#%&/!=<>](.+)");

    public enum Token {
        AT("@"),
        TITLE("#"),
        AMOUNT("%"),
        SLASH("/"),
        AMPERSAND("&"),
        EQ("=="),
        NE("!="),
        LT("<"),
        LE("<="),
        GT(">"),
        GE(">=");

        private final String symbol;

        Token(String symbol) {
            this.symbol = symbol;
        }

        public String getSymbol() {
            return symbol;
        }
    }

    public static List<ScamCaseWithUser> search(String input, List<ScamCaseWithUser> scamCaseLists) {

        Map<String, Token> tokenMap = getTokenMap();
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            String value = matcher.group(1);
            Token token = getTokenReplacement(input.charAt(0) + "", tokenMap);
            switch (token) {
                case AT: // @
                    return scamCaseLists.stream().filter(scamCaseWithUser -> Objects.equals(scamCaseWithUser.getUser().getUsername(), value)).collect(Collectors.toList());
                case TITLE: // #
                    return scamCaseLists.stream().filter(scamCaseWithUser -> Objects.equals(scamCaseWithUser.getScamCase().getTitle(), value)).collect(Collectors.toList());
                case AMOUNT: // %
                case EQ: // ==
                    return scamCaseLists.stream().filter(scamCaseWithUser -> scamCaseWithUser.getScamCase().getAmount() == Double.parseDouble(value)).collect(Collectors.toList());
                case NE: // !=
                    return scamCaseLists.stream().filter(scamCaseWithUser -> scamCaseWithUser.getScamCase().getAmount() != Double.parseDouble(value)).collect(Collectors.toList());
                case LT: // <
                    return scamCaseLists.stream().filter(scamCaseWithUser -> scamCaseWithUser.getScamCase().getAmount() < Double.parseDouble(value)).collect(Collectors.toList());
                case LE: // <=
                    return scamCaseLists.stream().filter(scamCaseWithUser -> scamCaseWithUser.getScamCase().getAmount() <= Double.parseDouble(value)).collect(Collectors.toList());
                case GT: // >
                    return scamCaseLists.stream().filter(scamCaseWithUser -> scamCaseWithUser.getScamCase().getAmount() > Double.parseDouble(value)).collect(Collectors.toList());
                case GE: // >=
                    return scamCaseLists.stream().filter(scamCaseWithUser -> scamCaseWithUser.getScamCase().getAmount() >= Double.parseDouble(value)).collect(Collectors.toList());
                case SLASH:
                case AMPERSAND:
                default:
                    return null;
            }
        } else {
            Pattern pattern = Pattern.compile(genPattern(input));
            return scamCaseLists.stream().filter(scamCaseList -> {
                Matcher m = pattern.matcher(scamCaseList.getScamCase().getDescription());
                return m.find();
            }).collect(Collectors.toList());
        }
    }

    private static Map<String, Token> getTokenMap() {
        Map<String, Token> tokenMap = new HashMap<>();
        Token[] tokens = Token.values();
        for (Token token : tokens) {
            tokenMap.put(token.getSymbol(), token);
        }
        return tokenMap;
    }

    private static Token getTokenReplacement(String matchedToken, Map<String, Token> tokenMap) {
        Token token = tokenMap.get(matchedToken);
        return token;
    }

    private static String genPattern(String text) {
        return "(" + text + ")+";
    }
}