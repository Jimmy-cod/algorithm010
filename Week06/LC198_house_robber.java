package Week06;

public class LC198_house_robber {
    /*
思路：
1. 状态数组定义： a[i]: 0..i 能偷到max value : a[n-1]
2. DP方程 : a[i] = a[i-1] + nums[i], 这里有问题，不知道i-1是否偷。没办法递推
3. 升维：a[i][0,1]: 0表示 i不偷； 1表示 i偷
4. DP方程：
    a[i][0] = Max(a[i-1][0], a[i-1][1])
    a[i][1] = Max(a[i-1][0], 0) + nums[i] => a[i-1][0] + nums[i]

    * */
    public int rob(int[] nums) {
        if (nums == null || nums.length==0) return 0;

        int n = nums.length;
        int[][] dp = new int[n][2];

        dp[0][0] = 0; //不偷
        dp[0][1] = nums[0]; //偷

        for (int i=2;i<n; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
        }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }

/*
重新定义状态数组，降维
状态数组: a[i]: 0..i天，且nums[i]必偷的最大值， 能偷到max value : max(a)
DP方程 : a[i] = Max(a[i-1], a[i-2] + nums[i]);
* */
    public int rob_2(int[] nums) {
        if (nums == null || nums.length==0) return 0;
        if (nums.length==1) return nums[0];

        int n = nums.length;
        int[] dp = new int[n];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        int res = Math.max(dp[0], dp[1]);

        for (int i=2;i<n; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
            res = Math.max(res, dp[i]);
        }
        return  res;
    }
}
