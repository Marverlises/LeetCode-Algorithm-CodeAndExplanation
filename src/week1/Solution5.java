package week1;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/1/22 8:36
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution5 {
    //Candy——从左到右先寻找升序序列，再从右到左寻找升序序列，取两者最大值
    public int candy(int[] ratings) {
        int n = ratings.length;
        int[] left = new int[n];
        for (int i = 0; i < n; i++) {
            if (i > 0 && ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        
        int right = 0, ret = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1 && ratings[i] > ratings[i + 1]) {
                right++;
            } else {
                right = 1;
            }
            ret += Math.max(left[i], right);
        }
        return ret;
    }
    
    //Candy2——从左到右遍历，若当前元素大于前一个元素，则当前元素的糖果数为前一个元素的糖果数加一，否则为一，
    // 如果为降序序列，这个降序每走一步，就把前面的糖果数加一，直到遇到升序序列为止
    public int candy2(int[] ratings) {
        int n = ratings.length;
        int ret = 1;
        int inc = 1, dec = 0, pre = 1;
        for (int i = 1; i < n; i++) {
            if (ratings[i] >= ratings[i - 1]) {
                dec = 0;
                pre = ratings[i] == ratings[i - 1] ? 1 : pre + 1;
                ret += pre;
                inc = pre;
            } else {
                dec++;
                if (dec == inc) {
                    dec++;
                }
                ret += dec;
                pre = 1;
            }
        }
        return ret;
    }
    
    //届雨水：给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
    public int trap(int[] height) {
        //思路：两两相同高度柱子之间就可以放水，其中一个高一点也行——降序和升序之间可以储水
        int len = height.length;
        // 表示左右最高柱子的索引
        int left = 0, right = 0;
        boolean pairFlag = false;
        int sum = 0;
        int ret = 0;
        for (int i = 1; i < len; i++) {
            ////找到左边最高的
            //if (height[i] > height[left] && !pairFlag) {
            //    left = i;
            //    continue;
            //}
            sum = 0;
            //下降序列
            while (height[i - 1] >= height[i]) {
                sum += height[i];
                i++;
                if (i == len){
                    break;
                }
            }
            if (i == len){
                break;
            }
            while (height[i - 1] <= height[i]) {
                sum += height[i];
                i++;
                if (i == len){
                    break;
                }
                pairFlag = true;
            }
            if (i == len){
                break;
            }
            //找到右边最高的
            if (pairFlag){
                i--;
            }
            right = i;
            //计算两个柱子之间的储水量
            if (right > left && height[left] * height[right] != 0) {
                ret += Math.min(height[left], height[right]) * (right - left - 1) - sum + height[right];
            }
            //更新左边最高柱子的索引
            left = right;
            //更新储水量
            //pairFlag = false;
            
        }
        return ret;
    }
}
