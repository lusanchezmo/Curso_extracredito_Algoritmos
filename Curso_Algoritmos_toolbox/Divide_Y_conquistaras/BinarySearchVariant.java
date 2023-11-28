public class BinarySearchVariant {

    public static void main(String[] args) {
        int[] nums = {2, 4, 4, 4, 7, 7, 9};
        int[] search = {9, 4, 5, 2, 7};

        for (int target : search) {
            int result = binarySearch(nums, target);

            if (result != -1) {
                System.out.println("Elemento " + target + " encontrado en la posición " + result);
            } else {
                System.out.println("Elemento " + target + " no encontrado en el arreglo");
            }
        }
    }

    public static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return searchFirst(nums, target, mid);
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static int searchFirst(int[] nums, int target, int m) {
        int left = 0;
        int right = m;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        if (left > m || nums[left] != target) {
            return 0;
        }

        return left;
    }
}
