package week1;

import java.util.Arrays;

/**
 * @Author Baiyu
 * @Time 2024/1/21 8:38
 * @StudentNumber 2018217662
 * @Description
 */
public class MyArrayList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elementData;
    private int size = 0;
    
    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }
    
    public void add(E e) {
        ensureCapacity();
        elementData[size++] = e;
    }
    
    public void display() {
        System.out.print("ArrayList Elements: ");
        for (int i = 0; i < size; i++) {
            System.out.print(elementData[i] + " ");
        }
        System.out.println();
    }
    
    private void ensureCapacity() {
        if (size == elementData.length) {
            int newCapacity = elementData.length * 2;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + index);
        }
        
        E removedElement = (E) elementData[index];
        
        // 将被删除元素后的元素向前移动
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }
        
        // 将最后一个元素置为null，以便垃圾回收
        elementData[--size] = null;
        
        // 缩容，如果元素个数小于数组长度的一半
        if (size < elementData.length / 2) {
            resize();
        }
        
        return removedElement;
    }
    
    // 缩容方法
    private void resize() {
        int newCapacity = elementData.length / 2;
        elementData = Arrays.copyOf(elementData, Math.max(DEFAULT_CAPACITY, newCapacity));
    }
    
}
