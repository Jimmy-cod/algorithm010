package Week09;

import java.util.HashMap;

public class LC387_first_unique_character {
    public int firstUniqChar(String s) {
        int[] count = new int[26];

        for (int i=0; i<s.length();i++) {
            int c = s.charAt(i)-'a';
            count[c]++;
        }
        for (int i=0; i<s.length();i++) {
            if (count[s.charAt(i)-'a']==1){
                return i;
            }
        }
        return -1;
    }
}
