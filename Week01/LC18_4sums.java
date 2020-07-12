    public List<List<Integer>> fourSum(int[] nums, int target) {
        
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len<4) return res;
        
        Arrays.sort(nums);
        
        for (int i=0;i<len-3;i++){
            if (i>0 && nums[i] ==nums[i-1]){
                continue;
            }
            if (nums[i]>target) break;

            for (int m = i+1;m<len-2;m++){
                
                if (i>0 && nums[i] ==nums[i-1]){
                    continue;
                }

                if (nums[i] + nums[m]>target) break;
                
                int left = m+1;
                int right = len-1;
                while (left<right){
                        
                        int sums = nums[i] + nums[left]+nums[m]+nums[right];
                        if (sums == target){
                            res.add(Arrays.asList(nums[i],nums[left],nums[m],nums[right]));                            
                                
                            while (left<right && nums[left]== nums[left+1]){
                                left++;
                            }
                            while (left<right && nums[right]== nums[right-1]){
                                right--;
                            }
                            left++;
                            right--;
                        }
                        else if (sums < target){
                            left++;
                        }
                        else {
                            right--;
                        }
                    }
                }
            }            
        }     
        
        return res;      

    }