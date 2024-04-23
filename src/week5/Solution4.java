package week5;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/2/21 10:47
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {
    // 同构字符串
    // 思路一
    public boolean isIsomorphic(String s, String t) {
        //1.定义一个hashMap,存储映射
        HashMap<Character, Character> mappingMap = new HashMap<>();
        //2.遍历字符串s
        for (int i = 0; i < s.length(); i++) {
            //3.如果mappingMap中不包含s.charAt(i)
            if (!mappingMap.containsKey(s.charAt(i))) {
                //要满足不同字符不能映射到同一个字符
                if (mappingMap.containsValue(t.charAt(i))) {
                    return false;
                }
                //将s.charAt(i)和t.charAt(i)存入mappingMap
                mappingMap.put(s.charAt(i), t.charAt(i));
            } else {
                //4.如果mappingMap中包含s.charAt(i)，判断s.charAt(i)和t.charAt(i)是否相等
                if (mappingMap.get(s.charAt(i)) != t.charAt(i)) {
                    return false;
                }
            }
        }
        //5.所有的字符都看过了没有导致返回false，说明s和t是同构字符串
        return true;
    }
}
