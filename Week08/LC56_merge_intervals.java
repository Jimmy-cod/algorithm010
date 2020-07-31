package Week08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC56_merge_intervals {
/*
1. sort 左边位置 intervals[][0]
2. 比较每个右边位置intervals[i][1]是否大于后面左边位置intervals[i+1][0]
3， 右边位置是 max(a[1], b[1])
*/
    public int[][] merge(int[][] intervals) {
        List<int[]> res = new ArrayList<>();
        if (intervals == null || intervals.length==0){
            return intervals;
        }
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
        int len = intervals.length;
        int i=0;
        while (i < len){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while (i<len-1 && right >= intervals[i+1][0]){
                i++;
                right = Math.max(right,intervals[i][1]);
            }
            res.add(new int[]{left,right});
            i++;
        }
        return res.toArray(new int[0][]);
    }

}
