package week8;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @Author Baiyu
 * @Time 2024/3/6 9:50
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution1 {
    //基本计算器
    //思路1：使用一个栈，一个临时栈
    public int calculate(String s) {
            //1.创建一个栈
            Stack<String> ret = new Stack<>();
            //2.去掉空格
            s = s.replaceAll(" ", "");
            //两个临时变量，一个存一个（）中的计算结果，一个存弹出内容的临时栈
            int sum = 0;
            Stack<String> tempStack = new Stack<>();   //临时栈
            //2.遍历字符串的每一个字符
            for (int i = 0; i < s.length(); i++) {
                //3.判断当前字符是否是数字
                if (Character.isDigit(s.charAt(i))) {
                    //4.如果是数字，找到数字的结束位置
                    int j = i;
                    while (j < s.length() && Character.isDigit(s.charAt(j))) {
                        j++;
                    }
                    //5.将数字入栈
                    ret.push(s.substring(i, j));
                    //6.更新i的值
                    i = j - 1;
                } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                    //7.如果是+或者-，将其入栈
                    ret.push(String.valueOf(s.charAt(i)));
                } else if (s.charAt(i) == '(') {
                    //8.如果是(，将其入栈
                    ret.push(String.valueOf(s.charAt(i)));
                } else if (s.charAt(i) == ')') {
                    //9.如果是)，计算栈中的表达式的值
                    sum = 0;
                    tempStack.clear();
                    //10.将栈中的元素弹出，直到遇到(,并将弹出的元素入栈
                    while (!ret.peek().equals("(")) {
                        tempStack.push(ret.pop());
                    }
                    //11.弹出"("
                    ret.pop();
                    //12.计算表达式的值
                    sum = getSum(tempStack);
                    //13.将计算的结果入栈
                    ret.push(String.valueOf(sum));
                }
            }
            //14.计算栈中的剩余表达式的值——因为栈中可能还有表达式（之前只是把括号中的内容计算完毕了）
            //将栈中的元素弹出，直到栈为空
            while (!ret.isEmpty()) {
                tempStack.push(ret.pop());
            }
            //计算表达式的值
            sum = getSum( tempStack);
            //15.返回计算的结果
            return sum;
    }
    
    private int getSum(Stack<String> tempStack) {
        int sum = 0;
        while (!tempStack.isEmpty()) {
            String temp = tempStack.pop();
            if (temp.equals("+")) {
                sum += Integer.parseInt(tempStack.pop());
            } else if (temp.equals("-")) {
                sum -= Integer.parseInt(tempStack.pop());
            } else {
                sum = Integer.parseInt(temp);
            }
        }
        return sum;
    }
    
    
    //思路2：使用两个栈，一个存数字，一个存符号
    public int calculate2(String s) {
        //1.创建两个栈
        //一个存数字
        Stack<Integer> numStack = new Stack<>();
        //一个存符号
        Stack<Character> opStack = new Stack<>();
        //2.去掉空格
        s = s.replaceAll(" ", "");
        //3.遍历字符串的每一个字符
        for (int i = 0; i < s.length(); i++) {
            //对于首字符是-的情况，需要先在-前面加个0，这样就能保证所有运算都是两个数字之间的运算
            if (i == 0 && s.charAt(i) == '-') {
                numStack.push(0);
                opStack.push('-');
                continue;
            }
            //4.判断当前字符是否是数字
            if (Character.isDigit(s.charAt(i))) {
                //5.如果是数字，找到数字的结束位置
                int j = i;
                while (j < s.length() && Character.isDigit(s.charAt(j))) {
                    j++;
                }
                //6.将数字入栈
                numStack.push(Integer.parseInt(s.substring(i, j)));
                //7.更新i的值
                i = j - 1;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                //注意对于类似这样"1-(-2)"的情况，需要先在-前面加个0，这样就能保证所有运算都是两个数字之间的运算
                //也就是说，如果当前字符是+或者-，并且前面是(或者+或者-，就在前面加个0
                if (i > 0 && (s.charAt(i - 1) == '(' || s.charAt(i - 1) == '+' || s.charAt(i - 1) == '-')) {
                    numStack.push(0);
                }
                //8.如果是+或者-，先计算栈中可以计算部分的值——也就是左括号或者栈为空之后的部分
                getSum(numStack, opStack);
                //最后将当前的符号入栈
                opStack.push(s.charAt(i));
            } else if (s.charAt(i) == '(') {
                //9.如果是(，将其入栈
                opStack.push(s.charAt(i));
            } else if (s.charAt(i) == ')') {
                //10.如果是)，计算栈中的表达式的值
                getSum(numStack, opStack);
                //11.弹出"("
                opStack.pop();
            }
        }
        //12.计算栈中的剩余表达式的值
        getSum(numStack, opStack);
        //13.返回计算的结果
        return numStack.pop();
    }
    
    private void getSum(Stack<Integer> numStack, Stack<Character> opStack) {
        while (!opStack.isEmpty() && opStack.peek() != '(') {
            //弹出两个数字和一个符号，计算结果
            int num2 = numStack.pop();
            int num1 = numStack.pop();
            char op = opStack.pop();
            //因为只有+和-，所以就以下两种情况
            if (op == '+') {
                numStack.push(num1 + num2);
            } else {
                numStack.push(num1 - num2);
            }
        }
    }
    
    
    
    
    
    
    //================================================================================================
    public int calculate3(String s) {
        // 用一个栈保存正负号，栈顶元素表示当前位置的正负号
        Deque<Integer> ops = new LinkedList<Integer>();
        // 初始时，栈顶元素为1，表示当前无需翻转符号
        ops.push(1);
        // 1表示正数，-1表示负数（对应符号不反转和反转）
        int sign = 1;
        // 结果
        int ret = 0;
        int n = s.length();
        int i = 0;
        // 遍历字符串
        while (i < n) {
            if (s.charAt(i) == ' ') {
                i++;
            // 如果是+，则正负号不变
            } else if (s.charAt(i) == '+') {
                sign = ops.peek();
                i++;
            // 如果是-，则正负号反转
            } else if (s.charAt(i) == '-') {
                sign = -ops.peek();
                i++;
            // 如果是(，则将当前的正负号入栈，此时栈顶元素表示当前括号内的计算是否需要翻转符号
            } else if (s.charAt(i) == '(') {
                ops.push(sign);
                i++;
            // 如果是)，则将当前的正负号出栈，表示括号内的计算结束，不需要再存储左括号前面的正负号
            } else if (s.charAt(i) == ')') {
                ops.pop();
                i++;
            // 如果是数字，计算结果
            } else {
                long num = 0;
                // 将数字取出
                while (i < n && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + s.charAt(i) - '0';
                    i++;
                }
                // 根据当前是否需要翻转符号，计算结果
                ret += sign * num;
            }
        }
        return ret;
    }
    
    public int calculate4(String s) {
        // 存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        nums.addLast(0);
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        // 存放所有的操作，包括 +/-
        Deque<Character> ops = new ArrayDeque<>();
        int n = s.length();
        // 用于存放数字的临时变量，注意可能有多位数
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.addLast(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char op = ops.peekLast();
                    if (op != '(') {
                        calc(nums, ops);
                    } else {
                        ops.pollLast();
                        break;
                    }
                }
            } else {
                if (isNum(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNum(cs[j])) u = u * 10 + (int)(cs[j++] - '0');
                    nums.addLast(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peekLast() != '(') calc(nums, ops);
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty()) calc(nums, ops);
        return nums.peekLast();
    }
    void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast(), a = nums.pollLast();
        char op = ops.pollLast();
        nums.addLast(op == '+' ? a + b : a - b);
    }
    boolean isNum(char c) {
        return Character.isDigit(c);
    }
    
}
