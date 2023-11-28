public class CoinChange {
    public static void main(String[] args) {
        int change = 30;
        int[] coins = {1, 3, 4};

        int result = getMoneyChange(change, coins);
        System.out.println("Minimum number of coins needed: " + result);
    }

    private static int getMoneyChange(int change, int[] coins) {
        int[] dp = new int[change + 1];

        
        dp[0] = 0;

        for (int i = 1; i <= change; i++) {
            dp[i] = Integer.MAX_VALUE;

            for (int coin : coins) {
                if (i >= coin && dp[i - coin] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        return dp[change];
    }
}
