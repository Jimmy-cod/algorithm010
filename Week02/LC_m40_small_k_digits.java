package Week02;

import java.util.PriorityQueue;

public class LC_m40_small_k_digits {
/*
* Solutions:
    //1. sort: NlogN
    //2. heap: NlogK
    //3. quick-sort: logN
* */
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0) return new int[0];
        // Java 默认是小根堆，实现大根堆需要重写一下比较器。
        PriorityQueue<Integer> heap = new PriorityQueue<>((a,b)->b-a);
        for (int i :arr){
            if (heap.size() < k){
                heap.offer(i);
            }
            else if (i<heap.peek()){
                heap.poll();
                heap.offer(i);
            }
        }
        return heap.stream().mapToInt(Integer::intValue).toArray();
    }
}
