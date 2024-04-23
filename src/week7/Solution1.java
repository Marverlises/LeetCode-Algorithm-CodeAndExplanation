package week7;

import java.util.Arrays;

/**
 * @Author Baiyu
 * @Time 2024/3/1 9:57
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution1 {
    //用最少数量的箭引爆气球
    //思路一
    public int findMinArrowShots(int[][] points) {
        //1.初始化两个变量，一个用来统计删除区间的位置，一个用来统计结果
        int maxNum = 0;
        int ret = 0;
        //2.对points按照每一个区间的头部元素进行从小到大排序
        Arrays.sort(points, (o1, o2) -> o1[0] - o2[0]);
        //3.遍历points
        for (int i = 0; i < points.length; i++) {
            //用来记录当前区间能够击破的最大气球数
            maxNum = 0;
            //用来记录当前区间能够击破的最大气球数的位置，也就是下一次的起始位置（因为前面的气球都爆了）
            int position = i;
            //临时变量，用来记录当前位置，因为在查看每一个区间的时候，i会发生变化，所以需要一个临时变量来记录当前位置
            int temp = i;
            //从最小的区间开始，查看每一个数字射出箭能够击破的最大气球数以及击破气球的位置
            for (int j = points[i][0]; j <= points[i][1]; j++) {
                int balloonNum = 0;
                //如果当前气球的位置大于等于下一个区间的头部且小于等于下一个区间的尾部，说明当前气球可以被击破
                while (i < points.length && j >= points[i][0] && j <= points[i][1]) {
                    i++;
                    balloonNum++;
                }
                //如果当前位置能够击破更多的气球，更新maxNum和position
                if (balloonNum > maxNum) {
                    //更新maxNum
                    maxNum = balloonNum;
                    //相当于记录击破到第几个气球
                    position = i;
                }
                //恢复i的值
                i = temp;
            }
            //相当于发出了一只箭，所以ret++
            ret++;
            //将i更新到已经被击破气球的后一个位置(因为i还要++，所以这里是position-1)
            i = position - 1;
        }
        return ret;
    }
    
    //思路二
    public int findMinArrowShots2(int[][] points) {
        //1. 对气球区间进行升序排序
        Arrays.sort(points, (o1, o2) ->  Integer.compare(o1[0], o2[0]));
        //对于[[-2147483646,-2147483645],[2147483646,2147483647]]这种区间，
        //进行排序后，会出现一个问题，就是第一个区间的右端点是-2147483645，第二个区间的左端点是2147483646，
        //这两个区间是没有交集的，但是排序后会出现一个问题，就是第一个区间的右端点小于第二个区间的左端点，所以这里需要一个变量来记录最大的右端点
        //2. 初始化变量，用来记录结果
        int ret = 0;
        //3. 遍历气球区间
        for (int i = 0; i < points.length; i++) {
            int maxRight = points[i][1];
            //查看当前区间的下一个区间的开始位置是否小于等于当前区间的结束位置
            while (i < points.length && points[i][0] <= maxRight) {
                //如果小于等于，说明两个区间有交集，更新当前区间的结束位置为两个区间的结束位置的最小值
                maxRight = Math.min(points[i][1], maxRight);
                //i++
                i++;
            }
            //如果走到某一个区间发现不满足上述条件，那么直接射出一只箭，然后遍历剩下的区间
            ret++;
            //因为在while循环中多算了一次当前区间，所以这里需要i--
            i--;
        }
        return ret;
    }
    
    //思路三
    public int findMinArrowShots3(int[][] points) {
        //1. 对气球区间进行升序排序（按照右边界）
        Arrays.sort(points, (o1, o2) ->  Integer.compare(o1[1], o2[1]));
        //2. 初始化变量，用来记录结果
        int ret = 0;
        //3. 遍历气球区间
        for (int i = 0; i < points.length; i++) {
            int maxRight = points[i][1];
            //查看当前区间的下一个区间的开始位置是否小于等于当前区间的结束位置
            while (i < points.length && points[i][0] <= maxRight) {
                i++;
            }
            //如果走到某一个区间发现不满足上述条件，那么直接射出一只箭，然后遍历剩下的区间
            ret++;
            //因为在while循环中多算了一次当前区间，所以这里需要i--
            i--;
        }
        return ret;
    }
}
