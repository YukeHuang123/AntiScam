package com.example.antiscam.tool;

import java.io.Serializable;

public abstract class Tree<K extends Comparable<K>, V> implements Serializable {
    protected Node<K, V> root;

    int size = 0;

    protected abstract Node<K, V> remove(Node<K, V> node, K key);

    protected static class Node<K, V> {
        int height = 0;
        K key;
        V value;
        Node<K, V> left;
        Node<K, V> right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public V find(K key) {
        Node<K, V> node = find(root, key);
        return node == null ? null : node.value;
    }

    private Node<K, V> find(Node<K, V> node, K key) {
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return find(node.left, key);
        } else if (cmp > 0) {
            return find(node.right, key);
        } else {
            return node;
        }
    }

    public Node<K, V> min(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }
        return min(node.left);
    }
}
