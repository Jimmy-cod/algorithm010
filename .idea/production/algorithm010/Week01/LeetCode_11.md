class Solution {


    //11. 盛最多水的容器
    
    public int maxArea(int[] height) {

        int len = height.length;
        int i=0, j=len-1,h=0;
        int maxArea =0;

        while (i<j) {
            int area = Math.min(height[i],height[j])*(j-i);
            maxArea = Math.max(area,maxArea);
            //only need to move the index in lower side
            if(height[i]<height[j]){
                i++;
            }
            else{
                j--;
            }
        }

        return maxArea;

    }
}