package week3;

import javax.smartcardio.ATR;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2024/2/11 10:08
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution5 {
    
    //三数之和——暴力解法
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i == j) {
                    continue;
                }
                int sum = nums[i] + nums[j];
                for (int k = 0; k < nums.length; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    if (sum + nums[k] == 0) {
                        // 返回结果
                        List<Integer> list = Arrays.asList(nums[i], nums[j], nums[k]);
                        Collections.sort(list);
                        if (!ret.contains(list)) {
                            ret.add(list);
                        }
                    }
                }
            }
        }
        return ret;
    }
    
    //三数之和——双指针
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 3){
            return null;
        }
        Arrays.sort(nums);
        // 三数之和为0，那么要么有不同符号的数，要么全为0，
        // 如果排序后的第一个元素都大于0，那么不可能有三数之和为0
        if (nums[0] > 0){
            return res;
        }
        for (int i = 0; i < len; i++) {
            // 如果当前元素大于0，那么后面的元素都大于0，不可能有三数之和为0
            if (nums[i] > 0){
                break;
            }
            // 如果当前元素和前一个元素相同，那么会有重复的结果，所以跳过
            if (i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int head = i + 1;
            int end = len - 1;
            while (head < end){
                int sum = nums[i] + nums[head] + nums[end];
                // 当三数之和为0时，添加到结果集中
                if (sum == 0){
                    res.add(Arrays.asList(nums[i], nums[head], nums[end]));
                    // 跳过重复的元素
                    while (head < end && nums[head] == nums[head + 1]){
                        head++;
                    }
                    // 跳过重复的元素
                    while (head < end && nums[end] == nums[end - 1]){
                        end--;
                    }
                    head++;
                    end--;
                }else if (sum < 0){
                    // 当三数之和小于0时，也就是head+end < - target head指针向右移动
                    head++;
                }else {
                    end--;
                }
            }
            
        }
        return res;
    }
}
