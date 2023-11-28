public class MinCoins {

  
    static int getChange(int change) {
        int[] coinValues = {10, 5, 1};
        int numCoins = 0;

        for (int coin : coinValues) {
            
            numCoins += change / coin;

            change %= coin;
        }

        return numCoins;
    }

    public static void main(String[] args) {
        int change = 10007;

        int result = getChange(change);

        System.out.println("Número mínimo de monedas necesarias: " + result);
    }
}
