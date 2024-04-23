package binary_tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author Baiyu
 * @Time 2024/4/20 12:59
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution7 {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;
        
        public Node() {
        }
        
        public Node(int _val) {
            val = _val;
        }
        
        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
    
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        // 1. 创建一个队列，用于存储每一层的节点
        Queue<Node> deque = new ArrayDeque<>();
        // 2. 将根节点添加到队列中
        deque.offer(root);
        // 3. 遍历队列
        while (!deque.isEmpty()) {
            // 4. 获取队列的长度
            int size = deque.size();
            Node last = null;
            // 5. 遍历队列中的节点(当前层)
            for (int i = 0; i < size; i++) {
                // 6. 从队列中取出节点
                Node node = deque.poll();
                // 7. 如果i不等于0，那么将上一个节点的next指向当前节点
                if (i != 0) {
                    last.next = node;
                }
                // 8. 如果当前节点的左子节点不为空，那么将左子节点添加到队列中
                if (node.left != null) {
                    deque.add(node.left);
                }
                // 9. 如果当前节点的右子节点不为空，那么将右子节点添加到队列中
                if (node.right != null) {
                    deque.add(node.right);
                }
                last = node; // 保存当前节点（前节点）
            }
        }
        return root;
    }
    
    // last指向当前层的最后一个节点
    // nextStart指向下一层的第一个节点
    Node last = null, nextStart = null;
    
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        Node start = root;
        while (start != null) {
            last = null;
            nextStart = null;
            // 遍历当前层的节点,并将下一层的节点连接起来
            for (Node p = start; p != null; p = p.next) {
                if (p.left != null) {
                    handle(p.left);
                }
                if (p.right != null) {
                    handle(p.right);
                }
            }
            // 移动到下一层
            start = nextStart;
        }
        return root;
    }
    public void handle(Node p) {
        // 1. 如果last不为空，那么将last的next指向p
        if (last != null) {
            last.next = p;
        }
        if (nextStart == null) {
            nextStart = p;
        }
        last = p;
    }
    
    
    public static void main(String[] args) {
        //test case
        Node root = new Solution7().new Node(1);
        Node left = new Solution7().new Node(2);
        Node right = new Solution7().new Node(3);
        Node leftleft = new Solution7().new Node(4);
        Node leftright = new Solution7().new Node(5);
        Node rightleft = new Solution7().new Node(6);
        Node rightright = new Solution7().new Node(7);
        root.left = left;
        root.right = right;
        left.left = leftleft;
        left.right = leftright;
        right.left = rightleft;
        right.right = rightright;
        Node node = new Solution7().connect(root);
        System.out.println(node);
    }
}
