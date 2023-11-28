import java.util.Arrays;

class Number {
    int value;
    String type;
    int position;

    public Number(int value, String type, int position) {
        this.value = value;
        this.type = type;
        this.position = position;
    }
}

public class LineSegmentIntersection {

    public static void main(String[] args) {
        int[][] segments = {{1, 3}, {2, 5}, {3, 8}, {6, 9}};
        int[] points = {4, 7, 2};

        Number[] values = initNumbers(segments, points);
        mergeSort(values);
        int[] intersections = getPoints(values);

        System.out.println("Cantidad de intersecciones para cada punto:");
        for (int i = 0; i < points.length; i++) {
            System.out.println("Punto " + points[i] + ": " + intersections[i]);
        }
    }

    public static Number[] initNumbers(int[][] segments, int[] points) {
        int totalNumbers = segments.length * 2 + points.length;
        Number[] values = new Number[totalNumbers];

        int index = 0;
        for (int i = 0; i < segments.length; i++) {
            values[index++] = new Number(segments[i][0], "begin", i);
            values[index++] = new Number(segments[i][1], "end", i);
        }

        for (int i = 0; i < points.length; i++) {
            values[index++] = new Number(points[i], "point", i);
        }

        return values;
    }

    public static void mergeSort(Number[] arr) {
        if (arr.length <= 1) {
            return;
        }

        int mid = arr.length / 2;
        Number[] left = Arrays.copyOfRange(arr, 0, mid);
        Number[] right = Arrays.copyOfRange(arr, mid, arr.length);

        mergeSort(left);
        mergeSort(right);
        merge(arr, left, right);
    }

    public static void merge(Number[] arr, Number[] left, Number[] right) {
        int i = 0, j = 0, k = 0;

        while (i < left.length && j < right.length) {
            if (left[i].value <= right[j].value || (left[i].value == right[j].value && left[i].type.equals("begin"))) {
                arr[k++] = left[i++];
            } else {
                arr[k++] = right[j++];
            }
        }

        while (i < left.length) {
            arr[k++] = left[i++];
        }

        while (j < right.length) {
            arr[k++] = right[j++];
        }
    }

    public static int[] getPoints(Number[] values) {
        int[] intersections = new int[values.length];

        int count = 0;
        for (Number num : values) {
            if (num.type.equals("begin")) {
                count++;
            } else if (num.type.equals("end")) {
                count--;
            } else {
                intersections[num.position] = count;
            }
        }

        return intersections;
    }
}
