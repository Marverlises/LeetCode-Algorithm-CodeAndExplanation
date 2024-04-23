package week8;

/**
 * @Author Baiyu
 * @Time 2024/3/7 16:17
 * @StudentNumber 2018217662
 * @Description /**
 * * Definition for singly-linked list.
 * * class ListNode {
 * *     int val;
 * *     ListNode next;
 * *     ListNode(int x) {
 * *         val = x;
 * *         next = null;
 * *     }
 * * }
 */


class ListNode1 {
    int val;
    ListNode next;
    
    ListNode1(int x) {
        val = x;
        next = null;
    }
}

public class Solution2 {
    //环形链表
    //思路1：使用快慢指针
    public boolean hasCycle(ListNode head) {
        //1.创建快慢指针
        ListNode slow = head;
        ListNode fast = head;
        //2.遍历链表
        while (fast != null && fast.next != null) {
            //3.慢指针走一步
            slow = slow.next;
            //4.快指针走两步
            fast = fast.next.next;
            //5.判断快慢指针是否相遇
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
    
    //思路2：使用哈希表——对比引用
    public boolean hasCycle2(ListNode head) {
        if (head == null) {
            return false;
        }
        //1. 创建一个哈希表
        java.util.HashSet<ListNode> set = new java.util.HashSet<>();
        //2.遍历链表
        while (head.next != null) {
            //3.判断当前节点是否在哈希表中
            if (set.contains(head)) {
                return true;
            }
            //4.将当前节点放入哈希表
            set.add(head);
            //5.更新head的值
            head = head.next;
        }
        //6.返回false
        return false;
    }
    
    //思路2：使用哈希表——对比hash值
    public boolean hasCycle2_2(ListNode head) {
        if (head == null) {
            return false;
        }
        //1. 创建一个哈希表
        java.util.HashSet<Integer> set = new java.util.HashSet<>();
        //2.遍历链表
        while (head.next != null) {
            //3.判断当前节点是否在哈希表中
            if (set.contains(head.hashCode())) {
                return true;
            }
            //4.将当前节点放入哈希表
            set.add(head.hashCode());
            //5.更新head的值
            head = head.next;
        }
        //6.返回false
        return false;
    }
}
