
package playground;

import java.util.*;
import com.google.common.collect.Comparators;
import java.util.stream.Collectors;

/*
 * Given a string of words, find the K most frequent words

  Words separated by:
   - Whitespace
   - Periods
   - Commas

  Period or comma will be followed by a space

  Edge cases:
      Upper case, Mixed case, Lower case words
      All whitespace
      All punctuation
      empty string

 */

public class Top_K_Words {
  public static void main(String[] args) {

    List<String> topK = process("Hello\n hello Hello aaa. bbb BBB, ", 2);
    System.out.println(topK);
  }

  private static List<String> process(String input, int k) {
    Map<String, Integer> map = new HashMap<>();
    StringTokenizer tokenizer = new StringTokenizer(input, " \t\n,.");

    while (tokenizer.hasMoreTokens()) {
      String token = tokenizer.nextToken().toLowerCase();
      Integer count = map.get(token);
      if (count == null) {
        count = 1;
      } else {
        count++;
      }
      map.put(token, count);
    }

    System.out.println(map);

    return map.entrySet().stream()
        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
        .sorted(Map.Entry.comparingByValue())
        .limit(k)
        .map(e -> e.getKey())
        .collect(Collectors.toList());

  }
}