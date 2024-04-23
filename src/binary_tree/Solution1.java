package binary_tree;

/**
 * @Author Baiyu
 * @Time 2024/4/15 12:00
 * @StudentNumber 2018217662
 * @Description
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode() {
    }
    
    TreeNode(int val) {
        this.val = val;
    }
    
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class Solution1 {
   
    public int maxDepth(TreeNode root) {
        if (root == null){
            return 0;
        }
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    //这个题目有过一定基础的都应该知道，采用递归解决问题，因为要求一个二叉树的深度（也就是高度），其实上就是根节点的左子树和右子树中高度最高的那个。因此这个问题就可以拆解为：
    //1.求左子树的高度
    //2.求右子树的高度
    //3.取左右子树中高度最高的那个
    //4.加上根节点的高度
    //5.返回条件为：如果根节点为空，返回0
    
}

