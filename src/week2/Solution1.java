package week2;

/**
 * @Author Baiyu
 * @Time 2024/1/26 11:48
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution1 {
    //最后一个单词的长度
    
    /**
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        String[] s1 = s.split(" ");
        return s1[s1.length - 1].length();
    }
    
    public int lengthOfLastWord2(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int index = s.length() - 1;
        while (s.charAt(index) == ' ') {
            index--;
        }
        int i;
        for (i = index; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                return index - i;
            }
        }
        return index + 1;
    }
    
    //最长公共前缀
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        int minLen = strs[0].length();
        int minIndex = 0;
        for (int i = 1; i < strs.length; i++) {
            if (minLen > strs[i].length()) {
                minLen = strs[i].length();
                minIndex = i;
            }
        }
        for (int i = 0; i < minLen; i++) {
            char temp = strs[0].charAt(i);
            for (int j = 0; j < strs.length; j++) {
                if (strs[j].charAt(i) != temp) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[minIndex];
    }
}
