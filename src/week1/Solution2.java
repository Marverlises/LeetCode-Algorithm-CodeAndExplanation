package week1;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/1/18 7:56
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    public static void main(String[] args) {
        //写一个majorityElement的测试用例
        int[] nums = {1};
        int i = majorityElement(nums);
        //写一个majorityElement2的测试用例
        int[] nums2 = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        int i2 = majorityElement2(nums2);
        //rotate1测试用例：nums = [1,2,3,4,5,6,7], k = 3
        int[] nums3 = {1, 2, 3, 4, 5, 6, 7};
        rotate1(nums3, 3);
        //rotate1测试用例：nums = [1,2,3,4,5,6,7], k = 3
        int[] nums4 = {-1, -100, 3, 99};
        rotate2(nums4, 2);
        //rotate2测试用例：nums为12个元素的乱序数组
        int[] nums5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        rotate2(nums5, 8);
        //num6 = {7,1,5,3,6,4}
        int[] nums6 = {7, 1, 5, 3, 6, 4};
        maxProfit2(nums6);
        //num7 = {3,2,1,0,4}测试
        int[] num7 = {3, 2, 1, 0, 4};
        canJump(num7);
        
        
    }
    
    public static int majorityElement(int[] nums) {
        int result = 0;
        HashMap<Integer, Integer> integerHashMap = new HashMap<>();
        if (nums.length == 1) {
            return nums[0];
        }
        for (int num : nums) {
            if (integerHashMap.get(num) == null) {
                integerHashMap.put(num, 1);
            } else {
                result = integerHashMap.get(num) + 1;
                if (result >= nums.length / 2 + 1) {
                    result = num;
                    break;
                }
                integerHashMap.replace(num, result);
            }
            
        }
        return result;
    }
    
    //解法2——最好
    public static int majorityElement2(int[] nums) {
        int x = 0, vote = 0;
        for (int num : nums) {
            if (vote == 0) {
                x = num;
            }
            vote += num == x ? 1 : -1;
        }
        return x;
    }
    
    
    // 轮转数组
    public static void rotate1(int[] nums, int k) {
        if (nums.length <= 1) {
            return;
        }
        int[] copied = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < copied.length; i++) {
            int cur = (i + k) % copied.length;
            nums[cur] = copied[i];
        }
    }
    
    // 轮转数组2,第二种解法：一个一个换
    public static void rotate2(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        int count = gcd(k, n);
        for (int start = 0; start < count; ++start) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % n;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
            } while (start != current);
        }
    }
    
    public static int gcd(int x, int y) {
        return y > 0 ? gcd(y, x % y) : x;
    }
    
    // 轮转数组2,第三种解法：总体翻转+局部翻转
    public static void rotate3(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    
    //买卖股票的最佳时机
    public static int maxProfit(int[] prices) {
        int result = 0;
        int min = prices[0];
        int profit;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
                continue;
            }
            profit = prices[i] - min;
            result = Math.max(result, profit);
        }
        return result;
    }
    
    //买卖股票的最佳时机2
    //我开始也是想着为什么要这么麻烦，明明只要增长就买，但是细想了一下，因为是模拟买股票，而代码中
    ////后一个比前一个大，就买
    //if (prices[i] > temp) {
    //    profit = profit + prices[i] - temp;
    //}
    //也就是你站在第二天的立场上去决定前一天是否买股票这是不太符合实际的，虽然解题没问题
    public static int maxProfit2(int[] prices) {
        int temp = prices[0];
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            //后一个比前一个大，就买
            if (prices[i] > temp) {
                profit = profit + prices[i] - temp;
            }
            temp = prices[i];
        }
        return profit;
    }
    
    //买卖股票的最佳时机2-2——动态规划解决
    public static int maxProfit2_2(int[] prices) {
        int length = prices.length;
        //考虑要想让利益最大，每一天面临的状态：
        /*
        1. 分析状态
        状态1:当前天没股票;     状态2:当前天有股票;
        2. 对每种状态向目标推进
        —— 每一个状态对应一个利益值,因此用一个二维数组，行表示第几天，列表示两种状态
                int[][] status = new int[length][2];
        —— 如果当前天为状态1:要想让利益最大,用前一天两种状态加上今天，计算最大值
        Math.max(前一天没股票,就不需要动:status[i - 1][0],
                 前一天有股票,因为今天的状态是无股票,因此需要卖出:status[i-1][1]+price[i]) (卖出就相当于我在之前的资金基础上增加了今天价目的资金)
        —— 如果当前天为状态2:要想让利益最大,用前一天两种状态加上今天，计算最大值
        Math.max(前一天没股票,今天就要买入:status[i - 1][0]-price[i],
                 前一天有股票,因为今天的状态是有股票,因此不需要动:status[i-1][1])
         */
        int[][] status = new int[length][2];
        //今天要没股票，那就不买，起始资金为0，不买就还是0
        status[0][0] = 0;
        //今天要有股票，那就买，买了就相当于我现在的资金是负数
        status[0][1] = -prices[0];
        //我要达到的目标是在最后一天我的资金数目最大（也就是最大收益）
        for (int i = 1; i < length; i++) {
            //当前天没股票,计算当前最理想的资金
            status[i][0] = Math.max(status[i - 1][0], status[i - 1][1] + prices[i]);
            //当前天有股票,计算当前最理想的资金
            status[i][1] = Math.max(status[i - 1][0] - prices[i], status[i - 1][1]);
        }
        //最后一天如果我还有股票肯定钱没在我自己手上，因为要我要求自己手上的资金最大化，
        // 因此结果肯定在当前天没股票时，所以返回相应结果即可
        return status[length - 1][0];
        
        ////优化内存占用
        //int length = prices.length;
        //int[] status = new int[2];
        ////今天要没股票，那就不买，起始资金为0，不买就还是0
        //status[0] = 0;
        ////今天要有股票，那就买，买了就相当于我现在的资金是负数
        //status[1] = -prices[0];
        ////我要达到的目标是在最后一天我的资金数目最大（也就是最大收益）
        //for (int i = 1; i < length; i++) {
        //    //当前天没股票,计算当前最理想的资金
        //    status[0] = Math.max(status[0], status[1] + prices[i]);
        //    //当前天有股票,计算当前最理想的资金
        //    status[1] = Math.max(status[0] - prices[i], status[1]);
        //}
        ////最后一天如果我还有股票肯定钱没在我自己手上，因为要我要求自己手上的资金最大化，
        //// 因此结果肯定在当前天没股票时，所以返回相应结果即可
        //return status[0];
    }
    
    //跳跃游戏
    
    /**
     * This method determines if it is possible to reach the end of the array by jumping from one element to another.
     * Each element in the array represents the maximum number of steps that can be made forward from that element.
     * The method uses a backward search strategy to check if it's possible to jump over each zero in the array.
     *
     * @param nums an array of non-negative integers where each integer represents the maximum number of steps that
     *             can be made forward from that element
     * @return true if it is possible to reach the end of the array, false otherwise
     */
    public static boolean canJump(int[] nums) {
        //核心思想：不能跳到0处
        //思路：遍历数组找0，找到0开始向前，看前面的数字是否能越过0
        int temp;
        for (int i = 0; i < nums.length; i++) {
            temp = i;
            if (nums[i] == 0 && i != nums.length - 1) {
                while (i >= 0) {
                    if (nums[i] > (temp - i)) {
                        break;
                    }
                    i--;
                }
                if (i == -1) {
                    return false;
                }
            }
            i = temp;
        }
        return true;
    }
    
    
    
}