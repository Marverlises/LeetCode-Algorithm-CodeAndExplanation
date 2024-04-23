package week1;

import java.util.*;

/**
 * @Author Baiyu
 * @Time 2024/1/21 8:31
 * @StudentNumber 2018217662
 * @Description
 */
class RandomizedSet {
    List<Integer> nums;
    Map<Integer, Integer> indices;
    Random random;
    
    public RandomizedSet() {
        nums = new ArrayList<Integer>();
        indices = new HashMap<Integer, Integer>();
        random = new Random();
        
    }
    
    public boolean insert(int val) {
        if (indices.containsKey(val)) {
            return false;
        }
        int index = nums.size();
        nums.add(val);
        indices.put(val, index);
        return true;
    }
    
    public boolean remove(int val) {
        if (!indices.containsKey(val)) {
            return false;
        }
        Integer point = indices.get(val);
        int temp = nums.get(nums.size() - 1);
        nums.set(point, temp);
        indices.put(temp, point);
        nums.remove(nums.size() - 1);
        indices.remove(val);
        return true;
    }
    
    public int getRandom() {
        int i = random.nextInt(nums.size());
        return nums.get(i);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */