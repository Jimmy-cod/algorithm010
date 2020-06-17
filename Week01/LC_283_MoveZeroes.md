class Solution {


    public void moveZeroes(int[] nums) {
        //把0用后面的数代替，并统计0的个数记录在j上
        int j =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                nums[j] = nums[i];
                j++;
            }
        }
        //补上0
        while(j<nums.length){
            nums[j]=0;
            j++;
        }
    }

    //swap 交换法, j 记录着0的位置
    public void moveZeroes2(int[] nums) {
        int j =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                int temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                j++;
            }
        }
    }

    //j 记录着0的位置，直接覆盖，只有 i != j 时才要覆盖，覆盖完把nums[i]顺便补上0
    public void moveZeroes3(int[] nums) {
        int j =0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]!=0){
                //因为值已经copy到nums[j], 可以把后面的值都设为0；这样可以在一个循环结束
                if (i!=j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }
}