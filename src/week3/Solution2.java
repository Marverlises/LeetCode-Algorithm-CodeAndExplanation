package week3;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/2/4 8:50
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    // 验证回文串
    public boolean isPalindrome(String s) {
        // 解题思路
        // 1. 大写字符转换为小写字符、并移除所有非字母数字字符(但无需真正移除，只需要在遍历时如果非字母就略过)
        // 2. 双指针判断是否为回文串
        // 3. 时间复杂度O(n)
        // 4. 空间复杂度O(1)
        // 5. 代码实现
        
        // 1. 大写字符转换为小写字符
        String lowerCase = s.toLowerCase();
        // 2. 双指针判断是否为回文串
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            // 2.1. 如果不是字母数字字符，跳过
            if (!Character.isLetterOrDigit(lowerCase.charAt(left))) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(lowerCase.charAt(right))) {
                right--;
                continue;
            }
            // 2.2. 如果不相等，返回false
            if (lowerCase.charAt(left) != lowerCase.charAt(right)) {
                return false;
            }
            // 2.3. 移动指针
            left++;
            right--;
        }
        return true;
    }
    
    public boolean isPalindrome2(String s) {
        StringBuffer clearStr = new StringBuffer();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            // 删除无关紧要的字符
            if (Character.isLetterOrDigit(ch)) {
                clearStr.append(Character.toLowerCase(ch));
            }
        }
        // 反转字符串
        StringBuffer sgood_rev = new StringBuffer(clearStr).reverse();
        // 判断是否为回文串
        return clearStr.toString().equals(sgood_rev.toString());
    }
    
    // 判断子序列
    public boolean isSubsequence(String s, String t) {
        // 解题思路
        // 1. 双指针判断是否为子序列，一个指向s，一个指向t，
        //    如果s的指针指向的字符等于t的指针指向的字符，s的指针向后移动
        //    最后判断s的指针是否指向了s的末尾
        // 2. 时间复杂度O(n)
        // 3. 空间复杂度O(1)
        // 4. 代码实现
        int i = 0, j = 0;
        if (s.isEmpty()) {
            return true;
        }
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
            if (i == s.length()) {
                return true;
            }
        }
        return false;
    }
    
    // 2. 动态规划
    public boolean isSubsequence2(String s, String t) {
        // 解题思路
        if (s.isEmpty()) {
            return true;
        }
        // 1. 创建bp数组
        int[][] bp = new int[t.length()][26];
        // 2. 从后往前遍历t，构建bp数组
        for (int i = t.length() - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++) {
                // 如果是最后一行，全初始化为-1
                if (i == t.length() - 1) {
                    bp[i][j] = -1;
                } else {
                    // 如果当前字符的下一个字符等于j + 'a'，则bp[i][j] = i + 1
                    if (t.charAt(i + 1) == j + 'a') {
                        bp[i][j] = i + 1;
                    } else {
                        // 否则，bp[i][j] = bp[i + 1][j]
                        bp[i][j] = bp[i + 1][j];
                    }
                }
            }
        }
        // 3. 为t字符串记录每一个字符的首次出现位置，方便后续查找
        HashMap<Character, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            if (!indexMap.containsKey(t.charAt(i))) {
                indexMap.put(t.charAt(i), i);
            }
        }
        // 4. 从前往后遍历s，判断是否为子序列
        // 首先需要找到s的首字符
        Integer firstCharS = indexMap.get(s.charAt(0));
        if (firstCharS == null) {
            return false;
        }
        // 如果找到s的首字符对应的位置firstCharS，就从firstCharS对应的行开始遍历
        int lineIndex = firstCharS;
        // 首字符已经匹配，所以从1开始
        for (int i = 1; i < s.length(); i++) {
            // 寻找下一个字符对应bp数组的值
            lineIndex = bp[lineIndex][s.charAt(i) - 'a'];
            // 如果lienIndex == -1，说明没有找到
            if (lineIndex == -1) {
                return false;
            }
        }
        return true;
    }
    
    // 3. hash可以吗？
    // 通过遍历长字符串，把每一个字符的位置记录下来
    // 然后遍历短字符串，看其相应的顺序，对应在hash表中最小的索引，最后看是否能找全
    // 代码实现
    public boolean isSubsequence3(String s, String t) {
        int n = s.length(), m = t.length();
        // 通过遍历长字符串，把每一个字符的位置记录下来
        HashMap<Character, ArrayList<Integer>> hashMap = new HashMap<>();
        HashMap<Character, Integer> indexMap = new HashMap<>();
        
        for (int i = 0; i < m; i++) {
            char ch = t.charAt(i);
            if (!hashMap.containsKey(ch)) {
                hashMap.put(ch, new ArrayList<>());
            }
            hashMap.get(ch).add(i);
        }
        // 给indexArray初始化
        for (Character c : hashMap.keySet()) {
            indexMap.put(c, -1);
        }
        // 用来记录上一个字符的索引
        int preIndex = -1;
        // 然后遍历短字符串，看其相应的顺序，对应在hashMap对应得Array中最小的索引，最后看是否能找全
        for (int i = 0; i < n; i++) {
            // 没有该字母 || (该字母的索引已经到末位&&该字母的索引不是-1也就是不是第一个字母)
            if ((!hashMap.containsKey(s.charAt(i))
                    || (hashMap.get(s.charAt(i)).get(hashMap.get(s.charAt(i)).size() - 1) <= indexMap.get(s.charAt(i)))
                    && indexMap.get(s.charAt(i)) != -1)) {
                return false;
            }
            // 方法1： 使用二分查找在hashMap.get(s.charAt(i))找到大于preIndex得最小值
            //int left = 0;
            //int right = hashMap.get(s.charAt(i)).size() - 1;
            //while (left < right) {
            //    int mid = left + (right - left) / 2;
            //    if (hashMap.get(s.charAt(i)).get(mid) <= preIndex) {
            //        left = mid + 1;
            //    } else {
            //        right = mid;
            //    }
            //}
            //if (hashMap.get(s.charAt(i)).get(left) <= preIndex) {
            //    return false;
            //}
            //preIndex = hashMap.get(s.charAt(i)).get(left);
            
            // 方法2：使用一个数组记录上次该字符处走到的位置，获取下一个索引，来寻找
            int index = indexMap.get(s.charAt(i)) + 1;
            // 有对应的字母键
            if (index < hashMap.get(s.charAt(i)).size()) {
                // 找到大于preIndex得最小值
                while (preIndex >= hashMap.get(s.charAt(i)).get(index)) {
                    index++;
                    if (index == hashMap.get(s.charAt(i)).size()) {
                        break;
                    }
                }
                //没找到
                if (index == hashMap.get(s.charAt(i)).size()) {
                    return false;
                }
                //找到了
                indexMap.put(s.charAt(i), index);
                preIndex = hashMap.get(s.charAt(i)).get(index);
            }
            //无对应的字母键
            else {
                return false;
            }
        }
        return true;
    }
    
}
