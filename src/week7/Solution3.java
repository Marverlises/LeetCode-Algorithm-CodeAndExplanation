package week7;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @Author Baiyu
 * @Time 2024/3/3 9:26
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    //简化路径
    //思路一
    public String simplifyPath(String path) {
        //1. 定义结果字符串，用一个字符数组最好（方便后续按照块删除）
        ArrayList<String> ret = new ArrayList<>();
        // temp用来存储每一个块
        StringBuilder temp = new StringBuilder();
        //2. 遍历字符串
        for (int i = 0; i < path.length(); i++) {
            //截取下一个 `/`之前的字符
            while (i < path.length() && path.charAt(i) != '/') {
                temp.append(path.charAt(i));
                i++;
            }
            //判断截取出的字符串是否是 `..`
            if (temp.toString().equals("..")) {
                //如果是 `..`，只要上一级不是“/”，就删除上一个块
                if (!ret.isEmpty()) {
                    ret.remove(ret.size() - 1);
                }
                //清空temp
                temp = new StringBuilder();
            }
            //判断截取出的字符串是否是 `.`
            else if (temp.toString().equals(".")) {
                //清空temp
                temp = new StringBuilder();
            }
            //判断截取出的字符串是否是 `/`
            else if (temp.toString().equals("/") || temp.toString().equals("")) {
                continue;
            } else {
                //发现是其它字符串，就添加到结果中
                ret.add("/" + temp.toString());
                //清空temp
                temp = new StringBuilder();
            }
        }
        //3. 如果结果为空，返回 `/`
        if (ret.isEmpty()) {
            return "/";
        }
        //4. 如果结果不为空，就把结果拼接起来
        StringBuilder result = new StringBuilder();
        for (String s : ret) {
            result.append(s);
        }
        return result.toString();
    }
    
    //思路二
    public String simplifyPath2(String path) {
        //1. 定义一个栈
        Stack<String> stack = new Stack<>();
        //2. 定义一个字符串数组，用来存储每一个块
        String[] paths = path.split("/");
        //3. 遍历字符串数组
        for (String s : paths) {
            //如果是 `..`，就出栈
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
            //如果是 `.`，就不做任何操作
            else if (s.equals(".") || s.equals("")) {
                continue;
            }
            //如果是其它字符串，就入栈
            else {
                stack.push(s);
            }
        }
        //4. 如果栈为空，返回 `/`
        if (stack.isEmpty()) {
            return "/";
        }
        //5. 如果栈不为空，就把栈中的元素拼接起来
        StringBuilder result = new StringBuilder();
        for (String s : stack) {
            result.append("/").append(s);
        }
        return result.toString();
    }
}
