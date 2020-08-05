package Week08;

import java.util.ArrayList;
import java.util.List;

public class LC118_pascals_triangle {

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows<1){
            return res;
        }
        for (int i=0;i<numRows;i++){
            List<Integer> newRow = new ArrayList<>();
            newRow.add(1);
            if (i==0){
                res.add(newRow);
            }
            else {
                List<Integer> preRow = res.get(i - 1);
                newRow.add(1);
                for (int j=1; j<preRow.size();j++){
                    newRow.add(preRow.get(j-1)+preRow.get(j));
                }
                newRow.add(1);
                res.add(newRow);
            }
        }
        return res;
    }
}
