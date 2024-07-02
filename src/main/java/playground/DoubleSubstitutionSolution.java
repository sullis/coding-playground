package playground;

import java.io.*;
import java.net.*;
import java.util.*;

import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoubleSubstitutionSolution {

    private static DoubleSubstitution ds = null;

    @Test
    public void doubleSubstitutionTest() {
        Map<String, String> key = new HashMap<>();
        key.put("a", "ba");
        key.put("b", "ab");
        key.put("t", "ab");
        key.put("m", "ca");
        key.put("n", "cc");

        assertEquals("abbaab", ds.encrypt(key, "bat"));
        assertEquals("", ds.encrypt(key, "ZZZ"));

        assertEquals("", ds.encrypt(key, ""));
        assertEquals("", ds.encrypt(new HashMap<String, String>(), "a"));

        assertEquals(
            new HashSet<String>(Arrays.asList(new String[] {"man"})), ds.decrypt(key, "cabacc"));

        assertEquals(
            new HashSet<String>(Arrays.asList(new String[] {"bat", "tab", "tat"})),
            ds.decrypt(key, "abbaab"));
    }

    /** Class that implements the double-substitution encryption and decryption */
    private static class DoubleSubstitution {

        private Set<String> validWords;

        /** Constructor */
        public DoubleSubstitution(Set<String> validWords) {
            this.validWords = validWords;
        }

        /**
         *  Given the encryption key and the ciphertext,
         *  return every possible plaintext message as a set
         *  of words from the supplied collection of valid words.
         */
        public Set<String> decrypt(Map<String, String> key, String ciphertext) {
            System.out.println("decrypt ciphertext: " + ciphertext);
            System.out.println("decrypt kvMap: " + key);

            if (ciphertext.length() % 2 != 0) {
                throw new IllegalStateException("odd length string");
            }

            Map<String, Set<String>> values = new HashMap<>();
            for (Map.Entry<String, String> entry : key.entrySet()) {
                Set<String> set = values.get(entry.getValue());
                if (set == null) {
                    set = new HashSet<String>();
                }
                set.add(entry.getKey());
                values.put(entry.getValue(), set);
            }

            int i = 0;
            List<String> twoChars = new ArrayList<>();
            while (i < ciphertext.length()) {
                char c = ciphertext.charAt(i);
                char d = ciphertext.charAt(i + 1);
                twoChars.add(c + "" + d);
                i = i + 2;
            }

            // start a set of words we're building
            List<String> wordList = new ArrayList<>();
            wordList.add("");

            System.out.println("twoChars:" + twoChars);

            for (String twoChar : twoChars) {
                Set<String> set = values.get(twoChar);
                System.out.println("twoChar: " + twoChar + " -> " + set);
                if (set == null) {
                    throw new IllegalStateException("unexpected: " + twoChar);
                }
                for (String x : set) {
                    final int wordListSize = wordList.size();
                    for (int j = 0; j < wordListSize; j++) {
                        wordList.add(wordList.get(j) + x);
                    }
                }
            }

            final int expectedLength = ciphertext.length() / 2;
            Set<String> wordSet = new HashSet<String>(wordList);
            wordSet.retainAll(validWords);

            Set<String> result = wordSet.stream()
                .filter(s -> s.length() == expectedLength)
                .collect(Collectors.toSet());
            System.out.println("decrypt result: " + result);
            return result;
        }

        /**
         * Given the encryption key and the plaintext, returns the ciphertext encrypted via
         * double-substitution.
         */
        public String encrypt(Map<String, String> key, String plaintext) {
            if (plaintext == null) {
                return null;
            }

            StringBuilder result = new StringBuilder(plaintext.length() * 2);

            for (int i = 0; i < plaintext.length(); i++) {
                char c = plaintext.charAt(i);
                String value = key.get("" + c);
                if (value == null) {
                    value = "";
                }
                result.append(value);
            }
            return result.toString();
        }
    }

    public static void main(String[] args) {
        Set<String> validWords = new TreeSet<>();
        // Read the word list in from a file
        try {
            URL url = new URL("https://s3.amazonaws.com/burr-data/word_list.txt");
            Scanner s = new Scanner(url.openStream());
            while (s.hasNextLine()) {
                String word = s.nextLine().toLowerCase().trim();
                validWords.add(word);
            }
        } catch (IOException ex) {
            // Connection problem!
            ex.printStackTrace();
        }

        // Create DoubleSubstitution instance
        ds = new DoubleSubstitution(validWords);

        Map<String, String> kvMap = Map.of(
            "a", "ab",
            "b", "cd",
            "t", "ab"
            );

        final String plaintext = "bat";
        final String ciphertext = ds.encrypt(kvMap, plaintext);
        assertEquals("cdabab", ciphertext);

        Set<String> decryptResult = ds.decrypt(kvMap, ciphertext);
        assertTrue(decryptResult.contains("bat"));
        decryptResult.forEach(s -> assertTrue(validWords.contains(s)));
    }
}


