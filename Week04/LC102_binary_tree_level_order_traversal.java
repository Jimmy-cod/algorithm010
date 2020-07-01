package Week04;

import Common.TreeNode;

import java.util.*;

public class LC102_binary_tree_level_order_traversal {
    /*
    * 用BFS 代码模板
    * */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        //这题中没必要visisted
        HashSet<TreeNode> visited = new HashSet<>();
        Deque<TreeNode> queue = new LinkedList<>();
        queue.addFirst(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            List<Integer> list = new ArrayList<>();
            for(int i=0;i<size;i++){
                TreeNode node = queue.pollLast();
                if (node != null) {
                    if (visited.isEmpty() || !visited.contains(node)) {
                        list.add(node.val);
                        visited.add(node);
                    }

                    queue.addFirst(node.left);
                    queue.addFirst(node.right);
                }
            }
            if (list.size()>0){
                res.add(list);
            }
        }
        return res;
    }
}
