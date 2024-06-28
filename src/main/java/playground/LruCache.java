package playground;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LruCache implements Cache<String, String> {
    private final int capacity;
    private final Map<String, LinkedListNode> map;
    private final LinkedList list = new LinkedList();

    class LinkedList {
        private LinkedListNode head;
        private LinkedListNode tail;

        public void addToFront(LinkedListNode node) {
            node.previous = null;
            node.next = this.head;
            if (this.head == null) {
                this.tail = node;
            } else {
                this.head.previous = node;
            }
            this.head = node;
        }

        public void moveToFront(LinkedListNode node) {
            remove(node);
            addToFront(node);
        }

        private LinkedListNode removeTail() {
            System.out.println("removeTail. tail=" + this.tail.key);
            LinkedListNode node = this.tail;
            remove(this.tail);
            this.tail = node.previous;
            return node;
        }

        private LinkedListNode removeHead() {
            System.out.println("removeHead. head=" + this.head.key);
            LinkedListNode node = this.head;
            remove(this.head);
            return node;
        }

        public void remove(final LinkedListNode node) {
            System.out.println("removing node: " + node.key);
            System.out.println(node.key + " isTail: " + (this.tail == node));

            LinkedListNode nextNode = node.next;
            LinkedListNode previousNode = node.previous;

            if (node == this.tail) {
                this.tail = node.previous;
            }

            if (previousNode != null) {
                previousNode.next = nextNode;
            }
            if (nextNode != null) {
                nextNode.previous = previousNode;
            }
        }

        public boolean isEmpty() {
            return this.head == null;
        }
    }

    class LinkedListNode {
        String key;
        String value;
        LinkedListNode next;
        LinkedListNode previous;
    }

    public LruCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<String, LinkedListNode>();
    }

    @Override
    public boolean put(String key, String value) {
        System.out.println(("put: " + key));
        if (isFull()) {
            System.out.println("cache is full. Calling removeTail");
            LinkedListNode removedNode = this.list.removeTail();
            this.map.remove(removedNode.key);
        }

        LinkedListNode newNode = new LinkedListNode();
        newNode.key = key;
        newNode.value = value;

        map.put(key, newNode);
        this.list.addToFront(newNode);

        return true;
    }

    @Override
    public Optional<String> get(String key) {
        LinkedListNode node = map.get(key);
        if (node == null) {
            return Optional.empty();
        } else {
            this.list.moveToFront(node);
            return Optional.ofNullable(node.value);
        }
    }

    @Override
    public int size() {
        return this.map.size();
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    private boolean isFull() {
        return (this.map.size() == this.capacity);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LruCache: ");
        for (Map.Entry<String, LinkedListNode> entry : this.map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue().value);
            sb.append("|");
        }
        sb.append("TAIL=" + this.list.tail.key);
        return sb.toString();
    }
}

class LruCacheDemo {
    public static void main(String[] args) {
        LruCache cache = new LruCache(3);
        cache.put("a", "a-value");
        cache.put("b", "b-value");
        cache.put("c", "c-value");
        System.out.println(cache);

        cache.put("d", "d-value");
        System.out.println(cache);

        System.out.println("dValue=" + cache.get("d"));

        cache.put("e", "e-value");
        System.out.println(cache);
    }
}