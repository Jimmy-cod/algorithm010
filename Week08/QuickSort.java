package Week08;

import java.util.Arrays;

public class QuickSort {
/*
快排的思想：如果要排序数组中下标从 p 到 r 之间的一组数据，我们选择 p 到 r 之间的任意一个数据作为 pivot（分区点）。
我们遍历 p 到 r 之间的数据，将小于 pivot 的放到左边，将大于 pivot 的放到右边，将 pivot 放到中间。
经过这一步骤之后，数组 p 到 r 之间的数据就被分成了三个部分，前面 p 到 q-1 之间都是小于 pivot 的，中间是 pivot，后面的 q+1 到 r 之间是大于 pivot 的。
根据分治、递归的处理思想，我们可以用递归排序下标从 p 到 q-1 之间的数据和下标从 q+1 到 r 之间的数据，直到区间缩小为 1，就说明所有的数据都有序了。
 */
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

    //将小于 pivot 的放到左边，将大于 pivot 的放到右边，将 pivot 放到中间
    private int partition(int[] arr, int begin, int end) {
        int pivot = end,count =begin;
        for(int i=begin;i<end;i++){
            if(arr[i]<arr[pivot]){
                swap(arr,i,count++);
            }
        }
        swap(arr,count,end);
        return count;
    }

    private void swap(int[] arr, int i, int j) {
        if (i!=j){
//            int temp = arr[i];
//            arr[i] = arr[j];
//            arr[j] = temp;
            //only int[] can do this way
            arr[i] = (arr[i]+arr[j])-(arr[j]=arr[i]);
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

