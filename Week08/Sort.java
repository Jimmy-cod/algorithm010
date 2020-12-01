package Week08;

import java.util.Arrays;
import java.util.Collection;

public class Sort {
//https://www.cnblogs.com/onepixel/p/7674659.html

//    对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
        public int[] insertSort(int[] arr){
            int len = arr.length;
            int preIdx, current;
            for (int i =1;i<len;i++){
                preIdx = i-1;
                current = arr[i];
                while(preIdx>=0 && arr[preIdx]>current){
                    arr[preIdx+1] = arr[preIdx];
                    preIdx--;
                }
                arr[preIdx+1] = current;
            }
            return arr;
        }
//是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
        public int[] shellSort(int[] arr){
            int len = arr.length;
            for (int gap = len/2; gap>0;gap=gap/2){
                for (int i = gap; i<len;i++){
                    int j = i;
                    int current = arr[i];
                    while(j-gap >= 0 && current<arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j = j-gap;
                    }
                    arr[j] = current;
                }
            }
            return arr;
        }

//   归并排序（Merge Sort）:
//   采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，
//   再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
/*
1.把长度为n的输入序列分成两个长度为n/2的子序列；
2.对这两个子序列分别采用归并排序；
3.将两个排序好的子序列合并成一个最终的排序序列。
*/

    public int[] mergeSort(int[] arr){
        int len = arr.length;
        if(len< 2) return arr;
        mergeSort(arr,0,arr.length-1);
        return arr;
    }

    private void mergeSort(int[] arr, int left, int right){
        if (left >= right) return;
        int mid = (left+right) >> 1;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid+1, right);
        merge(arr,left,mid,right);
    }
/*
merge 函数的作用就是，将已经有序的 A[p…q]和 A[q+1…r]合并成一个有序的数组，并且放入 A[p…r]
 */
    private void merge(int[] arr, int left, int mid, int right) {
        //申请一个大小跟A[p...r]一样的临时数组
        int[] temp = new int[right-left+1];
        int i = left;
        int j = mid+1;
        int k = 0;

        while (i <mid && j <= right){
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];

//            if (arr[i] <= arr[j]){
//                temp[k] = arr[i];
//                k++;
//                i++;
//            }
//            else {
//                temp[k] = arr[j];
//                k++;
//                j++;
//            }
        }
        // 将剩余的数据拷贝到临时数组tmp
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        // 将tmp中的数组拷贝回A[p...r]
        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
    }

/*
 快速排序（Quick Sort）:
快速排序使用分治法来把一个串（list）分为两个子串（sub-lists）。
具体算法描述如下：
从数列中挑出一个元素，称为 “基准”（pivot）；
重新排序数列，所有元素比基准值小的摆放在基准前面，所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
在这个分区退出之后，该基准就处于数列的中间位置。这个称为分区（partition）操作；
递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序。
*/

    public int[] quickSort(int[] arr){
        int len = arr.length;
        if(len< 2) return arr;
        quickSort(arr,0,arr.length-1);
        return arr;
    }

    private void quickSort(int[] arr, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(arr, begin,end);
        quickSort(arr,begin,pivot-1);
        quickSort(arr,pivot+1,end);
    }

    private int partition(int[] arr, int begin, int end) {
        // pivot: 标杆位置，counter: 小于pivot的元素的个数
        int pivot = end, count = begin;
        for (int i = begin; i<end;i++){
            if (arr[i] < arr[pivot]){
                swap(arr, count, i);
                count++;
            }
        }
        swap(arr,pivot,count);
        return count;
    }

    private void swap(int[] arr, int i, int j){
        if (i!=j){
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

}


class Test {

    public static void main(String[] args) {
        Collection<Integer> test ;
        int[] arr = {2,3,1,4};
        Sort sort = new Sort();
//        int[] ans1 = sort.insertSort(arr);
//        int[] ans2 = sort.shellSort(arr);
//        int[] ans3 = sort.mergeSort(arr);
        int[] ans = sort.quickSort(arr);
        System.out.println(Arrays.toString(ans));
    }
}
