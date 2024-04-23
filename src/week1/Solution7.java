package week1;

/**
 * @Author Baiyu
 * @Time 2024/1/25 8:19
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution7 {
    /**
     * 7. 整数转罗马数字
     * https://leetcode-cn.com/problems/integer-to-roman/
     *
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < roman.length; i++) {
            while (num >= value[i]) {
                ret.append(roman[i]);
                num -= value[i];
            }
        }
        return ret.toString();
    }
}
