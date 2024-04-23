package week4;

/**
 * @Author Baiyu
 * @Time 2024/2/18 8:36
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution6 {
    // 旋转图像
    public void rotate(int[][] matrix) {
        //1. 转置矩阵
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        ///================以下二者选一================
        //2. 水平翻转,就是每一行的元素逆序
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix[0].length - 1 - j];
                matrix[i][matrix[0].length - 1 - j] = temp;
            }
        }
        //2. 或者移动每一列的元素
        for (int i = 0; i < matrix.length / 2; i++) {
            for (int j = 0; j < matrix.length; j++) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[j][matrix.length - 1 - i];
                matrix[j][matrix.length - 1 - i]= temp;
            }
        }
    }
    
}
