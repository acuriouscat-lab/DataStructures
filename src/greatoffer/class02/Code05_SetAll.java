package greatoffer.class02;

import java.util.HashMap;

public class Code05_SetAll {

    // get set setAll 都要 O（1）

    public static class MyValue<V> {
        public V val;
        public long time;

        public MyValue(V v, long t) {
            val = v;
            time = t;
        }
    }

    public static class MyHashMap<K, V> {

        private HashMap<K, MyValue<V>> map;
        // 用 time 来记录 setAll 发生的时机
        private long time;
        private MyValue<V> setAll;

        public MyHashMap() {
            setAll = new MyValue<>(null, -1);
            time = 0;
            map = new HashMap<>();
        }

        public void put(K key, V value) {
            map.put(key, new MyValue<>(value, time++));
        }

        public void setAll(V val) {
            setAll = new MyValue<>(val, time++);
        }

        public V get(K key) {
            if (!map.containsKey(key)) {
                return null;
            }
            if (map.get(key).time > setAll.time) {
                return map.get(key).val;
            }else{
                return setAll.val;
            }
        }


    }


}
