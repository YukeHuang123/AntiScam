package com.example.antiscam.tool;

import java.io.Serializable;

public class AVLTree<K extends Comparable<K>, V> extends Tree<K, V>{

    private int height(Node<K, V> node) {
        if (node == null) {
            return -1;
        } else {
            return node.height;
        }
    }

    public boolean contains(K key) {
        return find(key) != null;
    }


    private int getBalance(Node<K, V> node) {
        if (node == null) {
            return 0;
        } else {
            int leftHeight = height(node.left);
            int rightHeight = height(node.right);
            return leftHeight - rightHeight;
        }
    }


    protected Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) return new Node<>(key, value);

        size++;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = add(node.left, key, value);
        } else if (cmp > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value; // overwrite existing value
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left heavy
        if (balance > 1) {
            // Check for "Left-Right" case
            if (key.compareTo(node.left.key) > 0) {
                node.left = singleRotateWithLeft(node.left);
                return singleRotateWithRight(node);
            }
            // "Left-Left" case
            else {
                return singleRotateWithRight(node);
            }
        }

        // Right heavy
        if (balance < -1) {
            // Check for "Right-Left" case
            if (key.compareTo(node.right.key) < 0) {
                node.right = singleRotateWithRight(node.right);
                return singleRotateWithLeft(node);
            }
            // "Right-Right" case
            else {
                return singleRotateWithLeft(node);
            }
        }

        return node;
    }


    @Override
    protected Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) return null;

        size--;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null || node.right == null) {
                Node<K, V> temp = (node.left != null) ? node.left : node.right;
                node = temp;
            } else {
                Node<K, V> temp = min(node.right);
                node.key = temp.key;
                node.value = temp.value;
                node.right = remove(node.right, temp.key);
            }
        }

        if (node == null) return null;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left heavy
        if (balance > 1) {
            // Check for "Left-Right" case
            if (getBalance(node.left) < 0) {
                node.left = singleRotateWithLeft(node.left);
                return singleRotateWithRight(node);
            }
            // "Left-Left" case
            else {
                return singleRotateWithRight(node);
            }
        }

        // Right heavy
        if (balance < -1) {
            // Check for "Right-Left" case
            if (getBalance(node.right) > 0) {
                node.right = singleRotateWithRight(node.right);
                return singleRotateWithLeft(node);
            }
            // "Right-Right" case
            else {
                return singleRotateWithLeft(node);
            }
        }

        return node;
    }


    private Node<K, V> singleRotateWithLeft(Node<K, V> k2) {
        Node<K, V> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = 1 + Math.max(height(k2.left), height(k2.right));
        k1.height = 1 + Math.max(height(k1.left), k2.height);
        return k1;
    }

    private Node<K, V> singleRotateWithRight(Node<K, V> k1) {
        Node<K, V> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = 1 + Math.max(height(k1.left), height(k1.right));
        k2.height = 1 + Math.max(height(k2.right), k1.height);
        return k2;
    }

    public AVLTree() {
    }




}
