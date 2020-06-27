package Week02;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC_22_generate_parentheses {

    public List<String> generateParenthesis_new(int n){
        List<String> res = new ArrayList<>();
        if (n<=0){
            return res;
        }
        StringBuilder sb = new StringBuilder();
        generate(res,0,0,sb ,n);
        return res;
    }

    private void generate(List<String> res, int l, int r, StringBuilder s, int n) {
        if (l==r && l == n){
            res.add(s.toString());
            return;
        }
        if (l<n){
            s.append("(");
            generate(res,l+1,r,s,n);
            s.deleteCharAt(s.length()-1);
        }
        if (r<l){
            s.append(")");
            generate(res,l,r+1,s,n);
            s.deleteCharAt(s.length()-1);
        }
    }

    /*------------------------------*/

    /*
     * no limitation for "("
     * before add "(", # of "(" must smaller than n
     * before add ")", # of ")" must smaller than before add "("
     * at the end # of "(" must be equal # of ")" --terminator
     *
     * */
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();

        useStringBuilder(0,0,n,new StringBuilder(),res);
        //  generate(0,0,n,"", res);
        //  useStack(n,res);
        return res;
    }

    private void useStringBuilder(int left, int right, int n, StringBuilder sb, List<String> res){

        //terminator
        if(left ==right && left == n){
            System.out.println(sb.toString());
            res.add(sb.toString());

        }
        //process current logic;
        if (left <n)
        {
            sb.append("(");
            int newleft = left+1;
            //drill down
            useStringBuilder(newleft, right, n,sb,res);
            //clear up
            //left = left-1;
            sb.deleteCharAt(sb.length()-1);
        }
        if (right<left)
        {
            sb.append(")");
            right = right+1;
            useStringBuilder(left, right, n,sb,res);
            //clear up
            right = right-1;
            sb.deleteCharAt(sb.length()-1);
        }
    }

    private void useStack(int n, List<String> res){
        Stack<Node> stack = new Stack<>();

        stack.add(new Node(0, 0,""));

        while(!stack.empty()){
            Node curr = stack.pop();
            if(curr.left<n){
                int l = curr.left+1;
                stack.push(new Node(l, curr.right,curr.val+"("));
            }

            if(curr.left>curr.right){
                stack.push(new Node(curr.left, curr.right+1,curr.val+")"));
            }

            if (curr.left == n && curr.right==n) {
                System.out.println(curr.val);
                res.add(curr.val);
            }
        }
    }

    class Node{
        int left;
        int right;
        String val;
        public Node(int l,int r, String s){
            left = l;
            right = r;
            val = s;
        }
    }
    // Recusion
    private void generate(int left, int right, int n, String sb, List<String> res) {
        System.out.println("left = "+left);
        //terminator
        if(left ==right && left == n){
            System.out.println(sb.toString());
            res.add(sb.toString());

        }
        //process curren logic;
        if (left <n)
        {
            //drill down
            // left = left+1; //this is incorrect!
            generate(left+1, right, n,sb+"(",res);
        }
        if (right<left)
        {
            // right = right+1; //generate wrong answer!
            generate(left, right+1, n,sb+")",res);
        }
    }
}
