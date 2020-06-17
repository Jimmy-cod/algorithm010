class Solution {
    public int[] plusOne(int[] digits) {
        //here, need to loop from eno of digits
        for (int i = digits.length-1; i>=0;i--){
            digits[i]++;
            digits[i] = digits[i] % 10;
            //e.g. 1,9,9; when it becomes 200. it will end th loop
            if (digits[i] != 0) return digits;
        }
        // reach here, only when all digits are 9. e.g. 999
        // in java, int[] initia with '0'
        int[] newDigits = new int[digits.length +1];
        // System.arraycopy(digits,0,newDigits,1,digits.length);
        newDigits[0] = 1;
        return newDigits;

    }
}