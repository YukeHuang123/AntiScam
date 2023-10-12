package com.example.antiscam.tool;

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
            ListNode<K, V> listNode = list.find(key);
            if (listNode != null) {
                listNode.value = node.getValue();
            }
            list.moveToFront(listNode);
            return node.getValue();
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
                    map.remove(key, value);
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
            map.remove(key, value);
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
            // If listNode was part of list
            if (listNode.prev != null) {
                listNode.prev.next = listNode.next;
            }
            if (listNode.next != null) {
                listNode.next.prev = listNode.prev;
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

        public ListNode<K> find(K key) {
            ListNode<K> currentNode = head;
            while (currentNode != null) {
                if (currentNode.key.equals(key)) {
                    return currentNode;
                }
                currentNode = currentNode.next;
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
