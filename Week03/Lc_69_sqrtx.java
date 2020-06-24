package Week03;

public class Lc_69_sqrtx {

    public static double mySqrt(int x) {
        if(x==0) return 0;
        double pre,cur = 1;
        do{
            pre = cur;
            cur = x/(2*pre) + pre/2.0;
        }while(Math.abs(cur-pre)>0.00001);
        return cur;
    }

    public static void main(String[] args) {
        double res = mySqrt(1234);

        System.out.println("res = "+res);
    }

}
