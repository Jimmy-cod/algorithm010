package Week04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LC315_count_of_smaller_number_after_self {
    
    //Solution 1: brute force
    public List<Integer> countSmaller_1(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<>();
        int f = 0;
        int count = 0;
        for (int i=0; i<len; i++){
            f = nums[i];
            count = 0;
            for (int j=i+1; j<len; j++){
                if (f>nums[j]){
                    count ++;
                }
            }
            res.add(count);
        }
        return res;
    }
    
    //Solution 2: Build sorted tree
    // 排序树,从无到有地create 排序树,同时count result.
    /* 使用二叉搜索树也可以完成插入并统计的功能，从右往左构建二叉树。
    递归实现：
    当走到右节点时，（1）统计根节点和左节点的个数，（2）继续插入并统计右边是否还有较小值；
    当走到左节点或者根节点时，（1）计数器加一，（2）继续插入并统计左边是否还有较小值。

    遍历一遍就可以完成搜索，时间复杂度O(nlogn)。
    */
    public List<Integer> countSmaller_2(int[] nums) {
        int len = nums.length;
        Integer[] res = new Integer[nums.length];
        Arrays.fill(res, 0);
        MyTreeNode root = null;
        for (int i = len -1;i>=0;i--){
            root = MyTreeNode.create(root,nums[i],i,res);
        }

        return  Arrays.asList(res);
    }


    static class MyTreeNode {
        int val;
        int count;
        MyTreeNode left;
        MyTreeNode right;
    
        MyTreeNode(int x) {
            val = x;
        }

        public static MyTreeNode create(MyTreeNode root,int v, int i, Integer[] res){
            if (root == null){
                root = new MyTreeNode(v);
            } else if (v <= root.val){
                //统计左节点的元素个数
                root.count++;
                root.left = create(root.left,v,i,res);
            }else {
                //走到右边获取该元素左边的个数（根节点 1 + 左节点 root.count）
                res[i]+= root.count +1;
                //统计右边是否还有更小的元素
                root.right = create(root.right,v,i,res);
            }

            return root;
        }
    }
    
}