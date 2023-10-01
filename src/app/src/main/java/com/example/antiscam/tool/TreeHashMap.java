package com.example.antiscam.tool;

import java.io.Serializable;

public class TreeHashMap<K extends Comparable<K>, V> implements Serializable {
    private final AVLTree<K, V>[] buckets;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public TreeHashMap() {
        this.buckets = new AVLTree[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            this.buckets[i] = new AVLTree<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % buckets.length; // Ensure non-negative hash value
    }

    public V get(K key) {
        int index = hash(key);
        if (buckets[index] != null) {
            return buckets[index].find(key); // 使用AVLTree的查询方法
        }
        return null;
    }

    public void put(K key, V value) {
        int index = hash(key);
        if (buckets[index] == null) {
            buckets[index] = new AVLTree<>();
        }
        buckets[index].add(new Tree.Node<>(key, value), key, value); // 使用AVLTree的插入方法
    }

    public void remove(K key, V value) {
        int index = hash(key);
        if (buckets[index] != null) {
            buckets[index].remove(new Tree.Node<>(key, value), key); // 使用AVLTree的删除方法
        }
    }

    public boolean contains(K key) {
        int index = hash(key);
        if (buckets[index] != null) {
            return buckets[index].contains(key); // 使用AVLTree的查询方法
        }
        return false;
    }

    public int size() {
        int totalSize = 0;
        for (AVLTree<K, V> bucket : buckets) {
            totalSize += bucket.size;  // 使用AVLTree的size方法
        }
        return totalSize;
    }


}

