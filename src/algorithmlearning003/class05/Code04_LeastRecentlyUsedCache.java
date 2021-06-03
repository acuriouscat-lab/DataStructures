package algorithmlearning003.class05;

import java.util.HashMap;
import java.util.Map;

public class Code04_LeastRecentlyUsedCache {

    // 支持双向的节点
    public static class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> last;
        public Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // 双向链表
    // 从head到tail所有节点都是串好的
    public static class NodeDoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        public NodeDoubleLinkedList() {
            head = null;
            tail = null;
        }

        // 增加节点到双向链表中
        // 新增节点默认为最新 -> 加入到末尾处
        public void addNode(Node<K, V> node) {
            if (node == null) {
                return;
            }
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
                node.last = tail;
            }
            tail = node;
        }

        // 潜台词 ： 双向链表上一定有这个node
        // node分离出，但是node前后环境重新连接
        // node放到尾巴上去
        public void moveNodeToTail(Node<K, V> node) {
            if (tail == node) {
                return;
            }

            if (head == node) {//头部
                head = node.next;
                head.last = null;
            } else {//中间的节点
                node.next.last = node.last;
                node.last.next = node.next;
            }

            node.last = tail;
            node.next = null;
            tail.next = node;
            tail = node;
        }

        // 删除最久没有使用过的节点 -> 删除当前的头部节点
        public Node<K, V> removeHead() {
            if (head == null) {
                return null;
            }
            Node<K, V> res = this.head;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                head = res.next;
                head.last = null;
                res.next = null;
            }
            return res;
        }
    }

    // 支持 LRU 缓存策略的 结构
    public static class MyCache<K, V> {
        public NodeDoubleLinkedList<K, V> nodelist;
        public Map<K, Node<K, V>> nodeMap;
        private final int capacity;

        public MyCache(int capacity) {
            this.capacity = capacity;
            nodelist = new NodeDoubleLinkedList<>();
            nodeMap = new HashMap<>();
        }

        public void set(K key, V val) {
            if (nodeMap.containsKey(key)) {
                Node<K, V> node = nodeMap.get(key);
                node.value = val;
                nodelist.moveNodeToTail(node);
            } else {
                Node<K, V> node = new Node<>(key, val);
                nodeMap.put(key, node);
                nodelist.addNode(node);
                if (nodeMap.size() == capacity + 1) {
                    removeMostUnusedCache();
                }
            }
        }

        public V get(K key) {
            if (nodeMap.containsKey(key)) {
                Node<K, V> node = nodeMap.get(key);
                nodelist.moveNodeToTail(node);
                return node.value;
            }
            return null;
        }

        private void removeMostUnusedCache() {
            Node<K, V> removeNode = nodelist.removeHead();
            nodeMap.remove(removeNode.key);
        }
    }

    public static void main(String[] args) {
        MyCache<String, Integer> testCache = new MyCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));

    }


}
