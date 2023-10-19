package com.example.antiscam.searchCore;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Token {
    public enum Type {
        USEREMAIL,
        TITLE,
        AMOUNT,
        SCAMTYPE,
        EQ,
        NE,
        LT,
        LE,
        GT,
        GE,
        STR,
        AND,
        OR,
    }

    ;
    private final String value1;
    private String value2;
    private final Type ctype1;  // category
    private final Type ctype2;  // compare

    public Token(String value1, String value2, Type type1, Type type2) {
        this.value1 = value1;
        this.value2 = value2;
        this.ctype1 = type1;
        this.ctype2 = type2;
    }

    public String getValue1() {
        return value1;
    }

    public String getValue2() {
        return value2;
    }

    public Type getCtype1() {
        return ctype1;
    }

    public Type getCtype2() {
        return ctype2;
    }

    public void setValue2(String value) {
        this.value2 = value;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Token)) return false;
        return this.ctype1 == (((Token) obj).getCtype1()) &&
                this.ctype2 == (((Token) obj).getCtype2()) &&
                this.value1.equals(((Token) obj).getValue1()) &&
                this.value2.equals(((Token) obj).getValue2());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value1, value2, ctype1, ctype2);
    }

    @Override
    public String toString() {
        return "Token{" +
                "value1='" + value1 + '\'' +
                ", value2='" + value2 + '\'' +
                ", ctype1=" + ctype1 +
                ", ctype2=" + ctype2 +
                '}';
    }
}
