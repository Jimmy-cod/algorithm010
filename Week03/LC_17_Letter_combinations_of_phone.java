package Week03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC_17_Letter_combinations_of_phone {

    //use a map to keep number->letter
    //number of digits will be number of letter that choose for map
    public static List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.length() == 0) return res;

        HashMap<String,String> map = new HashMap<String,String>(){{
            put("2", "abc");
            put("3", "def");
            put("4", "ghi");
            put("5", "jkl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};
        generate(res,digits,0,map,new StringBuilder());
        return res;
    }

    private static void generate(List<String> res, String digits, int level, HashMap<String, String> map, StringBuilder s) {
        if (level == digits.length()){
            res.add(s.toString());
            return;
        }
        //current process
        String key = String.valueOf(digits.charAt(level));
        String letters = map.get(key);

        for (int i =0; i<letters.length();i++){
            s.append(letters.charAt(i));
            //drill down
            //是下探到下个level，所以是level+1
            //压下面的letter
            generate(res,digits,level+1,map,s);
            s.deleteCharAt(s.length()-1);
        }
    }

    public static void main(String[] args) {
        List<String> res =letterCombinations("23578235782357823578");
        System.out.println("Done!");
    }
}
