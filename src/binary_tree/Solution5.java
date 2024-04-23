package binary_tree;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/4/18 9:55
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution5 {
    
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // 1. 构建hashmap，方便查找根节点的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        // 2. 遍历inorder，存储所有节点的位置
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        // 3. 递归构建二叉树
        return iterBuildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }
    
    private TreeNode iterBuildTree(int[] preorder, int preorder_left, int preorder_right,
                                   int[] inorder, int inorder_left, int inorder_right,
                                   HashMap<Integer, Integer> indexMap) {
        // 因为构建一棵二叉树无非就是三个步骤：
        // 1. 创建根节点
        // 2. 构建左子树
        // 3. 构建右子树
        // 因此这个问题就可以按照如下求解：
        // 1. 如果左边界大于右边界，那么返回null
        if (preorder_left > preorder_right) {
            return null;
        }
        // 2. 创建根节点
        TreeNode root = new TreeNode(preorder[preorder_left]);
        // 3. 获取根节点在inorder中的位置,从而可以知道左子树的节点是哪些
        int inorder_root_index = indexMap.get(preorder[preorder_left]);
        // 4. 获取左子树的节点个数
        int left_tree_size = inorder_root_index - inorder_left;
        // 5. 递归构建左子树
        root.left = iterBuildTree(preorder, preorder_left + 1, preorder_left + left_tree_size,
                inorder, inorder_left, inorder_root_index - 1, indexMap);
        // 6. 递归构建右子树
        root.right = iterBuildTree(preorder, preorder_left + left_tree_size + 1, preorder_right,
                inorder, inorder_root_index + 1, inorder_right, indexMap);
        return root;
    }
}
