package algorithmlearning002.class06;

import java.util.ArrayList;

public class Code03_SkipListMap {

    //跳表的节点定义
    public static class SkipListNode<K extends Comparable<K>, V> {

        public K key;
        public V val;
        public ArrayList<SkipListNode<K,V>> nextNodes;

        public SkipListNode(K k, V v) {
            key = k;
            val = v;
            nextNodes = new ArrayList<>();
        }


        // 遍历的时候，如果是往右遍历到的null(next == null), 遍历结束
        // 头(null), 头节点的null，认为最小
        // node  -> 头，node(null, "")  node.isKeyLess(!null)  true
        // node里面的key是否比otherKey小，true，不是false
        public boolean isKeyLess(K otherKey) {
            return otherKey != null && (key == null || key.compareTo(otherKey) < 0);
        }

        public boolean isKeyEqual(K otherKey) {
            return (key == null && otherKey == null) ||
                    (key != null && otherKey != null && key.compareTo(otherKey) == 0);
        }
    }

    public static class SkipListMap<K extends Comparable<K> ,V>{
        public static final double PROBABILITY = 0.5;
        private SkipListNode<K,V> head;
        private int size;
        private int maxLevel;
        public SkipListMap(){
            head = new SkipListNode<>(null, null);
            head.nextNodes.add(null);
            size = 0;
            maxLevel = 0;
        }
    }





}
