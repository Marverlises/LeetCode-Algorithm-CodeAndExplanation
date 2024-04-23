package week5;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/2/21 11:14
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution5 {
    // 290. 单词规律
    // 思路一
    public boolean wordPattern(String pattern, String s) {
        //1. 定义一个hashMap用来存放映射
        HashMap<Character, String> patternStrMapping = new HashMap<>();
        //2. 将字符串s按空格分割成字符串数组
        String[] words = s.split(" ");
        //3. 如果pattern的长度和字符串数组的长度不相等，返回false
        // (因为说明pattern和字符串数组的映射关系不对应)
        if (pattern.length() != words.length) {
            return false;
        }
        //4. 遍历pattern字符串
        for (int i = 0; i < pattern.length(); i++) {
            //5. 如果patternStrMapping中不包含pattern.charAt(i)
            if (!patternStrMapping.containsKey(pattern.charAt(i))) {
                //要满足不同字符不能映射到同一个字符串
                if (patternStrMapping.containsValue(words[i])) {
                    return false;
                }
                //将pattern.charAt(i)和words[i]存入patternStrMapping
                patternStrMapping.put(pattern.charAt(i), words[i]);
            } else {
                //6. 如果patternStrMapping中包含pattern.charAt(i)，判断patternStrMapping.get(pattern.charAt(i))和words[i]是否相等
                //equals() 方法用于比较两个字符串的内容是否相等
                //判断两个引用是否指向同一个对象可以使用==运算符
                if (!patternStrMapping.get(pattern.charAt(i)).equals(words[i])) {
                    return false;
                }
            }
        }
        return true;
    }
    // 思路二
    public boolean wordPattern2(String pattern, String s){
        //定义双指针截取单词
        int start = 0, end = 0;
        //定义一个hashMap用来存放映射
        HashMap<Character, String> patternStrMapping = new HashMap<>();
        //遍历pattern字符串
        for (int i = 0; i < pattern.length(); i++) {
            //找到单词的结束位置
            while (end < s.length() && s.charAt(end) != ' ') {
                end++;
            }
            //截取单词
            String word = s.substring(start, end);
            //如果patternStrMapping中不包含pattern.charAt(i)
            if (!patternStrMapping.containsKey(pattern.charAt(i))) {
                //要满足不同字符不能映射到同一个字符串
                if (patternStrMapping.containsValue(word)) {
                    return false;
                }
                //将pattern.charAt(i)和word存入patternStrMapping
                patternStrMapping.put(pattern.charAt(i), word);
            } else {
                //如果patternStrMapping中包含pattern.charAt(i)，
                //判断patternStrMapping.get(pattern.charAt(i))和word是否相等
                if (!patternStrMapping.get(pattern.charAt(i)).equals(word)) {
                    return false;
                }
            }
            //如果end == s.length()，说明已经遍历完字符串s，但是pattern字符串还没有遍历完
            //返回false
            if(end== s.length() && i < pattern.length()-1){
                return false;
            }
            //更新start和end的位置
            start = end + 1;
            end = start;
        }
        //如果pattern字符串已经遍历完，但是字符串s还没有遍历完，返回false
        if(start < s.length()){
            return false;
        }
        return true;
    }
}
