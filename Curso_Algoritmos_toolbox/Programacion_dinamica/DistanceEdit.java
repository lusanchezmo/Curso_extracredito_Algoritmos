import java.util.Stack;

public class DistanceEdit {

    public static void main(String[] args) {
        String a = "editing";
        String b = "distance";

        int[][] dp = editDistance(a, b);
        printEditDistance(dp, a, b);
    }

    public static int[][] editDistance(String a, String b) {
        int m = a.length();
        int n = b.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1;

                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        return dp;
    }

    public static void printEditDistance(int[][] dp, String a, String b) {
        int i = dp.length - 1;
        int j = dp[0].length - 1;

        Stack<Character> wordA = new Stack<>();
        Stack<Character> wordB = new Stack<>();

       
        while (i > 0 || j > 0) {
            int currentCost = dp[i][j];
            int deletionCost = (i > 0) ? dp[i - 1][j] : Integer.MAX_VALUE;
            int insertionCost = (j > 0) ? dp[i][j - 1] : Integer.MAX_VALUE;
            int substitutionCost = (i > 0 && j > 0) ? dp[i - 1][j - 1] : Integer.MAX_VALUE;

            if (currentCost == deletionCost + 1) {
                wordA.push(a.charAt(i - 1));
                wordB.push('-');
                i--;
            } else if (currentCost == insertionCost + 1) {
                wordA.push('-');
                wordB.push(b.charAt(j - 1));
                j--;
            } else if (currentCost == substitutionCost + ((a.charAt(i - 1) == b.charAt(j - 1)) ? 0 : 1)) {
                wordA.push(a.charAt(i - 1));
                wordB.push(b.charAt(j - 1));
                i--;
                j--;
            }
        }

        System.out.println("Distancia de edición: " + dp[dp.length - 1][dp[0].length - 1]);
        System.out.println("Operaciones:");
        System.out.println("Cadena A: " + getStringRepresentation(wordA));
        System.out.println("Cadena B: " + getStringRepresentation(wordB));
    }

    public static String getStringRepresentation(Stack<Character> stack) {
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }
}
