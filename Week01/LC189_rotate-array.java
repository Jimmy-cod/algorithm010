//move k times for all element - O(k^n)
class Solution {
    public void rotate(int[] nums, int k) {
        int length = nums.length;
        for(int i=0;i<k;i++){
            int tmp = nums[length-1];
            for(int j=length-1;j>0;j--){
                nums[j] = nums[j-1];
            }
            nums[0] = tmp;
        }
    }
}