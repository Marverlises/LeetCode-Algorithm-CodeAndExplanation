package week4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    
    @Test
    public void returnsMinimumSubArrayLengthForGivenTarget() {
        Solution1 solution = new Solution1();
        int[] nums = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        int target = 213;
        int expected = 2;
        assertEquals(expected, solution.minSubArrayLen2(target, nums));
    }
    
    @Test
    public void returnsZeroWhenTargetIsNotReachable() {
        Solution1 solution = new Solution1();
        int[] nums = {1, 1, 1, 1};
        int target = 5;
        int expected = 0;
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }
    
    @Test
    public void returnsArrayLengthWhenTargetIsEqualToArraySum() {
        Solution1 solution = new Solution1();
        int[] nums = {1, 2, 3, 4};
        int target = 10;
        int expected = 4;
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }
    
    @Test
    public void returnsOneWhenTargetIsPresentInArray() {
        Solution1 solution = new Solution1();
        int[] nums = {1, 2, 3, 4, 5};
        int target = 4;
        int expected = 1;
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }
    
    @Test
    public void returnsZeroForEmptyArray() {
        Solution1 solution = new Solution1();
        int[] nums = {};
        int target = 1;
        int expected = 0;
        assertEquals(expected, solution.minSubArrayLen(target, nums));
    }
    
    @Test
    public void lengthOfLongestSubstring() {
        Solution1 solution = new Solution1();
        int[] nums = {};
        int target = 1;
        int expected = 0;
        assertEquals(expected, solution.lengthOfLongestSubstring2("abcabcbb"));
    }
    
    @Test
    public void returnsIndicesWhenSubstringFormedByWordsExists() {
        Solution2 solution = new Solution2();
        String s = "barfoothefoobarman";
        String[] words = {"foo", "bar"};
        List<Integer> expected = Arrays.asList(0, 9);
        assertEquals(expected, solution.findSubstring(s, words));
    }
    
    @Test
    public void returnsEmptyListWhenNoSubstringFormedByWordsExists() {
        Solution2 solution = new Solution2();
        String s = "wordgoodgoodgoodbestword";
        String[] words = {"word", "good", "best", "word"};
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, solution.findSubstring(s, words));
    }
    
    @Test
    public void returnsEmptyListWhenInputStringIsEmpty() {
        Solution2 solution = new Solution2();
        String s = "";
        String[] words = {"foo", "bar"};
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, solution.findSubstring(s, words));
    }
    
    @Test
    public void returnsEmptyListWhenWordsArrayIsEmpty() {
        Solution2 solution = new Solution2();
        String s = "barfoothefoobarman";
        String[] words = {};
        List<Integer> expected = new ArrayList<>();
        assertEquals(expected, solution.findSubstring(s, words));
    }
    
    @Test
    public void returnsIndicesWhenWordsAreRepeatedInString() {
        Solution2 solution = new Solution2();
        String s = "barfoofoobarthefoobarman";
        String[] words = {"foo", "bar", "the"};
        List<Integer> expected = Arrays.asList(0, 3, 6, 9);
        assertEquals(expected, solution.findSubstring2(s, words));
    }
    
    @Test
    public void returnsCorrectSubstringWhenTargetIsPresent() {
        Solution3 solution = new Solution3();
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String expected = "BANC";
        assertEquals(expected, solution.minWindow2(s, t));
    }
    
    @Test
    public void returnsEmptyStringWhenTargetIsNotPresent() {
        Solution3 solution = new Solution3();
        String s = "ADOBECODEBANC";
        String t = "XYZ";
        String expected = "";
        assertEquals(expected, solution.minWindow(s, t));
    }
    
    @Test
    public void returnsWholeStringWhenTargetIsSameAsInput() {
        Solution3 solution = new Solution3();
        String s = "ADOBECODEBANC";
        String t = "ADOBECODEBANC";
        String expected = "ADOBECODEBANC";
        assertEquals(expected, solution.minWindow(s, t));
    }
    
    @Test
    public void returnsEmptyStringWhenInputStringIsEmpty() {
        Solution3 solution = new Solution3();
        String s = "";
        String t = "ABC";
        String expected = "";
        assertEquals(expected, solution.minWindow(s, t));
    }
    
    @Test
    public void returnsEmptyStringWhenTargetStringIsEmpty() {
        Solution3 solution = new Solution3();
        String s = "ADOBECODEBANC";
        String t = "";
        String expected = "";
        assertEquals(expected, solution.minWindow(s, t));
    }
    
    @Test
    public void returnsCorrectSubstringWhenTargetHasDuplicateCharacters() {
        Solution3 solution = new Solution3();
        String s = "ADOBECODEBANC";
        String t = "AAABC";
        String expected = "ADOBECODEBANC";
        assertEquals(expected, solution.minWindow(s, t));
    }
    
    @Test
    public void returnsTrueForValidSudoku() {
        Solution4 solution = new Solution4();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        assertTrue(solution.isValidSudoku(board));
    }
    
    @Test
    public void returnsFalseForInvalidSudokuWithRepeatedNumbersInBox() {
        Solution4 solution = new Solution4();
        char[][] board = {
                {'.', '.', '4', '.', '.', '.', '6', '3', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'5', '.', '.', '.', '.', '.', '.', '9', '.'},
                {'.', '.', '.', '5', '6', '.', '.', '.', '.'},
                {'4', '.', '3', '.', '.', '.', '.', '.', '1'},
                {'.', '.', '.', '7', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'}
        };
        assertFalse(solution.isValidSudoku2(board));
    }
    
    @Test
    public void returnsTrueForValidSudoku2() {
        Solution4 solution = new Solution4();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        assertTrue(solution.isValidSudoku2(board));
    }
    
    
    @Test
    public void returnsCorrectOrderForSquareMatrix() {
        Solution5 solution = new Solution5();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 6, 9, 8, 7, 4, 5);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
    
    @Test
    public void returnsCorrectOrderForRectangleMatrix() {
        Solution5 solution = new Solution5();
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 8, 12, 11, 10, 9, 5, 6, 7);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
    
    @Test
    public void returnsCorrectOrderForSingleRowMatrix() {
        Solution5 solution = new Solution5();
        int[][] matrix = {{1, 2, 3, 4, 5}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
    
    @Test
    public void returnsCorrectOrderForSingleColumnMatrix() {
        Solution5 solution = new Solution5();
        int[][] matrix = {{1}, {2}, {3}, {4}, {5}};
        List<Integer> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, solution.spiralOrder(matrix));
    }
    
    @Test
    public void returnsEmptyListForEmptyMatrix() {
        Solution5 solution = new Solution5();
        int[][] matrix = {};
        List<Integer> expected = Arrays.asList();
        assertEquals(expected, solution.spiralOrder(matrix));
    }
}
