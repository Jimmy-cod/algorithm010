package Common;

import java.util.LinkedList;
import java.util.Queue;

public class BuildBinaryTree {

        // PriorityQueue
        //    Deque -> Stack

        public static TreeNode  buildBinaryTree(Integer[] list) {

            if(list == null || list.length == 0) return null;
            Queue<TreeNode> queue = new LinkedList<>();
            TreeNode root = new TreeNode(list[0]);
            int j =1;
            int length = list.length;
            queue.add(root);
            while(!queue.isEmpty() && j< length){

                TreeNode curNode = queue.poll();
                if (list[j] != null){
                    curNode.left = new TreeNode(list[j]);
                    queue.add(curNode.left);
                }
                j++;
                if (j ==length){break;}

                if (list[j] != null) {
                    curNode.right = new TreeNode(list[j]);
                    queue.add(curNode.right);
                }
                j++;
            }
            return root;
        }

        public TreeNode build(Integer[] list){
            if(list.length == 0) return null;
            Queue<TreeNode> queue = new LinkedList<>();

            TreeNode root = new TreeNode(list[0]);

            queue.add(root);
            int lineNum =2;
            int startIndex =1;
            int restLength = list.length-1;

            while(restLength >0){
                for (int index = startIndex;index < startIndex + lineNum; index=index+2){
                    if (index == list.length){
                        return root;
                    }
                    TreeNode curNode = queue.poll();
                    if (list[index] != null)
                    {
                        if(curNode!=null) {
                            curNode.left = new TreeNode(list[index]);
                            queue.add(curNode.left);
                        }
                    }

                    if (index+1 == list.length)
                        return root;

                    if (list[index+1] != null)
                    {
                        if(curNode!=null){
                            curNode.right = new TreeNode(list[index+1]);
                            queue.add(curNode.right);
                        }
                    }
                }
                startIndex +=lineNum;
                restLength -=lineNum;
                lineNum = queue.size() * 2;
            }
            return root;
        }

}
