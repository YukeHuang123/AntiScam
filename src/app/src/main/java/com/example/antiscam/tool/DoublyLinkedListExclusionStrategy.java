package com.example.antiscam.tool;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class DoublyLinkedListExclusionStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        // 如果字段是 ListNode 类型的 prev 属性，则排除它
        return (f.getDeclaringClass() == ListNode.class && f.getName().equals("prev"));
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
