class Solution {

//用数组指针时，要特别注意边界问题，多用特殊cases检查边界问题

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p = m+n-1;
        int p1 = m-1;
        int p2 = n-1;
        while ( p2>=0) {
            // when nums1 reach the head, p1 will become -1
            if (p1 == -1 || nums1[p1] <= nums2[p2]) {
                nums1[p] = nums2[p2];
                p2--;
            }
            else{
                nums1[p] = nums1[p1];
                p1--;
            }
            p--;

            nums1[p--] = (p1 == -1 || nums1[p1] <= nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
    }
}

//可以简化成四行
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int p1 = m - 1, p2 = n - 1, p = m + n - 1;
        while (p2 >= 0) {
            nums1[p--] = (p1 == -1 || nums1[p1] <= nums2[p2]) ? nums2[p2--] : nums1[p1--];
        }
    }
}