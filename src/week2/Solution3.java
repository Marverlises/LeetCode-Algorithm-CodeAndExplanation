package week2;

/**
 * @Author Baiyu
 * @Time 2024/1/28 9:22
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    public static String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();
        int n = s.length();
        // 按行遍历，逐行读取字符
        for (int i = 0; i < numRows; i++) {
            int j = i;
            while (j < n) {
                res.append(s.charAt(j));
                // 第一行和最后一行较为特殊，在中间会夹着一个字符，需要单独处理
                // 其下标为算出的 j 减去两倍的当前行数
                j += 2 * numRows - 2;
                if (i != 0 && i != numRows - 1 && j - 2 * i < n) {
                    res.append(s.charAt(j - 2 * i));
                }
            }
        }
        return res.toString();
    }
}
