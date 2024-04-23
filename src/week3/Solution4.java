package week3;

/**
 * @Author Baiyu
 * @Time 2024/2/9 8:01
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {
    //  盛最多水的容器
    // 方法1：暴力求解
    public int maxArea(int[] height) {
        int ret = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = 0; j < height.length; j++) {
                // 计算面积
                // 长：j - i
                // 宽：Math.min(height[i], height[j])
                // 面积：(j - i) * Math.min(height[i], height[j])
                if (i != j) {
                    ret = Math.max(ret, (j - i) * Math.min(height[i], height[j]));
                }
            }
        }
        return ret;
    }
    
    // 方法1-1：暴力求解——优化
    public int maxArea1_1(int[] height) {
        int ret = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                // 计算面积
                // 长：j - i
                // 宽：Math.min(height[i], height[j])
                // 面积：(j - i) * Math.min(height[i], height[j])
                ret = Math.max(ret, (j - i) * Math.min(height[i], height[j]));
            }
        }
        return ret;
    }
    
    // 方法2：双指针
    public int maxArea2(int[] height) {
        int ret = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            ret = Math.max(ret, (right - left) * Math.min(height[left], height[right]));
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return ret;
    }
}
