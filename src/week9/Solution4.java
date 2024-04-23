package week9;

/**
 * @Author Baiyu
 * @Time 2024/4/12 7:53
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {
    public ListNode partition(ListNode head, int x) {
        //1. construct two linked list: one for less than x, one for others
        ListNode temp = head, target1 = null, target2 = null, target2Head = null;
        //2. the
        while (temp != null){
            if (temp.val < x){
                if (target1 == null){
                    head = temp;
                    target1 = temp;
                }else{
                    target1.next = temp;
                    target1 = target1.next;
                }
            }else{
                if (target2 == null){
                    target2Head = temp;
                    target2 = temp;
                }else{
                    target2.next = temp;
                    target2 = target2.next;
                }
            }
            temp = temp.next;
        }
        if (target1 == null){
            return target2Head;
        }else {
            target1.next = target2Head;
            if (target2 != null) {
                target2.next = null;
            }
        }
        return head;
    }
    

}
