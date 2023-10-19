package com.example.antiscam.searchCore;

import android.util.Log;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.Filter;
import com.google.firebase.firestore.Query;

public class Parser {
    private static final String TAG = "TokenHelper";
    private static Parser instance = new Parser();

    private Parser() {
    }

    public static Parser getInstance() {
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
        Log.i(TAG, "genFilter: " + token);
        switch (token.getCtype1()) {
            case USEREMAIL:
                return genFilter("post_user", token.getCtype2(), token.getValue1());
            case TITLE:
                return genFilter("title", token.getCtype2(), token.getValue1());
            case AMOUNT:
                return genFilter("amount", token.getCtype2(), Integer.parseInt(token.getValue1()));
            case SCAMTYPE:
                return genFilter("scam_type", token.getCtype2(), token.getValue1());
            case AND:
                Filter filter = genAndFilter(token.getValue1(), token.getValue2());
                if (filter == null) return Filter.and();
                else return filter;
            case OR:
                Filter orFilter = genOrFilter(token.getValue1(), token.getValue2());
                if (orFilter == null) return Filter.or();
                else return orFilter;
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
        if (tokenizer1.getTokens().getCtype2() == Token.Type.STR || tokenizer2.getTokens().getCtype2() == Token.Type.STR)
            return null;
        return Filter.and(instance.genFilter(tokenizer1), instance.genFilter(tokenizer2));
    }

    private static Filter genOrFilter(String exp1, String exp2) {
        Tokenizer tokenizer1 = new Tokenizer(exp1);
        Tokenizer tokenizer2 = new Tokenizer(exp2);
        if (tokenizer1.getTokens().getCtype2() == Token.Type.STR || tokenizer2.getTokens().getCtype2() == Token.Type.STR)
            return null;
        return Filter.or(instance.genFilter(tokenizer1), instance.genFilter(tokenizer2));
    }

}
