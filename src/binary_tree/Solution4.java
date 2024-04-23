package binary_tree;

import sun.reflect.generics.tree.Tree;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * @Author Baiyu
 * @Time 2024/4/17 14:03
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution4 {
    public boolean isSymmetric1(TreeNode root) {
        return check(root, root);
    }
    
    public boolean check(TreeNode p, TreeNode q) {
        // 1.如果两个节点都为空，那么返回true
        if (p == null && q == null) {
            return true;
        }
        // 2.如果两个节点中有一个为空，那么返回false
        if (p == null || q == null) {
            return false;
        }
        // 3.如果两个节点的值不相等，那么返回false，确保根节点的值相等
        //   如果根节点的左值与另一个根节点的右值不等，或者根节点的右值与另一个根节点的左值不等，那么返回false
        //   只有当根节点的值相等，且根节点的左右子节点分别与另一个根节点的右左子节点相等时，才返回true
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }
    
    public boolean isSymmetric2(TreeNode root) {
        return check2(root, root);
    }
    
    public boolean check2(TreeNode u, TreeNode v) {
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        // offer 添加元素到队列
        q.offer(u);
        q.offer(v);
        while (!q.isEmpty()) {
            u = q.poll();
            v = q.poll();
            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }
            
            q.offer(u.left);
            q.offer(v.right);
            
            q.offer(u.right);
            q.offer(v.left);
        }
        return true;
    }
    
    public boolean isSymmetric3(TreeNode root) {
        // 通过翻转左子树，判断左子树和右子树是否相等就相当于判断是否对称
        root.left = reverse(root.left);
        return isSameTree(root.left, root.right);
    }
    
    private boolean isSameTree(TreeNode left, TreeNode right) {
        // 递归判断左右子树是否相等
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        return left.val == right.val && isSameTree(left.left, right.left) && isSameTree(left.right, right.right);
    }
    
    private TreeNode reverse(TreeNode root) {
        // 递归翻转二叉树
        if (root == null) {
            return null;
        }
        TreeNode temp = reverse(root.left);
        root.left = reverse(root.right);
        root.right = temp;
        return root;
    }
    
    public static void main(String[] args) {
        //test isSymmetric2 [1,2,2,3,4,4,3]
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(2);
        TreeNode leftleft = new TreeNode(3);
        TreeNode leftright = new TreeNode(4);
        TreeNode rightleft = new TreeNode(4);
        TreeNode rightright = new TreeNode(3);
        root.left = left;
        root.right = right;
        left.left = leftleft;
        left.right = leftright;
        right.left = rightleft;
        right.right = rightright;
        Solution4 solution4 = new Solution4();
        System.out.println(solution4.isSymmetric2(root));
        
        
    }
}
