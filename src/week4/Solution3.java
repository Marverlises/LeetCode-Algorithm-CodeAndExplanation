package week4;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/2/15 8:22
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    //  最小覆盖子串
    // 1. 暴力求解
    public String minWindow(String s, String t) {
        // 初始化，对于 t 中字符，使用一个hashMap存储，键为字母，值为出现的次数
        HashMap<Character, Integer> tCharMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tCharMap.put(t.charAt(i), tCharMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 保存结果
        String ret = "";
        // 遍历字符串 s
        for (int i = 0; i < s.length(); i++) {
            // 复制一份tCharMap
            HashMap<Character, Integer> tCharMapCopy = new HashMap<>(tCharMap);
            for (int j = i; j < s.length(); j++) {
                if (tCharMapCopy.containsKey(s.charAt(j))) {
                    int count = tCharMapCopy.get(s.charAt(j));
                    if (count == 1) {
                        tCharMapCopy.remove(s.charAt(j));
                    } else {
                        tCharMapCopy.put(s.charAt(j), count - 1);
                    }
                }
                // 如果 tCharMap 为空，说明找到了一个解
                if (tCharMapCopy.isEmpty()) {
                    if (ret.equals("") || j - i + 1 < ret.length()) {
                        ret = s.substring(i, j + 1);
                    }
                    break;
                }
            }
        }
        return ret;
    }
    
    // 2. 滑动窗口
    public String minWindow2(String s, String t) {
        // 初始化，对于 t 中字符，使用一个hashMap存储，键为字母，值为出现的次数
        HashMap<Character, Integer> tCharMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            tCharMap.put(t.charAt(i), tCharMap.getOrDefault(t.charAt(i), 0) + 1);
        }
        // 保存结果
        String ret = "";
        //左指针、右指针、最小长度
        int left = 0;
        int right = 0;
        int minLen = Integer.MAX_VALUE;
        //复制一个tCharMap
        HashMap<Character, Integer> tCharMapCopy = new HashMap<>();
        //再创建一个hashMap，用于计数(因为所有指针包含的t中的字符可能有多个)
        HashMap<Character, Integer> countHashMap = new HashMap<>();
        //初始化两个hashMap
        for (Character c : tCharMap.keySet()) {
            tCharMapCopy.put(c, tCharMap.get(c));
            countHashMap.put(c, 0);
        }
        //遍历字符串s
        for (right = 0; right < s.length(); right++) {
            //移动右指针，当右指针指向一个 t 中出现的字符，对应的hashMap值减一,如果是t中的字符，但是已经减到0了，
            // 就需要把它放到countHashMap中，用于计数，后续左指针移动时，如果左指针指向这个字符，就可以忽略
            if (tCharMap.containsKey(s.charAt(right))) {
                if (tCharMapCopy.containsKey(s.charAt(right))) {
                    int count = tCharMapCopy.get(s.charAt(right));
                    if (count == 1) {
                        tCharMapCopy.remove(s.charAt(right));
                    } else {
                        tCharMapCopy.put(s.charAt(right), count - 1);
                    }
                } else {
                    //如果是t中的字符，但是tCharMapCopy中已经囊括了，就需要把它放到countHashMap中，用于计数
                    countHashMap.put(s.charAt(right), countHashMap.getOrDefault(s.charAt(right), 0) + 1);
                }
            }
            //当hashMap为空，说明找到了一个解
            while (tCharMapCopy.isEmpty()) {
                //更新最小长度
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    ret = s.substring(left, right + 1);
                }
                //移动左指针，如果左指针指向一个 t 中出现的字符，
                if (tCharMap.containsKey(s.charAt(left))) {
                    int count = countHashMap.get(s.charAt(left));
                    //如果countHashMap中这个字符对应的值大于1，就先count-1，否则countHashMap清0，并且把它放到tCharMapCopy中
                    if (count >= 1) {
                        countHashMap.put(s.charAt(left), count - 1);
                    } else {
                        countHashMap.put(s.charAt(left), 0);
                        tCharMapCopy.put(s.charAt(left), tCharMapCopy.getOrDefault(s.charAt(left), 0) + 1);
                    }
                }
                left++;
            }
        }
        if (minLen == Integer.MAX_VALUE) {
            return "";
        } else {
            return ret;
        }
    }
    
    // 2. 滑动窗口优化
    public String minWindow3(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }
        // 为t创建一个频率映射
        HashMap<Character, Integer> freqMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        // t中需要在窗口中出现的唯一字符数
        int required = freqMap.size();
        // 左右指针
        int left = 0, right = 0;
        // formed用于跟踪当前窗口中完全匹配t中所有字符所需的唯一字符数
        int formed = 0;
        // 用于跟踪当前窗口中的字符
        HashMap<Character, Integer> windowCounts = new HashMap<>();
        // 答案列表的形式是(窗口长度, 左, 右)
        int[] ans = {-1, 0, 0};
        while (right < s.length()) {
            // 将右侧的一个字符添加到窗口中
            char c = s.charAt(right);
            int count = windowCounts.getOrDefault(c, 0);
            windowCounts.put(c, count + 1);
            // 如果当前添加的字符的频率等于t中所需的频率，则formed增加1
            if (freqMap.containsKey(c) && windowCounts.get(c).intValue() == freqMap.get(c).intValue()) {
                formed++;
            }
            // 尝试并收缩窗口，直到它不再“可取”为止
            while (left <= right && formed == required) {
                c = s.charAt(left);
                // 保存到目前为止最小的窗口
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }
                // `left`指针指向的字符不再是窗口的一部分
                windowCounts.put(c, windowCounts.get(c) - 1);
                if (freqMap.containsKey(c) && windowCounts.get(c).intValue() < freqMap.get(c).intValue()) {
                    formed--;
                }
                // 向前移动左指针，这有助于寻找新的窗口
                left++;
            }
            // 完成收缩后，继续扩展窗口
            right++;
        }
        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }
}