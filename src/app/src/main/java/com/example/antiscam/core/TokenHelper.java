package com.example.antiscam.core;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;

public class TokenHelper {
    private static TokenHelper instance = new TokenHelper();

    private TokenHelper() {
    }

    public static TokenHelper getInstance() {
        return instance;
    }

    public Query genQuery(CollectionReference db, Tokenizer tokenizer) {
        return db.where(genFilter(tokenizer));
    }

    private Filter genFilter(Tokenizer tokenizer) {
        Filter filter = null;
        Token token = tokenizer.getNextToken();
        if (token != null) {
            String value = token.getValue();
            switch (token.getType()) {
                case USERNAME:
                    filter = Filter.or(
                            Filter.equalTo("post_user", value),
                            Filter.equalTo("username", value)
                    );
                    break;
                case TITLE:
                    filter = Filter.equalTo("title", value);
                    break;
                case AMOUNT:
                case EQ:
                    filter = getCommonAnd(token);
                    break;
                case NE:
                    filter = Filter.notEqualTo("amount", Double.parseDouble(value));
                    break;
                case LT:
                    filter = Filter.lessThan("amount", Double.parseDouble(value));
                    break;
                case LE:
                    filter = Filter.lessThanOrEqualTo("amount", Double.parseDouble(value));
                    break;
                case GT:
                    filter = Filter.greaterThan("amount", Double.parseDouble(value));
                    break;
                case GE:
                    filter = Filter.greaterThanOrEqualTo("amount", Double.parseDouble(value));
                    break;
                case AND:
                    String[] strs1 = value.split("&");
                    ArrayList<Filter> filters = new ArrayList<>();
                    for (String str : strs1) {
                        Tokenizer tokenizer1 = new Tokenizer(str);
                        filters.add(genFilter(tokenizer1));
                    }
                    Filter[] f = new Filter[filters.size()];
                    filters.toArray(f);
                    filter = Filter.and(f);
                    break;
                case OR:
                    String[] strs2 = value.split("|");
                    ArrayList<Filter> filters2 = new ArrayList<>();
                    for (String s2 : strs2) {
                        Tokenizer tokenizer2 = new Tokenizer(s2);
                        filters2.add(genFilter(tokenizer2));
                    }
                    Filter[] f2 = new Filter[filters2.size()];
                    filters2.toArray(f2);
                    filter = Filter.or(f2);
                    break;
                case COMPOUND:
                    filter = Filter.and(Filter.or());
                    break;
                default:
                    break;
            }
        }
        return filter;
    }

    @NonNull
    private static Filter getCommonOr(Token token1) {
        return Filter.or(
                Filter.equalTo("amount", token1.getValue()),
                Filter.equalTo("title", token1.getValue()),
                Filter.equalTo("post_user", token1.getValue())
        );
    }

    @NonNull
    private static Filter getCommonAnd(Token token1) {
        return Filter.or(
                Filter.equalTo("amount", token1.getValue()),
                Filter.equalTo("title", token1.getValue()),
                Filter.equalTo("post_user", token1.getValue())
        );
    }

    @NonNull
    private static void expAnd(String input) {
        String[] strs1 = input.split("&");
        for (String str : strs1) {
            Tokenizer tokenizer1 = new Tokenizer(str);
            Token token1 = tokenizer1.getNextToken();

        }
    }

}
