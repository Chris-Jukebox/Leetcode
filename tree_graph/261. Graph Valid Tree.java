class Solution {
    
    /*
    Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), 
    write a function to check whether these edges make up a valid tree.
    */
    
    
    // 树的判断条件
    // 1) 连通性：任意两个结点存在一条边连接
    // 2) 图中不存在环
    
    
    public boolean validTree(int n, int[][] edges) {
        // 极端情况：没有点
        if(n == 0)
            return false;
        
        // (1)树：刚好n-1条边， 边数 == 点数 - 1
        if(edges.length != n - 1)
            return false;
        
        // (2)树：判断连通性，n个点需要连通
        Set<Integer> nodes = new HashSet<>();
        Map<Integer, Set<Integer>> map = buildAdjacentMap(edges, n);
        
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        while(!queue.isEmpty()){
            Integer node = queue.poll();
            if(!visited.contains(node)){
                visited.add(node);
                for(int next: map.get(node)){
                    queue.offer(next);
                }
            }
        }
                
        if(visited.size() == n)
            return true;
      
        return false;
    }
    
    public Map<Integer, Set<Integer>> buildAdjacentMap(int[][] edges, int n){
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for(int i = 0; i < n; i++)
            map.put(i, new HashSet<>());
        
        for(int[] edge: edges){
            int src = edge[0];
            int dest = edge[1];
            Set<Integer> srcNeighbors = map.get(src);
            srcNeighbors.add(dest);
            map.put(src, srcNeighbors);
            Set<Integer> destNeighbors = map.get(dest);
            destNeighbors.add(src);
            map.put(dest, destNeighbors);
        }
        return map;
    }
    
    
}

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)
