package Week02;

import java.util.*;

class BinanryTree_Traversal {

    /*
    * 深度优先DFS:
    * 根节点、左孩子和右孩子的相对顺序被细分为前序遍历，中序遍历和后序遍历。
    *前序（144）~中序（94）~后序（145）遍历可以看成三个时间点

     前序遍历是在访问左子树之前，对父结点进行操作，也就是在左结点出栈前（！！！出栈即进入以左结点为根的左子树！！！），对父节点do something~
     中序遍历是在访问左子树之后，右子树之前，对父结点进行操作，即左结点（代表以左结点为根的子树）出栈后，右结点出栈前，对父节点do something~
     后序遍历是在访问左子树，右子树之后，对父结点进行操作，即左右结点都出栈后，对父节点do something~
     */
    //144. 二叉树的前序遍历: 从根节点开始，每次迭代弹出当前栈顶元素，并将其孩子节点压入栈中，先压右孩子再压左孩子。
    public List<Integer> preorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        if (root ==null){
            return result;
        }
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node != null){
                result.add(node.val);
                stack.push(node.right);
                stack.push(node.left);
            }
        }
        return result;
    }

    //Iteratively
    /*
     *94. 二叉树的中序遍历
     * 迭代 - iteratively
     * 递归的调用过程是不断往左边走，当左边走不下去了，就打印节点，并转向右边，然后右边继续这个过程。
     *我们在迭代实现时，就可以用栈来模拟上面的调用过程。
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while( !stack.empty()){
            if(root!=null){
                stack.push(root.left);
                root = root.left;
            }
            else{
                TreeNode node = stack.pop();
                if(node != null){
                    res.add(node.val);
                    root = node.right;
                }
            }
        }
        return res;
    }


    //145: 后序遍历:
    /*
     * 与 前序遍历， 从根节点开始，每次迭代弹出当前栈顶元素，并将其孩子节点压入栈中，先压右孩子再压左孩子
     * 可是对每次迭代弹出节点，把值存入插入结果链表的头部（最后输出）
     * */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<Integer>();
        if (root ==null){
            return result;
        }
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            if (node != null){
                //因为根节点要最后出，所有要再压入栈
                stack.push(node);
                stack.push(null); //用null使根节点出栈时去到else
                //压右孩子
                if (node.right != null)  stack.push(node.right);
                //压左孩子
                if (node.left != null)  stack.push(node.left);
            }
            else{
                TreeNode output= stack.pop();
                result.add(output.val);
            }
        }
        return result;
    }
    //用相同思路解决中序历遍
    public List<Integer> inorderTraversal2(TreeNode root) {

        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root ==null){
            return res;
        }
        stack.push(root);
        while(!stack.empty()){
            TreeNode node = stack.pop();
            if (node != null){
                if (node.right != null ) stack.push(node.right);
                stack.push(node);
                stack.push(null);
                if (node.left != null ) stack.push(node.left);
            }
            else{
                res.add(stack.pop().val);
            }
        }
        return res;
    }
    //前序 遍历， 因为输出结果是加入ArrayList最后, 所有压栈的顺序和Recursive 是相反的
    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> res = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if (root ==null){
            return res;
        }
        stack.push(root);
        while(!stack.empty()){
            TreeNode node = stack.pop();
            if (node != null){
                if (node.right != null ) stack.push(node.right);
                if (node.left != null ) stack.push(node.left);
                stack.push(node);
                stack.push(null);
            }
            else{
                res.add(stack.pop().val);
            }
        }
        return res;
    }

    //    102. 二叉树的层序遍历
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new ArrayDeque<TreeNode>();

        queue.add(root);
        while (!queue.isEmpty()){

            int n =queue.size(); //need the store size to n, as quene size keep changing inside loop
            List<Integer> list = new ArrayList<Integer>();
            for (int i=0; i<n; i++){
                TreeNode curr = queue.poll();
                list.add(curr.val);
                if (curr.left != null){
                    queue.add(curr.left);
                }
                if (curr.right != null){
                    queue.add(curr.right);
                }
            }
            res.add(list);
        }
        return res;
    }

    //递归解法 BPS
    public List<List<Integer>> levelOrder_bps(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        bps(root,0,res);
        return res;
    }

    private void bps(TreeNode curr,int level, List<List<Integer>> res) {
        if (curr == null){
            return;
        }
        // it's new level
        if (level + 1 > res.size()){
            res.add(new ArrayList<Integer>());
        }
        res.get(level).add(curr.val);
        //drill down
        if (curr.left != null){
            bps(curr.left,level+1,res);
        }
        if (curr.right != null){
            bps(curr.right, level+1, res);
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
