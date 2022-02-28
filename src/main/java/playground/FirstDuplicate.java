package playground;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *   Find First Duplicate in array
 *
 *   https://www.youtube.com/watch?v=XSdr_O-XVRQ
 *
 */
public class FirstDuplicate {
    private static int[] INPUT = { 1, 5, 3, 4, 3, 2, 2, 3, 1, 4 };

    static class Solution1 {
        public static int firstDuplicate(int[] input) {
            int minimumIndexOfSecondDuplicate = Integer.MAX_VALUE;
            for (int i = 0; i < input.length; i++) {
                for (int j = i + 1; j < input.length; j++) {
                    if (input[i] == input[j]) {
                        minimumIndexOfSecondDuplicate = Math.min(minimumIndexOfSecondDuplicate, j);
                    }
                }
            }
            if (Integer.MAX_VALUE == minimumIndexOfSecondDuplicate) {
                return -1;
            }
            return input[minimumIndexOfSecondDuplicate];
        }
    }

    static class Solution2 {
        public static int firstDuplicate(int[] input) {
            Set<Integer> seen = new HashSet<>(input.length);
            for (int i = 0; i < input.length; i++) {
                int value = input[i];
                if (seen.contains(value)) {
                    return value;
                } else {
                    seen.add(value);
                }
            }
            return -1;
        }
    }

    static class Solution3 {
        public static int firstDuplicate(int[] input) {
            for (int i = 0; i < input.length; i++) {
                int value = Math.abs(input[i]);
                int index = value - 1;
                if (input[index] < 0) {
                    // we found a duplicate
                    return value;
                } else {
                    input[index] = input[index] * -1;
                }
            }
            return -1;
        }
    }

    public static void main(String[] args) {
        int[][] inputs = {
                { 1 },
                { 1, 2, 3 },
                { 1, 2, 2 },
                { 1, 2, 1 },
                { 1, 2, 4, 4, 5, 4 },
                { 1, 2, 4, 3, 5, 5, 3 }
        };

        for (int[] input: inputs) {
            System.out.println("INPUT: " + Arrays.toString(input));
            System.out.println("solution 1: " + Solution1.firstDuplicate(input));
            System.out.println("solution 2: " + Solution2.firstDuplicate(input));
            System.out.println("solution 3: " + Solution3.firstDuplicate(input));
        }
    }
}
