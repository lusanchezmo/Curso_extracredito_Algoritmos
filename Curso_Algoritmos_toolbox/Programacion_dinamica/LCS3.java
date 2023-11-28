public class LCS3 {

    public static void main(String[] args){
        int[] sequence1 = {8,3,2,1,7};
        int[] sequence2 = {8,2,1,3,8,10,7};
        int[] sequence3 = {6,8,3,1,4,7};

        int[][][] lcsMatrix = longestCommonSubsequence(sequence1, sequence2, sequence3);
        printLongestCommonSubsequence(lcsMatrix, sequence1, sequence2, sequence3);
    }

    private static int[][][] longestCommonSubsequence(int[] sequence1, int[] sequence2, int[] sequence3){
        int[][][] dp = new int[sequence1.length+1][sequence2.length+1][sequence3.length+1];

        for (int i = 0; i < sequence1.length+1; i++){
            for (int j = 0; j < sequence2.length+1; j++){
                for (int k = 0; k < sequence3.length+1; k++){
                    if (i == 0){
                        dp[0][j][k] = 0;
                    }
                    else if (j == 0){
                        dp[i][0][k] = 0;
                    }
                    else if (k == 0){
                        dp[i][j][0] = 0;
                    }
                    else if (sequence1[i-1] == sequence2[j-1] && sequence2[j-1] == sequence3[k-1]){
                        dp[i][j][k] = dp[i-1][j-1][k-1] + 1;
                    }
                    else{
                        dp[i][j][k] = maximum(dp[i][j][k-1], dp[i][j-1][k], dp[i-1][j][k]);
                    }
                }
            }
        }

        return dp;
    }

    private static void printLongestCommonSubsequence(int[][][] dp, int[] sequence1, int[] sequence2, int[] sequence3){
        int i = sequence1.length;
        int j = sequence2.length;
        int k = sequence3.length;
        Stack<Integer> commonSubsequence = new Stack<>();

        while (i > 0 && j > 0 && k > 0){
            int max = maximum(dp[i-1][j][k], dp[i][j-1][k], dp[i][j][k-1]);

            if (sequence1[i-1] == sequence2[j-1] && sequence2[j-1] == sequence3[k-1]){
                commonSubsequence.push(sequence1[i-1]);
                i--; j--; k--;
            }
            else if (max == dp[i-1][j][k]){
                i--;
            }
            else if (max == dp[i][j-1][k]){
                j--;
            }
            else{
                k--;
            }
        }

        System.out.println("LCS: " + dp[dp.length-1][dp[0].length-1][dp[0][0].length-1]);
        while(!commonSubsequence.isEmpty()){
            System.out.print(commonSubsequence.pop());
        }
        System.out.println();
    }

    private static int maximum(int a, int b, int c){
        return Math.max(a, Math.max(b, c));
    }
}
