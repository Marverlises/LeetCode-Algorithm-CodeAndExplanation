package week4;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2024/2/14 10:39
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    // 串联所有单词的子串
    // 1. 暴力求解
    public List<Integer> findSubstring(String s, String[] words) {
        // 初始化
        int len = words[0].length();
        // 保存结果
        List<Integer> ret = new ArrayList<>();
        // 表示每个单词及其出现的次数
        HashMap<String, Integer> wordsAndCount = new HashMap<>();
        // 初始化wordsAndCount
        for (String word : words) {
            wordsAndCount.put(word, wordsAndCount.getOrDefault(word, 0) + 1);
        }
        // 遍历字符串 s
        for (int i = 0; i < s.length(); i++) {
            // 复制一份wordsAndCount
            HashMap<String, Integer> wordsAndCountCopy = new HashMap<>(wordsAndCount);
            // 取出一个长度为 len 的子串,注意下标越界
            if (i + len > s.length()) {
                break;
            }
            String subStr = s.substring(i, i + len);
            int temp = i;
            // 如果这个子串是 words 中的一个单词, 就继续往下找
            while (wordsAndCountCopy.containsKey(subStr)) {
                // 将这个单词的出现次数减去1，如果为0，就删除这个单词
                int count = wordsAndCountCopy.get(subStr);
                if (count == 1) {
                    wordsAndCountCopy.remove(subStr);
                } else {
                    wordsAndCountCopy.put(subStr, count - 1);
                }
                // 如果 wordsAndCount 为空，说明找到了一个解
                if (wordsAndCountCopy.isEmpty()) {
                    ret.add(i);
                    break;
                }
                // 移动 temp
                temp += len;
                // 取出一个长度为 len 的子串，注意下标越界
                if (temp + len > s.length()) {
                    break;
                }
                subStr = s.substring(temp, temp + len);
            }
        }
        return ret;
    }
    
    //2. 滑动窗口
    public List<Integer> findSubstring2(String s, String[] words) {
        // 初始化
        int len = words[0].length();
        // 保存结果
        List<Integer> ret = new ArrayList<>();
        // 表示每个单词及其出现的次数
        HashMap<String, Integer> wordsAndCount = new HashMap<>();
        // 初始化wordsAndCount
        for (String word : words) {
            wordsAndCount.put(word, wordsAndCount.getOrDefault(word, 0) + 1);
        }
        // 遍历字符串 s
        for (int i = 0; i < len; i++) {
            // 初始化左指针、右指针、窗口内单词及其出现次数
            int left = i;
            int right = i;
            HashMap<String, Integer> windowWordsAndCount = new HashMap<>();
            // 移动右指针
            while (right + len <= s.length()) {
                String subStr = s.substring(right, right + len);
                right += len;
                // 将这个单词的出现次数加1
                windowWordsAndCount.put(subStr, windowWordsAndCount.getOrDefault(subStr, 0) + 1);
                // 如果窗口内单词的出现次数大于 words 中的单词的出现次数，移动左指针，直到移除一个该单词
                while (windowWordsAndCount.getOrDefault(subStr, 0) > wordsAndCount.getOrDefault(subStr, 0)) {
                    String leftStr = s.substring(left, left + len);
                    left += len;
                    windowWordsAndCount.put(leftStr, windowWordsAndCount.getOrDefault(leftStr, 0) - 1);
                }
                // 如果窗口内单词的出现次数等于 words 中的单词的出现次数，说明找到了一个解
                if (right - left == len * words.length) {
                    ret.add(left);
                }
            }
        }
        return ret;
    }
    
}

