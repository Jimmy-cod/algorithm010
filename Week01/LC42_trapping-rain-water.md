class Solution {

    //两头逼近，以矮的那头为计算高度，向中间逼近，与11. 盛最多水的容器 相似
    
    
    public int trap(int[] height) {
        int ans = 0;
        int len = height.length;
        if (len <3){
            return 0;
        }
        int max_left = 0;
        int max_right = 0;
        int left =0;
        int right = len -1;
        while(left<=right){
            max_left = Math.max(max_left,height[left]);
            max_right = Math.max(max_right,height[right]);
            if (max_left<max_right){
                ans += max_left - height[left];
                left++;
            }
            else{
                ans += max_right - height[right];
                right--;
            }
        }

        return ans;
    }
}