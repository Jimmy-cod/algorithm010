package Week06;

import java.util.HashMap;
import java.util.Map;

public class LC167_two_sum_ii {

    //HashMap
    public int[] twoSum(int[] numbers, int target) {
        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i=0;i<numbers.length;i++){
            Integer val = target - numbers[i];
            if (map.containsKey(val)){
                return new int[]{map.get(val)+1,i+1};
            }
            else{
                map.put(numbers[i],i);
            }
        }
        return null;
    }
    //binary search
    public int[] twoSum_2(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; ++i) {
            int low = i + 1, high = numbers.length - 1;
            while (low <= high) {
                int mid = (high - low) / 2 + low;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return new int[]{-1, -1};
    }


    //double points
    public int[] twoSum_3(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length -1;
        while (left<right){
            int val = numbers[left]+numbers[right];
            if (val == target){
                return new int[]{left+1,right+1};
            }
            else if (val < target){
                left++;
            }
            else {
                right--;
            }

        }
        return null;
    }
}
