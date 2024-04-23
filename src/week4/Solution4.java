package week4;

/**
 * @Author Baiyu
 * @Time 2024/2/16 8:33
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {
    //有效的数独
    // 1. 暴力求解
    public boolean isValidSudoku(char[][] board) {
        //1. 遍历每一行
        for (int i = 0; i < 9; i++) {
            //初始化计数数组
            boolean[] row = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    // 将字符转换为数字,并减去1,因为需要对应0-8的boolean数组下标
                    int num = board[i][j] - '1';
                    // 因为第二次出现相同的数字时，row[num]已经为true，所以返回false
                    if (row[num]) {
                        return false;
                    }
                    row[num] = true;
                }
            }
        }
        //2. 遍历每一列
        for (int i = 0; i < board.length; i++) {
            boolean[] col = new boolean[9];
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] != '.') {
                    int num = board[j][i] - '1';
                    if (col[num]) {
                        return false;
                    }
                    col[num] = true;
                }
            }
        }
        //3. 遍历每一个3*3的小方格
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board.length; j += 3) {
                boolean[] box = new boolean[9];
                // 遍历每一个小方格
                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        if (board[k][l] != '.') {
                            int num = board[k][l] - '1';
                            if (box[num]) {
                                return false;
                            }
                            box[num] = true;
                        }
                    }
                }
            }
        }
        return true;
    }
    
    //2. 优化
    public boolean isValidSudoku2(char[][] board) {
        //每一列的判定数组
        boolean[][] col = new boolean[9][9];
        // 每一个3*3的小方格的判定数组
        boolean[][] box = null;
        //遍历每一个元素
        for (int i = 0; i < board.length; i++) {
            // 每一行的判定数组
            boolean[] row = new boolean[9];
            // 每一个3*3的小方格的判定数组(行数为i/3,列数为j%3)
            if (i % 3 == 0) {
                box = new boolean[3][9];
            }
            for (int j = 0; j < board.length; j++) {
                //如果是空格，就跳过
                if (board[i][j] == '.') {
                    continue;
                }
                //将字符转换为数字
                int num = board[i][j] - '1';
                //如果这个数字在这一行已经出现过了，就返回false
                if (row[num]) {
                    return false;
                }
                row[num] = true;
                //如果这个数字在这一列已经出现过了，就返回false
                if (col[j][num]) {
                    return false;
                }
                col[j][num] = true;
                //如果这个数字在这个3*3的小方格中已经出现过了，就返回false
                assert box != null;
                if (box[j / 3][num]) {
                    return false;
                }
                box[j / 3][num] = true;
            }
        }
        return true;
    }
}
