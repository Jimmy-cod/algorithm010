package Week02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class LC_49_Group_anagrams {

    //for each element, convert to char list->sort->convert back to String
    //use this one as key in a HashMap, value is the List<String> for output
    // loop through strs, if str is in the hashmap, get the value add str to the list
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String,List<String>> map = new HashMap<>();
        for (String s : strs){
            char[] c = s.toCharArray();
            Arrays.sort(c);
            String key = String.valueOf(c);//c.toString();
            if (!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(s);

            List<String> l = map.get(key);
            l.add(s);
            map.put(key,l);
        }

//        for (List<String> v : map.values()){
//            res.add(v);
//        }
//        return res;
        return new ArrayList(map.values());
    }

    /*
    * Solution 2: convert char to int
    * make a char[26] to keep all char counts.
    * e.g. "aabccee" will be "2*1*2*0*2*0...",need a separator to identify how count for each chars
    * dont need to sort here, so save logN for complexity
    * */

    public List<List<String>> groupAnagrams_2 (String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] cs = s.toCharArray();
            int[] count = new int[26];
            StringBuilder sb = new StringBuilder();
            for (char c : cs){
                count[c-'a']++;
            }

            for (int i=0;i<26;i++){
                sb.append(count[i]);
                sb.append("*");
            }
            //key will be like "2*1*2*0*2*0..."
            String key = sb.toString();
            if (!map.containsKey(key)){
                map.put(key,new ArrayList<String>());
            }
            map.get(key).add(s);
        }
        return new ArrayList<>(map.values());
    }
}
