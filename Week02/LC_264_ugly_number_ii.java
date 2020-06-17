package Week02;

import java.util.HashMap;

public class LC_264_ugly_number_ii {
/*
* 一开始，丑数只有{1}，1可以同2，3，5相乘，取最小的1×2=2添加到丑数序列中。

现在丑数中有{1，2}，在上一步中，1已经同2相乘过了，所以今后没必要再比较1×2了，我们说1失去了同2相乘的资格。

现在1有与3，5相乘的资格，2有与2，3，5相乘的资格，但是2×3和2×5是没必要比较的，因为有比它更小的1可以同3，5相乘，所以我们只需要比较1×3，1×5，2×2。

依此类推，每次我们都分别比较有资格同2，3，5相乘的最小丑数，选择最小的那个作为下一个丑数，假设选择到的这个丑数是同i（i=2，3，5）相乘得到的，所以它失去了同i相乘的资格，把对应的pi++，让pi指向下一个丑数即可。

* */
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
