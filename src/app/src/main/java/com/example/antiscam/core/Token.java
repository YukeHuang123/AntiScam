package com.example.antiscam.core;

import androidx.annotation.Nullable;

import java.util.Objects;

public class Token {
    public enum Type {
        USERNAME,
        TITLE,
        AMOUNT,
        EQ,
        NE,
        LT,
        LE,
        GT,
        GE,
        STR,
        AND,
        OR,
        COMPOUND,
    }

    ;
    private final String value;
    private final Type type;

    public Token(String value, Type type) {
        this.value = value;
        this.type = type;
    }

    public String getValue() {
        return value;
    }


    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Token)) return false;
        return this.type == (((Token) obj).getType()) && this.value.equals(((Token) obj).getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, type);
    }
}
