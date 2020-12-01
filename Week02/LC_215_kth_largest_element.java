package Week02;

public class LC_215_kth_largest_element {
/*
* using qickSort algorithms, from bigger to smaller, find the pivot postion equal k-1,
* means the nums[pivot] is the Kth largest
* */
    public int findKthLargest(int[] nums, int k) {
        return quickFind(nums, k-1, 0, nums.length-1);
    }

    private int quickFind(int[] nums, int k, int begin, int end){
        int pivot = partition(nums, begin, end);
        if (pivot == k){
            return nums[pivot];
        }

        if(pivot<k){
            return quickFind(nums,k,pivot+1,end);
        }
        else{
            return quickFind(nums,k,begin,pivot-1);
        }
    }
    private int partition(int[] nums, int begin, int end){
        int pivot = end, count = begin;
        for (int i=begin;i<end;i++){
            if (nums[i]>nums[pivot]){
                swap(nums,i,count++);
            }
        }
        swap(nums,count,pivot);
        return count;
    }
    private void swap(int[] nums, int i, int j){
        if (nums[i]!= nums[j]){
            nums[i] = nums[i] + nums[j] - (nums[j] = nums[i]);
        }
    }
}
