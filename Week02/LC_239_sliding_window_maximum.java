package Week02;

import java.util.ArrayDeque;
import java.util.Deque;

public class LC_239_sliding_window_maximum {
        /*
         * 单调栈/队列（Monotonous Stack/Queue) 要求栈上元素只能单调递减,即[5,4,3,1]
         * 栈顶元素是窗口的最大值。但是随着窗口移动，栈顶需要退出窗口，所以，题解在维护单调栈的时候用的是元素下标，而非元素实际的值
         * 这题中Queue只存元素下标！
         * 当新元素进Queue时，从最后往前比，如果比它大把该下标从Queue删除，最后加入新元素下标
         * 每个元素要历遍一次，在Queue删除一个，Queue 和nums再读一次，所有是O(4n) = Q(n)         *
         * */
        public int[] maxSlidingWindow(int[] nums, int k) {
            int lo = 0, hi = 0;
            int len = nums.length;
            int ans[] = new int[len - k + 1];
            Deque<Integer> queue = new ArrayDeque<>();

            while (hi < len) {
                if (hi - lo < k) {
                    addQueue(queue, nums, hi);
                    hi++;
                } else {
                    ans[lo] = nums[queue.getFirst()];
                    //queue顶元素是窗口的最后一个值，queue从queue中移除顶元素
                    if (queue.getFirst() == lo) {
                        queue.removeFirst();
                    }
                    lo++;
                }
            }
            //only last k of elements in queue, we need to get the max value
            ans[lo] = nums[queue.getFirst()];
            return ans;
        }

        // monotonous queue
        //queue 只存数组下标，每次进queue，从后比较其他下标的值，如果比它小，扔掉。最后加入该下标。
        //queue 允许超过k个元素，因为我们只关心最大值
        private void addQueue(Deque<Integer> q, int[] nums, int hi) {
            while (!q.isEmpty() && nums[q.getLast()] < nums[hi]) {
                q.removeLast();
            }
            q.addLast(hi);
        }

//----------------------test-------------//
    public static void main(String[] args) {
        LC_239_sliding_window_maximum solution = new LC_239_sliding_window_maximum();
        int[] nums = new int[]{1,-1};
        int k =1;
        int[] ans = solution.maxSlidingWindow(nums,k);

    }
}


