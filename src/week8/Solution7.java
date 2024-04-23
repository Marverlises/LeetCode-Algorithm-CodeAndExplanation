package week8;

/**
 * @Author Baiyu
 * @Time 2024/3/16 12:46
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution7 {
    //K个一组翻转链表
    //思路1
    public ListNode reverseKGroup(ListNode head, int k) {
        //1. 定义一个虚拟头节点和cur指针
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        //一个大循环
        while (cur != null){
            //2. 反转k个节点
            cur.next = reverse(cur.next, k);
            //3. 移动cur指针向后移动k个节点
            for (int i = 0; i < k && cur != null; i++){
                cur = cur.next;
            }
        }
        return dummy.next;
    }
    
    public ListNode reverse(ListNode head, int k){
        //1. 判断是否需要翻转
        int n = 0;
        ListNode temp = head;
        while (temp != null){
            n++;
            temp = temp.next;
        }
        if (n < k){
            return head;
        }
        //2. 定义快慢指针执行翻转
        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;
        while (k > 0){
            fast = fast.next;
            slow.next = pre;
            pre = slow;
            slow = fast;
            k--;
        }
        head.next = fast;
        return pre;
    }
}
