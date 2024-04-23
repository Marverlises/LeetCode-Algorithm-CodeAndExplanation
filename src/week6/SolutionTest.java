package week6;

/**
 * @Author Baiyu
 * @Time 2024/2/23 8:13
 * @StudentNumber 2018217662
 * @Description
 */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void twoSum_withValidInput_returnsCorrectIndices() {
        Solution1 week6Instance = new Solution1();
        int[] nums = {3,3};
        int target = 6;
        int[] expected = {0, 1};
        assertArrayEquals(expected, week6Instance.twoSum2(nums, target));
    }
    @Test
    void isHappy_withZero_returnsFalse() {
        Solution2 solution2Instance = new Solution2();
        assertFalse(solution2Instance.isHappy3(2));
    }
    @Test
    void longestConsecutive_withEmptyArray_returnsZero() {
        Solution4 solution4Instance = new Solution4();
        int[] nums = {4,0,-4,-2,2,5,2,0,-8,-8,-8,-8,-1,7,4,5,5,-4,6,6,-3};
        //assertEquals(0, solution4Instance.longestConsecutive(nums));
    }
    
    @Test
    void merge_withEmptyArray_returnsEmptyArray() {
        Solution5 solution5Instance = new Solution5();
        int[][] intervals = {{1, 3}, {2, 6}, {8, 16}, {15, 18}};
        int[][] expected = {};
        assertArrayEquals(expected, solution5Instance.merge(intervals));
    }
    
    @Test
    void insert_withNewIntervalInsideExistingInterval_returnsUnchangedIntervals() {
        Solution6 solution6Instance = new Solution6();
        //[1,2],[3,5],[6,7],[8,10],[12,16]
        int[][] intervals = {{1, 5}};
        int[] newInterval = {0, 3};
        int[][] expected = {{1, 5}};
        assertArrayEquals(expected, solution6Instance.insert(intervals, newInterval));
    }
  
}
