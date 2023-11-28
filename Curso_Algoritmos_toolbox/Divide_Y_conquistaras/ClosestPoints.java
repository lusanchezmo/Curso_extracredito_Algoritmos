import java.util.Arrays;

public class ClosestPoints {

    private static int[][] sortedX;
    private static int[][] sortedY;

    public static void main(String[] args) {
        int[][] pairs = {{4, 4}, {-2, -2}, {-3, -4}, {-1, 3}, {2, 2}, {-4, 4}, {1, 1}, {-1, -1}, {3, -3}, {-4, -4}};
        
        sortedX = Arrays.copyOf(pairs, pairs.length);
        sortedY = Arrays.copyOf(pairs, pairs.length);

        mergeSort(sortedX, true);
        mergeSort(sortedY, false);

        double closestDistance = getClosestPoints(0, sortedX.length - 1);
        System.out.printf("La distancia más cercana entre dos puntos es: %.2f%n", closestDistance);
    }

    public static void mergeSort(int[][] arr, boolean sortByX) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;
        int[][] left = Arrays.copyOfRange(arr, 0, mid);
        int[][] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left, sortByX);
        mergeSort(right, sortByX);

        merge(arr, left, right, sortByX);
    }

    public static void merge(int[][] result, int[][] left, int[][] right, boolean sortByX) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if ((sortByX && left[i][0] <= right[j][0]) || (!sortByX && left[i][1] <= right[j][1])) {
                result[k++] = left[i++];
            } else {
                result[k++] = right[j++];
            }
        }

        while (i < left.length) {
            result[k++] = left[i++];
        }

        while (j < right.length) {
            result[k++] = right[j++];
        }
    }

    public static double getClosestPoints(int left, int right) {
        if (right - left <= 2) {
            return bruteForce(left, right);
        }

        int mid = (left + right) / 2;
        int midPointX = sortedX[mid][0];

        double leftDistance = getClosestPoints(left, mid);
        double rightDistance = getClosestPoints(mid + 1, right);
        double minDistance = Math.min(leftDistance, rightDistance);

        int[] strip = createStrip(midPointX, minDistance);

        return Math.min(minDistance, stripClosest(strip, minDistance));
    }

    public static double bruteForce(int left, int right) {
        double minDistance = Double.MAX_VALUE;

        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                double distance = getDistance(sortedX[i], sortedX[j]);
                minDistance = Math.min(minDistance, distance);
            }
        }

        return minDistance;
    }

    public static int[] createStrip(int midPointX, double minDistance) {
        int[] strip = new int[sortedY.length];
        int j = 0;

        for (int i = 0; i < sortedY.length; i++) {
            if (Math.abs(sortedY[i][0] - midPointX) < minDistance) {
                strip[j++] = i;
            }
        }

        return Arrays.copyOf(strip, j);
    }

    public static double stripClosest(int[] strip, double minDistance) {
        double min = minDistance;

        for (int i = 0; i < strip.length; ++i) {
            for (int j = i + 1; j < strip.length && (sortedY[strip[j]][1] - sortedY[strip[i]][1]) < min; ++j) {
                double distance = getDistance(sortedY[strip[i]], sortedY[strip[j]]);
                min = Math.min(min, distance);
            }
        }

        return min;
    }

    public static double getDistance(int[] p1, int[] p2) {
        int dx = p1[0] - p2[0];
        int dy = p1[1] - p2[1];
        return Math.sqrt(dx * dx + dy * dy);
    }
}
