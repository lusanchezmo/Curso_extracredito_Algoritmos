public class GasStation {

    public static int getRefills(int distance, int refill, int[] stops) {
        int numRefills = 0;
        int currentRefill = 0;

        if (distance <= refill) {
            return 0;
        }

        if (stops[0] > refill) {
            return -1;
        }

        while (currentRefill < stops.length - 1) {
            int nextRefill = currentRefill;

            while (nextRefill < stops.length - 1 && stops[nextRefill + 1] - stops[currentRefill] <= refill) {
                nextRefill++;
            }

            if (nextRefill == currentRefill) {
                return -1;
            }

            numRefills++;
            currentRefill = nextRefill;
        }

        if (stops[currentRefill] + refill < distance) {
            numRefills++;
        }

        return numRefills;
    }

    public static void main(String[] args) {
        int distance = 500;
        int refill = 200;
        int[] stops = {100, 200, 300, 400};

        int result = getRefills(distance, refill, stops);

        if (result == -1) {
            System.out.println("No es posible realizar el viaje sin quedarse sin combustible.");
        } else {
            System.out.println("Número mínimo de paradas: " + result);
        }
    }
}
