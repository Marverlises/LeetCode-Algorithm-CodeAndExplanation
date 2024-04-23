package week7;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/3/6 9:28
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution6 {
    //最小覆盖子串
    
    //思路一：暴力求解
    public String minWindow(String s, String t) {
        //1. 定义一个map，存储t中的字符出现的个数
        HashMap<Character, Integer> tCharCount = new HashMap<>();
        //定义一个变量存储最小长度的子串
        String ret = "";
        for (int i = 0; i < t.length(); i++) {
            tCharCount.put(t.charAt(i), tCharCount.getOrDefault(t.charAt(i), 0) + 1);
        }
        //2. 遍历s，找到包含t中所有字符的最小子串
        for (int i = 0; i < s.length(); i++) {
            HashMap<Character, Integer> sCharCount = new HashMap<>();
            for (int j = i; j < s.length(); j++) {
                //判断s中的字符是否在t中
                if (tCharCount.containsKey(s.charAt(j))) {
                    sCharCount.put(s.charAt(j), sCharCount.getOrDefault(s.charAt(j), 0) + 1);
                }
                //判断s中是否包含t中的所有字符
                if (isContains(tCharCount, sCharCount)) {
                    //如果包含，计算子串的长度
                    if (ret.equals("") || ret.length() > j - i + 1) {
                        ret = s.substring(i, j + 1);
                    }
                    break;
                }
            }
        }
        return ret;
    }
    
    //判断s中是否包含t中的所有字符
    private boolean isContains(HashMap<Character, Integer> tCharCount, HashMap<Character, Integer> sCharCount) {
        //遍历t中的字符
        for (Character key : tCharCount.keySet()) {
            //如果s中不包含t中的字符，或者s中包含t中的字符，但是个数小于t中的个数
            if (!sCharCount.containsKey(key) || sCharCount.get(key) < tCharCount.get(key)) {
                return false;
            }
        }
        return true;
    }
}
