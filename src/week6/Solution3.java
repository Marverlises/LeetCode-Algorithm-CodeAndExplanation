package week6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2024/2/25 7:55
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    //存在重复元素 II
    //1. 思路一——暴力求解
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j && nums[i] == nums[j] && Math.abs(i - j) <= k) {
                    return true;
                }
            }
        }
        return false;
    }
    
    //2. 思路二——哈希表
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        HashMap<Integer, List<Integer>> intIndex = new HashMap<>();
        //初始化intIndex
        for (int i = 0; i < nums.length; i++) {
            //如果intIndex中包含nums[i]，将i存入对应的List中
            if (intIndex.containsKey(nums[i])) {
                List<Integer> indexList = intIndex.get(nums[i]);
                indexList.add(i);
                intIndex.put(nums[i], indexList);
                //如果intIndex中不包含nums[i]，新建一个List，将i存入List中
            } else {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                intIndex.put(nums[i], indexList);
            }
        }
        //遍历intIndex
        for (List<Integer> indexList : intIndex.values()) {
            //如果indexList的长度大于1，说明有重复元素
            if (indexList.size() > 1) {
                //将indexList中元素排序
                indexList.sort(Integer::compareTo);
                //两两比较，如果相邻元素差值小于等于k，返回true
                for (int i = 0; i < indexList.size() - 1; i++) {
                    if (indexList.get(i + 1) - indexList.get(i) <= k) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    //优化
    public boolean containsNearbyDuplicate2_modify(int[] nums, int k) {
        //初始化一个hashMap,key为不同元素，value为元素的最近一次遍历的下标
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        //初始化一个最小值
        int min = Integer.MAX_VALUE;
        //遍历nums
        for (int i = 0; i < nums.length; i++) {
            //如果indexMap中包含nums[i]，计算i和indexMap.get(nums[i])的差值
            if (indexMap.containsKey(nums[i])) {
                //更新min
                min = Math.min(min, i - indexMap.get(nums[i]));
            }
            //将i存入indexMap
            indexMap.put(nums[i], i);
            //如果min小于等于k，返回true
            if (min <= k) {
                return true;
            }
        }
        return false;
    }
    
    //3. 思路三——滑动窗口
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        //初始化一个hashSet,用来存储 框中的元素
        HashSet<Integer> hashSet = new HashSet<>();
        //遍历nums
        for (int i = 0; i < nums.length; i++) {
            //保持窗口大小为k或者更小（更小是因为在初始化窗口的时候，窗口大小为0）
            if (i > k) {
                //移除窗口最左边的元素
                hashSet.remove(nums[i - k - 1]);
            }
            //如果hashSet中包含nums[i]，返回true
            if (hashSet.contains(nums[i])) {
                return true;
            }
            //hashSet中不包含nums[i]，将nums[i]存入hashSet——相当于更新框中的元素
            hashSet.add(nums[i]);
        }
        //遍历结束，返回false
        return false;
    }
}
