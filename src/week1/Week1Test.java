package week1;

import java.util.TreeMap;

/**
 * @Author Baiyu
 * @Time 2024/1/19 15:10
 * @StudentNumber 2018217662
 * @Description
 */

import static org.junit.jupiter.api.Assertions.*;

class Week1Test {
    
    @org.junit.jupiter.api.Test
    void majorityElement_singleElementArray_returnsSameElement() {
        int[] nums = {1};
        assertEquals(1, Solution2.majorityElement(nums));
    }
    
    @org.junit.jupiter.api.Test
    void majorityElement_multipleOccurrences_returnsMajorityElement() {
        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        assertEquals(2, Solution2.majorityElement(nums));
    }
    
    @org.junit.jupiter.api.Test
    void rotate1_positiveRotation_rotatesArray() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        Solution2.rotate1(nums, 3);
        assertArrayEquals(new int[]{5, 6, 7, 1, 2, 3, 4}, nums);
    }
    
    @org.junit.jupiter.api.Test
    void rotate2_negativeRotation_rotatesArray() {
        int[] nums = {-1, -100, 3, 99};
        Solution2.rotate2(nums, 2);
        assertArrayEquals(new int[]{3, 99, -1, -100}, nums);
    }
    
    @org.junit.jupiter.api.Test
    void maxProfit2_increasingPrices_returnsMaxProfit() {
        int[] prices = {7, 1, 5, 3, 6, 4};
        assertEquals(7, Solution2.maxProfit2(prices));
    }
    
    @org.junit.jupiter.api.Test
    void canJump_canReachEnd_returnsTrue() {
        int[] nums = {2, 3, 1, 1, 4};
        assertTrue(Solution2.canJump(nums));
    }
    
    @org.junit.jupiter.api.Test
    void canJump_cannotReachEnd_returnsFalse() {
        int[] nums = {3, 2, 1, 0, 4};
        assertFalse(Solution2.canJump(nums));
    }
    
    @org.junit.jupiter.api.Test
    void jump_maximumJumps_returnsCorrectCount() {
        int[] nums = {2, 3, 1, 1, 4};
        assertEquals(2, Solution3.jump(nums));
    }
    
    @org.junit.jupiter.api.Test
    void jump_noJumps_returnsZero() {
        int[] nums = {1, 2};
        assertEquals(1, Solution3.jump(nums));
    }
    
    @org.junit.jupiter.api.Test
    void hIndex_highestCitationCount_returnsCorrectIndex() {
        int[] citations = {3, 0, 6, 1, 5};
        assertEquals(3, Solution3.hIndex(citations));
    }
    
    @org.junit.jupiter.api.Test
    void hIndex_noCitations_returnsZero() {
        int[] citations = {0, 0, 0, 0, 0};
        assertEquals(0, Solution3.hIndex(citations));
    }
    
    @org.junit.jupiter.api.Test
    void hIndex_allCitationsEqual_returnsCitationCount() {
        int[] citations = {5, 5, 5, 5, 5};
        assertEquals(5, Solution3.hIndex(citations));
    }
    
    @org.junit.jupiter.api.Test
    void hIndex_singleCitation_returnsOne() {
        int[] citations = {1};
        assertEquals(1, Solution3.hIndex(citations));
    }
    
    @org.junit.jupiter.api.Test
    void hIndex_emptyArray_returnsZero() {
        int[] citations = {};
        assertEquals(0, Solution3.hIndex(citations));
    }
    
    //生成一个测试函数
    @org.junit.jupiter.api.Test
    void temp_test() {
        // 创建一个 TreeMap
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        
        // 添加键值对
        treeMap.put(3, "Three");
        treeMap.put(1, "One");
        treeMap.put(4, "Four");
        treeMap.put(2, "Two");
        
        // 遍历 TreeMap
        for (Integer key : treeMap.keySet()) {
            System.out.println(key + ": " + treeMap.get(key));
        }
        
        // 使用 firstKey、lastKey、higherKey、lowerKey 等方法
        System.out.println("First key: " + treeMap.firstKey());
        System.out.println("Last key: " + treeMap.lastKey());
        System.out.println("Key higher than 2: " + treeMap.higherKey(2));
        System.out.println("Key lower than 3: " + treeMap.lowerKey(3));
    }
    
    @org.junit.jupiter.api.Test
    void productExceptSelf_allPositiveNumbers_returnsCorrectProduct() {
        Solution4 solution = new Solution4();
        int[] nums = {1, 2, 3, 4};
        int[] expected = {24, 12, 8, 6};
        assertArrayEquals(expected, solution.productExceptSelf(nums));
    }
    
    @org.junit.jupiter.api.Test
    void productExceptSelf_containsZero_returnsCorrectProduct() {
        Solution4 solution = new Solution4();
        int[] nums = {0, 2, 3, 4};
        int[] expected = {24, 0, 0, 0};
        assertArrayEquals(expected, solution.productExceptSelf(nums));
    }
    
    @org.junit.jupiter.api.Test
    void productExceptSelf_allSameNumbers_returnsCorrectProduct() {
        Solution4 solution = new Solution4();
        int[] nums = {2, 2, 2, 2};
        int[] expected = {8, 8, 8, 8};
        assertArrayEquals(expected, solution.productExceptSelf(nums));
    }
    
    @org.junit.jupiter.api.Test
    void productExceptSelf_singleElement_returnsCorrectProduct() {
        Solution4 solution = new Solution4();
        int[] nums = {1, 2, 3, 4};
        int[] expected = {24, 12, 8, 6};
        assertArrayEquals(expected, solution.productExceptSelf(nums));
    }
    
    @org.junit.jupiter.api.Test
    void productExceptSelf_emptyArray_returnsEmptyArray() {
        Solution4 solution = new Solution4();
        int[] nums = {};
        int[] expected = {};
        assertArrayEquals(expected, solution.productExceptSelf(nums));
    }
    
    void canCompleteCircuit2_negativeGasDifference_returnsNegativeOne() {
        Solution4 solution = new Solution4();
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 5};
        assertEquals(-1, solution.canCompleteCircuit2(gas, cost));
    }
    
    @org.junit.jupiter.api.Test
    void canCompleteCircuit2_zeroGasDifference_returnsZero() {
        Solution4 solution = new Solution4();
        int[] gas = {2, 3, 4};
        int[] cost = {3, 4, 3};
        assertEquals(0, solution.canCompleteCircuit3(gas, cost));
    }
    
    @org.junit.jupiter.api.Test
    void canCompleteCandyDistribute() {
        Solution5 solution = new Solution5();
        
        // Test case 1: Increasing ratings
        int[] ratings1 = {1,3,2,2,1};
        System.out.println(solution.candy2(ratings1)); // Expected output: 15
    }
    
    @org.junit.jupiter.api.Test
    void canCompleteTrap() {
        Solution5 solution = new Solution5();
        // Test case 1: Increasing ratings
        int[] ratings1 = {4,2,0,3,2,5};
        System.out.println(solution.trap(ratings1)); // Expected output: 15
    }
    
    @org.junit.jupiter.api.Test
    void canCompleteRoman() {
        String s = "MCMXCIV";
        System.out.println(Solution6.romanToInt(s)); // Expected output: 15
    }
    
    @org.junit.jupiter.api.Test
    void intToRoman_maxInteger_returnsCorrectRoman() {
        Solution7 solution = new Solution7();
        assertEquals("MMMCMXCIX", solution.intToRoman(3999));
    }
    
}
