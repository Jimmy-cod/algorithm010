package Week02;

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
        if (level == nums.length){
            ans.add(list);
            return;
        }
        dfs(ans,nums,list,level+1);
        list.add(nums[level]);
        dfs(ans,nums,list,level+1);
        list.remove(list.size()-1);
    }

}
