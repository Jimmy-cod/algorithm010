package Week08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Interview_Solution {

    public interface IEquation{

        double[] convertInput(String[] inputs);
        String calculate(double[] inputs);
    }

    public static class QuadatraticEquation implements IEquation{

        public double[] convertInput(String[] inputs) throws IllegalArgumentException{
            double[] res = new double[3];
            if (inputs.length == 3){
                try{
                    res[0] = Double.valueOf(inputs[0]);
                    res[1] = Double.valueOf(inputs[1]);
                    res[02] = Double.valueOf(inputs[2]);
                    if (res[0] == 0){
                        throw new IllegalArgumentException("a cannot ba 0;");
                    }
                }catch (Exception e){
                    throw new IllegalArgumentException("Input must be three doubles and separated by spaces.");
                }
            }
            else {
                throw new IllegalArgumentException("Input must be three doubles and separated by spaces.");
            }
            return res;
        }

        public String calculate(double[] inputs){
            String res = "";
            double a = inputs[0];
            double b = inputs[1];
            double c = inputs[2];
            double sqrt =0;
            double part2 = b*b - 4*a*c;
            if (part2 >= 0){
                if (part2==0) sqrt =0;
                else {
                    sqrt = Math.sqrt(part2);
                }
                double res1 = (b*-1 + sqrt)/2*a;
                double res2 = (b*-1 - sqrt)/2*a;
                return String.valueOf(res1) +" " + String.valueOf(res2);
            }
            else {
                sqrt = Math.sqrt((part2*-1));
                double p1 = (b*-1)/(2*a);
                double p2 = (sqrt)/(2*a);
                String s1 = String.valueOf(p1)+"+"
                        +String.valueOf(p2) +"i";
                String s2 = String.valueOf(p1)+"-"
                        +String.valueOf(p2) +"i";
                return s1 +" " + s2;
            }

        }

    }

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        String input = sc.nextLine();
        System.out.println(input);
        String[] inputVals = input.split(" ");

        IEquation equation = new QuadatraticEquation();
        double[] params = equation.convertInput(inputVals);
        String results = equation.calculate(params);

        System.out.println(results);
    }

}
