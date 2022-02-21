package playground;

import java.util.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.ImmutableBiMap;
import java.util.stream.Collectors;

public class GraphDemo {

    private final static char[][] input = {
            { 'A', 'B' },
            { 'B', 'C' },
            { 'B', 'A' },
            { 'C', 'D' },
            { 'B', 'G'},
            { 'G', 'H'},
            { 'Z', 'B'}
    };

    public static void main(String[] args) {
        System.out.println("INPUT: " + Arrays.deepToString(input));
        final var graph = new HashMap<Character, SortedSet<Character>>();
        for (int i = 0; i < input.length; i++) {
            final Character key = input[i][0];
            final var set = graph.getOrDefault(key, new TreeSet<>());
            set.add(input[i][1]);
            graph.put(key, set);
        }
        System.out.println(graph);
    }
}
