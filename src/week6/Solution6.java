package week6;

import java.util.ArrayList;

/**
 * @Author Baiyu
 * @Time 2024/2/29 10:01
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution6 {
    //插入区间
    
    public int[][] insert2(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        
        //1. 初始化一个Array，存储intervals，方便后续增删
        ArrayList<Integer[]> copyIntervals = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            copyIntervals.add(new Integer[]{intervals[i][0], intervals[i][1]});
        }
        //初始化一个数组，存储需要合并的区间的下标
        ArrayList<Integer> mergeIndex = new ArrayList<>();
        //2. 遍历copyIntervals
        for (int i = 0; i < copyIntervals.size(); i++) {
            //3. 判定newInterval和copyIntervals[i]的关系
            //4. 确定区间头部——找到第一个大于等于newInterval[0]的区间
            if (newInterval[0] >= copyIntervals.get(i)[0] && i != copyIntervals.size() - 1) {
                continue;
            }
            //5. 根据当前区间B的前一个区间C的结尾位置C[1]再和A[0]比较，如果C[1] >= A[0]，说明A和C有交集
            //就可以确定区间头部为C[0]
            if (newInterval[0] <= copyIntervals.get(i - 1)[1]) {
                newInterval[0] = copyIntervals.get(i - 1)[0];
            }
            //6. 确定区间尾部——找到第一个大于newInterval[1]的区间
            i--;//因为还要和当前区间的上一个区间尾部比较，所以i--
            while (i < copyIntervals.size() && newInterval[1] >= copyIntervals.get(i)[0]) {
                mergeIndex.add(i);
                i++;
            }
            //7. 确定结尾
            //如果已经遍历结束，说明newInterval[1]大于所有区间的头部，那么结尾就是newInterval[1]和最后一个区间的尾部的最大值
            if (i == copyIntervals.size()) {
                newInterval[1] = Math.max(newInterval[1], copyIntervals.get(i - 1)[1]);
            }
            //如果没有遍历结束，说明newInterval[1]小于等于某个区间的头部，那么结尾就是newInterval[1]和i--区间的尾部的最大值，
            //此时i要先回退一步，因为while循环中i++
            else {
                i--;//回退一步
                newInterval[1] = Math.max(newInterval[1], copyIntervals.get(i)[1]);
            }
            break;
        }
        
        //8. 将copyIntervals转换为int[][]，在合并mergeIndex中的区间的过程中将newInterval存入copyIntervals
        int[][] ret = new int[copyIntervals.size() - mergeIndex.size() + 1][2];
        int index = 0;
        boolean flag = false;
        for (int i = 0; i < copyIntervals.size(); i++) {
            if (mergeIndex.contains(i)) {
                continue;
            }
            //将newInterval存入copyIntervals
            if (newInterval[1] < copyIntervals.get(i)[0] && !flag) {
                ret[index][0] = newInterval[0];
                ret[index][1] = newInterval[1];
                flag = true;
                index++;
            }
            ret[index][0] = copyIntervals.get(i)[0];
            ret[index][1] = copyIntervals.get(i)[1];
            index++;
        }
        return ret;
    }
    
    public int[][] insert(int[][] intervals, int[] newInterval) {
        //1. 创建一个新的列表（或数组），用于存放最终的区间列表。
        ArrayList<int[]> result = new ArrayList<>();
        //2. 遍历区间列表
        for (int i = 0; i < intervals.length; i++) {
            //3. 如果当前区间的结束位置小于新区间的开始位置，说明当前区间在新区间的左侧，直接将当前区间加入到结果列表中。
            if (intervals[i][1] < newInterval[0]) {
                result.add(intervals[i]);
            }
            //4. 如果当前区间的开始位置大于新区间的结束位置，将新区间添加到新列表中，并将剩下的原始区间全部添加到新列表中，然后跳出循环。
            else if (intervals[i][0] > newInterval[1]) {
                //先把新区间加入到结果列表中
                result.add(newInterval);
                for (int j = i; j < intervals.length; j++) {
                    result.add(intervals[j]);
                }
                return result.toArray(new int[result.size()][]);
            }
            //5. 如果当前区间和新区间有重叠，将新区间的开始位置更新为当前区间和新区间的开始位置的最小值，将新区间的结束位置更新为当前区间和新区间的结束位置的最大值。
            else {
                //更新新区间的开始位置，这个newInterval就会在后续走到
                //满足intervals[i][0] > newInterval[1]的情况时被加入到结果列表中
                newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
                newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            }
        }
        //6. 如果遍历结束，还没返回，说明新区间是最后一个区间，将新区间加入到结果列表中。
        result.add(newInterval);
        return result.toArray(new int[result.size()][]);
    }
}
