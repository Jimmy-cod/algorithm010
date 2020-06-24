package Week03;

import java.util.Deque;
import java.util.LinkedList;

public class LC111_min_depth_b_tree {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        //1.左孩子和有孩子都为空的情况，说明到达了叶子节点，直接返回1即可
        if (root.right == null && root.left == null) return 1;

        int leftDep = minDepth(root.left);
        int rightDep = minDepth(root.right);

        //当节点左右孩子有一个为空时，返回不为空的孩子节点的深度
        //as sometimes return leftDep, sometime return rightDep, need to be  rightDep +leftDep + 1
        if (root.right == null || root.left == null) return rightDep +leftDep + 1;

        //当节点左右孩子都不为空时，返回左右孩子较小深度的节点值
        return Math.min(leftDep,rightDep)+1;

    }


    public int maxDepth_2(TreeNode root) {

        if (root == null) return 0;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        int level = 0;
        while (!queue.isEmpty()){
            level++;
            int size = queue.size();
            for (int i = 0; i< size; i++){
                TreeNode curr = queue.pollLast();
                //when left and right chile are null, mean it's a leaf of tree
                //we're looking for min, no need to drill down
                if (curr.left == null && curr.right == null){
                    return level;
                }
                if (curr.left != null) {
                    queue.addFirst(curr.left);
                }
                if (curr.right !=null) {
                    queue.addFirst(curr.right);
                }
            }
        }
        //should never reach here.
        return -1;
    }
}
