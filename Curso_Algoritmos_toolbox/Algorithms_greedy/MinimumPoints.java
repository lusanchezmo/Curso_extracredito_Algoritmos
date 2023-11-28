import java.util.Arrays;
import java.util.ArrayList;
import java.util.Comparator;

public class MinimumPoints {

    static class Points {
        int numPoints;
        ArrayList<Integer> points;

        Points(int numPoints, ArrayList<Integer> points) {
            this.numPoints = numPoints;
            this.points = points;
        }
    }

    static Points getPoints(int[][] lines) {
        int numLines = lines.length;

        if (numLines == 1) {
            int singlePoint = lines[0][1];
            ArrayList<Integer> singlePointList = new ArrayList<>();
            singlePointList.add(singlePoint);
            return new Points(1, singlePointList);
        }

        Arrays.sort(lines, Comparator.comparingInt(a -> a[0]));

        int farthest = lines[0][1];
        ArrayList<Integer> resultPoints = new ArrayList<>();
        resultPoints.add(farthest);
        int numPoints = 1;

        for (int i = 1; i < numLines; i++) {
            int currentEnd = lines[i][1];

            if (currentEnd < farthest) {
                farthest = currentEnd;
            } else {
                resultPoints.add(farthest);
                numPoints++;
                farthest = currentEnd;
            }
        }

        resultPoints.add(farthest);
        numPoints++;

        return new Points(numPoints, resultPoints);
    }

    public static void main(String[] args) {
        int[][] lines = {{1, 3}, {2, 5}, {5, 6}, {7, 9}};

        Points result = getPoints(lines);

        System.out.println("Número mínimo de puntos: " + result.numPoints);
        System.out.println("Lista de puntos: " + result.points);
    }
}
