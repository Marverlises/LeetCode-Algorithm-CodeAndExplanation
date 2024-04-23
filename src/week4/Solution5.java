package week4;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2024/2/17 8:20
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution5 {
    // 螺旋矩阵
    public List<Integer> spiralOrder(int[][] matrix) {
        //1. 初始化
        // 右下角边界
        int m = matrix.length - 1;
        int n = matrix[0].length - 1;
        // 左上角边界
        int i = 0;
        int j = 0;
        // 保存结果
        List<Integer> ret = new ArrayList<>();
        //2. while循环,每次循环都是一圈,停止条件为边界相等
        while (i <= m && j <= n) {
            // 从左到右
            for (int k = j; k <= n; k++) {
                ret.add(matrix[i][k]);
            }
            // 收缩边界
            i++;
            // 从上到下
            for (int k = i; k <= m; k++) {
                ret.add(matrix[k][n]);
            }
            // 收缩边界
            n--;
            // 从右到左
            for (int k = n; k >= j && i <= m; k--) {
                ret.add(matrix[m][k]);
            }
            // 收缩边界
            m--;
            // 从下到上
            for (int k = m; k >= i && j <= n; k--) {
                ret.add(matrix[k][j]);
            }
            // 收缩边界
            j++;
        }
        return ret;
    }
}
