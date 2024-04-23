package week8;

/**
 * @Author Baiyu
 * @Time 2024/3/6 10:58
 * @StudentNumber 2018217662
 * @Description
 */

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SolutionTest {
    
    @Test
    public void calculateReturnsCorrectResultForSimpleExpression() {
        Solution1 solution = new Solution1();
        String expression = "1 + 2";
        assertEquals(3, solution.calculate(expression));
    }
    
    @Test
    public void calculateReturnsCorrectResultForExpressionWithParentheses() {
        Solution1 solution = new Solution1();
        String expression = "(1 + 2) - 3";
        assertEquals(0, solution.calculate(expression));
    }
    
    @Test
    public void calculateReturnsCorrectResultForExpressionWithNestedParentheses() {
        Solution1 solution = new Solution1();
        String expression = "((1 + 2) - 3) + 4";
        assertEquals(4, solution.calculate(expression));
    }
    
    @Test
    public void calculateReturnsCorrectResultForExpressionWithMultipleOperators() {
        Solution1 solution = new Solution1();
        String expression = "1 + 2 - 3";
        assertEquals(0, solution.calculate(expression));
    }
    
    @Test
    public void calculateReturnsCorrectResultForExpressionWithSpaces() {
        Solution1 solution = new Solution1();
        String expression = "1+3 + (4+5+2)-3)+6+8";
        assertEquals(3, solution.calculate3(expression));
    }
    
    @Test
    public void addTwoNumbersReturnsCorrectResultForSimpleAddition() {
        Solution3 solution = new Solution3();
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(3);
        ListNode result = solution.addTwoNumbers(l1, l2);
        assertEquals(5, result.val);
    }
    
    @Test
    public void addTwoNumbersReturnsCorrectResultForDifferentLengths() {
        Solution3 solution = new Solution3();
        //[2,4,9]
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(9)));
        //[5,6,4,9]
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4, new ListNode(9))));
        ListNode result = solution.addTwoNumbers2(l1, l2);
        //[7,0,4,0,1]
    }
    
    
    @Test
    public void copyRandomListReturnsCorrectResultForMultipleNodesListWithRandomPointers() {
        //[[0,null]]
        Solution5 solution = new Solution5();
        Node head = new Node(0);
        Node result = solution.copyRandomList(head);
        assertEquals(0, result.val);
        assertNull(result.random);
        
    }
    
    
    @Test
    public void reverseBetweenReturnsCorrectResultForEdgeCaseRightEqualsListLength() {
        Solution6 solution = new Solution6();
        //【3，5】
        ListNode head = new ListNode(3, new ListNode(5));
        ListNode result = solution.reverseBetween(head, 1, 2);
        //【5，3】
        
    }
}
