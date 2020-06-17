package Week02;

import java.util.HashMap;

public class LC_264_ugly_number_ii {

    HashMap
    public int nthUglyNumber(int n) {
        if (n<=0) return 0;
        int[] ugly = new int[n];
        ugly[0]=1;
        int index2=0,index3=0,index5=0,i =1;
        while(i<n){
            ugly[i] = Math.min(ugly[i]*2,Math.min(ugly[i]*3,ugly[i]*5));
            i++;
        }
        return ugly[n-1];
    }
}
