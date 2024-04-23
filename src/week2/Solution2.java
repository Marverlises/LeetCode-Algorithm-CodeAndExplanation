package week2;

/**
 * @Author Baiyu
 * @Time 2024/1/27 9:11
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    //反转字符串中的单词
    public static String reverseWords(String s) {
        //字符串如何删除两边空格？
        s = s.trim();
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        
        for (int i = n - 1; i >= 0; i--) {
            boolean flag = false;
            int temp = i;
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            sb.append(s, i + 1, temp + 1);
            if (!(i <= 0)) {
                sb.append(" ");
            }
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
                flag = true;
            }
            if (flag) {
                i++;
            }
        }
        return sb.toString();
    }
}
