package Week08;

public class LC1122_relative_sort_array {
    /*
    * 提示：

arr1.length, arr2.length <= 1000
0 <= arr1[i], arr2[i] <= 1000
arr2 中的元素 arr2[i] 各不相同
arr2 中的每个元素 arr2[i] 都出现在 arr1 中
* 计数排序:
先将arr1中的数全都记录到数组count中，然后遍历arr2的同时，将arr2中的数置入arr1，
注意由于arr2中的数在arr1中也出现了，所以直接从count中取出即可
处理好arr2之后，再处理剩下的数字，一格一格往里填就好了
    * */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int[] count = new int [1001];
        for(int n1:arr1){
            count[n1]++;
        }
        int i =0;
        // 先安排 arr2 中的数
        for (int n2: arr2){
            while (count[n2]>0){
                arr1[i++] = n2;
                //从count中取出
                count[n2]--;
            }
        }
        // 再排剩下的数
        for (int j = 0; j < count.length; j++) {
            while ( (count[j]>0)){
                arr1[i++]=j;
                count[j]--;
            }
        }
        return arr1;
    }
}
