import java.util.Arrays;

public class FractionalKnapsack {

    static class Item {
        double weight;
        double value;
        double valuePerUnitWeight;

        Item(double weight, double value, double valuePerUnitWeight) {
            this.weight = weight;
            this.value = value;
            this.valuePerUnitWeight = valuePerUnitWeight;
        }
    }

    static double getOptimalValue(double capacity, double[] values, double[] weights) {
        int itemCount = values.length;


        Item[] items = new Item[itemCount];
        for (int i = 0; i < itemCount; i++) {
            double valuePerUnitWeight = values[i] / weights[i];
            items[i] = new Item(weights[i], values[i], valuePerUnitWeight);
        }

        
        Arrays.sort(items, (a, b) -> Double.compare(b.valuePerUnitWeight, a.valuePerUnitWeight));

        double totalValue = 0;

    
        for (int i = 0; i < itemCount; i++) {
            if (capacity == 0) {
                break;
            }

            double minWeight = Math.min(items[i].weight, capacity);
            totalValue += minWeight * items[i].valuePerUnitWeight;
            capacity -= minWeight;
        }

        
        return totalValue;
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        double capacity = 50; // Capacidad de la mochila
        double[] values = {60, 100, 120}; // Valores de los elementos
        double[] weights = {10, 20, 30}; // Pesos de los elementos

        double result = getOptimalValue(capacity, values, weights);

        System.out.println("El valor total óptimo es: " + result);
    }
}
