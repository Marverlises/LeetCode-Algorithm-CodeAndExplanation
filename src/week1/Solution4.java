package week1;

/**
 * @Author Baiyu
 * @Time 2024/1/21 8:11
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {
    /**
     * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
     * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
     * 请 不要使用除法，且在 O(n) 时间复杂度内完成此题。
     */
    public int[] productExceptSelf(int[] nums) {
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        int temp = 1;
        //计算前缀之积
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                prefix[i] = 1;
            } else {
                prefix[i] = temp;
            }
            temp = temp * nums[i];
        }
        temp = 1;
        //计后缀之积
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                suffix[i] = 1;
            } else {
                suffix[i] = temp;
            }
            temp = temp * nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = prefix[i] * suffix[i];
        }
        return nums;
    }
    
    //空间复杂度O(1)
    public int[] productExceptSelf2(int[] nums) {
        int temp = 1;
        int[] answer = new int[nums.length];
        //计算前缀之积
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                temp = 1;
            }
            answer[i] = temp;
            temp = temp * nums[i];
        }
        temp = 1;
        //计后缀之积
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                temp = 1;
            }
            answer[i] = answer[i] * temp;
            temp = temp * nums[i];
        }
        return answer;
    }
    
    //加油站——暴力解法
    public int canCompleteCircuit(int[] gas, int[] cost) {
        //肯定是从正数处开始,先计算每一站的汽油量（除去cost）
        int retFlag = 0;
        for (int i = 0; i < gas.length; i++) {
            gas[i] = gas[i] - cost[i];
            retFlag += gas[i];
        }
        if (retFlag < 0) {
            return -1;
        }
        int steps = 0;
        //从每一个为正数的地方开始，尝试向后走，不行就返回从下一个开始
        for (int i = 0; i < gas.length; i++) {
            retFlag = 0;
            steps = 0;
            if (gas[i] >= 0) {
                int j = i;
                while (retFlag >= 0 && steps < gas.length) {
                    retFlag = gas[j % gas.length] + retFlag;
                    steps++;
                    j++;
                }
            }
            //满足情况
            if (steps >= gas.length) {
                return i;
            }
        }
        return -1;
    }
    //加油站——二分解法
    
    /**
     * 失败测试用例
     * int[] gas = {6,1,4,3,5};
     * int[] cost = {3,8,2,4,2};
     */
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        //肯定是从正数处开始,先计算每一站的汽油量（除去cost）
        int retFlag = 0;
        for (int i = 0; i < gas.length; i++) {
            gas[i] = gas[i] - cost[i];
            retFlag += gas[i];
        }
        if (retFlag < 0) {
            return -1;
        }
        // 开始位置肯定在较大的一边（注意最后一个数要减去另一边所有的值）
        int left = 0;
        int right = gas.length;
        while (left < right) {
            int mid = (right + left) >> 1;
            int leftsum = 0;
            int rightsum = 0;
            for (int i = left; i < right; i++) {
                if (i <= mid) {
                    leftsum += gas[i];
                } else {
                    rightsum += gas[i];
                }
            }
            if (leftsum >= rightsum) {
                right = mid;
                gas[mid] = gas[mid] + rightsum;
            } else {
                left = mid + 1;
                gas[right - 1] = gas[right - 1] + leftsum;
            }
        }
        return left;
    }
    
    //二分法
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            int mid = (right + left) >> 1;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
    
    //加油站3
    public int canCompleteCircuit3(int[] gas, int[] cost) {
        // 从前向后走，如果卡在某一点——剩余油量<=0，则说明前面的路都作废，
        // 因为起点到任意一点的油量都>=0，如果以这个任意点开始，我不要前面>=0的油量
        // 一定会卡在这个点，因为我原先从起点开始相当于从这个任意点开始有前面剩余油量的加持还会卡在那个点，
        // 那我如果从这个点开始，我不要前面的油量，一定会卡在这个点
        int flag = 0;
        for (int i = 0; i < gas.length; ) {
            flag = 1;
            int count = 0;
            while (count < gas.length && flag >= 0) {
                if (count == 0){
                    flag =gas[i % gas.length] - cost[i % gas.length];
                    
                }else{
                    flag = flag + gas[i % gas.length] - cost[i % gas.length];
                }
                i++;
                count++;
            }
            if (count >= gas.length && flag >= 0) {
                return i % gas.length;
            }
        }
        return -1;
    }
}

/**
 * question 1
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
//import java.util.List;
//import java.util.Map;
//import java.util.Random;
//import java.util.ArrayList;
//import java.util.HashMap;
//class RandomizedSet {
//    List<Integer> nums;
//    Map<Integer, Integer> indices;
//    Random random;
//
//    public RandomizedSet() {
//        nums = new ArrayList<Integer>();
//        indices = new HashMap<Integer, Integer>();
//        random = new Random();
//    }
//
//    public boolean insert(int val) {
//        if (indices.containsKey(val)) {
//            return false;
//        }
//        int index = nums.size();
//        nums.add(val);
//        indices.put(val, index);
//        return true;
//    }
//
//    public boolean remove(int val) {
//        if (!indices.containsKey(val)) {
//            return false;
//        }
//        int index = indices.get(val);
//        int last = nums.get(nums.size() - 1);
//        nums.set(index, last);
//        indices.put(last, index);
//        nums.remove(nums.size() - 1);
//        indices.remove(val);
//        return true;
//    }
//
//    public int getRandom() {
//        int randomIndex = random.nextInt(nums.size());
//        return nums.get(randomIndex);
//    }
//}

