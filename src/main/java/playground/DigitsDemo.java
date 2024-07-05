package playground;

import java.util.ArrayList;
import java.util.List;


public class DigitsDemo {
    public static void main(String[] args) {
        List<Integer> result = digits(123);
        System.out.println("result: " + result);
    }

    private static List<Integer> digits(int number) {
        // assumption:  number is positive

        List<Integer> result  = new ArrayList<>();

        String numberString = "" + number;
        for (int i = 0; i < numberString.length(); i++) {
           int digit = Character.getNumericValue(numberString.charAt(i));
           int multiplier = (int) Math.pow(10, (numberString.length() - 1 - i));
           System.out.println("digit: " + digit);
           System.out.println("multiplier: " + multiplier);
           result.add(digit * multiplier);
        }

        return result;
    }
}
