import java.util.ArrayList;

public class MaximumNumberofPrizes {
    public static void main(String[] args){
        int candies = 15;
        ArrayList<Integer> prizes = getNumberPrizes(candies);

        System.out.println("Number of prizes: " + prizes.size());
        System.out.println("List of prizes: " + prizes);
    }



    public static ArrayList<Integer> getNumberPrizes(int candies){
        ArrayList<Integer> result = new ArrayList<>();


        int prize = 1;
        while (true){
            if (candies - prize == 0){
                result.add(prize);
                break;
            }
            else if (candies - prize < 0){
                int last = result.get(result.size()-1);
                result.set(result.size()-1, last+candies);
                break;
            }

            result.add(prize);
            candies -= prize;
            prize ++;
        }

        return result;
    }
}
