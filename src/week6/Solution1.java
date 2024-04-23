package week6;

import java.util.*;

/**
 * @Author Baiyu
 * @Time 2024/2/23 7:51
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution1 {
    // 两数之和
    // 思路一
    public int[] twoSum1(int[] nums, int target) {
        //1. 外层遍历数组 `nums`的每一个元素A
        for (int i = 0; i < nums.length; i++) {
            //2. 内层遍历数组 `nums`的每一个元素B
            for (int j = i + 1; j < nums.length; j++) {
                //3. 如果A+B=target，返回A和B的下标
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        //4. 如果没有找到满足条件的A和B，返回null
        return null;
    }
    
    // 思路二
    
    /**
     * 下面的解法会报错，因为nums中可能有重复元素，所以不能使用这种解法
     */
    public int[] twoSum2(int[] nums, int target) {
        //1. 初始化一个hashMap,用来存储数组 `nums`的元素和下标
        HashMap<Integer, Integer> numIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numIndexMap.put(nums[i], i);
        }
        //2. 对数组 `nums`进行排序
        Arrays.sort(nums);
        //3. 初始化两个指针，分别指向数组 `nums`的头和尾
        int left = 0, right = nums.length - 1;
        //4. 如果两个指针指向的元素之和不等于target
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                //如果小于target，左指针右移
                left++;
            } else if (sum > target) {
                //如果大于target，右指针左移
                right--;
            } else {
                //如果等于target，返回两个元素的下标
                return new int[]{numIndexMap.get(nums[left]), numIndexMap.get(nums[right])};
            }
        }
        //5. 如果没有找到满足条件的A和B，返回null
        return null;
    }
    
    // 思路二——修改
    
    /**
     * 因为上面求解当出现重复元素比如：
     * nums = [3,3]
     * target = 6
     * 那么hashMap中只能存储一个3的下标，所以我们需要修改一下
     */
    public int[] twoSum2Modify(int[] nums, int target) {
        //1. 初始化一个hashMap,用来存储数组 `nums`的元素和下标
        Map<Integer, List<Integer>> numIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numIndexMap.containsKey(nums[i])) {
                numIndexMap.get(nums[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                numIndexMap.put(nums[i], list);
            }
        }
        //2. 对数组 `nums`进行排序
        Arrays.sort(nums);
        //3. 初始化两个指针，分别指向数组 `nums`的头和尾
        int left = 0, right = nums.length - 1;
        //4. 如果两个指针指向的元素之和不等于target
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target) {
                //如果小于target，左指针右移
                left++;
            } else if (sum > target) {
                //如果大于target，右指针左移
                right--;
            } else {
                //如果等于target，返回两个元素的下标
                //如果两个元素相等，那么返回两个元素的下标
                if (nums[left] == nums[right]) {
                    return new int[]{numIndexMap.get(nums[left]).get(0), numIndexMap.get(nums[right]).get(1)};
                }
                //如果两个元素不相等
                return new int[]{numIndexMap.get(nums[left]).get(0), numIndexMap.get(nums[right]).get(0)};
            }
        }
        //5. 如果没有找到满足条件的A和B，返回null
        return null;
    }
    
    //思路三
    public int[] twoSum3(int[] nums, int target) {
        //1. 初始化一个hashMap,用来存储数组 `nums`的元素和下标
        Map<Integer, List<Integer>> numIndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (numIndexMap.containsKey(nums[i])) {
                numIndexMap.get(nums[i]).add(i);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                numIndexMap.put(nums[i], list);
            }
        }
        //2. 遍历数组 `nums`的每一个元素
        for (int i = 0; i < nums.length; i++) {
            //3. 如果hashMap中包含target-nums[i]，返回target-nums[i]和nums[i]的下标
            if (numIndexMap.containsKey(target - nums[i])) {
                //如果两个元素相等，那么返回两个元素的下标
                if (target - nums[i] == nums[i]) {
                    if (numIndexMap.get(nums[i]).size() > 1) {
                        return new int[]{numIndexMap.get(nums[i]).get(0), numIndexMap.get(nums[i]).get(1)};
                    }
                } else {
                    //如果两个元素不相等
                    return new int[]{numIndexMap.get(target - nums[i]).get(0), numIndexMap.get(nums[i]).get(0)};
                }
            }
        }
        return null;
    }
    
    // 思路四
    public int[] twoSum4(int[] nums, int target) {
        //1. 初始化一个hashMap,用来存储数组 `nums`的元素和下标
        HashMap<Integer, Integer> numIndexMap = new HashMap<>();
        //2. 遍历数组 `nums`的每一个元素
        for (int i = 0; i < nums.length; i++) {
            //3. 如果hashMap中包含target-nums[i]，返回target-nums[i]和nums[i]的下标
            if (numIndexMap.containsKey(target - nums[i])) {
                return new int[]{numIndexMap.get(target - nums[i]), i};
            }
            //4. 将nums[i]和i存入hashMap
            numIndexMap.put(nums[i], i);
        }
        //5. 如果没有找到满足条件的A和B，返回null
        return null;
    }
}
