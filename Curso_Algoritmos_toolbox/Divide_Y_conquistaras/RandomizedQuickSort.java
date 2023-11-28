import java.util.Arrays;
import java.util.Random;

public class RandomizedQuickSort {

    public static void main(String[] args) {
        int[] nums = {2, 3, 9, 1, 2};
        randomizedQuickSort(nums, 0, nums.length - 1);
        System.out.println("Arreglo ordenado: " + Arrays.toString(nums));
    }

    public static void randomizedQuickSort(int[] nums, int left, int right) {
        if (left < right) {
            int[] pivotIndices = randomPartition(nums, left, right);
            int m1 = pivotIndices[0];
            int m2 = pivotIndices[1];

            randomizedQuickSort(nums, left, m1 - 1);
            randomizedQuickSort(nums, m2 + 1, right);
        }
    }

    public static int[] randomPartition(int[] nums, int left, int right) {
        // Seleccionar un índice aleatorio entre left y right (no incluido)
        Random rand = new Random();
        int randNum = rand.nextInt(right - left) + left;

        // Intercambiar el elemento en randNum con el primer elemento (pivote)
        swap(nums, left, randNum);

        int pivot = nums[left];
        int m1 = left;
        int m2 = left;

        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < pivot) {
                m1++;
                m2++;
                swap(nums, i, m2);
                swap(nums, m1, m2);
            } else if (nums[i] == pivot) {
                m2++;
                swap(nums, i, m2);
            }
        }

        // Mover el pivote a la posición final correcta
        swap(nums, left, m1);

        // Devolver el rango donde los elementos son iguales al pivote
        return new int[]{m1, m2};
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
/*  O(n log n)*/ 