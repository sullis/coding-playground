package playground;

import java.util.Arrays;


// LeetCode Longest Common Prefix
public class LongestCommonPrefix {

  public static String longestCommonPrefix(String[] input) {
    System.out.println("input: " + Arrays.toString(input));

    if (input.length == 0) {
      return "";
    }

    String prefix = input[0];
    for (int i = 1; i < input.length; i++) {
      String s = input[i];
      while (s.indexOf(prefix) != 0) {
        prefix = prefix.substring(0, prefix.length() - 1);
      }
    }

    System.out.println("result: \"" + prefix + "\"");

    return prefix;
  }

  public static void main(String[] args) {

    longestCommonPrefix(new String[] { "aaa" } );

    longestCommonPrefix(new String[] { "aaa", "bbb" } );

    longestCommonPrefix(new String[] { "flower", "flow", "flight" } );

  }
}
