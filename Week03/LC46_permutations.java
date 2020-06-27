package Week03;

import java.util.ArrayList;
import java.util.List;

public class LC46_permutations {
    int length;
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length ==0){
            return res;
        }
        length = nums.length;
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[length];
        dps(res,nums,list,used);
        return res;
    }

    private void dps(List<List<Integer>> res, int[] nums, List<Integer> list, boolean[] used){
        if (list.size() == length){
            res.add(new ArrayList(list));
            return;
        }
        //每次要用所有可能的数
        for (int i = 0;i<length;i++){
            if (!used[i]){
                list.add(nums[i]);
                used[i] = true;
                dps(res,nums,list,used);
                used[i] = false;
                list.remove(list.size()-1);

            }
        }
    }
}
