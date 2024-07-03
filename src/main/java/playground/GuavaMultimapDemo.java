package playground;

import com.google.common.collect.MultimapBuilder;
import com.google.common.collect.SetMultimap;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GuavaMultimapDemo {
    public static void main(String[] args) {
        SetMultimap<String, Integer> multimap = MultimapBuilder.hashKeys().hashSetValues().build();
        multimap.put("a", 1);
        multimap.put("a", 2);
        multimap.put("a", 3);
        assertEquals(Set.of(1, 2, 3), multimap.get("a"));
        assertThat(multimap.get("b")).isEmpty();
        System.out.println(multimap);
    }
}
