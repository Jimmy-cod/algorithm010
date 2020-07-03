package Week03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Solution {
    /*Solution 2:
     * 就是把数组排列完再放入结果
     * 以[1, 2|, 3] -> [1, 2, 3|], [3, 2, 1|], [1, 3, 2|]为例，
     * '|'往后移动一位，得到[1, 2, 3|]；[1, 2, 3|]中的3和1位置交换，
     * 得到[3, 2, 1|]；[1, 2, 3|]中的3和2交换位置，得到[1, 3, 2|]。
     * */

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        // don't need to sort
        Arrays.sort(nums);
        backtrack(res, nums, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, int level) {
        if (nums.length == level) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        //no swap, original list
        backtrack(res, nums, level + 1);

        for (int i = 0; i < level; i++) {
            swap(nums, i, level);
            backtrack(res, nums, level + 1);
            swap(nums, i, level);
        }
        return;
    }

    public static void swap(int[] arr, int i, int j) {
        arr[i] = (arr[i] + arr[j]) - (arr[j] = arr[i]);
    }

    /*********************Solution 2:*******************************************************************
       取出不同的数放入结果

    */
    int length;

    public List<List<Integer>> permute_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        length = nums.length;
        List<Integer> list = new ArrayList<>();
        boolean[] used = new boolean[length];
        dps(res, nums, list, used);
        return res;
    }

    private void dps(List<List<Integer>> res, int[] nums, List<Integer> list, boolean[] used) {
        if (list.size() == length) {
            res.add(new ArrayList(list));
            return;
        }
        // 每次要用所有可能的数
        for (int i = 0; i < length; i++) {
            if (!used[i]) {
                list.add(nums[i]);
                used[i] = true;
                dps(res, nums, list, used);
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}

public class LC46_permutations {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        Solution solution = new Solution();
        List<List<Integer>> res = solution.permute(nums);
    }
}
