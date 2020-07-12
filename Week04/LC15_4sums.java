package Week04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC15_4sums {
    
    public List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> res = new ArrayList<>();
        
        int len = nums.length;

        if (len<4) return res;
        
        Arrays.sort(nums);
        
        for (int i=0;i<len-3;i++){
            if (i>0 && nums[i] ==nums[i-1]){
                continue;
            }
            
            //can't have this, as target can be negative number
            //if (nums[i]>target) break;
            
            for(int m = i+1;m<len-2;m++){
                if(m>i+1&&nums[m]==nums[m-1]){
                    continue;
                }
                
                int left = m+1;
                int right = len-1;
                
                while (left<right){             
                    
                    int sum = nums[i]+nums[m]+ nums[left]+nums[right];
                    if (sum == target){
                        res.add(Arrays.asList(nums[i],nums[left],nums[m],nums[right]));                        
                        left++;
                        while (left<right && nums[left]== nums[left-1]){
                            left++;
                        }
                        right--;  
                        while (left<right&&m<right && nums[right]== nums[right+1]){
                            right--;
                        }                      
                    }else if(sum>target){
                        right--;
                    }else {
                       left++;
                    }
                }
            } 
        }                      
        
        return res;      
    }
    
    
    static class test {
        public static void main(String[] args) {

            LC15_4sums solution = new LC15_4sums();
            //solution.fourSum(nums, target);
        }
    }
    
}