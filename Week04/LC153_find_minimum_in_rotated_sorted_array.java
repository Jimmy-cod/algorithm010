package Week04;

public class LC153_find_minimum_in_rotated_sorted_array {
    static class Solution {
        //旋转数组,
        //找变化点Inflection Point
        //1.所有变化点左侧元素 > 数组第一个元素
        //2.所有变化点右侧元素 < 数组第一个元素
        //二分法查找，需要始终将目标值（这里是最小值）套住，并不断收缩左边界或右边界
        //1234567: left<mid,mid<right. shrink right
        //5671234: left>min,mid<right. shrink right
        //2345671: left<mid,mid>right, shrink left
        //7654321: left>mid,mid>right, shrink left
        //总结：只要比较mid与左右两边值，>右，收缩左；否则收缩右(r=m,as left side contains target)，最后l<=r时，返回nums[l]
        public int findMin(int[] nums) {
            int l = 0;
            int r = nums.length - 1;
            while(l<r){
                int m = l + (r-l)/2;
                if (nums[m]>nums[r]){
                    l = m+1;
                }
                else {
                    r = m;
                }
            }
            return nums[l];
        }
    }


    public static void main(String[] args) {
        int[] nums = {3,1,2};
        Solution solution = new Solution();
        int res = solution.findMin(nums);
        System.out.println(res);
    }

}
