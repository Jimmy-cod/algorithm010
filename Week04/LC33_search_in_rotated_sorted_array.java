package Week04;

public class LC33_search_in_rotated_sorted_array {

    //还是用二分查找
    //1.如果左<右，表示这部分是排序的,另一部分没有排序
    //2.在排序这部分找,如果target在左右值间，说明target在有序这边
    //3.否则在无序这边，据继续递归查找
    public int search(int[] nums, int target) {
        int mid,left=0;
        int right=nums.length-1;
        while (left <= right){
            mid = left+(right-left)/2;
            if (nums[mid]==target){
                return mid;
            }
            //有序在前半部分,注意要把=nums[mid]也归入
            if (nums[left]<=nums[mid]){
                //必须考虑左右边界值等于target情况
                //有两种情况：target在前半部有序处，或后半部无序处
                //这里因为right = mid-1;加入target=左边界值
                if (nums[left]<=target && target<nums[mid]){
                    right = mid-1;
                }
                else {
                    left = mid+1;
                }
            }
            else {
                //这里也有两种情况：target在后半部有序处，或前半部无序处
                if (nums[mid]<target && target<=nums[right]){
                    left = mid +1;
                }
                else {
                    right = mid -1;
                }
            }
        }
        return -1;
    }
}
