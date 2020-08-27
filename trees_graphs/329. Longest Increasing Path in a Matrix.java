ass Solution {
    public int longestIncreasingPath(int[][] matrix) {
        // BFS,
        // Note: increasing path means two numbers can not be eqaul
        int res = 0;
        if(matrix == null || matrix.length < 1)
            return res;
        
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dirX = {0, -1, 1, 0};
        int[] dirY = {-1, 0, 0, 1};
        
        // topological sort
        // calculate outDegree first
        int[][] outDegree = new int[m][n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int k = 0; k < 4; k++){
                    int x = i + dirX[k];
                    int y = j + dirY[k];
                    if(isBound(x, y, m, n) && matrix[i][j] < matrix[x][y]){
                        outDegree[i][j]++;
                    }
                }
            }
        }
        
        // locate nodes that have outDegrees == 0
        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(outDegree[i][j] == 0)
                    queue.offer(new Pair(i, j));
            }
        }
        
        // 层级遍历
        while(!queue.isEmpty()){
            int size = queue.size();
            res++;
            
            for(int i = 0; i < size; i++){
                Pair<Integer, Integer> cur = queue.poll();
                int x = cur.getKey();
                int y = cur.getValue();    
                
                for(int k = 0; k < 4; k++){
                    int newX = x + dirX[k];
                    int newY = y + dirY[k];
                    if(isBound(newX, newY, m, n) && matrix[newX][newY] < matrix[x][y]){
                        outDegree[newX][newY]--;
                        if(outDegree[newX][newY] == 0)
                            queue.offer(new Pair(newX, newY));
                    }
                }
            } 
        }
        
        return res;
    }
    
    public boolean isBound(int x, int y, int m, int n){
        if(x < 0 || y < 0 || x >= m || y >= n)
            return false;
        else
            return true;
    }
    
}

/*
Time complexity : O(mn). The the topological sort is O(V+E) =O(mn). Here, V is the total number of vertices and E is the total number of edges. In our problem, O(V) = O(mn), O(E) = O(4V) = O(mn)

Space complexity : O(mn). We need to store the out degrees and each level of leaves.
*/
