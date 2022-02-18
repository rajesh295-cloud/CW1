package assignmentCode;
import java.util.*;
public class WeekFiveSum{
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        if (n == 1){
            return new int[]{0};
        }
        var root = buildTree(n, edges);
        int[] sums = new int[n];
        prepare(root);
        calc( root, sums, 0, 0);
        return sums;
    }
    // calculate the number of inner nodes (including current node) and the sum of distance between inner nodes and the current node
    void prepare(N cur){
        cur.distanceToInternalNodes = 0;
        cur.elementCount = 1;
        for (var child : cur.childrenMap.values()){
            prepare(child);
            cur.elementCount += child.elementCount ;
            cur.distanceToInternalNodes += child.distanceToInternalNodes + child.elementCount ;
        }
    }

    // calculate and save the distanceses
    void calc(  N cur, int[] sums, int distancesToExternalSum, int externalElementCount){
        sums[cur.val] = distancesToExternalSum + cur.distanceToInternalNodes;
        for (var child : cur.childrenMap.values()){
            var externalCountForChild = externalElementCount + cur.elementCount - child.elementCount;
            var externalDistanceSumForChild =
                    distancesToExternalSum // external distance sum
                            + cur.distanceToInternalNodes  // cur node to all inner element distance
                    - child.distanceToInternalNodes - child.elementCount // exculde the specific child branch
                    + externalCountForChild; // add the distance between cur to child for each external elements
            calc(child, sums, externalDistanceSumForChild, externalCountForChild);
        }
    }
 // build the tree
    N buildTree(int n, int[][] edges){
        N[] nodes = new N[n];
        for (var i=0; i<edges.length; i++){
            int x = edges[i][0];
            int y = edges[i][1];
            if (nodes[x] == null){
                nodes[x] = new N(x);
            }
            if (nodes[y] == null){
                nodes[y] = new N(y);
            }
            nodes[x].childrenMap.put(y, nodes[y]);
            nodes[y].childrenMap.put(x, nodes[x]);
        }
        LinkedList queue = new LinkedList<>();
        var root = nodes[0];
        queue.add(root);
        while (!queue.isEmpty() ){
            var cur = queue.poll();
            if (cur.parent != null) {
                cur.childrenMap.remove(cur.parent.val);
            }
                for (var child : cur.childrenMap.values()){
                    child.parent = cur;
                    queue.add(child);
                }
        }
        return root;
    }
    // Node structure
    class N{
        public N parent;
        public int distanceToInternalNodes = 0;
        public int elementCount = 1; // including this node
        public Map<Integer, N> childrenMap = new HashMap<>();
        public int val;
        public N(int val){
            this.val = val;
        }
        public N(N parent, int val){
            this.parent = parent;
            this.val = val;
        }
    }
}