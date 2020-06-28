package Week03;

import java.util.HashMap;

public class LC105_construct_binary_tree_from_preorder {

    //1.pick up root value of sub_tre from preorder
    //2.find the root value index in inorder arr (using HashMap to keep the value-index of inorder)
    //3.假设根节点在中序遍历中索引为 index。从 in_left 到 index - 1 属于左子树，从 index + 1 到 in_right 属于右子树。
    //4.pre_idx must be globe value

    int pre_idx =0;
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        int length = preorder.length;

        HashMap<Integer,Integer> in_map = new HashMap<>();
        for (int i=0;i<length;i++){
            in_map.put(inorder[i],i);
        }

        return dps(0,length-1,in_map,preorder);
    }

    public TreeNode  dps(int in_left, int in_right, HashMap<Integer,Integer> in_map, int[]preorder){
        if (in_left>in_right){
            return null;
        }

        int root_val = preorder[pre_idx];
        TreeNode root = new TreeNode(root_val);
        int in_idx = in_map.get(root_val);
        pre_idx++;

        root.left = dps(in_left,in_idx-1,in_map,preorder);
        root.right = dps(in_idx+1,in_right,in_map,preorder);
        return root;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
