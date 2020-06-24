package Week03;

import java.util.ArrayList;
import java.util.List;

public class LC_78_subsets {

    /*  //Solutions:
     * 1. Recusive
     * dps: for any element, there has only two options:
     * select or no-select. in every level save the result to output
     * as we change the param, need to reverse the changes
     * */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length == 0 ){
            return ans;
        }
        List<Integer> list = new ArrayList<>();
        dfs(ans,nums,list,0);
        return ans;
    }

    private void dfs(List<List<Integer>> ans, int[] nums, List<Integer> list, int level) {
        //走到的数组nums的末尾了
        if (level == nums.length){
            //走到的数组nums的末尾了，把结果放入answer
            ans.add(list);
            return;
        }
        dfs(ans,nums,list.cl,level+1);
        list.add(nums[level]);
        dfs(ans,nums,list,level+1);
        list.remove(list.size()-1);
    }

    /*
     * 2. Iterative
     * 逐个枚举，空集的幂集只有空集，每增加一个元素，让之前幂集中的每个集合，追加这个元素，就是新增的子集。
     *
     * */
    public List<List<Integer>> subsets_iterate(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        //加一个空集[[]]
        ans.add(new ArrayList<Integer>());
        for (int n: nums) {
            int size = ans.size();
            for (int i =0; i<size; i++ ){
                //把以前的集取出，做成一个新集
                List<Integer> newList = new ArrayList<>(ans.get(i));
                //加入新的元素
                newList.add(n);
                ans.add(newList);
            }
        }

        return ans;
    }

}
