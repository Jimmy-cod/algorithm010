package Week03;

public class LC_50_powx_n {
/*
* Solution 1.
*  Divide & Conque
* */
    public double myPow_1(double x, int n) {
        //1. terminator
        //2. process (split your big problem)
        //3. drill down (sub-problems)
        //4. merge(sub-result)
        //5. reverse states
        //Java 代码中 int32, n∈[−2147483648,2147483647] , 因此当 n = -2147483648n=−2147483648 时执行 n = -nn=−n 会因越界而赋值出错
        long k = n;
        if (n<0) {
            k = -k;
        }
        double s = quickPow(x, k);
        return n<0? 1/s: s;
    }

    private double quickPow (double x, long n) {
        if (n==0){
            return 1.0;
        }
        double s = quickPow(x, n/2);
        return (n%2==0)? s*s : s*s*x;
    }

}
