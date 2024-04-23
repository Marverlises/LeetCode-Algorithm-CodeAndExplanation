package week3;

/**
 * @Author Baiyu
 * @Time 2024/2/7 7:52
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    //两数之和 II - 输入有序数组
    public int[] twoSum1(int[] numbers, int target) {
        int length = numbers.length;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i + 1, j + 1};
                 }
            }
        }
        return null;
    }
    
    //双指针
    public int[] twoSum2(int[] numbers, int target) {
        int length = numbers.length;
        int left = 0;
        int right = length - 1;
        while (left < right) {
            if (numbers[left] + numbers[right] == target) {
                return new int[]{left + 1, right + 1};
            } else if (numbers[left] + numbers[right] < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }
}
