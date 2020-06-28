package Week03;

import java.util.*;
import java.util.stream.Collectors;

public class LC47_permutations_ii {

    /*
     * 先排序，有相同值跳过一个，把数组排列完再放入结果
     * */

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        Arrays.sort(nums);
        backtrack(res, nums, 0);
        return res;
    }

    private void backtrack(List<List<Integer>> res, int[] nums, int level) {
        if (nums.length == level) {
            res.add(Arrays.stream(nums).boxed().collect(Collectors.toList()));
            return;
        }
        backtrack(res, nums, level + 1);
        for (int i = 0; i < level; i++) {
            //avoid duplicated,need to be break for the same value
            if (nums[i] == nums[level]) {
                break;
            }
            swap(nums, i, level);
            backtrack(res, nums, level + 1);
            swap(nums, i, level);
        }
        return;
    }

    private void swap(int[] nums, int i, int j) {
        // must be value of i assign to j first
        nums[i] = (nums[i] + nums[j]) - (nums[j] = nums[i]);
    }

/*Solution2:

Bruit force*/

    int length;

    public List<List<Integer>> permuteUnique_1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        length = nums.length;
        List<Integer> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        boolean[] used = new boolean[length];
        dps(res, nums, list, used, set);
        return res;
    }

    private void dps(
            List<List<Integer>> res, int[] nums, List<Integer> list, boolean[] used, Set<String> set) {
        if (list.size() == length) {
            StringBuilder sb = new StringBuilder();
            for (Integer i : list) {
                sb.append(i);
                sb.append("-");
            }
            if (!set.contains(sb.toString())) {
                res.add(new ArrayList(list));
                set.add(sb.toString());
            }
            return;
        }

        for (int i = 0; i < length; i++) {
            if (!used[i]) {
                list.add(nums[i]);
                used[i] = true;
                dps(res, nums, list, used, set);
                used[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}


class Test {

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1, 1};
        LC47_permutations_ii solution = new LC47_permutations_ii();
        List<List<Integer>> res = solution.permuteUnique(nums);
    }
}
