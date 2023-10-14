package com.example.antiscam.core;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.List;

public class TokenHelper {
    private static TokenHelper instance = new TokenHelper();

    private TokenHelper() {
    }

    public static TokenHelper getInstance() {
        return instance;
    }

    public Query genQuery(CollectionReference db, Tokenizer tokenizer) {
        try {
            Filter filter = genFilter(tokenizer);
            if (filter != null) {
                return db.where(filter);
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Firestore Query Error", "Error generating query: " + e.getMessage());
            return db.whereEqualTo("nonExistentField", "nonExistentValue");
        }
    }

    private Filter genFilter(Tokenizer tokenizer) {
        Token token = tokenizer.getTokens();
        switch (token.getCtype1()) {
            case USERNAME:
                return genFilter("post_user", token.getCtype2(), token.getValue1());
            case TITLE:
                return genFilter("title", token.getCtype2(), token.getValue1());
            case AMOUNT:
                return genFilter("amount", token.getCtype2(), Integer.parseInt(token.getValue1()));
            case SCAMTYPE:
                return genFilter("scam_type", token.getCtype2(), token.getValue1());
            case AND:
                Filter filter = genAndFilter(token.getValue1(), token.getValue2());
                return filter;
            case OR:
                return genOrFilter(token.getValue1(), token.getValue2());
            case STR:
                break;
        }
        return null;
    }


    private static Filter genFilter(String field, Token.Type type2, Object value) {
        switch (type2) {
            case EQ: // == or =
                return Filter.equalTo(field, value);
            case NE:  // !=
                return Filter.notEqualTo(field, value);
            case LT: // <
                return Filter.lessThan(field, value);
            case LE: // <=
                return Filter.lessThanOrEqualTo(field, value);
            case GT: // >
                return Filter.greaterThan(field, value);
            case GE: // >=
                return Filter.greaterThanOrEqualTo(field, value);
        }
        return null;
    }

    private static Filter genAndFilter(String exp1, String exp2) {
        Tokenizer tokenizer1 = new Tokenizer(exp1);
        Tokenizer tokenizer2 = new Tokenizer(exp2);

        return Filter.and(instance.genFilter(tokenizer1), instance.genFilter(tokenizer2));
    }

    private static Filter genOrFilter(String exp1, String exp2) {
        Tokenizer tokenizer1 = new Tokenizer(exp1);
        Tokenizer tokenizer2 = new Tokenizer(exp2);
        return Filter.or(instance.genFilter(tokenizer1), instance.genFilter(tokenizer2));
    }

}
