package playground;

// Apple coding interview, February 28 2022 (coderpad)

/**
 * Implement a service that computes a moving average over some window.
 * It should receive numeric values and provide a way to take a current moving average.
 *
 * Example for window size = 3
 * Initial state: [] -> Avg: 0
 *   2 -> [2] -> Avg: 2
 *   4 -> [2,4] -> Avg: 3
 *   3 -> [2,4,3] -> Avg: 3
 *   5 -> [4,3,5] -> Avg: 4
 *
 * Avg = (X1 + X2 + ... + Xn) / n, n <= window size
 */
public class MovingAverageCalculator {
    private int[] recordedValues;
    private int numRecordedValues;
    private int currentIndex;
    private int sum;
    private final int windowSize;

    public MovingAverageCalculator(int windowSize) {
        this.windowSize = windowSize;
        this.recordedValues = new int[windowSize];
        this.numRecordedValues = 0;
        this.sum = 0;
    }

    /**
     * Records a new value.
     *
     * @param value positive or negative value
     */
    void record(final int value) {
        if (numRecordedValues >= windowSize) {
            int oldValue = recordedValues[currentIndex];
            sum -= oldValue;
        }

        if (numRecordedValues < windowSize) {
            numRecordedValues++;
        }

        recordedValues[currentIndex] = value;
        sum += value;
        currentIndex++;
        if (currentIndex >= windowSize) {
            currentIndex = 0;
        }
    }

    /**
     * Returns rounded current average.
     */
    int currentAvg() {
        if (numRecordedValues == 0) {
            return 0;
        }
        return sum / numRecordedValues;
    }

    public static void main(String[] args) {
        MovingAverageCalculator calc0 = new MovingAverageCalculator(3);
        calc0.record(2);
        calc0.record(4);
        calc0.record(3);
        System.out.println("Avg: " + calc0.currentAvg());

        MovingAverageCalculator calculator = new MovingAverageCalculator(3);
        calculator.record(2);
        calculator.record(4);
        calculator.record(2);
        calculator.record(4);
        System.out.println("Avg: " + calculator.currentAvg());

        MovingAverageCalculator calc2 = new MovingAverageCalculator(2);
        calc2.record(5);
        calc2.record(0);
        calc2.record(5);
        calc2.record(11);
        System.out.println("Avg: " + calc2.currentAvg());
    }
}
