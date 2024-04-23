package week5;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Baiyu
 * @Time 2024/2/19 9:43
 * @StudentNumber 2018217662
 * @Description
 */
public class SolutionTest {
    
    @Test
    public void setZeroes3_allZeros() {
        Solution1 solution = new Solution1();
        int[][] matrix = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        solution.setZeroes3(matrix);
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, matrix);
    }
    
    @Test
    public void setZeroes3_noZeros() {
        Solution1 solution = new Solution1();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solution.setZeroes3(matrix);
        assertArrayEquals(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}}, matrix);
    }
    
    @Test
    public void setZeroes3_singleZero() {
        Solution1 solution = new Solution1();
        int[][] matrix = {{1, 2, 3}, {4, 0, 6}, {7, 8, 9}};
        solution.setZeroes3(matrix);
        assertArrayEquals(new int[][]{{1, 0, 3}, {0, 0, 0}, {7, 0, 9}}, matrix);
    }
    
    @Test
    public void setZeroes3_multipleZeros() {
        Solution1 solution = new Solution1();
        int[][] matrix = {
                {0, 0, 0, 5},
                {4, 3, 1, 4},
                {0, 1, 1, 4},
                {1, 2, 1, 3},
                {0, 0, 1, 1}
        };
        solution.setZeroes3(matrix);
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, matrix);
    }
    
    @Test
    public void setZeroes3_emptyMatrix() {
        Solution1 solution = new Solution1();
        // 创建并初始化一个二维数组
        int[][] matrix = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        solution.setZeroes4(matrix);
        assertArrayEquals(new int[][]{}, matrix);
    }
    
    
    @Test
    public void gameOfLife_allDeadCells() {
        Solution2 solution = new Solution2();
        int[][] board = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
        solution.gameOfLife(board);
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, board);
    }
    
    @Test
    public void gameOfLife_allLiveCells() {
        Solution2 solution = new Solution2();
        int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        solution.gameOfLife(board);
        assertArrayEquals(new int[][]{{1, 0, 1}, {0, 0, 0}, {1, 0, 1}}, board);
    }
    
    @Test
    public void gameOfLife_singleLiveCell() {
        Solution2 solution = new Solution2();
        int[][] board = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        solution.gameOfLife(board);
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, board);
    }
    
    @Test
    public void gameOfLife_multipleLiveCells() {
        Solution2 solution = new Solution2();
        int[][] board = {
                {0, 1, 0},
                {1, 1, 1},
                {0, 1, 0}
        };
        solution.gameOfLife(board);
        assertArrayEquals(new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, board);
    }
    
    @Test
    public void gameOfLife_emptyBoard() {
        Solution2 solution = new Solution2();
        int[][] board = {};
        solution.gameOfLife(board);
        assertArrayEquals(new int[][]{}, board);
        
    }
    
    @Test
    public void gameOfLife_customScenario() {
        Solution2 solution = new Solution2();
        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };
        solution.gameOfLife5(board);
        // Replace the expected result with the correct one after running the method on the given board
        assertArrayEquals(new int[][]{{0, 0, 0}, {0, 0, 0}, {0, 0, 0}, {0, 0, 0}}, board);
    }
    
    
    @Test
    public void canConstruct_allLettersAvailable() {
        Solution3 solution = new Solution3();
        assertTrue(solution.canConstruct("abc", "cbad"));
    }
    
    @Test
    public void canConstruct_notEnoughLetters() {
        Solution3 solution = new Solution3();
        assertFalse(solution.canConstruct("abc", "ab"));
    }
    
    @Test
    public void canConstruct_emptyRansomNote() {
        Solution3 solution = new Solution3();
        assertTrue(solution.canConstruct("", "magazine"));
    }
    
    @Test
    public void canConstruct_emptyMagazine() {
        Solution3 solution = new Solution3();
        assertFalse(solution.canConstruct("ransom", ""));
    }
    
    @Test
    public void canConstruct_caseSensitive() {
        Solution3 solution = new Solution3();
        assertFalse(solution.canConstruct("A", "a"));
    }
    
    @Test
    public void canConstruct_repeatedLetters() {
        Solution3 solution = new Solution3();
        assertTrue(solution.canConstruct("aa", "aab"));
    }
    
    @Test
    public void canConstruct_repeatedLettersNotEnough() {
        Solution3 solution = new Solution3();
        assertFalse(solution.canConstruct("aaa", "aab"));
    }
    
    @Test
    public void groupAnagrams3_sameWordRepeated() {
        Solution6 solution = new Solution6();
        String[] input = {"eat", "eat", "eat"};
        List<List<String>> result = solution.groupAnagrams3(input);
        List<List<String>> expected = Arrays.asList(
                Arrays.asList("eat", "eat", "eat")
        );
        assertTrue(result.containsAll(expected) && expected.containsAll(result));
    }
}
