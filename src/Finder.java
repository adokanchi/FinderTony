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
    //private static final int p = 249_998_741;
    private static final int p = 2_000_003;
    private static CollisionSet[] map;

    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // n = number

        map = new CollisionSet[p+1];
        for (int i = 0; i < p+1; i++) {
            map[i] = new CollisionSet();
        }
        String line;
        while ((line = br.readLine()) != null) {
            String[] dataRow = line.split(",");
            String key = dataRow[keyCol];
            String val = dataRow[valCol];
            Node node = new Node(key, val);
            int keyHash = hash(key);
            map[keyHash].add(node);
        }
        br.close();
    }

    public String query(String key){
        // Go to the place in the table where all matching hashes are.
        // (Linear) Search through those hashed values.
        return map[hash(key)].linearSearch(key);
    }

    public int hash(String s) {
        return polyRollingHash(s, 0) % p;
    }

    public int polyRollingHash(String s, int current) {
        final int R = 256;
        if (s.isEmpty()) {
            return current;
        }
        int next = ((current * R) % p + s.charAt(0));
        return polyRollingHash(s.substring(1), next);
    }
}