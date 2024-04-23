package week5;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/2/21 9:12
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    //赎金信
    //思路一
    public boolean canConstruct(String ransomNote, String magazine) {
        HashMap<Character, Integer> ransomNoteCharCount = new HashMap<>();
        //遍历ransomNote字符串，统计每个字符出现的次数
        for (char c : ransomNote.toCharArray()) {
            ransomNoteCharCount.put(c, ransomNoteCharCount.getOrDefault(c, 0) + 1);
        }
        //遍历magazine字符串，判断magazine字符串中的字符是否能够完全包含在ransomNote字符串中的字符
        for (char c : magazine.toCharArray()) {
            //如果ransomNoteCharCount中包含c，将c对应的value减1
            if (ransomNoteCharCount.containsKey(c)) {
                ransomNoteCharCount.put(c, ransomNoteCharCount.get(c) - 1);
                //如果c对应的value为0，将c从ransomNoteCharCount中移除
                if (ransomNoteCharCount.get(c) == 0) {
                    ransomNoteCharCount.remove(c);
                }
            }
            //如果ransomNoteCharCount为空，说明magazine字符串中的字符能够完全包含在ransomNote字符串中的字符
            if (ransomNoteCharCount.isEmpty()) {
                return true;
            }
        }
        //如果所有的字符都看过了没有导致返回true，说明magazine字符串中的字符不能够完全包含在ransomNote字符串中的字符
        return false;
    }
    
    //思路二
    public boolean canConstruct2(String ransomNote, String magazine) {
        //定义两个指针分别指向ransomNote和magazine
        int point1 = 0, point2 = 0;
        //如果ransomNote字符串的长度大于magazine字符串的长度，返回false
        // (因为magazine长度都不够肯定不能包含ransomNote)
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        //遍历magazine字符串
        while (point2 < magazine.length()) {
            //如果ransomNote.charAt(point1) == magazine.charAt(point2)，point1++
            if (point1 < ransomNote.length() && ransomNote.charAt(point1) == magazine.charAt(point2)) {
                point1++;
            }
            //如果point1 == ransomNote.length()，说明ransomNote字符串中的字符能够完全包含在magazine字符串中的字符
            if (point1 == ransomNote.length()) {
                return true;
            }
            point2++;
        }
        //如果point2 == magazine.length() && point1 < ransomNote.length()，说明ransomNote字符串中的字符不能够完全包含在magazine字符串中的字符
        if (point2 == magazine.length() && point1 < ransomNote.length()) {
            return false;
        }
        return true;
    }
    
    //思路三
    public boolean canConstruct3(String ransomNote, String magazine) {
        //定义一个长度为26的数组，用来存储magazine字符串中每个字符出现的次数
        int[] magazineCharCount = new int[26];
        //遍历magazine字符串，统计magazine字符串中每个字符出现的次数
        for (char c : magazine.toCharArray()) {
            magazineCharCount[c - 'a']++;
        }
        //遍历ransomNote字符串，判断magazine字符串中的字符是否能够完全包含在ransomNote字符串中的字符
        for (char c : ransomNote.toCharArray()) {
            //如果magazineCharCount[c - 'a'] == 0，说明magazine字符串中的字符不能够完全包含在ransomNote字符串中的字符
            if (magazineCharCount[c - 'a'] == 0) {
                return false;
            }
            //magazineCharCount[c - 'a']--
            magazineCharCount[c - 'a']--;
        }
        return true;
    }
    //思路三
    public boolean canConstruct3_2(String ransomNote, String magazine) {
        //定义一个长度为26的数组，用来存储ransomNote字符串中每个字符出现的次数
        //对应a-z的字符
        int[] ransomNoteCharCount = new int[26];
        //遍历ransomNote字符串，统计ransomNote字符串中每个字符出现的次数
        for (char c : ransomNote.toCharArray()) {
            ransomNoteCharCount[c - 'a']++;
        }
        //遍历magazine字符串，判断magazine字符串中的字符是否能够完全包含在ransomNote字符串中的字符
        for (char c : magazine.toCharArray()) {
            //ransomNoteCharCount[c - 'a']--
            ransomNoteCharCount[c - 'a']--;
        }
        //遍历ransomNoteCharCount数组，如果ransomNoteCharCount[i] > 0，
        // 说明magazine字符串中的字符不能够完全包含在ransomNote字符串中的字符
        for (int i : ransomNoteCharCount) {
            if (i > 0) {
                return false;
            }
        }
        //如果ransomNoteCharCount数组中的所有元素都小于等于0，
        // 说明magazine字符串中的字符能够完全包含在ransomNote字符串中的字符
        return true;
    }
}

