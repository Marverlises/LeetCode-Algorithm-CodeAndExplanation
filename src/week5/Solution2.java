package week5;

import java.util.ArrayList;

/**
 * @Author Baiyu
 * @Time 2024/2/20 8:12
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    // 生命游戏
    public void gameOfLife(int[][] board) {
        //1. 创建一个和board数组一样大小的数组
        int[][] copy = new int[board.length][board[0].length];
        //2. 遍历board数组的每一个元素，将它的值赋值给copy数组的对应元素
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //3. 将copy数组的元素赋值为board数组的元素
                copy[i][j] = board[i][j];
            }
        }
        // 计算board数组的每一个元素周围的活细胞数量
        int count = 0;
        //3. 遍历board数组的每一个元素
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                count = 0;
                //4. 计算board数组的每一个元素周围的活细胞数量
                // 行和列的范围是i-1到i+1，j-1到j+1
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        //5. 判断k和l的范围是否合法，如果合法，判断copy数组的元素是否为1，如果是，count++
                        if (k >= 0 && k < board.length && l >= 0 && l < board[0].length && !(k == i && l == j)) {
                            if (copy[k][l] == 1) {
                                count++;
                            }
                        }
                    }
                }
                //6. 根据count的值，更新board数组的元素
                if (copy[i][j] == 1) {
                    if (count < 2 || count > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }
    
    // 思路二——原地修改
    public void gameOfLife2(int[][] board) {
        //遍历面板每一个元素，根据原始状态和需要改变为的值确定该位置的值
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //计算周围活细胞的数量，
                // - 对于面板每一个元素，遇见周围八个位置中有2和0就把它当作0
                // - 对于面板每一个元素，遇见周围八个位置中有1和3就把它当作1
                // 行和列的范围是i-1到i+1，j-1到j+1
                int count = 0;
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        //5. 判断k和l的范围是否合法，如果合法，判断copy数组的元素是否为1，如果是，count++
                        if (k >= 0 && k < board.length && l >= 0 && l < board[0].length && !(k == i && l == j)) {
                            if (board[k][l] == 1 || board[k][l] == 3) {
                                count++;
                            }
                        }
                    }
                }
                
                //根据原始状态和需要改变为的值确定该位置的值
                if (board[i][j] == 1) {
                    if (count < 2 || count > 3) {
                        // 1 -> 0
                        board[i][j] = 3;
                    }
                } else {
                    if (count == 3) {
                        // 0 -> 1
                        board[i][j] = 2;
                    }
                }
            }
        }
        //遍历面板每一个元素，根据原始状态和需要改变为的值确定该位置的值
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                //计算周围活细胞的数量，
                // - 对于面板每一个元素，遇见周围八个位置中有2和0就把它当作0
                // - 对于面板每一个元素，遇见周围八个位置中有1和3就把它当作1
                if (board[i][j] == 2) {
                    board[i][j] = 1;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 0;
                }
            }
        }
    }
    
    
    // 思路三——位运算
    public void gameOfLife3(int[][] board) {
        //设置一个和board数组一样行数的int数组命名位copy，每一个int值表示board的每一行
        int[] copy = new int[board.length];
        //遍历board数组的每一个元素，将它的值赋值给copy数组的对应元素
        for (int i = 0; i < board.length; i++) {
            //将board的每一行转换为一个int值
            for (int j = 0; j < board[0].length; j++) {
                copy[i] = copy[i] | (board[i][j] << (31 - j));
            }
        }
        //计算周围活细胞的数量
        int count = 0;
        //遍历board数组的每一个元素
        for (int i = 0; i < board.length; i++) {
            //遍历board数组的每一个元素
            for (int j = 0; j < board[0].length; j++) {
                count = 0;
                //行和列的范围是i-1到i+1，j-1到j+1
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        //判断k和l的范围是否合法，如果合法，判断copy数组的元素是否为1，如果是，count++
                        if (k >= 0 && k < board.length && l >= 0 && l < board[0].length && !(k == i && l == j)) {
                            //判断copy数组的元素是否为1，如果是，count++
                            /**
                             *  在Java中，表达式 (copy[k] & (1 << (31 - l))) 并不直接结果为0或1，
                             *  而是执行了一个按位与（操作，这个操作的结果取决于copy[k]在指定位上的值。
                             *  因此不能直接判断是否为1，而是要判断是否为0，如果为0表示指定位为0，但是不为0则表示指定位为1。
                             *  这里的操作细节见图片下方注释
                             */
                            if ((copy[k] & (1 << (31 - l))) != 0) {
                                count++;
                            }
                        }
                    }
                }
                //根据count的值，更新board数组的元素
                //当原来的位置处于活细胞状态时（1时）
                if ((copy[i] & (1 << (31 - j))) != 0) {
                    if (count < 2 || count > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (count == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }
    
    // 思路四——位运算，但是copy存储在board数组中
    public void gameOfLife4(int[][] board) {
        //1. 初始化，采用位运算初始化copy数组（实际上就是board的第一个元素的相应位）
        for (int i = 0; i < board.length; i++) {
            //将board的每一行转换为一个int值
            for (int j = 0; j < board[0].length; j++) {
                board[i][0] = board[i][0] | (board[i][j] << (31 - j));
                //最后一个位肯定不会动，因为board数组的列数最大为25
            }
        }
        //2. 遍历复制矩阵的每一个元素，查看其周围八个位置的状态，统计1的个数
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                count = 0;
                // 行和列的范围是i-1到i+1，j-1到j+1
                for (int k = i - 1; k <= i + 1; k++) {
                    for (int l = j - 1; l <= j + 1; l++) {
                        if (k >= 0 && k < board.length && l >= 0 && l < board[0].length && !(k == i && l == j)) {
                            //判断copy数组的元素是否为1，如果是，count++
                            if ((board[k][0] & (1 << (31 - l))) != 0) {
                                count++;
                            }
                        }
                    }
                }
                //3. 根据count的值，更新board数组的元素
                // 当原来的位置处于活细胞状态时（1时）
                if ((board[i][0] & (1 << (31 - j))) != 0) {
                    if (count < 2 || count > 3) {
                        //注意这里赋值是要看是不是该行第一个元素，因为第一个元素的话我们不能改变了我们存储的copy数组
                        //所以只能动最后一个位,将它置为0
                        board[i][j] = board[i][j] & 0xfffffffe; // 11111111111111111111111111111110
                    }
                } else {
                    // 当原来的位置处于死细胞状态时（0时）
                    if (count == 3) {
                        //注意这里赋值是要看是不是该行第一个元素，因为第一个元素的话我们不能改变了我们存储的copy数组
                        //所以只能动最后一个位,将它置为0
                        board[i][j] = board[i][j] | 0x1; //00000000000000000000000000000001
                    }
                }
            }
        }
        //最后需要更新第一列恢复为原来的值（其最后一位）
        for (int i = 0; i < board.length; i++) {
            board[i][0] = board[i][0] & 1;
        }
    }
    
    // 思路五——位运算，将结果存储在每个元素的左边一位
    public void gameOfLife5(int[][] board) {
        // 1. 初始化周围八个位置
        int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
        int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
        //得到的就是周围八个位置相对于当前位置的偏移量
        // 2. 遍历复制矩阵的每一个元素，查看其周围八个位置的状态，统计1的个数
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int count = 0;
                //遍历周围八个位置
                for (int k = 0; k < 8; k++) {
                    //得到周围八个位置的坐标
                    int x = i + dx[k];
                    int y = j + dy[k];
                    //判断x和y的范围是否合法，如果合法，
                    // 判断copy数组的元素是否为1，如果是，count++
                    if (x >= 0 && x < board.length && y >= 0 && y < board[0].length && (board[x][y] & 1) != 0) {
                        count++;
                    }
                }

                //3. 根据count的值，更新board数组的元素
                // 当原来的位置处于活细胞状态时（1时）
                if ((board[i][j] & 1) != 0) {
                    if (count == 2 || count == 3) {
                        // 将board的倒数第二位也就是结果位置为1
                        // 也就是和00000000000000000000000000000010进行或操作
                        board[i][j] = board[i][j] | 0x2;
                    }
                    //因为本来board每一个元素的其它位置就是0，所以对于致死的情况不需要处理，结果就是0
                }
                // 当原来的位置处于死细胞状态时（0时）
                else {
                    if (count == 3) {
                        // 将board的倒数第二位也就是结果位置为1
                        // 也就是和00000000000000000000000000000010进行或操作
                        board[i][j] = board[i][j] | 0x2;
                    }
                    //因为本来board每一个元素的其它位置就是0，所以对于其它保持死亡状态的情况不需要处理，结果就是0
                }
            }
        }
        //4. 最后将board数组的每一个元素右移一位
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] >>= 1;
            }
        }
    }
}