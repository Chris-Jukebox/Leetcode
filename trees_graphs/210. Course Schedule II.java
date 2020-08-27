class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        /* 
        拓扑排序 计算结点入度
        */
        
        // note: 没有prerequisites的情况也合理
        
        boolean isPossible = true;
        Map<Integer, List<Integer>> dict = new HashMap<>();
        int[] indegree = new int[numCourses];
        int[] topologicalOrder = new int[numCourses];
        
        // create adjacent map 
        for(int i = 0; i < prerequisites.length; i++){
            int[] prerequisite = prerequisites[i];
            List<Integer> l = dict.getOrDefault(prerequisite[1], new ArrayList<Integer>());
            l.add(prerequisite[0]);
            dict.put(prerequisite[1], l);
            
            // adjust the indegree for the node
            indegree[prerequisite[0]]++;
        }
        
        // Add all vertices with 0 in-degree to the queue
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++){
            if(indegree[i] == 0)
                queue.add(i);
        }
        
        // Process until the Q becomes empty
        int i = 0;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            topologicalOrder[i++] = node;
            
            // Reduce the in-degree of each neighbor by 1
            if(dict.containsKey(node)) {
                for(Integer neighbor: dict.get(node)){
                    indegree[neighbor]--;
                
                // If in-degree of a neighbor becomes 0, add it to the Q
                if(indegree[neighbor] == 0)
                    queue.add(neighbor); 
                }
            }
        }
        
        if(i == numCourses)
            return topologicalOrder;        
        
        return new int[0];
    }
}
// Time Complexity: O(V+E) where V represents the number of vertices and E represents the number of edges.
/* Space Complexity: O(V + E). We use an intermediate queue data structure to keep all the nodes with 0 in-degree. 
In the worst case, there won't be any prerequisite relationship and the queue will contain all the vertices initially since all of them will have 0 in-degree. 
That gives us O(V). Additionally, we also use the adjacency list to represent our graph initially. 
The space occupied is defined by the number of edges because for each node as the key, we have all its adjacent nodes in the form of a list as the value. 
Hence, O(E). So, the overall space complexity is O(V + E).
*/
