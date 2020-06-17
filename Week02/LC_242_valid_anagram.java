package Week02;

import java.util.HashMap;

public class LC_242_valid_anagram {

    public boolean isAnagram(String s, String t) {
        //Brute force
        //#1. sort, compare one by one
        //#2, HashMap, tracking charaters
        //similar with LC_49
        if (s.length() != t.length()){
            return false;
        }
        HashMap<Character,Integer> map = new HashMap<>();
        for(int i =0; i<s.length();i++){
            Character c = s.charAt(i);
            if (map.containsKey(c)){
                map.replace(c,map.get(c) + 1);
            }
            else{
                map.put(c,1);
            }
        }

        for(int i =0; i<t.length();i++){
            Character c = t.charAt(i);
            Integer count = map.get(c);
            if (count == null){
                return false;
            }
            if (count == 1){
                map.remove(c);
            }
            else {
                map.replace(c,--count);
            }
        }
        if (map.isEmpty()){
            return true;
        }
        return false;
    }
}