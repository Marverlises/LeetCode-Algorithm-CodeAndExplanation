package week8;

import org.w3c.dom.NodeList;

/**
 * @Author Baiyu
 * @Time 2024/3/15 10:01
 * @StudentNumber 2018217662
 * @Description * Definition for singly-linked list.
 * * public class ListNode {
 * *     int val;
 * *     ListNode next;
 * *     ListNode() {}
 * *     ListNode(int val) { this.val = val; }
 * *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * * }
 */

class ListNode {
    int val;
    ListNode next;
    
    ListNode() {
    }
    
    ListNode(int val) {
        this.val = val;
    }
    
    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class Solution6 {
    
    //反转链表II
    //思路1
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left >= right) {
            return head;
        }
        //创建一个虚拟头节点——防止出现left为1的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //创建一个节点寻找left节点的前一个节点
        ListNode pre = dummy;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        //创建一个前驱节点
        ListNode leftNodePre = null;
        //当前需要反转的节点
        ListNode cur = pre.next;
        //总共需要反转right-left+1个节点比如【2，4】就要反转【2，3，4】位置
        for (int i = 0; i < right - left + 1; i++) {
            ListNode temp = cur.next;
            cur.next = leftNodePre;
            leftNodePre = cur;
            cur = temp;
        }
        //连接前后节点
        //将反转后的链表的尾部与right下一个位置连接
        pre.next.next = cur;
        //将left前一个位置与反转后的链表头部连接
        pre.next = leftNodePre;
        //返回虚拟头节点的下一个节点，也就是实际的头节点
        return dummy.next;
    }
}
