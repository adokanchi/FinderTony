public class HashMap {
    private Pair[] map;
    private final static int DEFAULT_SIZE = 192;
    private int tableSize;
    private int n;
    private final static double LOAD_FACTOR = 0.5;

    public HashMap() {
        map = new Pair[DEFAULT_SIZE];
        tableSize = DEFAULT_SIZE;
        n = 0;
    }

    public void add(String key, String val) {
        if (++n > tableSize * LOAD_FACTOR) {
            resize();
        }
        addToMap(key, val);
    }

    public String get(String key) {
        int index = polyHash(key);
        while (map[index] != null) {
            if (map[index].getKey().equals(key)) {
                return map[index].getVal();
            }
            index = ++index % tableSize;
        }
        return Finder.INVALID;
    }

    // Helper method for add() and resize()
    private void addToMap(String key, String val) {
        int index = polyHash(key);
        while (map[index] != null) {
            index = ++index % tableSize;
        }
        map[index] = new Pair(key, val);
    }

    private void resize() {
        Pair[] lastMap = map;
        tableSize *= 2;
        map = new Pair[tableSize];
        for (Pair pair : lastMap) {
            if (pair != null) addToMap(pair.getKey(), pair.getVal());
        }
    }

    private int polyHash(String key) {
        final int R = 853;
        long hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * R + key.charAt(i)) % tableSize;
        }
        return (int) hash;
    }
}
