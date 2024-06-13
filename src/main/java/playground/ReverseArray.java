package playground;

import java.util.Arrays;


public class ReverseArray {

  public static void main(String[] args) {
   System.out.println(Arrays.toString(reverseArray( new int[] { 1, 2, 3 })));
   System.out.println(Arrays.toString(reverseArray( new int[] { 1, 2 })));
   System.out.println(Arrays.toString(reverseArray( new int[] { 1 })));
  }

  private static int[] reverseArray(int[] input) {
    int j = input.length - 1;
    for (int i = 0; i < input.length / 2; i++) {
      int temp = input[i];
      input[i] = input[j];
      input[j] = temp;
      j--;
    }
    return input;
  }
}
