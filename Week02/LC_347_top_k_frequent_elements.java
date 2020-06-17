package Week02;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LC_347_top_k_frequent_elements {
    //using HashMap to keep frequent of all elements
    //using a 最小堆（min heap） to keep top K frequent from hashmap entry
    public static int[] topKFrequent(int[] nums, int k) {
        if (k==0){
            return new int[0];
        }
        HashMap<Integer,Integer> map = new HashMap();
        for (int n : nums){
            map.put(n,map.getOrDefault(n,0)+1) ;
        }

        PriorityQueue<Map.Entry<Integer,Integer>> heap =   new PriorityQueue<>(new Comparator<Map.Entry<Integer,Integer>>() {
            @Override
            public int compare(Map.Entry<Integer,Integer> a, Map.Entry<Integer,Integer> b) {
                return a.getValue() - b.getValue();
            }
        });

        for (Map.Entry e: map.entrySet()){
            heap.add(e);
            if (heap.size()>k){
                heap.poll();
            }
        }

        int[] topK = new int[k];
        for (int i = k-1;i>=0;i--){
            Map.Entry e = heap.poll();
            if (e == null) break;
            topK[i] = (Integer) e.getKey();
        }
        return topK;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{4,4,4,5,5,6};
        int k =2;
        int[] topK = topKFrequent(nums,k);
    }
}
