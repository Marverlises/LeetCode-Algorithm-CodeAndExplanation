package week9;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @Author Baiyu
 * @Time 2024/4/10 22:24
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        // 先便利一边链表，将所有重复的节点存入set中
        HashMap<Integer, Integer> integerHashMap = new HashMap<>();
        ListNode temp = head;
        while (temp != null) {
            if (integerHashMap.containsKey(temp.val)) {
                integerHashMap.put(temp.val, integerHashMap.get(temp.val) + 1);
            } else {
                integerHashMap.put(temp.val, 1);
            }
            temp = temp.next;
        }
        // get all the nodes that are duplicated
        HashSet<Integer> duplicated = new HashSet<>();
        for (Integer key : integerHashMap.keySet()) {
            if (integerHashMap.get(key) > 1) {
                duplicated.add(key);
            }
        }
        // iterate the linked list again, and remove the duplicated nodes
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        while (cur != null) {
            if (duplicated.contains(cur.val)) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
    
    public ListNode deleteDuplicates2(ListNode head) {
        // important information: the linked list is sorted
        // idea: if the following node has the same value as the current node, then remove the following node
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy; // in case the head node is removed
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                int val = cur.val;
                while (cur != null && cur.val == val) {
                    cur = cur.next;
                }
                pre.next = cur;
            } else {
                pre = cur;
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    
}