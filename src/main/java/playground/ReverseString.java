package playground;

import static org.assertj.core.api.Assertions.assertThat;


public class ReverseString {
  static class Solution1 {
    public static void main(String[] args) {
      String input = "Hello";
      String result = reverse(input);
      assertThat(result).isEqualTo("olleH");
    }

    private static String reverse(String input) {
      StringBuilder sb = new StringBuilder(input.length());
      for (int i = input.length() - 1; i >= 0; i--)  {
        sb.append(input.charAt(i));
      }
      return sb.toString();
    }
  }
}
