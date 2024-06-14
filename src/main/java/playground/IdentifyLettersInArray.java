package playground;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**

 Implement a function that accepts an array as input.

 The input array can contain characters and nested arrays.
 Each nested array can contain characters and nested arrays.

 The function returns an array of characters. The characters
 must appear in the same order that they appear in the input array.

 The output array will have no duplicate characters.
 Characters should be treated case insensitively.

 Example:
   input = [ 'm', [ 'a', 'p', 'p', 'l', [ 'e' ] ], 's' ]
   output = [ 'm', 'a', 'p', 'l', 'e', 's' ]

 Example:
    input = [ 'a', 'A' ]
    output = [ 'a' ]

 Example:
    input = [ [ 'x' ], [ 'A', 'a' ], [ 'X' ] ]
    output = [ 'x', 'A' ]

 Example:
    input = [ 'Z' ]
    output = [ 'Z' ]

 */
public class IdentifyLettersInArray {

  public static void main(String[] args) {
    Object[] input = new Object[] {
        'A',
        new Object[] { 'B', 'b', 'B', 'c', new Object[] { 'd' }, 'e'},
        'f'
    };
    char[] result = solve(input);
    System.out.println("result: " + Arrays.toString(result));
  }

  private static char[] solve(Object[] input) {
    return solve(input, new HashSet<Character>());
  }

  private static char[] solve(Object[] input, Set<Character> seen) {
    StringBuilder sb = new StringBuilder();
    for (Object o : input) {
      if (o instanceof Character) {
        Character c = (Character) o;
        Character lower = Character.toLowerCase(c);
        if (!seen.contains(lower)) {
          sb.append(c);
          seen.add(lower);
        }
      } else if (o instanceof Object[]) {
        char[] chars = solve((Object[]) o, seen);
        sb.append(chars);
      }
    }
    return sb.toString().toCharArray();
  }
}
