package Week09;

public class LC72_edit_distance {
/*
(一)、当word1[i]==word2[j]时,由于遍历到了i和j,说明word1的0~i-1和word2的0~j-1的匹配结果已经生成,
由于当前两个字符相同,因此无需做任何操作,dp[i][j]=dp[i-1][j-1]
(二)、当word1[i]!=word2[j]时,可以进行的操作有3个:
      ① 替换操作:可能word1的0~i-1位置与word2的0~j-1位置的字符都相同,
           只是当前位置的字符不匹配,进行替换操作后两者变得相同,
           所以此时dp[i][j]=dp[i-1][j-1]+1(这个加1代表执行替换操作)
      ②删除操作:若此时word1的0~i-1位置与word2的0~j位置已经匹配了,
         此时多出了word1的i位置字符,应把它删除掉,才能使此时word1的0~i(这个i是执行了删除操作后新的i)
         和word2的0~j位置匹配,因此此时dp[i][j]=dp[i-1][j]+1(这个加1代表执行删除操作)
      ③插入操作:若此时word1的0~i位置只是和word2的0~j-1位置匹配,
          此时只需要在原来的i位置后面插入一个和word2的j位置相同的字符使得
          此时的word1的0~i(这个i是执行了插入操作后新的i)和word2的0~j匹配得上,
          所以此时dp[i][j]=dp[i][j-1]+1(这个加1代表执行插入操作)
      ④由于题目所要求的是要最少的操作数:所以当word1[i] != word2[j] 时,
          需要在这三个操作中选取一个最小的值赋格当前的dp[i][j]
(三)总结:状态方程为:
if(word1[i] == word2[j]):
      dp[i][j] = dp[i-1][j-1]
else:
       min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])+1


PS:大佬的代码中word1.charAt(i-1)==word2.charAt(j-1)的原因是:
     初始化DP Table时dp[i][0]和dp[0][j]已经填写完成,所以接下来填表需要从1开始,
     但是字符的比较需要从0开始,因此才这样子写
*/
    //自底向上
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        int[][] dp = new int[n+1][m+1];
        for (int i=0;i<=n;i++){
            dp[i][0] = i;
        }
        for (int j=0;j<=m;j++){
            dp[0][j] = j;
        }
        for (int i =1;i<=n;i++){
            for (int j=1;j<=m;j++){
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j])) + 1;
                }
            }
        }
        return dp[n][m];
    }

    //自顶向下:Cursive
    //dfs暴力解法会超时,会对大量的[i,j]重复计算,使用记忆化搜索
    int n1,n2;
    public int minDistance_2(String word1, String word2) {
        n1 = word1.length();
        n2 = word2.length();
        int[][] memo = new int[n1][n2];
        return dfs(word1,word2,0,0,memo);
    }

    private int dfs(String word1, String word2, int idx1, int idx2,int[][] memo){
        if (idx1==n1 || idx2==n2){
            return n1-idx1+n2-idx2;
        }
        if(memo[idx1][idx2] > 0){
            return memo[idx1][idx2];
        }
        if (word1.charAt(idx1) == word2.charAt(idx2)){
            memo[idx1][idx2] = dfs(word1,word2,idx1+1,idx2+1,memo);
        }
        else {
            int inserted = dfs(word1,word2,idx1,idx2+1,memo);
            int deleted = dfs(word1,word2,idx1+1,idx2,memo);
            int replaced = dfs(word1,word2,idx1+1,idx2+1,memo);
            memo[idx1][idx2] = Math.min(Math.min(inserted,deleted),replaced)+1;
        }
        return memo[idx1][idx2];
    }
}
