package binary_tree;

/**
 * @Author Baiyu
 * @Time 2024/4/17 11:37
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution3 {
    public TreeNode invertTree(TreeNode root) {
        //1.如果根节点为空，那么返回null
        if (root == null){
            return null;
        }
        //2.递归交换左右子树.temp是为了防止左右子树交换后丢失
        TreeNode temp = invertTree(root.right);
        root.right = invertTree(root.left);
        root.left = temp;
        //3.返回根节点
        return root;
    }
    
    
    public static void main(String[] args) {
        //test case
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(7);
        TreeNode leftleft = new TreeNode(1);
        TreeNode leftright = new TreeNode(3);
        TreeNode rightleft = new TreeNode(6);
        TreeNode rightright = new TreeNode(9);
        root.left = left;
        root.right = right;
        left.left = leftleft;
        left.right = leftright;
        right.left = rightleft;
        right.right = rightright;
        Solution3 solution3 = new Solution3();
        TreeNode treeNode = solution3.invertTree(root);
        System.out.println(treeNode);
        
    }
}
