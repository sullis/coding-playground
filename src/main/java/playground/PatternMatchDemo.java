package playground;

/*

Write a function with the following signature:

     boolean isMatch(String pattern, String word)

This function returns true if (and only if)
word is a complete match for pattern.

If pattern contains only letters, this function should just
do a string comparison:

  isMatch("guitar", "guitar") => true
  isMatch("guitar", "guitars") => false

If pattern contains a number, we'll treat it as a wildcard. In the
number's position, a matching word can contain any letters, as
long as there are as many letters as the number.

isMatch("3", "aaa") => true
isMatch("3", "aa") => false
isMatch("g3ar", "guitar") => true
isMatch("g3ar", "gtar") => false

The expected functionality can be summarized in a table:

pattern  |   word   | output
===========================
guitar   | guitar   | true
guitar   | guitars  | false
3        | aaa      | true
3        | aa       | false
g3ar     | guitar   | true
g3ar     | gar      | false
g0tar    | gtar     | true
g0tar    | guitar   | false

""  -> ""  => true

Assumptions:

Zero is a valid digit.

Pattern contains digits or letters.

Notes:

  Index position for the pattern
  Index position for the word

Increment index and checking the if
characters in the word are matching.

 */

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PatternMatchDemo {
  public static void main(String[] args) {
    assertTrue(isMatch("guitar", "guitar"));
    assertFalse(isMatch("guitar", "guitars"));
    assertTrue(isMatch("3", "aaa"));
    assertFalse(isMatch("3", "aa"));
    assertTrue(isMatch("g3ar", "guitar"));
    assertFalse(isMatch("g3", "gar"));
    assertTrue(isMatch("g3", "gtar"));
    assertTrue(isMatch("g0tar", "gtar"));
    assertFalse(isMatch("g0tar", "guitar"));
    assertFalse(isMatch("", "guitar"));

    // These test cases are for the empty w rd string
    assertTrue(isMatch("", ""));
    assertFalse(isMatch("a", ""));
    assertTrue(isMatch("0", ""));
    assertFalse(isMatch("3", ""));
    assertFalse(isMatch("aa", ""));
    assertFalse(isMatch("0a", ""));
    assertFalse(isMatch("3a", ""));
    assertFalse(isMatch("34", ""));
  }

  private static boolean isMatch(String pattern, String word) {
    int i = 0;
    int wordIndex = 0;

    while (i < pattern.length() && wordIndex < word.length()) {
      char c = pattern.charAt(i);
      if (Character.isLetter(c)) {
        if (word.length() == 0) {
          return false;
        }
        if (c != word.charAt(wordIndex)) {
          return false;
        }
        wordIndex++;
      }
      else if (Character.isDigit(c)) {
        int num = Character.getNumericValue(c);
        wordIndex += num;
      }
      i++;
    }

    // special handling for an empty word string
    if (word.length() == 0) {
      if (pattern.length() == 0) {
        return true;
      }
      char c = pattern.charAt(0);
      if (Character.isLetter(c)) {
        return false;
      }
      int num = Character.getNumericValue(c);
      return (num == 0) && (pattern.length() == 1);
    }

    return (wordIndex == word.length());
  }
}



