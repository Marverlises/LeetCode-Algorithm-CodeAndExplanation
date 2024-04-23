package week5;

import java.util.*;

/**
 * @Author Baiyu
 * @Time 2024/2/22 9:12
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution6 {
    // 判断是否为有效的字母异位词
    // 思路一——使用hashMap
    public boolean isAnagram(String s, String t) {
        //1. 初始化一个hashMap
        HashMap<Character, Integer> sCharCount = new HashMap<>();
        //2. 遍历字符串s，统计每个字符出现的次数
        for (char c : s.toCharArray()) {
            sCharCount.put(c, sCharCount.getOrDefault(c, 0) + 1);
        }
        //3. 遍历字符串t，判断字符串t中的字符是否能够完全包含在字符串s中的字符
        for (char c : t.toCharArray()) {
            //如果sCharCount中不包含c，返回false
            if (!sCharCount.containsKey(c)) {
                return false;
            }
            sCharCount.put(c, sCharCount.getOrDefault(c, 0) - 1);
        }
        //4. 遍历sCharCount，判断sCharCount中的字符是否能够完全包含在t中的字符
        for (int value : sCharCount.values()) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
    
    // 思路一——使用数组
    public boolean isAnagram2(String s, String t) {
        //1. 初始化一个数组，每个位置存储对应a-z字符出现的次数
        int[] sCharCount = new int[26];
        //2. 遍历字符串s，统计每个字符出现的次数
        for (char c : s.toCharArray()) {
            sCharCount[c - 'a']++;
        }
        //3. 遍历字符串t，判断字符串t中的字符是否能够完全包含在字符串s中的字符
        for (char c : t.toCharArray()) {
            sCharCount[c - 'a']--;
        }
        //4. 遍历sCharCount，判断sCharCount中的字符是否能够完全包含在t中的字符
        for (int value : sCharCount) {
            if (value != 0) {
                return false;
            }
        }
        return true;
    }
    
    // 思路二——排序
    public boolean isAnagram3(String s, String t) {
        // 对s和t的字符排序
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        // 比较排序后的sChars和tChars是否相等
        return Arrays.equals(sChars, tChars);
    }
    
    //字母异位词分组
    //思路一
    public List<List<String>> groupAnagrams(String[] strs) {
        //1. 创建一个和strs一样长的数组，表示哪些单词已经被加入结果集
        boolean[] used = new boolean[strs.length];
        //创建一个结果集
        List<List<String>> ret = new ArrayList<>();
        //2. 遍历strs的每一个单词
        for (int i = 0; i < strs.length; i++) {
            //如果该单词已经被加入结果集，跳过
            if (used[i]) {
                continue;
            }
            //如果没有被加入结果集，将该单词先加入结果集
            ArrayList<String> list = new ArrayList<>();
            list.add(strs[i]);
            ret.add(list);
            //设置为用过
            used[i] = true;
            //3. 遍历strs的其余单词
            for (int j = i + 1; j < strs.length; j++) {
                //如果该单词已经被加入结果集，跳过
                if (used[j]) {
                    continue;
                }
                //如果没有被加入结果集，判断该单词是否和结果集中的单词是字母异位词
                if (isAnagram(strs[i], strs[j])) {
                    //如果是字母异位词，将该单词加入结果集
                    list.add(strs[j]);
                    //设置为用过
                    used[j] = true;
                }
            }
        }
        return ret;
    }
    
    //思路二——先排序再比较
    public List<List<String>> groupAnagrams2(String[] strs) {
        //1. 使用一个新的strsCopy字符串数组，存储strs每个单词排序后的形式
        String[] strsCopy = new String[strs.length];
        //2. 对strs每个单词排序，并存入strsCopy
        for (int i = 0; i < strs.length; i++) {
            char[] chars = strs[i].toCharArray();
            Arrays.sort(chars);
            strsCopy[i] = new String(chars);
        }
        //3. 创建一个hashMap，key为strsCopy中的单词，value为该单词在结果集中的位置，也就是第几个list
        HashMap<String, Integer> map = new HashMap<>();
        //4. 创建一个结果集
        List<List<String>> ret = new ArrayList<>();
        //5. 遍历strsCopy
        for (int i = 0; i < strsCopy.length; i++) {
            //如果map中不包含strsCopy[i]
            if (!map.containsKey(strsCopy[i])) {
                //创建一个新的list，将strs[i]加入list
                ArrayList<String> list = new ArrayList<>();
                list.add(strs[i]);
                //将list加入结果集
                ret.add(list);
                //将strsCopy[i]和结果集的位置存入map
                map.put(strsCopy[i], ret.size() - 1);
            } else {
                //如果map中包含strsCopy[i]，将strs[i]加入结果集
                ret.get(map.get(strsCopy[i])).add(strs[i]);
            }
        }
        return ret;
    }
    
    //
    //思路三——计数
    public List<List<String>> groupAnagrams3(String[] strs) {
        //1. 创建一个hashMap，key为每个单词中每个字符出现的次数拼接后的字符串，value为该单词的集合
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        //2. 遍历strs
        for (String str : strs) {
            //创建一个长度为26的数组，存储每个单词中每个字符出现的次数
            int[] counts = new int[26];
            int length = str.length();
            //统计每个单词中每个字符出现的次数
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            //遍历counts，拼接字符串，作为哈希表的键，value为该单词的集合
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            //将拼接后的字符串和str存入map
            String key = sb.toString();
            //如果map中不包含key，创建一个新的list，将str加入list，将list存入map
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
