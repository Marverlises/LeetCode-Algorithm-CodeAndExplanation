package week4;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @Author Baiyu
 * @Time 2024/2/12 8:02
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution1 {
    // 长度最小的子数组
    // 1. 滑动窗口
    public int minSubArrayLen(int target, int[] nums) {
        int ret = Integer.MAX_VALUE;
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            int j;
            for (j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target) {
                    ret = Math.min(ret, j - i + 1);
                    flag = true;
                    break;
                }
            }
            // 如果第一次就遍历到结尾，那么后面的就不用遍历了，没有解
            if (j >= nums.length) {
                break;
            }
        }
        // 如果没有解，也就是根本没有进入sum>=target，flag就为false，返回0，进入了就返回ret
        if (flag)
            return ret;
        else
            return 0;
    }
    
    // 2. 滑动窗口
    public int minSubArrayLen2(int target, int[] nums) {
        // 1. 初始化——左指针、右指针、最小长度
        Arrays.binarySearch(nums, 0, nums.length, target);
        int point1 = 0;
        int point2 = 0;
        int minLen = Integer.MAX_VALUE;
        int sum = 0;
        boolean flag = false;
        // 2. 移动右指针，将元素加入窗口
        while (point2 < nums.length) {
            sum += nums[point2];
            // 3. 当窗口内元素和大于等于目标值时，移动左指针，更新最小长度，在后面寻找可行解
            while (sum >= target) {
                flag = true;
                // 更新最小长度
                minLen = Math.min(minLen, point2 - point1 + 1);
                // 移动左指针，将最开始进入的元素移出窗口
                sum -= nums[point1];
                point1++;
            }
            point2++;
        }
        if (flag)
            return minLen;
        else
            return 0;
    }
    
    
    // 无重复字符的最长子串
    // 1. 暴力法
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty())
            return 0;
        if (s.length() == 1)
            return 1;
        int ret = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                //从当前位置向后遍历，如果有重复的字符，记录位置，跳出循环
                if (s.substring(i, j).contains(s.substring(j, j + 1))) {
                    ret = Math.max(ret, j - i);
                    break;
                }
                //如果遍历到最后一个字符，那么从i开始的最长的子串就是j-i+1
                if (j == s.length() - 1) {
                    ret = Math.max(ret, j - i + 1);
                }
            }
        }
        return ret;
    }
    
    // 2. 滑动窗口
    public int lengthOfLongestSubstring2(String s) {
        int head = 0;
        int end = 0;
        HashSet<Character> characterHashSet = new HashSet<>();
        // 用于记录最长子串的长度
        int ret = -1;
        for (end = 0; end < s.length(); end++) {
            while (characterHashSet.contains(s.charAt(end))) {
                // 如果有重复的字符，记录当前子串的长度
                ret = Math.max(ret, end - head);
                // 删除头元素并且移动头指针
                characterHashSet.remove(s.charAt(head));
                head++;
            }
            // 如果遍历到最后一个字符，那么从head开始的最长的子串就是end-head+1
            if (end == s.length() - 1) {
                ret = Math.max(ret, end - head + 1);
            }
            characterHashSet.add(s.charAt(end));
        }
        // 如果从头到尾都没进入while (s.substring(head, end).contains(s.substring(end, end + 1)))
        // 说明ret保持为-1，整个字符串没有重复的字符，返回s.length()
        if (ret == -1)
            return s.length();
        else
            return ret;
    }
}
