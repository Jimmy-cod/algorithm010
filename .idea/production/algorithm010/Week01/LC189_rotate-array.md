
class Solution {

    //Brute-force
    //move k times for all elements - O(k^n)
    
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