package week3;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Baiyu
 * @Time 2024/2/3 8:33
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution1 {
    // 文本左右对齐
    public List<String> fullJustify(String[] words, int maxWidth) {
        ArrayList<String> lineRet = new ArrayList<>();
        int len = 0;
        int wordCount = 0;
        ArrayList<String> ret = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            // 保证每一个单词后有一个空格
            int strLen = word.length() + 1;
            len += strLen;
            // 可以放入当前行——len-1是因为最后一个单词后没有空格
            if (len - 1 <= maxWidth) {
                //当是最后一行
                if (i == words.length - 1) {
                    StringBuilder lastLine = new StringBuilder();
                    // 最后一行的单词之间只有一个空格，末尾要加空格
                    for (String item : lineRet) {
                        lastLine.append(item).append(" ");
                    }
                    // 加上当前单词
                    lastLine.append(word);
                    // 补充结尾空格
                    for (int j = lastLine.length(); j < maxWidth; j++) {
                        lastLine.append(" ");
                    }
                    ret.add(lastLine.toString());
                } else {
                    lineRet.add(word);
                    wordCount++;
                }
            } else {
                // 放不下
                // 如果只有一个单词，左对齐
                if (wordCount == 1) {
                    int spaceLen = maxWidth - lineRet.get(0).length();
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < spaceLen; j++) {
                        sb.append(" ");
                    }
                    String spaces = sb.toString();
                    ret.add(lineRet.get(0) + spaces);
                } else {
                    // 如果包含很多个单词，那就左右对齐，同时保证空格数尽量平均，且左边更多
                    int spaceCount = maxWidth - len + strLen + wordCount;
                    // 获取左右对齐的字符串
                    StringBuilder sb = getSb(spaceCount, wordCount, lineRet);
                    ret.add(sb.toString());
                }
                lineRet.clear();
                len = 0;
                wordCount = 0;
                i--;
            }
        }
        return ret;
    }
    
    /**
     * 获取左右对齐的字符串
     *
     * @param spaceCount 总共的空格数
     * @param wordCount  单词数
     * @param lineRet    当前行的单词
     * @return 左右对齐的字符串
     */
    private static StringBuilder getSb(int spaceCount, int wordCount, ArrayList<String> lineRet) {
        // 每个间隔放几个空格
        int space = spaceCount / (wordCount - 1);
        // 多余的空格
        int extraSpace = spaceCount % (wordCount - 1);
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < lineRet.size(); j++) {
            sb.append(lineRet.get(j));
            // 最后一个单词后不加空格
            if (j != lineRet.size() - 1) {
                for (int k = 0; k < space; k++) {
                    sb.append(" ");
                }
                // 每一次进来就减少一个多余的空格，也就相当于我左边肯定比右边空格多
                if (extraSpace > 0) {
                    sb.append(" ");
                    extraSpace--;
                }
            }
        }
        return sb;
    }
}
