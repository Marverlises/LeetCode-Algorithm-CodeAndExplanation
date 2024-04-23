package week6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author Baiyu
 * @Time 2024/2/26 10:27
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {
    //最长连续序列
    public int longestConsecutive_fault(int[] nums) {
        //1. 初始化一个HashSet
        HashSet<Integer> numSet = new HashSet<>();
        if (nums.length == 1) {
            return 1;
        }
        //2. 初始化一个序列的头尾——也就是最大最小值
        int max = nums[0];
        int min = nums[0];
        //3. 遍历nums
        for (int i = 0; i < nums.length; i++) {
            //4. 判定当前数与某个走过的数字之差是否等于1
            //当之前走过的数字中有nums[i]-1，说明nums[i]是一个序列的尾部
            if (numSet.contains(nums[i] - 1)) {
                max = nums[i];
            }
            //当之前走过的数字中有nums[i]+1，说明nums[i]是一个序列的头部
            if (numSet.contains(nums[i] + 1)) {
                min = nums[i];
            }
            //5. 将nums中的元素存入numSet
            numSet.add(nums[i]);
        }
        //6. 返回最大最小值之差+1
        return max - min + 1;
    }
    
    //优化
    public int longestConsecutive_optimized(int[] nums) {
        //定义一个hashMap，用来存储走过的数字
        HashMap<Integer, Integer> integerHashMap = new HashMap<>();
        if (nums.length == 0) {
            return 0;
        }
        //定义一个变量存储最大值
        int ret = 1;
        //遍历整个nums数组，判定当前数与某个走过的数字绝对值之差是否等于1
        for (int i = 0; i < nums.length; i++) {
            //如果integerHashMap中包含nums[i]，说明nums[i]已经走过了
            if (integerHashMap.containsKey(nums[i])) {
                continue;
            }
            //如果integerHashMap中包含nums[i]-1，并且包含nums[i]+1，说明nums[i]是一个序列的中间部分
            if (integerHashMap.containsKey(nums[i] - 1) && integerHashMap.containsKey(nums[i] + 1)) {
                //更新序列头尾数字对应长度
                //获取前一个数字对应长度
                Integer preLong = integerHashMap.get(nums[i] - 1);
                //获取后一个数字对应长度
                Integer nextLong = integerHashMap.get(nums[i] + 1);
                //更新头尾
                //头部数字对应长度=头部数字对应长度+尾部数字对应长度+1
                integerHashMap.put(nums[i] - preLong, preLong + nextLong + 1);
                //尾部数字对应长度=头部数字对应长度+尾部数字对应长度+1
                integerHashMap.put(nums[i] + nextLong, preLong + nextLong + 1);
                //将当前数字放入integerHashMap
                integerHashMap.put(nums[i], 1);
                //更新ret
                ret = Math.max(ret, preLong + nextLong + 1);
            }
            //如果integerHashMap中仅包含nums[i]+1，说明nums[i]是一个序列的头部
            else if (integerHashMap.containsKey(nums[i] + 1)) {
                //获取nums[i]+1对应长度
                Integer nextLong = integerHashMap.get(nums[i] + 1);
                //获得新的长度
                int curVal = integerHashMap.get(nums[i] + 1) + 1;
                //更新头尾
                //头部数字（也就是当前数字）对应长度=原始头部数字对应长度+1
                integerHashMap.put(nums[i], curVal);
                //尾部数字对应长度=原始头部数字对应长度+1
                integerHashMap.put(nums[i] + nextLong, curVal);
                //更新ret
                ret = Math.max(ret, curVal);
            }
            //如果integerHashMap中仅包含nums[i]-1，说明nums[i]是一个序列的尾部
            else if (integerHashMap.containsKey(nums[i] - 1)) {
                //获取nums[i]-1对应长度
                Integer preLong = integerHashMap.get(nums[i] - 1);
                //获得新的长度
                int curVal = integerHashMap.get(nums[i] - 1) + 1;
                //更新头尾
                //头部数字对应长度=原始尾部数字对应长度+1
                integerHashMap.put(nums[i] - preLong, curVal);
                //尾部数字（也就是当前数字）对应长度=原始尾部数字对应长度+1
                integerHashMap.put(nums[i], curVal);
                //更新ret
                ret = Math.max(ret, curVal);
            }
            //如果integerHashMap中不包含nums[i]，将nums[i]存入integerHashMap
            // if (!integerHashMap.containsKey(nums[i]))
            else {
                integerHashMap.put(nums[i], 1);
            }
        }
        return ret;
    }
    
    //思路二
    public int longestConsecutive2(int[] nums) {
        //1. 初始化一个HashSet，用来存储nums中的元素，方便查找，时间复杂度为O(1)
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }
        //2. 初始化一个变量，用来存储最长序列长度
        int longestStreak = 0;
        //3. 遍历num_set
        for (int num : num_set) {
            //4. 如果num-1不在num_set中，说明num是一个序列的头部
            if (!num_set.contains(num - 1)) {
                //5. 初始化一个变量，用来存储当前数字
                int currentNum = num;
                //6. 初始化一个变量，用来存储当前序列长度
                int currentStreak = 1;
                //7. 不断把下一个数字加入序列，直到找不到下一个数字
                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }
                //8. 更新最长序列长度
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        //9. 返回最长序列长度
        return longestStreak;
    }
    
}

