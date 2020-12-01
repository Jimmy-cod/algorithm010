package Interview;

import Common.BuildBinaryTree;
import Common.TreeNode;

public class HSBC {

    public static void swap(int a, int b){
//        a = (a+b)-(b=a);

        a=a+b;
        b=a-b;
        a=a-b;

        System.out.println("a="+a+"; b="+b);
    }


    public static int sumOfBinaryTree(TreeNode root){
        if (root==null){
            return 0;
        }
        return sumOfBinaryTree(root.left)+ sumOfBinaryTree(root.right)+root.val;
    }


    public static void main(String[] args) {

        int a=1, b=2;
        swap(a,b);

//        System.out.println("a="+a+"; b="+b);
//        Integer[] list = new Integer[]{1,2,3,null,null,4};
//        TreeNode root = BuildBinaryTree.buildBinaryTree(list);
//        sumOfBinaryTree(root);
//        System.out.println("Sum="+ sumOfBinaryTree(root));
    }
}
