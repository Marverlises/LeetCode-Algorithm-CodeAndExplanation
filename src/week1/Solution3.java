package week1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * @Author Baiyu
 * @Time 2024/1/20 12:48
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    /**
     * 跳跃游戏2——生成文档
     * <p>
     * This method determines the minimum number of jumps required to reach the end of the array.
     * Each element in the array represents the maximum number of steps that can be made forward from that element.
     * The method uses a backward search strategy to find all the points that can reach the end of the array.
     *
     * @param nums an array of non-negative integers where each integer represents the maximum number of steps that
     *             can be made forward from that element
     * @return the minimum number of jumps required to reach the end of the array
     */
    public static int jump(int[] nums) {
        int result = 1;
        if (nums.length <= 1) {
            return 0;
        }
        //1 从终点向前找到所有能到达当前点的最远的点——不太会
        //for (int i = len; i >= 0; i--) {
        //    if (nums[i] > cur - i) {
        //        continue;
        //    }
        //    result++;
        //    cur = i;
        //}
        
        int max = nums[0] + 0;
        int temp = 0;
        
        //2 从前往后找当前可以跳跃范围内{可以到达最远距离=当前位置+可以跳跃的距离}数作为跳跃节点
        //  如果当前跳跃范围可以到达最后一个点就结束
        //  [2,3,1,1,4]
        for (int i = 0; i < nums.length && max < nums.length - 1; i++) {
            int j = i + 1;
            while (nums[i] > 0) {
                if (nums[j] + j > max) {
                    max = nums[j] + j;
                    temp = j;
                }
                if (max >= nums.length - 1) {
                    break;
                }
                j++;
                nums[i]--;
            }
            result++;
            if (max >= nums.length - 1) {
                break;
            }
            i = temp - 1;
        }
        return result;
    }
    
    //H指数
    //生成文档
    public static int hIndex(int[] citations) {
        //Arrays.sort(citations);
        //int result = 0;
        //for (int i = 0; i < citations.length; i++) {
        //    if (citations[i] >= (citations.length - i)){
        //        result = citations.length - i;
        //        return result;
        //    }
        //}
        //return 0;
        //============================解法2============================
        //int n = citations.length, tot = 0;
        ////数组相当于对应【0，1，2，。。。】次引用次数的文章，最后一个是所有大于文章数量的引用次数文章（必被算入H指数）
        //int[] counter = new int[n + 1];
        ////相当于是排序了
        //for (int i = 0; i < n; i++) {
        //    if (citations[i] >= n) {
        //        counter[n]++;
        //    } else {
        //        counter[citations[i]]++;
        //    }
        //}
        ////从后往前，如果文章数量加起来大于等于当前引用次数，即为答案
        //for (int i = n; i >= 0; i--) {
        //    tot += counter[i];
        //    if (tot >= i) {
        //        return i;
        //    }
        //}
        //return 0;
        //============================解法3============================
        //对于H指数的二分，每一次H指数二分都会遍历所有文章看其引用量（H指数肯定不会超过总发文量）
        int left = 0, right = citations.length;
        int mid = 0, cnt = 0;
        while (left < right) {
            // +1 防止死循环
            mid = (left + right + 1) >> 1;
            cnt = 0;
            for (int i = 0; i < citations.length; i++) {
                if (citations[i] >= mid) {
                    cnt++;
                }
            }
            if (cnt >= mid) {
                // 要找的答案在 [mid,right] 区间内，这里的区间是指的H指数的区间
                left = mid;
            } else {
                // 要找的答案在 [0,mid) 区间内
                right = mid - 1;
            }
        }
        return left;
    }
    
    
}
