package com.example.antiscam.tool;

public class AVLTree<K extends Comparable<K>, V> {

    public int size;
    private Node<K, V> root;

    public AVLTree() {
        this.size = 0;
    }

    public V get(K key) {
        Node<K, V> node = getNode(root, key);
        return node == null ? null : node.value;
    }

    private Node<K, V> getNode(Node<K, V> node, K key) {
        if (node == null) return null;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) return getNode(node.left, key);
        if (cmp > 0) return getNode(node.right, key);
        return node; // cmp == 0
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node<K, V> add(Node<K, V> node, K key, V value) {
        size++;
        if (node == null) return new Node<>(key, value);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = add(node.left, key, value);
        } else if (cmp > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value; // 更新已有的键值
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    public void remove(K key) {
        root = remove(root, key);
    }

    private Node<K, V> remove(Node<K, V> node, K key) {
        if (node == null) return null;
        size--;

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<K, V> temp = node;
            node = min(temp.right);
            node.right = deleteMin(temp.right);
            node.left = temp.left;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private Node<K, V> deleteMin(Node<K, V> node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return balance(node);
    }

    private Node<K, V> min(Node<K, V> node) {
        if (node.left == null) return node;
        return min(node.left);
    }

    private int height(Node<K, V> node) {
        return node == null ? 0 : node.height;
    }

    private int balanceFactor(Node<K, V> node) {
        return height(node.left) - height(node.right);
    }

    private Node<K, V> balance(Node<K, V> node) {
        if (balanceFactor(node) > 1) {
            if (balanceFactor(node.left) < 0) node.left = rotateLeft(node.left);
            node = rotateRight(node);
        }
        if (balanceFactor(node) < -1) {
            if (balanceFactor(node.right) > 0) node.right = rotateRight(node.right);
            node = rotateLeft(node);
        }
        return node;
    }

    private Node<K, V> rotateRight(Node<K, V> y) {
        Node<K, V> x = y.left;
        y.left = x.right;
        x.right = y;
        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));
        return x;
    }

    private Node<K, V> rotateLeft(Node<K, V> x) {
        Node<K, V> y = x.right;
        x.right = y.left;
        y.left = x;
        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));
        return y;
    }

    public Node<K, V> find(K key) {
        return find(root, key);
    }

    private Node<K, V> find(Node<K, V> node, K key) {
        if (node == null) {
            return null; // 返回null表示没有找到
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return find(node.left, key);
        } else if (cmp > 0) {
            return find(node.right, key);
        } else {
            return node; // 找到对应的节点并返回
        }
    }

    public boolean contains(K key) {
        return find(key) != null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<K, V> getRoot() {
        return root;
    }

    public void setRoot(Node<K, V> root) {
        this.root = root;
    }
}
