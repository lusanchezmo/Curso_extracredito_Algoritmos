public class Mochila {

    public static void main(String[] args) {
        int pesoMaximo = 10;
        int[] pesos = {6, 3, 4, 2};

        int resultado = pesoOptimo(pesoMaximo, pesos);
        System.out.println("Peso máximo de oro que se puede obtener: " + resultado);
    }

    public static int pesoOptimo(int pesoMaximo, int[] pesos) {
        int[][] dp = new int[pesos.length + 1][pesoMaximo + 1];

        for (int i = 1; i <= pesos.length; i++) {
            for (int j = 1; j <= pesoMaximo; j++) {
                dp[i][j] = dp[i - 1][j];

                if (pesos[i - 1] <= j) {
                    int incluir = dp[i - 1][j - pesos[i - 1]] + pesos[i - 1];
                    dp[i][j] = Math.max(dp[i][j], incluir);
                }
            }
        }

        return dp[pesos.length][pesoMaximo];
    }
}
