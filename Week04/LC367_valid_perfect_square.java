package Week04;

public class LC367_valid_perfect_square {
    class Solution {
        //二分法
        //注意两个int相乘会溢出
        public boolean isPerfectSquare(int num) {
            if (num == 1) {
                return true;
            }
            int left = 1, right = num / 2;
            int mid;
            while (left <= right) {
                mid = left + (right - left) / 2;
                if (mid * mid == num) {
                    return true;
                } else if ((long) mid * mid > num) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return false;
        }
    }
}
