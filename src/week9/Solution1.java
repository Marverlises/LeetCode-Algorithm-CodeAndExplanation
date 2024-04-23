package week9;

/**
 * @Author Baiyu
 * @Time 2024/4/7 22:13
 * @StudentNumber 2018217662
 * @Description
 *  * Definition for singly-linked list.
 *  * public class ListNode {
 *  *     int val;
 *  *     ListNode next;
 *  *     ListNode() {}
 *  *     ListNode(int val) { this.val = val; }
 *  *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 *  * }
 */

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class Solution1 {
    //删除链表的倒数第 N 个结点
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //1. 从前向后遍历链表计数
        int count = 0;
        ListNode temp = head;
        while (temp != null){
            count++;
            temp = temp.next;
        }
        //2. 计算要删除的节点的位置
        int index = count - n;
        //3. 为了防止要删除的是头节点，创建一个虚拟头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        //4. 创建一个指针指向虚拟头节点
        ListNode cur = dummy;
        //5. 移动指针到要删除的节点的前一个节点
        for (int i = 0; i < index; i++){
            cur = cur.next;
        }
        //6. 删除节点
        cur.next = cur.next.next;
        //7. 返回头节点
        return dummy.next;
    }
}
