package week7;

/**
 * @Author Baiyu
 * @Time 2024/3/4 9:30
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {

}

//最小栈
class MinStack {
    //存储元素
    int[] elements;
    //栈顶指针
    int top;
    //最小值
    int min;
    
    public MinStack() {
        elements = new int[10];
        top = 0;
        min = Integer.MAX_VALUE;
    }
    
    public void push(int val) {
        //判断是否需要扩容
        if (top == elements.length) {
            //扩容：按照原来的长度的两倍进行扩容
            int[] temp = new int[elements.length * 2];
            //扩容后，将原来的元素拷贝到新的数组中
            System.arraycopy(elements, 0, temp, 0, elements.length);
            elements = temp;
        }
        elements[top] = val;
        //更新栈顶指针
        top++;
        //更新最小值
        if (val < min) {
            min = val;
        }
    }
    
    public void pop() {
        //判断栈是否为空
        if (top == 0) {
            return;
        }
        //判断是否需要缩容
        if (top < elements.length / 4) {
            //缩容：按照原来的长度的四分之一进行缩容
            int[] temp = new int[elements.length / 4];
            //缩容后，将原来top个元素拷贝到新的数组中
            System.arraycopy(elements, 0, temp, 0, top);
            elements = temp;
        }
        //更新最小值
        if (elements[top - 1] == min) {
            min = Integer.MAX_VALUE;
            //遍历栈中的元素，找到最小值
            for (int i = 0; i < top - 1 ; i++) {
                if (elements[i] < min) {
                    min = elements[i];
                }
            }
        }
        //top--
        top--;
        if (top == 0) {
            //栈为空，最小值重置
            min = Integer.MAX_VALUE;
        }
    }
    
    public int top() {
        //直接返回栈顶元素
        if (top == 0) {
            return 0;
        }
        return elements[top - 1];
    }
    
    public int getMin() {
        //返回最小值
        return min;
    }
}



/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */