package Week09;

public class LC44_wildcard_matching {
/*
状态定义：
dp[i][j] 表示 p 的前 i 个字符和 s 的前 j 个字符是否匹配。
状态转移方程如下：
如果 p[i - 1] == s[j - 1] 或 p[i - 1] == '?'，表示当前的字符串是匹配的，dp[i][j] 可以从 dp[i - 1][j - 1] 转移而来。
如果 p[i - 1] == '*'，这个位置可以匹配 0 到 若干个字符。那么 dp[i][j] 可以从 dp[i - 1][j] 转移而来（表示当前星号没有匹配字符），
也可以从 dp[i][j - 1] 转移而来（表示当前星号匹配了当前的位置的字符）
初始条件:
dp[0][0] = true 表示空串是匹配的
*/
    public boolean isMatch(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[pLen+1][sLen+1];
        dp[0][0] = true;
        //处理一下匹配串 p 以若干个星号开头的情况
        for (int i=1;i<=pLen;i++){
            if (p.charAt(i-1) != '*'){
                break;
            }
            dp[i][0] = true;
        }
        for (int i=1;i<=pLen;i++){
            for (int j=1;j<=sLen;j++){
                if (p.charAt(i-1)==s.charAt(j-1) || p.charAt(i-1)=='?'){
                    dp[i][j] = dp[i-1][j-1];
                } else if (p.charAt(i-1) == '*'){
                    dp[i][j] = dp[i][j-1] || dp[i-1][j];
                }
            }
        }
        return dp[pLen][sLen];
    }


    public boolean isMatch_2(String s, String p) {
        int sLen = s.length();
        int pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int i = 1; i <= pLen; ++i) {
            if (p.charAt(i - 1) == '*') {
                dp[0][i] = true;
            } else {
                break;
            }
        }
        for (int i = 1; i <= sLen; ++i) {
            for (int j = 1; j <= pLen; ++j) {
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || dp[i - 1][j];
                } else if (p.charAt(j - 1) == '?' || s.charAt(i - 1) == p.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[sLen][pLen];
    }
}
