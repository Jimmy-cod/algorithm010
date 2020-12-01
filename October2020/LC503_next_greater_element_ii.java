package October2020;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class LC503_next_greater_element_ii {

    public static int[] nextGreaterElements(int[] nums) {
        int[] ans = new int[nums.length];
        Deque<Integer> stack = new LinkedList<>();
        int n = nums.length;
        for (int i=2*n-1;i>=0;i--){
            while(!stack.isEmpty() && stack.peek()<=nums[i%n]){
                stack.pop();
            }
            ans[i%n] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i%n]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,1};
        int[] ans = nextGreaterElements(nums);

        Arrays.stream(ans).forEach(a->System.out.println(a));
    }
}
