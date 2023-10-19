package com.example.antiscam.tool;


/**
 * @author Zhaoyun Xu, u7558707
 * @param <K>
 */
public class ListNode<K> {
        K key;
        ListNode<K> prev;
        ListNode<K> next;

        public ListNode(K key) {
            this.key = key;
        }

        public ListNode() {
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public ListNode<K> getPrev() {
            return prev;
        }

        public void setPrev(ListNode<K> prev) {
            this.prev = prev;
        }

        public ListNode<K> getNext() {
            return next;
        }

        public void setNext(ListNode<K> next) {
            this.next = next;
        }
    }