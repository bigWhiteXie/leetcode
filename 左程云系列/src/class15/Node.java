package class15;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Node {
    int val;
    int in;
    int out;
    Set<Node> nexts;
    List<Edge> edegs;

    public Node(int val) {
        this.val = val;
        nexts = new HashSet<>();
        edegs = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }
}
