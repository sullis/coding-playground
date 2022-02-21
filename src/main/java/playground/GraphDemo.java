package playground;

import java.util.*;

public class GraphDemo {

    private final static char[][] input = {
            { 'A', 'B' },
            { 'B', 'C' },
            { 'C', 'D' },
            { 'B', 'G'},
            { 'G', 'H'},
            { 'Z', 'B'}
    };

    public static void main(String[] args) {
        final var graph = new HashMap<Character, SortedSet<Character>>();
        System.out.println(graph);
    }
}
