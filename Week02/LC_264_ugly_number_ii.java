package Week02;

import java.util.HashMap;

public class LC_264_ugly_number_ii {

    public int nthUglyNumber(int n) {
        if (n<=0) return 0;
        int[] ugly = new int[n];
        ugly[0]=1;
        int index1=0,index2=0,index3=0,i =1;
        while(i<n){
            //找到最小的丑数，存在ugly[i]
            ugly[i] = Math.min(ugly[index1]*2,Math.min(ugly[index2]*3,ugly[index3]*5));
            //倒回去找相应的下标存起来
            while(ugly[index1]*2==ugly[i]) index1++;
            while(ugly[index2]*3==ugly[i]) index2++;
            while(ugly[index3]*5==ugly[i]) index3++;
            i++;
        }
        return ugly[n-1];
    }
}
