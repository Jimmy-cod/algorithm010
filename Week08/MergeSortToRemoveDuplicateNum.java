package Week08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSortToRemoveDuplicateNum {
    int count;
    public int[] mergeSort(int[] arr){
        mergeSort(arr,0,arr.length-1);
       // return arr;
        int idx =0;
        while (arr[idx]!=Integer.MAX_VALUE) idx++;
        return Arrays.copyOfRange(arr,0,idx);
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
        Arrays.fill(temp,Integer.MAX_VALUE);
        int i=left,j=mid+1;
        int k=0;
        while (i<=mid && j<=right){

            if (temp[k]==arr[i]){//remove duplicated
                i++;
            }
            else if (temp[k] == arr[j]){//remove duplicated
                j++;
            }
            else if (arr[i]<arr[j]){
                temp[k] = arr[i];
                k++;
                i++;
            }
            else if (arr[i]>arr[j]){
                temp[k] = arr[j];
                k++;
                j++;
            }
            else{
                i++;
            }
        }

        while (i<=mid) {
            if (temp[k]==arr[i]){ //remove duplicated
                i++;
            }
            else{
                temp[k++] = arr[i++];
            }
        }
        while (j<=right){
            if (temp[k]==arr[j]){ //remove duplicated
                j++;
            }
            else {
                temp[k++] = arr[j++];
            }
        }

        //copy from temp to arr
        for (int p=0;p<temp.length;p++){
            arr[left + p] = temp[p];
            if (temp[p] == Integer.MAX_VALUE) count++;
        }
//        System.arraycopy(temp,0,arr,left,temp.length);
    }

}

class Test32 {

    public static void main(String[] args) {
        int[] arr = {6,2,3,3,1,4,2,2,6,0,0,0};
        MergeSortToRemoveDuplicateNum sort = new MergeSortToRemoveDuplicateNum();
        int[] ans = sort.mergeSort(arr);
        System.out.println(Arrays.toString(ans));
        System.out.println("count="+sort.count);
    }
}
