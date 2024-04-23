package week8;

/**
 * @Author Baiyu
 * @Time 2024/3/8 9:16
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
//class ListNode {
//    int val;
//    ListNode next;
//
//    ListNode() {
//    }
//
//    ListNode(int val) {
//        this.val = val;
//    }
//
//    ListNode(int val, ListNode next) {
//        this.val = val;
//        this.next = next;
//    }
//}

public class Solution3 {
    //两数相加
    
    //整体代码思路为：
    //1. 创建一个新的链表作为结果，一个指针指向结果链表的头部，创建一个变量存储进位
    //2. 遍历两个链表，获取两个链表的值，计算当前位的值，更新进位，更新当前位的值，更新两个链表的指针
    //3. 如果最后一位有进位，需要再创建一个节点
    //4. 返回结果链表
    //思路1
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //1. 创建一个新的链表作为结果
        ListNode ret = new ListNode(0);
        //2. 创建一个指针指向结果链表的头部
        ListNode cur = ret;
        //3. 创建一个变量存储进位
        int carry = 0;
        //4. 遍历两个链表
        while (l1 != null || l2 != null) {
            //5. 获取两个链表的值
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            //6. 计算当前位的值
            int sum = x + y + carry;
            //7. 更新进位
            carry = sum / 10;
            //8. 更新当前位的值
            cur.val = sum % 10;
            //9. 更新两个链表的指针
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            //如果已经遍历完两个链表，跳出循环
            if (l1 == null && l2 == null) {
                break;
            }
            //还没遍历完两个链表，创建一个新的节点
            cur.next = new ListNode(0);
            cur = cur.next;
        }
        //10. 如果最后一位有进位，需要再创建一个节点
        if (carry > 0) {
            cur.next = new ListNode(carry);
            cur = cur.next;
            cur.next = null;
            cur.val = carry;
        }
        //11. 返回结果链表
        return ret;
    }
    
    //优化
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        //在原链表上进行操作——假设以l1为基准
        //表示进位
        int carry = 0;
        //用一个指针指向l1，表示结果链表头
        ListNode ret = l1;
        //临时节点存储l1的前一个位置方便后续操作
        ListNode pre = null;
        //遍历两个链表
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            //计算当前位的值
            int sum = x + y + carry;
            //更新进位
            carry = sum / 10;
            //更新当前位的值
            l1.val = sum % 10;
            //更新两个链表的指针
            pre = l1;
            l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            //如果l1已经遍历完，l2还没遍历完，就将l1的指针指向l2
            if (l1 == null && l2 != null) {
                pre.next = l2;
                l1 = l2;
                l2 = null;
            }
        }
        //如果最后一位有进位，需要再创建一个节点
        if (carry > 0) {
            pre.next = new ListNode(carry);
        }
        //返回结果链表
        return ret;
    }
    
}
