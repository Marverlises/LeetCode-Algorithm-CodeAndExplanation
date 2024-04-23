package week2;

/**
 * @Author Baiyu
 * @Time 2024/1/29 8:21
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {
    public static int strStr(String haystack, String needle) {
        int ret = haystack.indexOf(needle);
        return ret;
        //int ret = -1;
        //for (int i = haystack.length() - 1; i >= 0; i--) {
        //    int j = needle.length() - 1;
        //    while (i >= 0 && j >= 0 && haystack.charAt(i) == needle.charAt(j)) {
        //        if (j == 0) {
        //            ret = i;
        //        }
        //        i--;
        //        j--;
        //    }
        //}
        //return ret;
    }
    
    // KMP 算法
    public static int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        
        // 分别读取原串和匹配串的长度
        int n = haystack.length(), m = needle.length();
        // 原串和匹配串前面都加空格，使其下标从 1 开始
        haystack = " " + haystack;
        needle = " " + needle;
        
        char[] stack = haystack.toCharArray();
        char[] need = needle.toCharArray();
        
        // 构建 next 数组，数组长度为匹配串的长度（next 数组是和匹配串相关的）
        int[] next = new int[m + 1];
        // 构造过程 i = 2，j = 0 开始，i 小于等于匹配串长度 【构造 i 从 2 开始】
        for (int i = 2, j = 0; i <= m; i++) {
            // 匹配不成功的话，j = next(j)
            while (j > 0 && need[i] != need[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++
            if (need[i] == need[j + 1]) j++;
            // 更新 next[i]，结束本次循环，i++
            next[i] = j;
        }
        
        // 匹配过程，i = 1，j = 0 开始，i 小于等于原串长度 【匹配 i 从 1 开始】
        for (int i = 1, j = 0; i <= n; i++) {
            // 匹配不成功 j = next(j)
            while (j > 0 && stack[i] != need[j + 1]) j = next[j];
            // 匹配成功的话，先让 j++，结束本次循环后 i++
            if (stack[i] == need[j + 1]) j++;
            // 整一段匹配成功，直接返回下标
            if (j == m) return i - m;
        }
        
        return -1;
    }
    
    public static int strStr3(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        // 分别读取原串和匹配串的长度
        int haystackLen = haystack.length();
        int needleLen = needle.length();
        // 有的解释中也会把它叫next数组，其实没什么区别只不过为了编程方便把PMT数组右移了一位, 然后把PMT[0] = 0
        // 我这还是按照我文章中讲的来，不对PMT数组进行移位，但是在实际使用的时候记着用的是当前位置-1的PMT的值
        // 并且最后一个PMT值是用不到的，因为最后一个PMT值是在他后面的位置的元素匹配失败才会用的，但已经是最后一个元素了，所以用不到
        //int[] PMT = new int[needleLen];
        // ===============先空着===============
        // 1. 构造PMT数组——注意我这构造的PMT数组比较特殊，多了一个PMT[0] = 0
        //int[] PMT = new int[]{0, 0, 1, 2, 3, 4, 0, 1};
        int[] PMT = getPTM(needle);
        // ===============先空着===============
        // 2. 匹配过程
        for (int i = 0, j = 0; i < haystackLen; i++) {
            // while相当于当前位置匹配失败了，而对于已经扫描过的和目标字符串匹配的字符串
            // 的前缀和后缀的交集{xxxx, xxx, x}的不断尝试
            // 也就是对有效起始点也就是交集的部分的匹配过程
            while (j > 0 && haystack.charAt(i) != needle.charAt(j)) {
                j = PMT[j - 1];
            }
            // 如果匹配成功，那么j向后移动一位，尝试匹配下一个位置
            if (haystack.charAt(i) == needle.charAt(j)) {
                j++;
            }
            // 当j等于needleLen的时候，说明needleLen个字符匹配成功，那也就是整个字符串完全匹配
            // 就可以返回匹配的起始位置
            if (j == needleLen) {
                return i - needleLen + 1;
            }
        }
        //没有找到匹配的字符串，返回-1
        return -1;
    }
    
    
    // 根据needle获取PTM数组
    public static int[] getPTM(String needle) {
        int[] PMT = new int[needle.length()];
        PMT[0] = 0;
        int i = 1, j = 0;
        
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(j)) {
                ++j;
                PMT[i] = j;
                ++i;
            } else {
                // 防止当第二个字符位置不相等时，j-1<0 数组越界
                if (j < 1) {
                    PMT[i] = 0;
                    i++;
                    j = 0;
                } else {
                    j = PMT[j - 1];
                }
            }
        }
        return PMT;
    }
    
}