public class InversionCount {

    public static void main(String[] args) {
        int[] nums = {1, 8, 9, 2, 3, 4};
        int inversionCount = mergeSort(nums, 0, nums.length - 1);

        System.out.println("El número de inversiones es: " + inversionCount);
    }



    public static int mergeSort(int[] arr, int left, int right) {
        int inversionCount = 0;

        if (left < right) {
            int mid = (left + right) / 2;

            inversionCount += mergeSort(arr, left, mid);
            inversionCount += mergeSort(arr, mid + 1, right);
            inversionCount += merge(arr, left, mid, right);
        }

        return inversionCount;
    }

    public static int merge(int[] arr, int left, int mid, int right) {
        int inversionCount = 0;
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        System.arraycopy(arr, left, leftArray, 0, n1);
        System.arraycopy(arr, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k++] = leftArray[i++];
            } else {
                arr[k++] = rightArray[j++];
                inversionCount += n1 - i;
            }
        }

        while (i < n1) {
            arr[k++] = leftArray[i++];
        }

        while (j < n2) {
            arr[k++] = rightArray[j++];
        }

        return inversionCount;
    }
}
