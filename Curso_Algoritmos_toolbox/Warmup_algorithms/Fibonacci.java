import java.util.Scanner;

public class Fibonacci {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese un número entero positivo:");
        int userInput = scanner.nextInt();

        System.out.println(calculateCustomFibonacci(userInput));

        System.out.println(calculateLastDigitCustomFibonacci(userInput));

        long customW = 100_000;
        int customM = 10_000;
        System.out.println(calculateFibonacciModuloM(customW, customM));

        int customS = 100;
        System.out.println(calculateCustomFibonacciSum(customS));

        int customK = 10;
        int customL = 10;
        System.out.println(calculatePartialSumCustomFibonacci(customK, customL));

        int customF = 1234567890;
        System.out.println(calculatePartialSquareSumCustomFibonacci(customF));

        scanner.close();
    }

    public static long calculateCustomFibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        long current = 1;
        long previous = 0;
        long temp;

        for (int i = 1; i < n; i++) {
            temp = current;
            current += previous;
            previous = temp;
        }

        return current;
    }

    public static int calculateLastDigitCustomFibonacci(int n) {
        int current = 1;
        int previous = 0;
        int temp;

        for (int i = 1; i < n; i++) {
            temp = current % 10;
            current = (current + previous) % 10;
            previous = temp;
        }

        return current;
    }

    public static int calculateFibonacciModuloM(long n, int m) {
        int current = 1;
        int previous = 0;
        int temp;

        for (long i = 1; i < n; i++) {
            temp = current;
            current = (current + previous) % m;
            previous = temp;
        }

        return current;
    }

    public static int calculateCustomFibonacciSum(int n) {
        return calculatePartialSumCustomFibonacci(0, n);
    }

    public static int calculatePartialSumCustomFibonacci(int m, int n) {
        int[] residues = new int[getCustomPeriod()];
        residues[0] = 0;
        residues[1] = 1;
        for (int i = 2; i < residues.length; i++) {
            residues[i] = residues[i - 2] + residues[i - 1];
        }

        int sum = 0;
        for (int i = m; i <= n; i++) {
            sum = (sum + residues[i % residues.length]) % 10;
        }

        return sum;
    }

    public static int calculatePartialSquareSumCustomFibonacci(int n) {
        int[] residues = new int[getCustomPeriod()];
        residues[0] = 0;
        residues[1] = 1;
        int[] powers = new int[residues.length];
        powers[0] = 0;
        powers[1] = 1;
        for (int i = 2; i < residues.length; i++) {
            residues[i] = (residues[i - 2] + residues[i - 1]) % 10;
            powers[i] = (int) Math.pow(residues[i], 2) % 10;
        }

        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum = (sum + powers[i % powers.length]) % 10;
        }

        return sum;
    }

    private static int getCustomPeriod() {
        int mod = 10;
        int periods = 1;
        int current = 1;
        int previous = 0;
        int temp;

        while (true) {
            temp = current;
            current = (current + previous) % mod;
            previous = temp;
            if (current == 1 && previous == 0) {
                break;
            }
            periods += 1;
        }

        return periods;
    }
}
