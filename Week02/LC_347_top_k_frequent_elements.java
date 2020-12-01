package Week02;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
//        int[] topK = topKFrequent(nums,k);
        int[] topK = topKFrequent_qsort(nums,k);
        Arrays.stream(topK).forEach(System.out::println);
//        System.out.println(Arrays.toString(topK));
//        Stream.of(topK).forEach(i->System.out.println(i));
    }


    public static int[] topKFrequent_qsort(int[] nums, int k) {
        if (k == 0) {
            return new int[0];
        }
        HashMap<Integer, Integer> map = new HashMap();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        List<Map.Entry<Integer, Integer>> queue = new ArrayList<>(map.size());
        for (Map.Entry<Integer, Integer> e: map.entrySet()){
            queue.add(e);
        }
        int[] res = new int[k];
        //利用快排，倒序排序，当pivot==k-1位置时，表示前面k个元素就是top K
        List<Map.Entry<Integer, Integer>> selectedList = quickSelect( queue,0,queue.size()-1, k-1);
        int idx =0;
        for(Map.Entry<Integer, Integer> e : selectedList){
            res[idx++] = e.getKey();
        }
        return res;
    }

    private static List<Map.Entry<Integer, Integer>> quickSelect( List<Map.Entry<Integer, Integer>> queue, int begin, int end, int k) {
        if (begin>end){
            return null;
        }
        int pivot = partition(queue,begin,end);
        if (pivot == k) {
            List<Map.Entry<Integer, Integer>> newList= new ArrayList<>();
            for (int i =0;i<=k;i++){
                newList.add(queue.get(i));
            }
            return newList;
        }
        //如果pivot<k,说明后面还有topK元素，继续对后半部分细分
        //pivot>k,说明太多，对前半部分细分
        return pivot<k? quickSelect(queue,pivot+1,end,k):quickSelect(queue,begin,pivot-1,k);
    }

    private static int partition(List<Map.Entry<Integer, Integer>> queue, int begin, int end) {
        int pivot = end, count = begin;
        for (int i = begin;i<queue.size();i++){
            if (queue.get(i).getValue() > queue.get(pivot).getValue()){
                Collections.swap(queue,i,count++);
            }
        }
        Collections.swap(queue,count,pivot);
        return count;
    }

}
