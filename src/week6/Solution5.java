package week6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2024/2/28 9:01
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution5 {
    //汇总区间
    //1. 思路一
    public List<String> summaryRanges(int[] nums) {
        //定义一个List，存储结果
        List<String> ret = new ArrayList<>();
        //定义一个变量存储区间头部
        int head = 0;
        //遍历nums
        for (int i = 0; i < nums.length; i++) {
            //对于第一个元素，直接赋值给head
            if (i == 0) {
                head = nums[i];
                //对于其他元素，判定是否是连续的
            } else {
                //如果不连续，将区间头部和尾部存入ret
                if (nums[i] != nums[i - 1] + 1) {
                    //如果区间头部和尾部不相等，说明是一个区间
                    if (head != nums[i - 1]) {
                        ret.add(head + "->" + nums[i - 1]);
                        //如果区间头部和尾部相等，说明是一个单独的数字
                    } else {
                        ret.add(String.valueOf(head));
                    }
                    head = nums[i];
                }
            }
            //如果i等于nums.length-1，说明遍历结束，将最后一个区间存入ret
            if (i == nums.length - 1) {
                if (head != nums[i]) {
                    ret.add(head + "->" + nums[i]);
                } else {
                    ret.add(String.valueOf(head));
                }
            }
        }
        return ret;
    }
    
    public int[][] merge(int[][] intervals) {
        //1.对intervals按照每一个区间的头部元素进行从小到大排序,通过内置的排序函数
        //其中o1[0]表示第一个区间的头部元素，o2[0]表示第二个区间的头部元素，o1[0]-o2[0]表示从小到大排序
        //->表示lambda表达式也就是匿名函数
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        //2.定义一个List，存储结果
        List<int[]> ret = new ArrayList<>();
        //3.遍历intervals
        //最后一个区间不需要遍历，因为对比的是当前区间和下一个区间
        for (int i = 0; i < intervals.length; i++) {
            //4.定义两个变量存储区间头部和尾部
            int head = intervals[i][0];
            int end = intervals[i][1];
            //如果i等于intervals.length-1，说明遍历结束，判断最后一个区间和新的区间是否有交集
            // (这是对应上一个区间没有将最后一个区间合并的情况)
            if (i == intervals.length - 1) {
                //将最后一个区间存入ret
                ret.add(new int[]{head, end});
                break;
            }
            //查看当前区间尾部与下一个区间头部的关系
            //如果当前区间尾部大于等于下一个区间头部，说明两个区间有交集——如此循环，直到找到两个区间没有交集的情况
            while (end >= intervals[i + 1][0]) {
                //新的区间的头部就是当前区间头部
                //新的区间的尾部就是当前区间头部和下一个区间尾部的最大值——因为两个区间的尾部不一定是后一个比前一个大
                end = Math.max(end, intervals[i + 1][1]);
                i++;
                //如果i等于intervals.length-1，说明遍历结束
                // (这是对应上一个区间将最后一个区间合并的情况)
                if (i == intervals.length - 1) {
                    break;
                }
            }
            //将新的区间存入ret
            ret.add(new int[]{head, end});
        }
        //5. 最后将ret转换为int[][]返回
        return ret.toArray(new int[0][]);
    }
}
