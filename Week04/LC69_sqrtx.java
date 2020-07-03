package Week04;

public class LC69_sqrtx {

    //二分法
    //面试时要说出为啥能用二分法，
    //二分查找的前提
    //1. 目标函数单调性 （单调递增或递减）
    //2. 存在上下界 （bounded)
    //3. 能够通过索引访问 （index accessible)
    public int  mySqrt(int x) {
        //平方根肯定在1..n之间
        int left=1,mid =1;
        int right = x;
        while(left<=right){
            mid = left + (right-left)/2;
            if (mid*mid  >= x ){
                right = mid -1;
            }else {
                left = mid +1;
            }
        }
        return right;
    }
}
