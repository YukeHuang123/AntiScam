package com.example.antiscam.tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class LRUCache<K extends Comparable<K>, V> implements Serializable {
    private final int capacity;
    private final TreeHashMap<K, Node<K, V>> map;
    private final DoublyLinkedList<K, V> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new TreeHashMap<>();
        this.list = new DoublyLinkedList<>();
    }

    public V get(K key) {
        if (!map.contains(key)){
            return null;
        } else {
            Node<K, V> node = map.get(key);
            list.moveToFront(node);
            return node.value;
        }
    }

    public void put(K key, V value) {
        if (map.contains(key)) {
            Node<K, V> node = map.get(key);
            node.value = value;
            list.moveToFront(node);
        } else {
            if (map.size() == capacity) {
                Node<K, V> tail = list.removeTail();
                map.remove(tail.key, tail);
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
