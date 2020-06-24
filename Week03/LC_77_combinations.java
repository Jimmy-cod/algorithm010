package Week03;

import java.util.ArrayList;
import java.util.List;

public class LC_77_combinations {

    //iterrate 1..n
    //recursive
    //every layer, need a loop to get rest of value for n, until reach k.
    //在每一层中，把余下的值加到list中直到k,然后输出list.
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (k == 0 ){
            return ans;
        }
        List<Integer> list = new ArrayList<>();
        dps(ans, list, 1, k ,n);
        return ans;
    }
    private void dps(List<List<Integer>> ans, List<Integer> list, int begin,  int k, int n){
        if (list.size() == k){
            //need new a list
            //here is the output of stack
            ans.add(new ArrayList<>(list));
            return;
        }

        for (int i = begin;i <= n;i++) {
            list.add(i);
            //add 1 to Stack, then 2, then 3 .. all n add to stack.
            //then go to for loop. add second number.
            dps(ans,list, i+1,k,n);
            list.remove(list.size()-1);
        }
    }
}
