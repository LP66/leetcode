import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @description: 利用树的特性解决的问题
 * @author: 吕立屏
 */
public class MyTree {


}


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


/**
 * @decription: 94.二叉树的中序遍历
 * @solution: 利用递归，把访问的操作夹在递归中间
 * @difficulty: 中等, 二叉树的遍历每个节点只会被访问一次, O(n)
 * @url: https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
 * @date: 2021/03/04
 */
class Solution94 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        inorder(root, list);
        return list;
    }

    void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }
}


/**
 * @decription: 226.翻转二叉树
 * @solution: 递归前序遍历加swap
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/invert-binary-tree/
 * @date: 2021/3/4
 */
class Solution226 {
    public TreeNode invertTree(TreeNode root) {
        preorder(root);
        return root;
    }

    void preorder(TreeNode root) {
        if (root == null) return;
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        preorder(root.left);
        preorder(root.right);
    }
}


/**
 * @decription: 98.验证二叉搜索树
 * @solution: 利用递归遍历二叉搜索树，设置上下界以保证当前节点在左则小于父节点，在右则大于父节点，
 * 利用递归的特性，max越来越大，min越来越小可以保证整颗子树一直保持变大或变小
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/validate-binary-search-tree/
 * @date: 2021/3/5
 */
class Solution98 {

    boolean res = true;

    public boolean isValidBST(TreeNode root) {
        validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
        return res;
    }

    void validate(TreeNode node, long min, long max) {
        if (node == null) return;
        if (node.val <= min || node.val >= max) {
            res = false;
            return;
        }
        validate(node.left, min, node.val);
        validate(node.right, node.val, max);
    }
}


/**
 * @decription: 104.二叉树的最大深度
 * @solution: 递归比较选择左右同时+1
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
 * @date: 2021/3/5
 */
class Solution104 {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}


/**
 * @decription: 111.二叉树的最小深度
 * @solution: 空节点一侧不参与计算，计算一侧保持加1
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
 * @date: 2021/3/5
 */
class Solution111 {
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left != null && root.right == null) {
            return minDepth(root.left) + 1;
        }
        if (root.right != null && root.left == null) {
            return minDepth(root.right) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}


/**
 * @decription: 236.二叉树的最近公共祖先
 * @solution: 难点在于确认 最近的 公共祖先，寻找可以交给递归来完成
 * 最近公共祖先区别于公共祖先的特点在于两侧分别连接着p、q，而其他公共祖先只能有一侧连接着p、q
 * 那么确认当前节点是最近公共祖先有两种情况：1：当前节点两侧分别连接有p、q 2：当前节点就是p或q
 * 那么便剩下递归寻找的部分，根据递归的思想，认为函数的设计已经能返回一侧祖先
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * @date: 2021/3/5
 */
class Solution236 {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode leftAncestor = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAncestor = lowestCommonAncestor(root.right, p, q);
        if (leftAncestor == null) return rightAncestor;
        if (rightAncestor == null) return leftAncestor;
        return root;
    }
}


/**
 * @decription: 102.二叉树的层序遍历
 * @solution: bfs，背起来
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
 * @date: 2021/3/11
 */
class Solution102 {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int n = queue.size();
            // 这个for i -> n 很妙，可以保证只将这一层加入level，下一层加入queue，并在处理完后将当前层加入结果集
            for (int i = 0; i < n; i++) {
                TreeNode current = queue.poll();
                level.add(current.val);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            res.add(level);
        }
        return res;
    }
}


/**
 * @decription: 515.在每个树行中找最大值
 * @solution: 层序遍历，每层比较max
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/
 * @date: 2021/3/16
 */
class Solution515 {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            int n = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                TreeNode current = queue.poll();
                max = Math.max(current.val, max);
                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}


/**
 * @decription: 230.二叉搜索树中第k小的元素
 * @solution: 二叉搜索树满足性质：中序遍历，元素value递增，中序逆遍历，元素value递减
 *            所以找第k小，只要中序中判断k次就能到达第k小元素
 *            如果要找第k大，用中序逆遍历中判断k次便可
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/
 * @date: 2021/3/18
 */
class Solution230 {

    int target;
    int current;
    int res;

    public int kthSmallest(TreeNode root, int k) {
        target = k;
        inorder(root);
        return res;
    }

    void inorder(TreeNode node) {
        if (node == null) {
            return;
        }
        inorder(node.left);
        if (++current == target) {
            res = node.val;
            return;
        }
        inorder(node.right);
    }

}