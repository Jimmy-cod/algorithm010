package Week04;

public class LC850_lemonade_change {
/*
* 1.loop through all bill
* 2.count how many 5, 10, 20 bills
* 3.for $5 just reduce the count, $10 reduce one $5 count,
* greedy: for $20 try to reduce one $5 and one $10 count, if not able, reduce 3 of $5
* 4.if don't have enough count return false
* */
    public boolean lemonadeChange(int[] bills) {
        int c5=0, c10=0;
        for (int b:bills){
            if (b==5){
                c5++;
            }
            else if(b==10){
                if (c5 == 0){
                    return false;
                }
                c5--;
                c10++;
            }
            else {
                if (c10>0) {
                    c10--;
                    if (c5 == 0){
                        return false;
                    }
                    c5--;
                }
                else {
                    if (c5>=3){
                        c5 = c5 -3;
                    }
                    else {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}
