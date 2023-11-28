import java.util.Arrays;
import java.util.Collections;

public class MaximumAdvertisementRevenue {




    static long getMaxRevenue(int[] prices, int[] clicks) {
        Integer[] sortedPrices = Arrays.stream(prices).boxed().toArray(Integer[]::new);
        Integer[] sortedClicks = Arrays.stream(clicks).boxed().toArray(Integer[]::new);

        Arrays.sort(sortedPrices, Collections.reverseOrder());
        Arrays.sort(sortedClicks, Collections.reverseOrder());

        long totalRevenue = 0;
        int n = Math.min(sortedPrices.length, sortedClicks.length);

        for (int i = 0; i < n; i++) {
            totalRevenue += (long) sortedPrices[i] * sortedClicks[i];
        }

        

        return totalRevenue;
    }

    public static void main(String[] args) {
        int[] prices = {5, 3, 7, 2, 8};
        int[] clicks = {2, 4, 1, 3, 7};

        long result = getMaxRevenue(prices, clicks);

        System.out.println("Ingreso total máximo: " + result);
    }
}
