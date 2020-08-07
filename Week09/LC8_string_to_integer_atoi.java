package Week09;

public class LC8_string_to_integer_atoi {
    //chr = charAt(idx), if it's ' ', idx++
    //int i = chr-'0'
    public static int myAtoi(String str) {
        if(str.length() == 0) return 0;
        int len = str.length();
        int idx =0;
        int n =0;
        char sign='+';
        //remove leading space, we can use trim(), but string in immutable, that cause extra space
        while ( idx<len-1 && str.charAt(idx) == ' ') {
            idx++;
        }
        if (str.charAt(idx) == '-' || str.charAt(idx) == '+'){
            sign = str.charAt(idx);
            idx++;
        }

        while (idx<len){
            int i = str.charAt(idx) - '0';
            //it's not an integer
            if (i<0 || i>9){
                break;
            }
            if (n>Integer.MAX_VALUE/10 || n==Integer.MAX_VALUE/10 && i> Integer.MAX_VALUE%10){
                if (sign=='-'){
                    return Integer.MIN_VALUE;
                }
                else {
                    return Integer.MAX_VALUE;
                }
            }
            n = n*10 +i;
            idx++;
        }
        return sign=='-'?-1*n:n;
    }

    public static void main(String[] args) {
       int n = myAtoi("+1");
       System.out.println(n);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
