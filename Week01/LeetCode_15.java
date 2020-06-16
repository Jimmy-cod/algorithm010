class Solution {
//15. 三数之和
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (nums == null || len<3) {
            return result;
        }
        //sort是关键，排序完就很容易去重了
        Arrays.sort(nums);

        for (int i=0;i<len;i++){
            //nums[i] is the first integer of three
            // 去重
            //if (i>0 && nums[i] == nums[i-1]) continue;
            //用while一样效果，不用回到for去判断
            while (i>0 && i<len-1 && nums[i] == nums[i-1]){
                i++;
            }
            if (nums[i]>0) break;

            int left = i+1;
            int right = len-1;
            while (left<right){
                int sums = nums[i]+nums[left]+nums[right];
                if ( sums == 0){
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    //必须是在找到答案后去重，不然会过滤掉正确答案
                    // 去重
                    while (left<right && nums[left]== nums[left+1]){
                        left++;
                    }
                    // 去重
                    while (left<right && nums[right]== nums[right-1]){
                        right--;
                    }
                    left++;
                    right--;
                }
                else if ( sums < 0){
                    left++;
                }
                else{
                    right--;
                }
            }
        }
        return result;
    }
}