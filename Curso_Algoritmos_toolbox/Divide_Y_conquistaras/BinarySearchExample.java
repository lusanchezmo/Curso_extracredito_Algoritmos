public class BinarySearchExample {

    public static void main(String[] args) {
        int[] nums = {1, 5, 8, 12, 13};
        int[] search = {8, 1, 23, 1, 11};

        for (int target : search) {
            int result = binarySearch(nums, target);

            if (result != -1) {
                System.out.println("Elemento " + target + " encontrado en la posición " + result);
            } else {
                System.out.println("Elemento " + target + " no encontrado en el arreglo");
            }
        }
    }

    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
