package Week04;

public class LC45_jump_game_ii {
    class Solution {
        //在本下标可达到的最远位置内找到下一个最远位置
        //用end表示本本下标可达到的最远位置
        //当i==end,进入下一轮
        public int jump(int[] nums) {
            int end =0;
            int rightMost =0;
            int jump=0;
            //when reach the (length-1) don't need to jump. so i<nums.length-1
            for (int i=0;i<nums.length-1;i++){
                rightMost = Math.max(rightMost,i+nums[i]);
                if (i==end){
                    end = rightMost;
                    jump++;
                }
            }
            return jump;
        }
    }
}
