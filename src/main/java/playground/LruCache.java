package playground;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;

public class LruCache implements Cache<String, String> {
    private final int capacity;
    private final Map<String, Integer> map;

    public LruCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();
    }

    @Override
    public boolean put(String key, String value) {
        if (isFull()) {
            // todo
        }
        map.put(key, 0);
        return true;
    }

    @Override
    public Optional<String> get(String key) {
        Integer position = map.get(key);
        if (position == null) {
            return Optional.empty();
        } else {
            return Optional.ofNullable(null); // todo
        }
    }

    @Override
    public int size() {
        return 0; // todo
    }

    @Override
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    private boolean isFull() {
        return (this.map.size() == this.capacity);
    }

    public void clear() {
      // todo
    }
}
