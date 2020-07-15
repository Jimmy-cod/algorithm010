package Week06;

public class LC1143_longest_common_subsequence {
/*
1.把两个字符串变成二维数组，两个String分别在行和列上
2.从底部往上，如果最后字母相同，dp[i][j]=dp[i-1][j-1]+1;
3.如果不同，等于左右两边的最大值
4.从1开始，计算考虑text1在i-1和text2在j-1的值
* */
    public int longestCommonSubsequence(String text1, String text2) {
        if (text1 == null || text2 == null) return 0;
        int ans = 0;
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m+1][n+1];
        if (m==0 || n ==0) return 0;

        for (int i=1;i<=m;i++){
            for (int j=1;j<=n;j++){
                if (text1.charAt(i-1) ==text2.charAt(j-1) ){
                    dp[i][j] = dp[i-1][j-1]+1;
                }
                else {
                    dp[i][j] =Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        return dp[m][n];
    }
}
