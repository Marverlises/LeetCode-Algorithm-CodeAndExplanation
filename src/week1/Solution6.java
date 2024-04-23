package week1;

/**
 * @Author Baiyu
 * @Time 2024/1/24 14:38
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution6 {
    
    public static int romanToInt(String s) {
        //字符          数值
        //I             1
        //V             5
        //X             10
        //L             50
        //C             100
        //D             500
        //M             1000
        int sum = 0;
        int i = 0;
        for (i = 0; i < s.length() - 1; i++) {
            int temp = i + 1;
            int[] a = getNum(i, temp, s, sum);
            sum = a[0];
            i = a[1];
        }
        //已经把最后一个算了
        if (i >= s.length()){
            return sum;
        }
        sum = getNum(s.length() - 1, s.length() - 1, s, sum)[0];
        return sum;
    }
    
    public static int[] getNum(int i, int temp, String s, int sum) {
        int[] ret = new int[2];
        switch (s.charAt(i)) {
            case 'I':
                //分为1，2，3,IV,IX
                switch (s.charAt(temp)) {
                    case 'V':
                        sum += 4;
                        i++;
                        break;
                    case 'X':
                        sum += 9;
                        i++;
                        break;
                    default:
                        sum += 1;
                        break;
                }
                break;
            case 'V':
                sum += 5;
                break;
            case 'X':
                //分为X,XL,XC
                switch (s.charAt(temp)) {
                    case 'L':
                        sum += 40;
                        i++;
                        break;
                    case 'C':
                        sum += 90;
                        i++;
                        break;
                    default:
                        sum += 10;
                        break;
                }
                break;
            case 'L':
                sum += 50;
                break;
            case 'C':
                //分为C,CD,CM
                switch (s.charAt(temp)) {
                    case 'D':
                        sum += 400;
                        i++;
                        break;
                    case 'M':
                        sum += 900;
                        i++;
                        break;
                    default:
                        sum += 100;
                        break;
                }
                break;
            case 'D':
                sum += 500;
                break;
            case 'M':
                sum += 1000;
                break;
        }
        ret[0] = sum;
        ret[1] = i;
        return ret;
    }
    
}
