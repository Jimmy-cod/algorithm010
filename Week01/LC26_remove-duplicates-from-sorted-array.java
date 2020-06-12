
class Solution {
    //代替法，用j保存修改数组的位置
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length==0){
            return 0;
        }
        int j =0;
        for(int i=0;i<nums.length;i++){
            if( nums[i] != nums[j]){
                j++;
                nums[j]=nums[i];
            }
        }
        //Cause: can't return j++
        return j+1;
    }
}