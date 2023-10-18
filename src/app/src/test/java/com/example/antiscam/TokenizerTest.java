package com.example.antiscam;

import com.example.antiscam.core.Token;
import com.example.antiscam.core.Tokenizer;

import org.junit.Assert;
import org.junit.Test;

public class TokenizerTest {

    @Test
    public void categoryTest() {
        String amount = "%=2000";
        String username = "@=test@gmail.com";
        String title = "#=Post Tile";

        // expected get token @#%
        assertCategory(Token.Type.AMOUNT, amount);
        assertCategory(Token.Type.USERNAME, username);
        assertCategory(Token.Type.TITLE, title);
        // expected get value
        assertValue("2000", amount);
        assertValue("test@gmail.com", username);
        assertValue("Post Tile", title);

        String eq = "%=2000";       // = or ==
        String le = "%<=2000";      // <=
        String lt = "%<2000";       // <
        String ge = "%>=2000";      // >=
        String gt = "%>2000";       // >
        String ne = "%!=2000";      // !=

        // expected get token !=<>
        assertExp(Token.Type.EQ, eq);
        assertExp(Token.Type.LE, le);
        assertExp(Token.Type.LT, lt);
        assertExp(Token.Type.GE, ge);
        assertExp(Token.Type.GT, gt);
        assertExp(Token.Type.NE, ne);

        // expected get value
        assertValue("2000", eq);
        assertValue("2000", le);
        assertValue("2000", lt);
        assertValue("2000", ge);
        assertValue("2000", gt);
        assertValue("2000", ne);


        String compoundAnd = "%=2000&@=test@gmail.com";
        String compoundOr = "%=2000||@=test@gmail.com";

        // expected get token [And,Or]
        assertCategory(Token.Type.AND, compoundAnd);
        assertCategory(Token.Type.OR, compoundOr);

        eachCompund(compoundAnd);
    }

    private void assertCategory(Object expected, String input) {
        Tokenizer tokenizer = new Tokenizer(input);
        Token token = tokenizer.getTokens();
        Assert.assertEquals(expected, token.getCtype1());
    }

    private void assertExp(Object expected, String input) {
        Tokenizer tokenizer = new Tokenizer(input);
        Token token = tokenizer.getTokens();
        Assert.assertEquals(expected, token.getCtype2());
    }

    private void assertValue(Object expected, String input) {
        Tokenizer tokenizer = new Tokenizer(input);
        Token token = tokenizer.getTokens();
        Assert.assertEquals(expected, token.getValue1());
    }

    private void eachCompund(String input) {
        Tokenizer tokenizer = new Tokenizer(input);
        Token token = tokenizer.getTokens();
        // expected get token @#%
        assertCategory(Token.Type.AMOUNT,token.getValue1());
        assertCategory(Token.Type.USERNAME,token.getValue2());
        // expected get value
        assertValue("2000",token.getValue1());
        assertValue("test@gmail.com",token.getValue2());

    }
}
