import java.io.BufferedReader;
import java.io.IOException;

/**
 * Finder
 * A puzzle written by Zach Blick
 * for Adventures in Algorithms
 * At Menlo School in Atherton, CA
 * <p>
 * Completed by: Tony Dokanchi
 **/

public class Finder {
    public static final String INVALID = "INVALID KEY";
    private static final int p = 100_003;
    private static CollisionSet[] map;

    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        map = new CollisionSet[p+1];
        for (int i = 0; i < p+1; i++) {
            map[i] = new CollisionSet();
        }

        String line;
        while ((line = br.readLine()) != null) {
            String[] dataRow = line.split(",");
            String key = dataRow[keyCol];
            String val = dataRow[valCol];
            map[hash(key)].add(new Node(key, val));
        }

        br.close();
    }

    public String query(String key){
        return map[hash(key)].linearSearch(key);
    }

    public int hash(String s) {
        return polyRollingHash(s, 0);
    }

    public int polyRollingHash(String s, int current) {
        final int R = 256;
        if (s.isEmpty()) return current;
        int next = ((current * R) % p + s.charAt(0)) % p;
        return polyRollingHash(s.substring(1), next);
    }
}