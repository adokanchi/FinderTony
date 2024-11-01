import java.util.ArrayList;

public class CollisionSet {
    private ArrayList<Node> nodes;

    public CollisionSet() {
        nodes = new ArrayList<Node>();
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public String linearSearch(String key) {
        for (Node node : nodes) {
            if (node.getKey().equals(key)) {
                return node.getVal();
            }
        }
        return Finder.INVALID;
    }
}
