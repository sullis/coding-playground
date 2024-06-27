
package playground;

import java.util.*;

/*

Given a string of characters, return a string where
all occurrences of 3 or more matching characters in a
row have been removed.

This removal is performed in a greedy fashion, moving
from left to right. Phrased differently, matching
characters are removed as soon as they are observed.

This is similar to the game candy crush, where
objects move down a 2-D board and are crushed when
3 or more matching objects are stacked.

Example:  "aaa" -> ""

Example: "abbb" -> "a"

Example: "abbbc" -> "ac"

Example: "BBAAAACCCBBBD" -> "D"

Example:  "abbbcccaadZddd" -> "dZ"

Approach:

  - iterate from char 0 to end of string
  - track: current position index
  - track: count of current character
  - when count is 3 or more, dropping characters

Iteration logic:
   start index 0
   iterate to character
   B = 1
   B = 2
   A = 1
   A = 2
   A = 3
   A = 4
   throw away 4 A's
   C = 1
   C = 2
   C = 3
   throw away 3 C's
   B = 3
   B = 4
   B = 5
   throw away 5 B's
   D = 1
   end of string

Data structures:
   keep track of count's
   Map ?
   Stack ?

Edge cases:
  - ""
  - odd number string length
  - even number string length
  - all variations of input
      - without repeating characters
      - with repeating characters

 */

public class StringCharacterSequence {
    public static void main(String[] args) {
        System.out.println(solve("abbbcccaadZddd"));
    }

    private static String solve(String input) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Integer> countMap = new HashMap<>();

        char[] chars = input.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            if (stack.size() > 0) {
                char topChar = stack.peek();
                if (topChar != currentChar) {
                    int countOfTopChar = countMap.get(topChar);
                    if (countOfTopChar >= 3) {
                        for (int k = 0; k < countOfTopChar; k++) {
                            stack.pop();
                        }
                        countMap.put(topChar, 0);
                    }
                }
            }
            countMap.put(currentChar, countMap.getOrDefault(currentChar, 0) + 1);
            stack.push(currentChar);
        }

        if (stack.size() > 0) {
            char topChar = stack.peek();
            int countOfTopChar = countMap.get(topChar);

            if (countOfTopChar >= 3) {
                for (int k = 0; k < countOfTopChar - 1; k++) {
                    stack.pop();
                }
                countMap.put(topChar, 0);
            }
        }

        StringBuilder result = new StringBuilder();
        result.setLength(stack.size());
        for (int i = stack.size() - 1; i >= 0; i--) {
            result.setCharAt(i, stack.pop());
        }
        return result.toString();

    }
}



