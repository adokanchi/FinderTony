import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

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
    private static final int p = 249_998_741;
    private static CollisionSet[] map;

    public Finder() {}

    public void buildTable(BufferedReader br, int keyCol, int valCol) throws IOException {
        // Skip first line which is the labels of the csv file
        br.readLine();

        // Read the csv file
        // Hash using radix p, Store everything with the same hash together
        String[] line;
        int count = 0;
        do {
            line = br.readLine().split(",");
            String key = line[keyCol];
            String val = line[valCol];
            Node toStore = new Node(key, val);
            map[hash(key)].add(toStore);
            count++;
        }
        while (count < 1_300_000);

        br.close();
    }

    public String query(String key){
        // Go to the place in the table where all matching hashes are.
        // (Linear) Search through those hashed values.
        return map[hash(key)].linearSearch(key);
    }

    public int hash(String s) {
        return polyRollingHash(s, 0);
    }

    public int polyRollingHash(String s, int current) {
        final int R = 256;
        if (s.isEmpty()) {
            return current;
        }
        int next = ((current * R) % p + s.charAt(0)) % p;
        return polyRollingHash(s.substring(1), next);
    }
}