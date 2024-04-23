package week6;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/2/23 17:53
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    // 快乐数
    //1. 思路一
    public boolean isHappy(int n) {
        //定义一个hashMap,存储平方值
        HashMap<Integer, Integer> squareMap = new HashMap<>();
        //初始化squareMap
        for (int i = 0; i < 10; i++) {
            squareMap.put(i, i * i);
        }
        //循环条件
        while (n != 1 && n != 4) {
            //定义一个变量存储n的平方和
            int sum = 0;
            //将n的每一位数的平方和存入sum
            while (n != 0) {
                sum += squareMap.get(n % 10);
                n /= 10;
            }
            //如果sum不等于1，将sum赋值给n
            n = sum;
        }
        // 如果n等于1，返回true
        // 如果不等于1（等于4），说明进入了循环，返回false
        return n == 1;
    }
    
    //2. 思路二——1
    public boolean isHappy2(int n) {
        //定义一个hashMap,存储平方值
        HashMap<Integer, Integer> squareMap = new HashMap<>();
        //定义一个hashMap，保存走过的值
        HashMap<Integer, Integer> numMap = new HashMap<>();
        //初始化squareMap
        for (int i = 0; i < 10; i++) {
            squareMap.put(i, i * i);
        }
        //进行循环
        //如果n等于1，表示成功，那么结束循环
        //如果我们之前走过n，那么说明进入了循环，结束循环
        while (n != 1) {
            //将n存入numMap
            numMap.put(n, 1);
            //定义一个变量存储n的平方和
            int sum = 0;
            //将n的每一位数的平方和存入sum
            while (n != 0) {
                sum += squareMap.get(n % 10);
                n /= 10;
            }
            //如果得到了之前走过的值，结束循环
            if (numMap.containsKey(sum)) {
                return false;
            }
                //将sum赋值给n
            n = sum;
        }
        return true;
    }
    
    //2. 思路二——2
    public int squareSum(HashMap<Integer, Integer> squareMap, int n) {
        int sum = 0;
        while (n != 0) {
            sum += squareMap.get(n % 10);
            n /= 10;
        }
        return sum;
    }
    public boolean isHappy3(int n) {
        //定义一个hashMap,存储平方值
        HashMap<Integer, Integer> squareMap = new HashMap<>();
        //初始化squareMap
        for (int i = 0; i < 10; i++) {
            squareMap.put(i, i * i);
        }
        //定义两个指针，一个快指针，一个慢指针，表示两个速度不同的跑步者
        int slow = n, fast = squareSum(squareMap, n);
        //进行循环
        //如果n等于1，表示成功，那么结束循环
        //如果快指针和慢指针相等，说明进入了循环，结束循环
        while (!(fast == 1 || fast == slow)) {
            //速度慢的走一步
            slow = squareSum(squareMap, slow);
            //速度快的走两步
            fast = squareSum(squareMap, squareSum(squareMap, fast));
        }
        //如果快指针等于1，返回true，否则返回false
        return fast == 1;
    }
}
