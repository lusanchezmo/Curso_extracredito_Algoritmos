public class Calculator {
    public static void main(String[] args){
        int number = 96234;
        int[] divisors = {2, 3};
        int[] operations = calculateOperations(number, divisors);
        displayOperations(operations, divisors);
    }

    private static int[] calculateOperations(int number, int[] divisors){
        int[] dp = new int[number + 1];
        dp[1] = 0;

        for (int i = 2; i < number + 1; i++){
            dp[i] = Integer.MAX_VALUE;
            int minOperations;
            int operations;
            for (int divisor: divisors){
                if (i % divisor == 0){
                    operations = dp[i / divisor] + 1;
                    if (operations < dp[i]) {
                        dp[i] = operations;
                    }
                }
            }
            operations = dp[i - 1] + 1;
            if (operations < dp[i]) {
                dp[i] = operations;
            }
        }

        return dp;
    }

    private static void displayOperations(int[] dp, int[] divisors) {
        Stack<Integer> numbers = new Stack<>();

        int i = dp.length - 1;
        numbers.push(dp.length - 1);
        while (i > 1){
            int minOperations = Integer.MAX_VALUE;
            int operations = Integer.MAX_VALUE;
            int num = 0;
            for (int divisor : divisors) {
                if (i % divisor == 0) {
                    operations = dp[i / divisor];
                }
                if (operations < minOperations) {
                    minOperations = operations;
                    num = i / divisor;
                }
            }
            operations = dp[i - 1];
            if (operations < minOperations) {
                num = i - 1;
            }
            numbers.push(num);
            i = num;
        }

        System.out.println(dp[dp.length - 1]);

        while(!numbers.isEmpty()){
            System.out.print(numbers.pop() + " ");
        }
    }
}
