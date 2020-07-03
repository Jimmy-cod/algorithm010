package Week04;

import java.util.Arrays;

public class LC455_assign_cookies {
    public int findContentChildren(int[] g, int[] s) {
        int res =0;

        Arrays.sort(g);
        Arrays.sort(s);
        int gi =0,si=0;
        while (gi<g.length&& si<s.length){
            if (g[gi]<=s[si]){
                gi++;
            }
            si++;
        }
        return gi;
    }
}
