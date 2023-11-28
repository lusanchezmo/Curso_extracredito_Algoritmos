import java.util.ArrayList;
import java.util.Arrays;

public class MaximumSalary {
    public static void main(String[] args){
        int[] integers = {23, 3};
        System.out.println(getSalary(integers));
    }

    public static String getSalary(int[] integers){
        Digits[] digits = new Digits[integers.length];
        for (int i = 0; i < integers.length; i++){
            digits[i] = new Digits(integers[i]);
        }

        
        Arrays.sort(digits);

        
        StringBuilder salary = new StringBuilder();
        for (Digits digit : digits) {
            salary.append(digit.digit);
        }

        return salary.toString();
    }

    private static class Digits implements Comparable<Digits>{
        private final int digit;

        public Digits(int digit){
            this.digit = digit;
        }

        private ArrayList<Integer> getDigits (Digits digit){
            
            ArrayList<Integer> list = new ArrayList<>();

            int num = digit.digit;
            while (num > 0){
                list.add(0, num % 10);
                num /= 10;
            }

            return list;
        }

        private int isBetter(Digits other){
            ArrayList<Integer> a = getDigits(this);
            ArrayList<Integer> b = getDigits(other);
            ArrayList<Integer> tmp = (ArrayList<Integer>) a.clone();

            a.addAll(b);
            b.addAll(tmp);

            for (int i = 0; i < a.size(); i++){
                if (a.get(i % a.size()) > b.get(i % b.size())){
                    return -1; 
                }
                else if (a.get(i % a.size()) < b.get(i % b.size())){
                    return 1;
                }
            }

            return 0;
        }

        @Override
        public int compareTo(Digits o) {
            return isBetter(o);
        }

        @Override
        public String toString(){
            return String.valueOf(this.digit);
        }
    }
}
