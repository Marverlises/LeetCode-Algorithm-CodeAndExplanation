package binary_tree;

/**
 * @Author Baiyu
 * @Time 2024/4/15 12:16
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution2 {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        //1.如果两个节点都为空，那么返回true
        if (p == null && q == null){
            return true;
        }
        //2.如果两个节点中有一个为空，那么返回false
        if (p == null || q == null){
            return false;
        }
        //3. 遍历两个节点的左子树和右子树判断是否相等——因为如果两个二叉树完全相同，
        // 那么两个二叉树的根节点的值相等，且它们的左子树和右子树也分别相等，左右子树又可以当作新的根节点进行判断
        if (p.val != q.val){
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
