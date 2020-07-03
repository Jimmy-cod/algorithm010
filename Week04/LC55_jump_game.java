package Week04;

public class LC55_jump_game {
    //loop throught nums
    //int rightMost = max( i+nums[i])
    //从rightMost 开始，只考虑每一步可以到的最远位置i+nums[i]
    public boolean canJump(int[] nums) {
        int rightMost =0;
        int len= nums.length;
        for (int i =0;i<len;i++){
            if (i<=rightMost){
                rightMost = Math.max(rightMost,(i+nums[i]));
                //already able to reach the end (len-1)
                if (rightMost>=len-1){
                    return true;
                }
            }
        }
        return false;
    }
}
