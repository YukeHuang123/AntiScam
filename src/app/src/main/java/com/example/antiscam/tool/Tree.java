//package com.example.antiscam.tool;
//
//import java.io.Serializable;
//
//public abstract class Tree<K extends Comparable<K>, V>  {
//    private Node<K, V> root;
//
//    int size = 0;
//
//    protected abstract Node<K, V> remove(Node<K, V> node, K key);
//
//
//
//    public V find(K key) {
//        Node<K, V> node = find(root, key);
//        return node == null ? null : node.value;
//    }
//
//    private Node<K, V> find(Node<K, V> node, K key) {
//        if (node == null) {
//            return null;
//        }
//        int cmp = key.compareTo(node.key);
//        if (cmp < 0) {
//            return find(node.left, key);
//        } else if (cmp > 0) {
//            return find(node.right, key);
//        } else {
//            return node;
//        }
//    }
//
//    public Node<K, V> min(Node<K, V> node) {
//        if (node.left == null) {
//            return node;
//        }
//        return min(node.left);
//    }
//
//    public Tree() {
//    }
//
//    public Node<K, V> getRoot() {
//        return root;
//    }
//
//    public void setRoot(Node<K, V> root) {
//        this.root = root;
//    }
//
//    public int getSize() {
//        return size;
//    }
//
//    public void setSize(int size) {
//        this.size = size;
//    }
//}
