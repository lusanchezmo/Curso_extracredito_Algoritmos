public class MaximumValue {
    public static void main(String[] args){
        String expression = "5-8+7*4-8+9";
        int maxValue = calculateMaxValue(expression);
        System.out.println(maxValue);
    }

    private static int calculateMaxValue(String expression){
        int[] values = extractValues(expression);
        char[] operators = extractOperators(expression);

        int[][] minMatrix = new int[values.length][values.length];
        int[][] maxMatrix = new int[values.length][values.length];

        for (int i = 0; i < values.length; i++){
            minMatrix[i][i] = values[i];
            maxMatrix[i][i] = values[i];
        }

        int j;
        int[] minMax;
        for (int s = 1; s < values.length; s ++){
            for (int i = 0; i < values.length - s; i++){
                j = i+s;
                minMax = getMinMax(minMatrix, maxMatrix, operators, i, j);
                minMatrix[i][j] = minMax[0];
                maxMatrix[i][j] = minMax[1];
            }
        }

        return maxMatrix[0][values.length-1];
    }

    private static int[] getMinMax(int[][] minMatrix, int[][] maxMatrix, char[] operators, int i, int j){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        int a, b, c, d;
        for (int k = i; k < j; k ++){
            a = calculateResult(minMatrix[i][k], minMatrix[k+1][j], operators[k]);
            b = calculateResult(minMatrix[i][k], maxMatrix[k+1][j], operators[k]);
            c = calculateResult(maxMatrix[i][k], maxMatrix[k+1][j], operators[k]);
            d = calculateResult(maxMatrix[i][k], minMatrix[k+1][j], operators[k]);

            min = Math.min(min, Math.min(Math.min(a, b), Math.min(c, d)));
            max = Math.max(max, Math.max(Math.max(a, b), Math.max(c, d)));
        }

        return new int[] {min, max};
    }

    private static int[] extractValues(String expression){
        int[] values = new int[(int) Math.ceil(expression.length() / 2.0)];

        int index = 0;
        for (int i = 0; i < expression.length(); i++){
            if (i % 2 == 0) {
                values[index] = Integer.parseInt(String.valueOf(expression.charAt(i)));
                index++;
            }
        }
        return values;
    }

    private static int calculateResult(int a, int b, char operator){
        if (operator == '+'){
            return a + b;
        }
        if (operator == '-'){
            return a - b;
        }
        return a * b;
    }

    private static char[] extractOperators(String expression){
        char[] operators = new char[expression.length() / 2];

        int index = 0;
        for (int i = 0; i < expression.length(); i++){
            if (i % 2 == 1){
                operators[index] = expression.charAt(i);
                index++;
            }
        }
        return operators;
    }
}
