package week9;


/**
 * @Author Baiyu
 * @Time 2024/4/12 7:22
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    public ListNode rotateRight(ListNode head, int k) {
        //1. link the end of the linked list to the head
        if (head == null){
            return null;
        }
        ListNode temp = head;
        int count = 1;
        while (temp.next != null){
            temp = temp.next;
            count++;
        }
        //2. constructed a round linked list
        temp.next = head;
        temp = head;
        
        //3. because the new head is the number k end node
        for (int i = 0; i < Math.abs(count - k % count - 1); i++) {
            temp = temp.next;
            head = temp;
        }
        head = temp.next;
        temp.next = null;
        return head;
    }
    
    
}
