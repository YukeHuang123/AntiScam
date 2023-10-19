package com.example.antiscam.tool;

import org.checkerframework.checker.units.qual.K;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * @author Zhaoyun Xu, u7558707
 * @param <K>
 * @param <V>
 */
public class LRUCache<K extends Comparable<K>, V> {
    private int capacity;
    private TreeHashMap<K, V> map;
    private DoublyLinkedList<K> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new TreeHashMap<>();
        this.list = new DoublyLinkedList<>(capacity);
    }

    public V get(K key) {
        if (!map.contains(key)){
            return null;
        } else {
            Node<K, V> node = map.get(key);
            ListNode<K> listNode = list.find(key);
//            if (listNode != null) {
//                listNode.value = node.getValue();
//            }
            list.moveToFront(listNode);
            return node.getValue();
        }
    }

    public List<V> getAll() {
        List<V> values = new ArrayList<>();
        ListNode<K> current = list.head;

        while (current != null) {
            K key = current.key;
            V value = map.get(key).getValue();
            if (value != null) {
                values.add(value);
            }
            current = current.next;
        }

        return values;
    }

    public int getSize() {
        return list.getSize();
    }

    public void put(K key, V value) {
        if (map == null) {
            map = new TreeHashMap<>();
        }
        if (list == null) {
            list = new DoublyLinkedList<>(capacity);
        }
        if (map.contains(key)) {
            Node<K, V> node = map.get(key);
            node.setValue(value);

            ListNode<K> listNode = list.find(key);
//            if (listNode != null) {
//                listNode.value = value;
//            }
            list.moveToFront(listNode);
        } else {
            if (map.size() == capacity) {
                ListNode<K> tail = list.removeTail();
                if (tail != null) {
                    map.remove(tail.key);
                }
            }
            ListNode<K> newNode = new ListNode<>(key);
            map.put(key, value);
            list.addToFront(newNode);
        }
    }

    public void remove(K key, V value) {
        ListNode<K> listNode= list.find(key);
        if  (listNode != null){
            list.remove(listNode);
            map.remove(key);
        }

    }

    private static class DoublyLinkedList<K> {
        ListNode<K> head;
        ListNode<K> tail;
        int size = 0;  // current size
        int capacity;  // max length of the list

        public DoublyLinkedList(int capacity) {
            this.capacity = capacity;
        }

        public void moveToFront(ListNode<K> listNode) {
            if (listNode == null || listNode == head) return;
            remove(listNode);
            addToFront(listNode);
        }

        public void addToFront(ListNode<K> listNode) {
            ListNode<K> prevNode = findPreviousNode(listNode.key);
            // If listNode was part of list
            if (prevNode != null) {
                prevNode.next = listNode.next;
            }
            if (listNode.next != null) {
                listNode.next.prev = prevNode;
            }

            if (head == null) {
                head = listNode;
                tail = listNode;
                listNode.prev = null;
                listNode.next = null;
            } else {
                listNode.prev = null;
                listNode.next = head;
                head.prev = listNode;
                head = listNode;
            }
            size++;
            ensureCapacity();
        }


        public ListNode<K> removeTail() {
            if (tail == null) return null;
            ListNode<K> res = tail;
            remove(tail);
            return res;
        }

        public void remove(ListNode<K> listNode) {
            ListNode<K> prevNode = findPreviousNode(listNode.key);

            if (prevNode != null) {
                prevNode.next = listNode.next;
            } else {
                head = listNode.next;
            }

            if (listNode.next != null) {
                listNode.next.prev = prevNode;
            } else {
                tail = prevNode;
            }

            size--;

            if (head == null) {
                tail = null;
            }
        }

        private void ensureCapacity() {
            while (size > capacity) {
                removeTail();
            }
        }

        public ListNode<K> find(K key) {
            DLLIterator iterator = new DLLIterator(head);
            while (iterator.hasNext()) {
                ListNode<K> currentNode = iterator.getCurrentNode();
                if (currentNode.key.equals(key)) {
                    return currentNode;
                }
                iterator.next();
            }
            return null;
        }

        public ListNode<K> findPreviousNode(K key) {
            DLLIterator iterator = new DLLIterator(head);
            ListNode<K> previous = null;
            while (iterator.hasNext()) {
                ListNode<K> current = iterator.next();
                if (current.key.equals(key)) {
                    return previous;
                }
                previous = current;
            }
            return null;
        }



        public ListNode<K> getHead() {
            return head;
        }

        public void setHead(ListNode<K> head) {
            this.head = head;
        }

        public ListNode<K> getTail() {
            return tail;
        }

        public void setTail(ListNode<K> tail) {
            this.tail = tail;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        private class DLLIterator implements Iterator<ListNode<K>> {
            private ListNode<K> previousNode = null;
            private ListNode<K> currentNode;

            public DLLIterator(ListNode<K> currentNode) {
                this.currentNode = currentNode;
            }

            @Override
            public boolean hasNext() {
                return currentNode != null;
            }

            @Override
            public ListNode<K> next() {
                if (currentNode == null) {
                    throw new NoSuchElementException();
                }
                previousNode = currentNode;
                currentNode = currentNode.next;
                return previousNode;
            }


            public ListNode<K> getCurrentNode() {
                return currentNode;
            }

            public ListNode<K> getPreviousNode() {
                return previousNode;
            }

        }

    }





    public LRUCache() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public TreeHashMap<K, V> getMap() {
        return map;
    }

    public void setMap(TreeHashMap<K, V> map) {
        this.map = map;
    }

    public DoublyLinkedList<K> getList() {
        return list;
    }

    public void setList(DoublyLinkedList<K> list) {
        this.list = list;
    }
}
