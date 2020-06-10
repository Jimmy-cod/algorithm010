
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