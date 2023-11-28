public class Subsequence {

    public static void main(String[] args) {
        int[] sequence1 = {1, 2, 3, 1, 1};
        int[] sequence2 = {2, 4, 1, 1, 1, 3};

        int[][] subseqMatrix = longestCommonSubsequence(sequence1, sequence2);
        printLongestCommonSubsequence(subseqMatrix, sequence1, sequence2);
    }

    public static int[][] longestCommonSubsequence(int[] sequence1, int[] sequence2) {
        int lenSeq1 = sequence1.length;
        int lenSeq2 = sequence2.length;

        int[][] dp = new int[lenSeq1 + 1][lenSeq2 + 1];

        for (int i = 1; i <= lenSeq1; i++) {
            for (int j = 1; j <= lenSeq2; j++) {
                if (sequence1[i - 1] == sequence2[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        return dp;
    }

    public static void printLongestCommonSubsequence(int[][] dp, int[] sequence1, int[] sequence2) {
        int lenSeq1 = sequence1.length;
        int lenSeq2 = sequence2.length;

        int lcsLength = dp[lenSeq1][lenSeq2];
        System.out.println("Longitud de la Subsecuencia Común Más Larga (LCS): " + lcsLength);

        Stack<Integer> commonSubsequence = new Stack<>();
        int i = lenSeq1, j = lenSeq2;

        while (i > 0 && j > 0) {
            if (sequence1[i - 1] == sequence2[j - 1]) {
                commonSubsequence.push(sequence1[i - 1]);
                i--;
                j--;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }

        System.out.print("LCS: ");
        while (!commonSubsequence.isEmpty()) {
            System.out.print(commonSubsequence.pop() + " ");
        }
    }
}
