package Week09;

import java.util.HashMap;

public class LC10_regular_expression_matching {
/*
https://leetcode-cn.com/problems/regular-expression-matching/solution/ji-yu-guan-fang-ti-jie-gen-xiang-xi-de-jiang-jie-b/
DP 状态函数:
bool dp(string& s, int i, string& p, int j);
若 dp(s, i, p, j) = true，则表示 s[i..] 可以匹配 p[j..]；若 dp(s, i, p, j) = false，则表示 s[i..] 无法匹配 p[j..]。
根据这个定义，我们想要的答案就是 i = 0, j = 0 时 dp 函数的结果

*/
    public boolean isMatch(String s, String p) {
        HashMap<String,Boolean> memo = new HashMap<>();
        // 指针 i，j 从索引 0 开始移动
        return dp(s.toCharArray(),0,p.toCharArray(),0, memo);
    }

    private boolean dp(char[] s, int i, char[] p, int j,HashMap<String,Boolean> memo) {
        //base case:
        //case 1: j == p.size() 时，
        // 按照 dp 函数的定义，这意味着模式串 p 已经被匹配完了，那么应该看看文本串 s 匹配到哪里了，如果 s 也恰好被匹配完，则说明匹配成功
        if (j == p.length){
            return i == s.length;
        }
        //case 2: i == s.size() 时,
        //按照 dp 函数的定义，这种情况意味着文本串 s 已经全部被匹配了，那么是不是只要简单地检查一下 p 是否也匹配完就行了
        //这是不正确的，此时并不能根据 j 是否等于 p.size() 来判断是否完成匹配，只要 p[j..] 能够匹配空串，就可以算完成匹配。
        // 比如说 s = "a", p = "ab*c*"，当 i 走到 s 末尾的时候，j 并没有走到 p 的末尾，但是 p 依然可以匹配 s
        if (i == s.length){
            // 如果能匹配空串，一定是字符和 * 成对儿出现
            if ( (p.length - j)%2 == 1) {
                return false;
            }
            // 检查是否为 x*y*z* 这种形式
            for (; j + 1 < p.length; j += 2) {
                if (p[j + 1] != '*') {
                    return false;
                }
            }
            return true;
        }
        // 记录状态 (i, j)，消除重叠子问题
        String key = String.valueOf(i) + "," + String.valueOf(j);
        if (memo.containsKey(key)) return memo.get(key);

        boolean res = false;

        if (s[i] == p[j] || p[j] == '.') {
            // 匹配
            if (j<p.length-1 && p[j+1] == '*'){
                // 1.1 通配符匹配 0 次或多次
                //将 j 加 2，i 不变，含义就是直接跳过 p[j] 和之后的通配符，即通配符匹配 0 次
                //将 i 加 1，j 不变，含义就是 p[j] 匹配了 s[i]，但 p[j] 还可以继续匹配，即通配符匹配多次的情况
                //两种情况只要有一种可以完成匹配即可，所以对上面两种情况求或运算
                res = dp(s, i, p, j + 2,memo)
                        || dp(s, i + 1, p, j,memo);
            } else {
                // 1.2 常规匹配 1 次
                //由于这个条件分支是无 * 的常规匹配，那么如果 s[i] == p[j]，就是 i 和 j 分别加一
                res = dp(s, i + 1, p, j + 1,memo);
            }

        }
        else {
            if ( j< p.length-1 && p[j+1] == '*') {
                // 2.1 通配符匹配 0 次
                //类似情况 1.1，将 j 加 2，i 不变
                res = dp(s, i, p, j + 2,memo);
            } else {
                // 2.2 无法继续匹配
                // 如果没有 * 通配符，也无法匹配，那只能说明匹配失败了
                res = false;
            }
        }
        // 将当前结果记入备忘录
        memo.put(key,res);
        return res;
    }

    //Recursion
    /*
        if(*p == '\0')
    {
        if(*s == '\0') return true;
        else return false;
    }
    bool flag = false;
    if(*p == *s || *p == '.' && *s != '\0') flag = true;
    // Discuss *
    if(*(p + 1) == '*')
    return isMatch(s, p+2) || flag && isMatch(s+1, p);
    else
    return flag && isMatch(s+1, p+1);
    */
    public boolean isMatch_2(String s, String p) {
        //this is wrong
        if (s.isEmpty()) {
            return p.isEmpty();
        }
        boolean b = !p.isEmpty() && (p.charAt(0) == '.' || s.charAt(0) == p.charAt(0));
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (b && isMatch(s.substring(1), p));
        }
        return b && isMatch(s.substring(1), p.substring(1));
    }
}
