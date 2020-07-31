package Week08;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LC146_lru_cache {
    class LRUCache_LHM extends LinkedHashMap<Integer,Integer> {
        private int capacity;

        public LRUCache_LHM(int capacity) {
            super(capacity, 0.75F, true);
            this.capacity = capacity;
        }

        public int get(int key) {
            return super.getOrDefault(key, -1);
        }

        public void put(int key, int value) {
            super.put(key,value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
            return super.size() > capacity;
        }
    }

    class LRUCache {
        //注意map里面的值也要remove
        class LinkedNode {
            int key;
            int value;
            LinkedNode pre;
            LinkedNode next;

            public LinkedNode() {
            }

            public LinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private Map<Integer, LinkedNode> cache = new HashMap<>();
        private int size;
        private int capacity;
        private LinkedNode head, tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            head = new LinkedNode();
            tail = new LinkedNode();
            head.next = tail;
            tail.pre = head;
        }
        public int get(int key) {
            LinkedNode node = cache.get(key);
            if (node != null){
                moveToHead(node);
                return node.value;
            }
            else {
                return -1;
            }
        }
        public void put(int key, int value) {
            LinkedNode node = cache.get(key);
            if (node == null){
                addNewNode(key, value);
                if (size > capacity){
                    removeLast();
                }
            }
            else{
                node.value = value;
                moveToHead(node);
            }
        }

        private void moveToHead(LinkedNode node) {
            removeNode(node);
            addToHead(node);
        }

        private void removeNode(LinkedNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        private void addToHead(LinkedNode newNode) {
            newNode.pre = head;
            newNode.next = head.next;
            head.next.pre = newNode;
            head.next = newNode;
        }
        private void removeLast() {
            LinkedNode last = tail.pre;
            removeNode(last);
            cache.remove(last.key);
            size--;
        }
        private void addNewNode(int key, int value){
            LinkedNode newNode = new LinkedNode(key,value);
            cache.put(key,newNode);
            addToHead(newNode);
            size++;
        }
    }
}
