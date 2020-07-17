package Week06;

public class LC70_climbing_stairs_ii {

    //每次你可以爬 1 或 2 或 3 个台阶
    public int climbStairs(int n) {
        // Dynamic Programming
        if(n<=3) return n;

        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=2;
        dp[3]=4;

        for(int i=4;i<=n;i++){
            dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
        }
        return dp[n];
    }

    //每次你可以爬 1 或 2  个台阶,相邻不能重。如 1，1，2 或 122
    public int climbStairs_3(int n) {
        // Dynamic Programming
        if(n<=3) return n;

        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1]=1;
        dp[2]=1;
        dp[3]=2;
        dp[4]=2;

        for(int i=5;i<=n;i++){
            dp[i] = dp[i-1]-1+dp[i-2]-1;
        }
        return dp[n];
    }

}

class Test{
    public static void main(String[] args) {
        LC70_climbing_stairs_ii s =new LC70_climbing_stairs_ii();
        int res = s.climbStairs_3(7);

        System.out.println("Result=" +res);
    }
}
