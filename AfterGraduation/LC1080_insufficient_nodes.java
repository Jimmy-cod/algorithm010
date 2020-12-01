package AfterGraduation;

import Common.TreeNode;

public class LC1080_insufficient_nodes {
/*
用root.val - limit 带入每层即可在sufficientSubset递归
 */
    public TreeNode sufficientSubset(TreeNode root, int limit) {

        if (root == null){
            return null;
        }
        //如果是叶子节点，判断是否需要删去
        if (root.left==null && root.right==null){
            if (root.val < limit)
                return null;
            else{
                return root;
            }
        }
        TreeNode left = sufficientSubset(root.left,limit-root.val);
        TreeNode right = sufficientSubset(root.right,limit-root.val);

        //如果两个子树都被删掉,说明这个节点是不足节点
        if(left == null && right == null)
            return null;

        //没被全删点就说明不是不足节点，保留即可
        root.left = left;
        root.right = right;
        return root;
    }

    /*
对于二叉树上某一节点的删除，一般有两种做法：
一种是由其父节点对左右子节点的删除，另一种则是将自身删除。从测试用例3 [5,-6,-6] 的返回结果为 [] 可知，在某些情况下根节点也需要被删除，由于根节点没有父结点，
因此选择第二种删除方式，为了避免像链表一样出现断链，采用后序遍历，从叶子节点开始删除。

解题思路：不妨设 bool dfs(X, sum, limit) 为判断 root 是否为不足节点的函数，其中 sum 为从根节点 root 到 X 的父结点的路径和，显然若出现以下三种情况，则应当删除：
1、若 X 的左孩子节点是不足节点，且右孩子也是不足节点，则 X 也是不足节点
2、若 X 的左孩子节点是不足节点，且右孩子为空，则 X 也是不足节点
3、若 X 的左孩子为空，且右孩子是不足节点，则 X 也是不足节点
递归的 Base Case 为：若 X == NULL 则返回 sum < limit
以下解法错误
    private boolean dfs_wrong(TreeNode node, int sum, int limit){
        if (node == null){
            return sum < limit;
        }
        boolean left = dfs(node.left, sum+node.val,limit);
        //Case 2:
        if (left && node.right==null){
            node = null;
            return true;
        }

        boolean right = dfs(node.right, sum+node.val,limit);
        //Case 3:
        if (right && node.left == null){
            node = null;
            return true;
        }
        //Case 1;
        if (left && right){
            node = null;
            return true;
        }
        return left && right;
    }

     */
}
