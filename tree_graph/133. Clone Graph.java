/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null)
            return null;
        
        Map<Node, Node> map = copyNodes(node);
        copyEdges(map);
        return map.get(node);
    }
    
    public Map<Node, Node> copyNodes(Node node){
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node, new Node(node.val));
        
        while(!queue.isEmpty()){
            Node n = queue.poll();
            for(Node neighbor : n.neighbors){
                if(!map.containsKey(neighbor)){
                    map.put(neighbor, new Node(neighbor.val));
                    queue.offer(neighbor);
                }
            }
        }
        return map;
    }
    
    public void copyEdges(Map<Node, Node> map){
        for(Node oldNode : map.keySet()){
            Node newNode = map.get(oldNode);
            for(Node oldNeighbor : oldNode.neighbors){
                newNode.neighbors.add(map.get(oldNeighbor));
            }
        }
    }
    
}
