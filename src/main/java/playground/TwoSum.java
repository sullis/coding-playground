package playground;

import org.eclipse.collections.api.map.primitive.MutableIntIntMap;
import org.eclipse.collections.impl.map.mutable.primitive.IntIntHashMap;

import static org.assertj.core.api.Assertions.assertThat;


/*

Given an array of integers, return the indices of the two numbers whose sum is equal to a given target.

Input:  [2, 7, 11, 15]
Target:  9

Output: [0, 1]

( 2 + 7 = 9 )

 */
public class TwoSum {
  static class Solution1 {
    public static void main(String[] args) {
      {
        int[] result = process(new int[] {2, 7, 11, 15}, 9);
        assertThat(result).containsExactlyInAnyOrder(0, 1);
      }
      {
        int[] result = process(new int[] { }, 9);
        assertThat(result).isEmpty();
      }
      {
        int[] result = process(new int[] { 9 }, 9);
        assertThat(result).isEmpty();
      }
      {
        int[] result = process(new int[] { 7, 2 }, 9);
        assertThat(result).containsExactlyInAnyOrder(0, 1);
      }
      {
        int[] result = process(new int[] { 7, 7, 2 }, 9);
        assertThat(result).containsExactlyInAnyOrder(1, 2);
      }
      {
        int[] result = process(new int[] { 7, 7, 2 }, Integer.MAX_VALUE);
        assertThat(result).isEmpty();
      }
    }

    // assumption:
    //   input array may contain zero, positive values, or negative values
    private static int[] process(final int[] input, final int target) {
      MutableIntIntMap map = new IntIntHashMap(input.length);
      for (int i = 0; i < input.length; i++) {
        int diff = target - input[i];
        if (map.containsKey(diff)) {
          return new int[] { i, map.get(diff) };
        }
        map.put(input[i], i);
      }
      return new int[] { };
    }
  }
}
