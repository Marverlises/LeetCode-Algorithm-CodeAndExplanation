package week5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author Baiyu
 * @Time 2024/2/19 8:16
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution1 {
    
    // 矩阵置零
    //1. 暴力求解
    public void setZeroes(int[][] matrix) {
        //复制一个matrix矩阵
        int[][] copy = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            System.arraycopy(matrix[i], 0, copy[i], 0, matrix[0].length);
        }
        //遍历copy矩阵的每一个元素
        for (int i = 0; i < copy.length; i++) {
            for (int j = 0; j < copy[0].length; j++) {
                //如果copy矩阵的元素为0
                if (copy[i][j] == 0) {
                    //将matrix矩阵的第i行和第j列的元素全部置为0
                    for (int k = 0; k < matrix.length; k++) {
                        matrix[k][j] = 0;
                    }
                    for (int k = 0; k < matrix[0].length; k++) {
                        matrix[i][k] = 0;
                    }
                }
            }
        }
    }
    
    //2. 优化
    public void setZeroes2(int[][] matrix) {
        //1. 创建两个数组，分别记录哪些行和哪些列需要置零
        boolean[] row = new boolean[matrix.length];
        boolean[] col = new boolean[matrix[0].length];
        //这种记录方式的好处是，不需要额外的空间，只需要两个数组即可，它的原理是，如果matrix[i][j] == 0，那么row[i]和col[j]都会被置为true
        //2. 遍历matrix矩阵的每一个元素
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                //如果matrix[i][j] == 0
                if (matrix[i][j] == 0) {
                    //将row[i]和col[j]置为true
                    row[i] = true;
                    col[j] = true;
                }
            }
        }
        //3. 遍历row数组，如果row[i] == true，那么将matrix[i]的所有元素置为0
        for (int i = 0; i < row.length; i++) {
            if (row[i]) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }
        //4. 遍历col数组，如果col[j] == true，那么将matrix的第j列的所有元素置为0
        for (int j = 0; j < col.length; j++) {
            if (col[j]) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
    
    //3. 优化2
    public void setZeroes3(int[][] matrix) {
        //1. 初始化一个数组，用来记录需要置为0的列
        ArrayList<Integer> column = new ArrayList<>();
        //设置一个标志位,判断是否需要将该行的元素置为0
        boolean flag = false;
        //2. 遍历matrix矩阵的每一个元素
        for (int i = 0; i < matrix.length; i++) {
            flag = false;
            //根据数组的列将对应位置赋值为0
            for (int j = 0; j < matrix[0].length; j++) {
                //如果matrix[i][j] == 0而且column数组中不包含j
                if (matrix[i][j] == 0) {
                    if (!column.contains(j)) {
                        column.add(j);
                    }
                    flag = true;
                }
            }
            //如果flag为true，那么将matrix[i]的所有元素置为0
            if (flag) {
                Arrays.fill(matrix[i], 0);
            }
        }
        //3. 遍历column数组，将matrix的第j列的所有元素置为0
        for (Integer integer : column) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][integer] = 0;
            }
        }
    }
    
    //4. 优化3
    public void setZeroes4(int[][] matrix) {
        //1. 初始化两个标志位，用来记录第一行和第一列是否需要置为0
        boolean rowFlag = false;
        boolean colFlag = false;
        //2. 遍历matrix矩阵的第一行，如果有0，那么将rowFlag置为true
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowFlag = true;
                break;
            }
        }
        //3. 遍历matrix矩阵的第一列，如果有0，那么将colFlag置为true
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                colFlag = true;
                break;
            }
        }
        //4. 遍历matrix矩阵的每一个元素(除了第一行与第一列)，
        // 如果matrix[i][j] == 0，那么将matrix[i][0]和matrix[0][j]置为0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        //5. 遍历matrix矩阵的每一个元素(除了第一行与第一列)，
        // 如果matrix[i][0] == 0或者matrix[0][j] == 0，那么将matrix[i][j]置为0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        // 最后处理第一行和第一列
        //6. 如果rowFlag为true，那么将matrix的第一行的所有元素置为0
        if (rowFlag) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        //7. 如果colFlag为true，那么将matrix的第一列的所有元素置为0
        if (colFlag) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
    
    //5. 优化4
    public void setZeroes5(int[][] matrix) {
        //1. 初始化1个标志位，用来记录第一行是否需要置为0
        boolean rowFlag = false;
        // 初始化一个flag判断当前行是否需要置为0
        boolean flag = false;
        
        //2. 遍历matrix矩阵的第一行，如果有0，那么将rowFlag置为true
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowFlag = true;
                break;
            }
        }
        //3. 遍历matrix矩阵的每一个元素(除了第一行)，
        // 如果matrix[i][j] == 0，那么将matrix[0][j]置为0,同时将该行的所有元素置为0
        for (int i = 1; i < matrix.length; i++) {
            flag = false;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    //设置flag为true
                    flag = true;
                }
            }
            if (flag) {
                Arrays.fill(matrix[i], 0);
            }
        }
        //4. 遍历matrix的列，如果matrix[0][j] == 0，那么将matrix的第j列的所有元素置为0
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        //5. 如果rowFlag为true，那么将matrix的第一行的所有元素置为0
        if (rowFlag) {
            Arrays.fill(matrix[0], 0);
        }
    }
    
    //6. 优化5
    public void setZeroes6(int[][] matrix) {
        //1. 初始化1个标志位，用来记录第一行是否需要置为0
        boolean rowFlag = false;
        //2. 遍历matrix矩阵的第一行，如果有0，那么将rowFlag置为true
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                rowFlag = true;
                break;
            }
        }
        //3. 遍历matrix矩阵的每一个元素(除了第一行)，
        // 如果matrix[i][j] == 0，那么将matrix[0][j]置为0,同时将该行的所有元素置为0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    //将该行第一个元素当作一个标志位,判断是否需要将该行的元素置为0
                    // (如果该行某个元素为0,那么将该行的所有元素应该置为0,因此无所谓用该行哪个元素当标志位都是可以的)
                    matrix[i][0] = 0;
                }
            }
            if (matrix[i][0] == 0) {
                Arrays.fill(matrix[i], 0);
            }
        }
        //4. 遍历matrix的列，如果matrix[0][j] == 0，那么将matrix的第j列的所有元素置为0
        for (int j = 0; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 1; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }
        //5. 如果rowFlag为true，那么将matrix的第一行的所有元素置为0
        if (rowFlag) {
            Arrays.fill(matrix[0], 0);
        }

    }
    
}