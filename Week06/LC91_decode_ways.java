package Week06;

public class LC91_decode_ways {
    /*
a.子问题: 长度为n - 1的字符串s1有多少种解码方法
b.状态数组定义: dp[i]：从索引0到i的字符串s的子串所能够解码的总数
c.DP方程:
    case 1: s[i]=0: s[i-1]= '1' or '2', then dp[i]=dp[i-2]; else return 0;
    case 2: s[i-1]='1', dp[i]=do[i-1]+dp[i-2]
    case 3: s[i-1]='2' and '1'<=si[i]<='6', then dp[i]=do[i-1]+dp[i-2]
    case 4: others, return dp[i]=do[i-1];
    * */
    public int numDecodings(String s) {
        char[] chars = s.toCharArray();
        if (chars[0] == '0') return 0;
        int n = s.length();
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i=1;i<n;i++){
            if (chars[i] == '0'){
                if (chars[i-1] == '1' || chars[i-1] == '2'){
                    //由于s[1]指第二个下标，对应为dp[2],所以dp的下标要比s大1，故为dp[i+1]
                    dp[i+1] = dp[i-1];
                }
                else {
                    //"30" or "40".. are 非法数字，直接返回0
                    return 0;
                }
            }
            //s[i-1]s[i]两位数要小于26的情况
            else if (chars[i-1] =='1' || (chars[i-1] =='2' && chars[i] <='6')){
                dp[i+1] = dp[i-1] + dp[i];
            }
            else {
                dp[i+1] = dp[i];
            }
        }
        return dp[n];
    }
}
