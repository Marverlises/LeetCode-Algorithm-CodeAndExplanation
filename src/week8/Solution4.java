package week8;

/**
 * @Author Baiyu
 * @Time 2024/3/9 18:36
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
 *  */
public class Solution4 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        //1.创建一个新的链表作为结果
        ListNode ret = new ListNode(0);
        //2.创建一个指针指向结果链表的头部
        ListNode cur = ret;
        //3.遍历两个链表
        while (list1 != null && list2 != null) {
            //4.比较两个链表的值
            if (list1.val < list2.val) {
                //5.如果list1的值小于list2的值，将list1的值放入结果链表中
                cur.next = list1;
                //6.更新list1的指针
                list1 = list1.next;
            } else {
                //7.如果list2的值小于list1的值，将list2的值放入结果链表中
                cur.next = list2;
                //8.更新list2的指针
                list2 = list2.next;
            }
            //9.更新结果链表的指针
            cur = cur.next;
        }
        //10.将剩余的链表放入结果链表中
        cur.next = list1 == null ? list2 : list1;
        //11.返回结果链表
        return ret.next;
    }
}
