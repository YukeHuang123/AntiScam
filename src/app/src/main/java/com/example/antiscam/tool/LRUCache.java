package com.example.antiscam.tool;

import java.util.HashMap;

public class LRUCache<K, V> {
    private final int capacity;
    private final HashMap<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.list = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if (!map.containsKey(key)){
            return null;
        } else {
            Node<K, V> node = map.get(key);
            list.moveToFront(node);
            return node.value;
        }
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            list.moveToFront(node);
        } else {
            if (map.size() == capacity) {
                Node<K, V> tail = list.removeTail();
                map.remove(tail.key);
            }
            Node<K, V> newNode = new Node<>(key, value);
            map.put(key, newNode);
            list.addToFront(newNode);
        }
    }

    private static class DoublyLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public void moveToFront(Node<K, V> node) {
            if (node == head) return;
            remove(node);
            addToFront(node);
        }

        public void addToFront(Node<K, V> node) {
            node.prev = null;
            node.next = head;
            if (head != null) {
                head.prev = node;
            }
            head = node;
            if (tail == null) {
                tail = head;
            }
        }

        public Node<K, V> removeTail() {
            Node<K, V> res = tail;
            remove(tail);
            return res;
        }

        public void remove(Node<K, V> node) {
            if (node.prev != null) {
                node.prev.next = node.next;
            } else {
                head = node.next;
            }

            if (node.next != null) {
                node.next.prev = node.prev;
            } else {
                tail = node.prev;
            }
        }
    }

    private static class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
