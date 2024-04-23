package week9;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/4/14 21:51
 * @StudentNumber 2018217662
 * @Description
 */




class LRUCache {
    class Node {
        int key;
        int value;
        Node next;
        Node pre;
        
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    // 1. 定义一个HashMap存储key和Node
    private HashMap<Integer, Node> map;
    // 2. 定义一个虚拟头节点，一个尾部节点
    private Node head;
    // 3. 获取容量大小
    private int capacity;
    
    public LRUCache(int capacity) {
        map = new HashMap<>(capacity);
        // 定义一个capacity长度的双向链表
        this.head = new Node(-1, -1);
        // head 相当于虚拟头节点
        Node cur = head;
        for (int i = 0; i < capacity; i++) {
            Node node = new Node(-1, -1);
            cur.next = node;
            node.pre = cur;
            cur = cur.next;
        }
        // 完成环形链表的构建
        cur.next = head.next;
        head.next.pre = cur;
    }
    
    public int get(int key) {
        // 1. 如果key不存在，返回-1
        if (!map.containsKey(key)) {
            return -1;
        }
        // 2. 如果key存在，返回value
        Node node = map.get(key);
        // 移动到头部
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        // 1. 如果key存在，更新value
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            // 移动到头部
            moveToHead(node);
        } else {
            // 2. 如果key不存在，将新节点插入到头部并将尾部节点删除
            Node pre = head.next.pre;
            //删除尾部节点
            map.remove(pre.key);
            head.next = pre;
            pre.key = key;
            pre.value = value;
            map.put(key, pre);
        }
    }
    
    private void moveToHead(Node node) {
        //如果当前节点是头部节点，直接返回
        if (head.next == node) {
            return;
        }
        //如果当前节点是中间节点，将当前节点的前一个节点和后一个节点连接
        node.pre.next = node.next;
        node.next.pre = node.pre;
        //移动到头部
        Node pre = head.next.pre;
        head.next.pre = node;
        node.next = head.next;
        node.pre = pre;
        pre.next = node;
        head.next = node;
    }
}

public class Solution5 {

}
