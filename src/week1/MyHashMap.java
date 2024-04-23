package week1;

/**
 * @Author Baiyu
 * @Time 2024/1/21 8:55
 * @StudentNumber 2018217662
 * @Description
 */
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;
    
    private List<Entry<K, V>>[] buckets;
    private int size;
    
    public MyHashMap() {
        this(DEFAULT_CAPACITY);
    }
    
    public MyHashMap(int capacity) {
        buckets = new ArrayList[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new ArrayList<>();
        }
    }
    
    public void put(K key, V value) {
        int index = getIndex(key);
        List<Entry<K, V>> bucket = buckets[index];
        
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                // 如果已经存在相同的键，更新值
                entry.setValue(value);
                return;
            }
        }
        
        // 如果键不存在，添加新的 Entry
        bucket.add(new Entry<>(key, value));
        size++;
        
        // 检查是否需要扩容
        if ((double) size / buckets.length > LOAD_FACTOR) {
            resize();
        }
    }
    
    public V get(K key) {
        int index = getIndex(key);
        List<Entry<K, V>> bucket = buckets[index];
        
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        
        return null;
    }
    
    private int getIndex(K key) {
        return key.hashCode() % buckets.length;
    }
    
    private void resize() {
        int newCapacity = buckets.length * 2;
        List<Entry<K, V>>[] newBuckets = new ArrayList[newCapacity];
        
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = new ArrayList<>();
        }
        
        for (List<Entry<K, V>> bucket : buckets) {
            for (Entry<K, V> entry : bucket) {
                int newIndex = entry.getKey().hashCode() % newCapacity;
                newBuckets[newIndex].add(entry);
            }
        }
        
        buckets = newBuckets;
    }
    
    private static class Entry<K, V> {
        private K key;
        private V value;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        
        public K getKey() {
            return key;
        }
        
        public V getValue() {
            return value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }
    }
    
}
