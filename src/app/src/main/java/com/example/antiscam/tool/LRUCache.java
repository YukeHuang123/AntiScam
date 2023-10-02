package com.example.antiscam.tool;

public class LRUCache<K extends Comparable<K>, V> {
    private int capacity;
    private TreeHashMap<K, listNode<K, V>> map;
    private DoublyLinkedList<K, V> list;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new TreeHashMap<>();
        this.list = new DoublyLinkedList<>(capacity);
    }

    public V get(K key) {
        if (!map.contains(key)){
            return null;
        } else {
            listNode<K, V> listNode = map.get(key);
            list.moveToFront(listNode);
            return listNode.value;
        }
    }

    public void put(K key, V value) {
        if (map == null) {
            map = new TreeHashMap<>();
        }
        if (list == null) {
            list = new DoublyLinkedList<>(capacity);
        }
        if (map.contains(key)) {
            listNode<K, V> listNode = map.get(key);
            listNode.value = value;
            list.moveToFront(listNode);
        } else {
            if (map.size() == capacity) {
                listNode<K, V> tail = list.removeTail();
                if (tail != null) {
                    map.remove(tail.key, tail);
                }
            }
            listNode<K, V> newListNode = new listNode<>(key, value);
            map.put(key, newListNode);
            list.addToFront(newListNode);
        }
    }

    private static class DoublyLinkedList<K, V> {
        listNode<K, V> head;
        listNode<K, V> tail;
        int size = 0;  // current size
        int capacity;  // max length or capacity of the list

        public DoublyLinkedList(int capacity) {
            this.capacity = capacity;
        }

        public void moveToFront(listNode<K, V> listNode) {
            if (listNode == null || listNode == head) return;
            remove(listNode);
            addToFront(listNode);
        }

        public void addToFront(listNode<K, V> listNode) {
            if (head == null) {
                head = listNode;
                tail = listNode;
            } else {
                listNode.prev = null;
                listNode.next = head;
                head.prev = listNode;
                head = listNode;
            }
            size++;
            ensureCapacity();
        }

        public listNode<K, V> removeTail() {
            if (tail == null) return null;
            listNode<K, V> res = tail;
            remove(tail);
            return res;
        }

        public void remove(listNode<K, V> listNode) {
            if (listNode.prev != null) {
                listNode.prev.next = listNode.next;
            } else {
                head = listNode.next;
            }

            if (listNode.next != null) {
                listNode.next.prev = listNode.prev;
            } else {
                tail = listNode.prev;
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

        public listNode<K, V> getHead() {
            return head;
        }

        public void setHead(listNode<K, V> head) {
            this.head = head;
        }

        public listNode<K, V> getTail() {
            return tail;
        }

        public void setTail(listNode<K, V> tail) {
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
    }



    private static class listNode<K, V> {
        K key;
        V value;
        listNode<K, V> prev;
        listNode<K, V> next;

        public listNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public listNode() {
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

        public listNode<K, V> getPrev() {
            return prev;
        }

        public void setPrev(listNode<K, V> prev) {
            this.prev = prev;
        }

        public listNode<K, V> getNext() {
            return next;
        }

        public void setNext(listNode<K, V> next) {
            this.next = next;
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

    public TreeHashMap<K, listNode<K, V>> getMap() {
        return map;
    }

    public void setMap(TreeHashMap<K, listNode<K, V>> map) {
        this.map = map;
    }

    public DoublyLinkedList<K, V> getList() {
        return list;
    }

    public void setList(DoublyLinkedList<K, V> list) {
        this.list = list;
    }
}
