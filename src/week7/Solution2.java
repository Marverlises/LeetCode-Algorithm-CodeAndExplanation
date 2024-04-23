package week7;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author Baiyu
 * @Time 2024/3/2 10:50
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    //有效的括号
    //思路1：用栈来解决
    public boolean isValid(String s) {
        //1. 定义一个栈
        Stack<Character> characters = new Stack<>();
        //2. 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            //3. 如果栈顶元素和当前元素匹配，就出栈
            if (!characters.isEmpty() && (characters.peek() == '(' && s.charAt(i) == ')'
                    || characters.peek() == '[' && s.charAt(i) == ']'
                    || characters.peek() == '{' && s.charAt(i) == '}')) {
                characters.pop();
            }
            //4. 如果栈顶元素和当前元素不匹配，就入栈
            else {
                characters.push(s.charAt(i));
            }
        }
        //5. 如果栈为空，说明括号匹配
        return characters.isEmpty();
    }
    
    //思路2：指针优化
    public boolean isValid2(String s) {
        //1. 定义两个指针
        int i = 0;
        //定义一个变量，用来记录栈顶元素的位置，
        //之所以是-1，是因为栈顶元素的位置是从0开始的，首个元素肯定是入栈的，所以这里初始化为-1，++后就是0
        int peek = -1;
        //2. 使用i指针遍历字符串
        for ( i = 0; i < s.length(); i++) {
            //3. 如果栈顶元素和当前元素匹配，就出栈
            if (peek >= 0 && (s.charAt(peek) == '(' && s.charAt(i) == ')'
                    || s.charAt(peek) == '[' && s.charAt(i) == ']'
                    || s.charAt(peek) == '{' && s.charAt(i) == '}')) {
                peek--;
                //把出栈的部分删除，因为这部分已经匹配的会影响后面的匹配
                s = s.substring(0, peek + 1) + s.substring(i + 1);
                //更新i的值
                i = peek;
            }
            //4. 如果栈顶元素和当前元素不匹配，就入栈
            else {
                peek++;
            }
        }
        //5. 如果栈为空，说明括号匹配
        return peek == -1;
    }
    
    //思路3：用map来存储括号的对应关系,根据括号方向来判断
    public boolean isValid3(String s) {
        //1. 定义一个map，用来存储括号的对应关系
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        //2. 定义一个栈
         Stack<Character> characters = new Stack<>();
        //3. 遍历字符串
        for (int i = 0; i < s.length(); i++) {
            //4. 如果当前元素是右括号
            if (map.containsKey(s.charAt(i))) {
                //5. 如果栈为空，说明没有左括号与之匹配
                if (characters.isEmpty()) {
                    return false;
                }
                //6. 如果栈顶元素和当前元素不匹配，说明括号不匹配
                if (characters.peek() != map.get(s.charAt(i))) {
                    return false;
                }
                //7. 如果栈顶元素和当前元素匹配，就出栈
                characters.pop();
            }
            //8. 如果当前元素是左括号，就入栈
            else {
                characters.push(s.charAt(i));
            }
        }
        //9. 如果栈为空，说明括号匹配
        return characters.isEmpty();
    }
}
