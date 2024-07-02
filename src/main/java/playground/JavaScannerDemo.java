package playground;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


// demo:  java.util.Scanner
public class JavaScannerDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner("123 abc 456");
        assertEquals(123, scanner.nextInt());
        assertEquals("abc", scanner.next());
        assertEquals(456, scanner.nextInt());
    }
}
