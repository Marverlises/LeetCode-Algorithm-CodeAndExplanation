package binary_tree;

import java.util.Stack;

/**
 * @Author Baiyu
 * @Time 2024/4/22 22:01
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution8 {
    // 创建一个栈，用于存储丢失的右子树节点
    Stack<TreeNode> stack = new Stack<>();
    
    public void flatten(TreeNode root) {
        createLinkedList(root);
    }
    
    private TreeNode createLinkedList(TreeNode node) {
        // 1. 如果节点为空，那么返回null
        if (node == null) {
            return null;
        }
        // 2. 如果节点的右子树不为空，那么将右子树压入栈中
        if (node.right != null) {
            stack.push(node.right);
        }
        // 3. 如果节点的左子树不为空，那么递归构建左子树的链表
        if (node.left != null) {
            node.right = createLinkedList(node.left);
            node.left = null;
        }
        // 4. 如果栈不为空，那么递归构建右子树的链表
        if (!stack.isEmpty()) {
            node.right = createLinkedList(stack.pop());
        }
        // 5. 返回当前节点
        return node;
    }
    
    public void flatten2(TreeNode root) {
        while (root != null){
            //1. 如果左子树为空，那么直接遍历右子树
            if (root.left != null){
                TreeNode pre = root.left;
                // 2. 找到左子树的最右节点
                while (pre.right != null){
                    pre = pre.right;
                }
                // 3. 将右子树接到左子树的最右节点上
                pre.right = root.right;
                // 4. 将左子树接到右子树上
                root.right = root.left;
                root.left = null;
            }
            // 5. 移动到下一个节点
            root = root.right;
        }
    }
    
    // pre节点用于存储上一个节点
    TreeNode pre = null;
    public void flatten3(TreeNode root) {
        postOrder(root);
    }
    
    private void postOrder(TreeNode root) {
        // 1. 如果节点为空，那么返回
        if (root == null) {
            return;
        }
        // 2. 后序遍历 右子树 -> 左子树 -> 根节点
        postOrder(root.right);
        postOrder(root.left);
        // 3. 将当前节点的右子树指向上一个节点
        root.right = pre;
        root.left = null;
        // 4. 将当前节点赋值给pre，更新pre
        pre = root;
    }
    
    
    public static void main(String[] args) {
        // test case
        TreeNode root = new TreeNode(1);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(5);
        TreeNode leftleft = new TreeNode(3);
        TreeNode leftright = new TreeNode(4);
        TreeNode rightright = new TreeNode(6);
        root.left = left;
        root.right = right;
        left.left = leftleft;
        left.right = leftright;
        right.right = rightright;
        Solution8 solution8 = new Solution8();
        solution8.flatten(root);
        System.out.println(root);
    }
}
