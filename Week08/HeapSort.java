package Week08;

import java.util.Arrays;
import java.util.PriorityQueue;

public class HeapSort {
    public void heapSort(int arr[]){
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i=0;i<arr.length;i++){
            queue.add(arr[i]);
        }

        for (int i=0;i<arr.length;i++){
            arr[i] = queue.poll();
        }
    }
}


class TestHeapSort {

    public static void main(String[] args) {
        int[] arr = {2,3,1,4,0,-1,1};
        MergeSort sort = new MergeSort();
        int[] ans = sort.mergeSort(arr);
        System.out.println(Arrays.toString(ans));
    }
}
