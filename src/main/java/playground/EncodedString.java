package playground;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


/*

given a string of input, output the elongated form

example:  a3b4c => abbbcccc

example: 2ab4c => aabcccc

 */
public class EncodedString {
  static final Map<String, String> INPUT_OUTPUT = Map.of(
      "a3b4c", "abbbcccc",
      "2ab4c", "aabcccc",
      "a", "a",
      "abc", "abc",
      "", ""
  );

  static class Solution1 {
    public static void main(String[] args) throws Exception {
      for (String input : INPUT_OUTPUT.keySet()) {
        String output = process(input);
        System.out.println("input: " + input + " output: " + output);
        assertThat(output).isEqualTo(INPUT_OUTPUT.get(input));
      }
    }

    public static String process(String input) {
      StringBuilder sb = new StringBuilder();
      StringBuilder number = new StringBuilder();
      for (int position = 0; position < input.length(); position++) {
        char c = input.charAt(position);
        if (Character.isDigit(c)) {
          number.append(c);
          continue;
        } else {
          if (number.length() == 0) {
            sb.append(c);
          }
          else {
            int n = Integer.parseInt(number.toString());
            for (int i = 0; i < n; i++) {
              sb.append(c);
            }
            number.setLength(0);
          }
        }
      }
      return sb.toString();
    }
  }
}
