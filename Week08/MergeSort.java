package Week08;

import java.util.Arrays;

public class MergeSort {

    public int[] mergeSort(int[] arr){
        mergeSort(arr,0,arr.length-1);
        return arr;
    }

    private void mergeSort(int[] arr, int begin, int end) {
        if (begin>=end) return;
        int mid = (begin+end) >> 1;

        mergeSort(arr,begin,mid);
        mergeSort(arr,mid+1,end);
        merge(arr,begin,mid,end);
    }

    private void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right-left+1];
        int i=left,j=mid+1;
        int k=0;
        while (i<=mid && j<=right){
            temp[k++] = arr[i]<arr[j] ? arr[i++] : arr[j++];
        }
        while (i<=mid) temp[k++] = arr[i++];
        while (j<=right) temp[k++] = arr[j++];

        //copy from temp to arr
//        for (int p=0;p<temp.length;p++){
//            arr[left+p] = temp[p];
//        }
        System.arraycopy(temp,0,arr,left,temp.length);
    }

    private void swap(int[] arr, int i, int j) {
        if (i==j) return;
        arr[i] = (arr[i] + arr[j]) - (arr[j] = arr[i]);
    }
}

class Test31 {

    public static void main(String[] args) {
        int[] arr = {2,3,1,4,0,-1,1};
        MergeSort sort = new MergeSort();
        int[] ans = sort.mergeSort(arr);
        System.out.println(Arrays.toString(ans));
    }
}
