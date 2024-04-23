package week1;

/**
 * @Author Baiyu
 * @Time 2024/1/17 7:35
 * @StudentNumber 2018217662
 * @Description 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 */
class Solution1 {
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);
        
        //removeElement(nums1, 3);
        int[] nums = {3, 2, 2, 3};
        int val = 3;
        removeElement(nums, 3);
        
        int[] nums3 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int i = removeDuplicates(nums3);
        System.out.println(i);
        
        int[] num4 = {1,1,1,2,2,3};
        int removeDuplicates2 = removeDuplicates2(num4);
        System.out.println(removeDuplicates2);
    }
    
    //1.合并两个有序数组
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int point1 = 0;
        int point2 = 0;
        int[] temp = new int[m];
        for (point1 = 0; point1 < m; point1++) {
            temp[point1] = nums1[point1];
        }
        for (point1 = 0, point2 = 0; point1 + point2 < m + n; ) {
            if (m != 0 && n == 0) {
                nums1[point1 + point2] = temp[point1];
                point1++;
                continue;
            }
            if (m == 0 && n != 0) {
                nums1[point1 + point2] = nums2[point2];
                point2++;
                continue;
            }
            if (m == 0 && n == 0) {
                break;
            }
            if (point1 < m && point2 < n) {
                if (temp[point1] < nums2[point2]) {
                    nums1[point1 + point2] = temp[point1];
                    point1++;
                } else {
                    nums1[point1 + point2] = nums2[point2];
                    point2++;
                }
                continue;
            }
            //解释下面的代码
            if (point1 == m) {
                nums1[point1 + point2] = nums2[point2];
                point2++;
            } else {
                nums1[point1 + point2] = temp[point1];
                point1++;
            }
            
        }
        System.out.println(nums1);
    }
    
    //Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of
    // the elements may be changed. Then return the number of elements in nums which are not equal to val.
    //
    //Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the
    // following things:
    //
    //Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
    // The remaining elements of nums are not important as well as the size of nums.
    //Return k.
    //2.移除元素
    public static int removeElement(int[] nums, int val) {
        int count = 0;
        int point = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                count++;
                nums[point] = nums[i];
                point++;
            }
        }
        for (int i = point; i < nums.length; i++) {
            nums[i] = 0;
        }
        return point;
    }
    
    //3.删除有序数组中的重复项
    public static int removeDuplicates(int[] nums) {
        int point = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[point]) {
                point++;
                nums[point] = nums[i];
            }
        }
        return point + 1;
    }
    
    //4.删除有序数组中的重复项 II
    public static int removeDuplicates2(int[] nums) {
        int point1 = 0;
        int point2 = 0;
        int cur = 0;
        if (nums.length <= 2) {
            return nums.length;
        }
        for (point2 = 0; point2 < nums.length; point2++) {
            //要么1个要么两个
            if (nums[point1] == nums[point2]) {
                continue;
            }
            //只有一个
            if (point2 - point1 == 1) {
                nums[cur] = nums[point1];
            }
            //大于等于两个
            if (point2 - point1 > 1) {
                nums[cur] = nums[point1];
                cur++;
                nums[cur] = nums[point1 + 1];
            }
            cur++;
            point1 = point2;
        }
        if (point2 - point1 > 1) {
            nums[cur] = nums[point1];
            cur++;
            nums[cur] = nums[point1 + 1];
        }
        if (point2 - point1 == 1) {
            nums[cur] = nums[point1];
        }
        return cur + 1;
    }
}