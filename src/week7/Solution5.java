package week7;

import java.util.Stack;

/**
 * @Author Baiyu
 * @Time 2024/3/5 9:36
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution5 {
    //逆波兰表达式求值
    //思路一：使用栈
    public int evalRPN(String[] tokens) {
        //1.创建一个栈
        Stack<Integer> ret = new Stack<>();
        //2.遍历数组
        for (int i = 0; i < tokens.length; i++) {
            //3.判断当前元素是否是数字,使用正则表达式判断
            // -?表示负号出现0次或1次，\\d+表示数字出现1次或多次
            if (tokens[i].matches("-?\\d+")) {
                //4.如果是数字，将其入栈
                ret.push(Integer.parseInt(tokens[i]));
            } else {
                //5.如果是运算符，从栈中弹出两个元素进行运算，并将结果入栈
                int num2 = ret.pop();
                int num1 = ret.pop();
                switch (tokens[i]) {
                    case "+":
                        ret.push(num1 + num2);
                        break;
                    case "-":
                        ret.push(num1 - num2);
                        break;
                    case "*":
                        ret.push(num1 * num2);
                        break;
                    case "/":
                        ret.push(num1 / num2);
                        break;
                }
            }
        }
        //6.返回栈顶元素
        return ret.pop();
    }
    
    //思路一：使用栈
    public int evalRPN2(String[] tokens) {
        //1.创建一个栈
        Stack<Integer> ret = new Stack<>();
        //2.遍历数组
        for (int i = 0; i < tokens.length; i++) {
            //3.判断当前元素是否是数字,使用正则表达式判断
            // -?表示负号出现0次或1次，\\d+表示数字出现1次或多次
            if (isNumber(tokens[i])) {
                //4.如果是数字，将其入栈
                ret.push(Integer.parseInt(tokens[i]));
            } else {
                //5.如果是运算符，从栈中弹出两个元素进行运算，并将结果入栈
                int num2 = ret.pop();
                int num1 = ret.pop();
                switch (tokens[i]) {
                    case "+":
                        ret.push(num1 + num2);
                        break;
                    case "-":
                        ret.push(num1 - num2);
                        break;
                    case "*":
                        ret.push(num1 * num2);
                        break;
                    case "/":
                        ret.push(num1 / num2);
                        break;
                }
            }
        }
        //6.返回栈顶元素
        return ret.pop();
    }
    
    public boolean isNumber(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }
    
    public int evalRPN3(String[] tokens) {
        int n = tokens.length;
        //创建一个数组模拟栈，因为根据题目要求，栈的大小不会超过n/2+1
        int[] stack = new int[(n + 1) / 2];
        //栈顶指针
        int index = -1;
        //遍历数组
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            //判断当前元素是数字还是操作符
            switch (token) {
                case "+":
                    index--;
                    stack[index] += stack[index + 1];
                    break;
                case "-":
                    index--;
                    stack[index] -= stack[index + 1];
                    break;
                case "*":
                    index--;
                    stack[index] *= stack[index + 1];
                    break;
                case "/":
                    index--;
                    stack[index] /= stack[index + 1];
                    break;
                //如果是数字，直接入栈
                default:
                    index++;
                    stack[index] = Integer.parseInt(token);
            }
        }
        return stack[index];
    }
}
