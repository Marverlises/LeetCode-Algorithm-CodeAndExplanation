package week7;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @Author Baiyu
 * @Time 2024/3/1 10:00
 * @StudentNumber 2018217662
 * @Description
 */
public class SolutionTest {
    
    
    @Test
    public void minimalArrowsNeededForNonOverlappingIntervals() {
        Solution1 solution = new Solution1();
        int[][] points = {{1, 2}, {3, 4}, {5, 6}, {7, 8}};
        assertEquals(4, solution.findMinArrowShots(points));
    }
    
    @Test
    public void minimalArrowsNeededForOverlappingIntervals() {
        Solution1 solution = new Solution1();
        //[[-2147483646,-2147483645],[2147483646,2147483647]]
        int[][] points = {{-2147483646, -2147483645}, {2147483646, 2147483647}};
        
        assertEquals(1, solution.findMinArrowShots2(points));
    }
    
    @Test
    public void minimalArrowsNeededForMixedIntervals() {
        Solution1 solution = new Solution1();
        int[][] points = {{1, 2}, {3, 4}, {2, 3}, {4, 5}};
        assertEquals(2, solution.findMinArrowShots2(points));
    }
    
    @Test
    public void minimalArrowsNeededForSingleInterval() {
        Solution1 solution = new Solution1();
        int[][] points = {{1, 2}};
        assertEquals(1, solution.findMinArrowShots(points));
    }
    
    @Test
    public void minimalArrowsNeededForEmptyIntervals() {
        Solution1 solution = new Solution1();
        int[][] points = {};
        assertEquals(0, solution.findMinArrowShots(points));
    }
    
    
    @Test
    public void isValidReturnsFalseForSingleCloseParentheses() {
        Solution2 solution = new Solution2();
        assertFalse(solution.isValid2("()[]{}"));
    }
    
    
    @Test
    public void simplifyPathReturnsSimplifiedPathForPathWithDotSegments() {
        Solution3 solution = new Solution3();
        String path = "/a//b////c/d//././/..";
        assertEquals("/a/b/c", solution.simplifyPath(path));
    }
    
    @Test
    public void popUpdatesMinValueWhenMinValueIsPopped() {
        MinStack minStack = new MinStack();
//["MinStack","push","push","push","getMin","pop","top","getMin"]
        
        //[[],[-2],[0],[-3],[],[],[],[]]
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        assertEquals(-3, minStack.getMin());
        minStack.pop();
        assertEquals(0, minStack.top());
        assertEquals(-2, minStack.getMin());
        System.out.println();
    }
    
    
    @Test
    public void testMinStackOperations() {
        MinStack minStack = new MinStack();
        minStack.push(-124);
        minStack.push(-164);
        System.out.println(minStack.getMin());
        
    }
    
    @Test
    public void testMinStackOperations2() {
        String s = null;
        System.out.println(Integer.parseInt(s));
    }
    
    @Test
    public void evalRPNReturnsCorrectResultForComplexExpression() {
        Solution5 solution = new Solution5();
        //["2","1","+","3","*"]
        String[] tokens = {"2", "1", "+", "3", "*"};
        
        assertEquals(6, solution.evalRPN2(tokens));
    }
    
    @Test
    public void minWindowReturnsCorrectSubstringWhenAllCharactersOfTAreInS() {
        Solution6 solution = new Solution6();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        assertEquals("BANC", solution.minWindow(s, t));
    }
    
}
