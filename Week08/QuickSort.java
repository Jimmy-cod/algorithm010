package Week08;

import java.util.Arrays;

public class QuickSort {

    public int[] quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
        return arr;
    }

    private void quickSort(int[] arr, int begin, int end) {
        if (begin>=end) return;;
        int pivot = partition(arr,begin, end);
        quickSort(arr,begin,pivot-1);
        quickSort(arr,pivot+1,end);
    }

    private int partition(int[] arr, int begin, int end) {
        int pivot = end,count =begin;
        for(int i=begin;i<end;i++){
            if(arr[i]>arr[pivot]){
                swap(arr,i,count++);
            }
        }
        swap(arr,count,end);
        return count;
    }

    private void swap(int[] arr, int i, int j) {
        if (i!=j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

}

class Test3 {

    public static void main(String[] args) {
        int[] arr = {2,3,1,4};
        QuickSort sort = new QuickSort();
        int[] ans = sort.quickSort(arr);
        System.out.println(Arrays.toString(ans));
    }
}

