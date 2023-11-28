public class CalculadoraMCDyMCM {
    public static void main(String[] args) {
        int num1 = 2000000;
        int num2 = 1000000;
        System.out.println(calcularMCD(num1, num2));
        int num3 = 10000000;
        int num4 = 9256845;
        System.out.println(calcularMCM(num3, num4));
    }
    public static int calcularMCD(int a, int b) {
        if (b == 0) {
            return a;
        }

        return calcularMCD(b, a % b);
    }

    public static long calcularMCM(int a, int b) {
        return ((long) a * b) / calcularMCD(a, b);
    }
}
