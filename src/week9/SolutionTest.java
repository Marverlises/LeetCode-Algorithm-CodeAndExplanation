package week9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Baiyu
 * @Time 2024/4/10 22:31
 * @StudentNumber 2018217662
 * @Description
 */
public class SolutionTest {
    @Test
    public void testDeleteDuplicates() {
        // Create a linked list: 1 -> 2 -> 3 -> 3 -> 4 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);
        
        // Expected result: 1 -> 2 -> 3 -> 4 -> 5
        ListNode expected = new ListNode(1);
        expected.next = new ListNode(2);
        expected.next.next = new ListNode(3);
        expected.next.next.next = new ListNode(4);
        expected.next.next.next.next = new ListNode(5);
        
        ListNode result = new Solution2().deleteDuplicates(head);
        
        // Compare each node's value
        while (expected != null && result != null) {
            assertEquals(expected.val, result.val);
            expected = expected.next;
            result = result.next;
        }
        
        // Both expected and result should be null at the end
        assertNull(expected);
        assertNull(result);
    }
    
    
    @Test
    public void testPartition() {
        Solution4 main = new Solution4();
        // Create the input linked list
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        
        int x = 3;
        
        // Expected output
        ListNode expected = new ListNode(1);
        expected.next = new ListNode(2);
        expected.next.next = new ListNode(2);
        expected.next.next.next = new ListNode(4);
        expected.next.next.next.next = new ListNode(3);
        expected.next.next.next.next.next = new ListNode(5);
        
        // Call the partition method
        ListNode result = main.partition(head, x);
        
        // Check if the result matches the expected output
        while (expected != null && result != null) {
            assertEquals(expected.val, result.val);
            expected = expected.next;
            result = result.next;
        }
        
        // Both expected and result should be null if the lists are of equal length
        assertNull(expected);
        assertNull(result);
    }
    
    
    @Test
    public void testLRUCache() {
        // 创建一个容量为 2 的 LRUCache 实例
        LRUCache lruCache = new LRUCache(2);
        
        // 调用 put 方法
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        
        // 调用 get 方法
        System.out.println(lruCache.get(1)); // 应该输出 1
        lruCache.put(3, 3);
        
        // 调用 get 方法
        System.out.println(lruCache.get(2)); // 应该输出 -1，因为键 2 已经被移除
        lruCache.put(4, 4);
        
        // 调用 get 方法
        System.out.println(lruCache.get(1)); // 应该输出 -1，因为键 1 已经被移除
        System.out.println(lruCache.get(3)); // 应该输出 3
        System.out.println(lruCache.get(4)); // 应该输出 4
    }
    
    
}
