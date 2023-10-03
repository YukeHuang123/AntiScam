package com.example.antiscam.tool;

public class ListNode<K, V> {
        K key;
        V value;
        ListNode<K, V> prev;
        ListNode<K, V> next;

        public ListNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public ListNode() {
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public ListNode<K, V> getPrev() {
            return prev;
        }

        public void setPrev(ListNode<K, V> prev) {
            this.prev = prev;
        }

        public ListNode<K, V> getNext() {
            return next;
        }

        public void setNext(ListNode<K, V> next) {
            this.next = next;
        }
    }