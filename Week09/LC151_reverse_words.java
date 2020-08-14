package Week09;

public class LC151_reverse_words {
    public static String reverseWords(String s) {
        if (s==null || s.length()==0 || !s.contains(" ")){
            return s;
        }
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i=words.length-1;i>=0;i--){
            if (!words[i].isEmpty()){
                sb.append(words[i]);
                sb.append(" ");
            }
        }
        if (sb.length()>0)
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static void main(String[] args) {
        String s =  "  hello world!  ";
        String res = reverseWords(s);


    }
    }
