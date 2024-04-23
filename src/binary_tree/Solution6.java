package binary_tree;

import java.util.HashMap;

/**
 * @Author Baiyu
 * @Time 2024/4/19 12:45
 * @StudentNumber 2018217662
 * @Description
 */
public class Solution6 {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        //1. 构建hashmap，方便查找根节点的位置
        HashMap<Integer, Integer> map = new HashMap<>();
        //2. 遍历inorder，存储所有节点的位置
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        //3. 递归构建二叉树
        return iterBuildTree(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
        
    }
    
    private TreeNode iterBuildTree(int[] inorder, int inorderLeft, int inorderRight,
                                   int[] postorder, int postorderLeft, int postorderRight,
                                   HashMap<Integer, Integer> indexMap) {
        // 因为构建一棵二叉树无非就是三个步骤：
        // 1. 创建根节点
        // 2. 构建左子树
        // 3. 构建右子树
        // 因此这个问题就可以按照如下求解：
        // 1. 如果左边界大于右边界，那么返回null
        if (inorderLeft > inorderRight) {
            return null;
        }
        //2. 创建根节点
        TreeNode root = new TreeNode(postorder[postorderRight]);
        //3. 获取根节点在inorder中的位置,从而可以知道左子树的节点是哪些
        int inorderRootIndex = indexMap.get(postorder[postorderRight]);
        //4. 获取左子树的节点个数
        int leftTreeSize = inorderRootIndex - inorderLeft;
        //5. 递归构建左子树
        root.left = iterBuildTree(inorder, inorderLeft, inorderRootIndex - 1,
                postorder, postorderLeft, postorderLeft + leftTreeSize - 1, indexMap);
        //6. 递归构建右子树
        root.right = iterBuildTree(inorder, inorderRootIndex + 1, inorderRight,
                postorder, postorderLeft + leftTreeSize, postorderRight - 1, indexMap);
        return root;
    }
}
