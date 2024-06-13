package playground;

import java.util.List;
import org.eclipse.collections.impl.map.mutable.primitive.CharIntHashMap;

import static org.assertj.core.api.Assertions.assertThat;

/*

Calculate length of the longest substring
without repeating characters

In other words:
  given a string s, find the length of the
  longest substring without repeating characters

Example:

  foobarxx -> len(obarx) = 5

 */
public class LongestSubstring {
  static class Solution1 {
    public static void main(String[] args) {
      String input = "foobarxx";
      int result = process(input);
      System.out.println(result);
      assertThat(result).isEqualTo(5);
    }

    public static int process(String input) {
      int result = 0;
      final int n = input.length();
      CharIntHashMap lastIndex = new CharIntHashMap(input.length());

      int startIndexOfSubstring = 0;
      for (int i = 0; i < n; i++) {
        char c = input.charAt(i);
        if (lastIndex.containsKey(c)) {
          startIndexOfSubstring = Math.max(startIndexOfSubstring, lastIndex.get(c) + 1);
        }
        result = Math.max(result, i - startIndexOfSubstring + 1);
        lastIndex.put(c, i);
      }
      return result;
    }
  }
}
