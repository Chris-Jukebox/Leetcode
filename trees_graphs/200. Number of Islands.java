ass Solution {
    // Soltion 1: DFS
    /*
    Linear scan the 2d grid map, if a node contains a '1', then it is a root node that triggers a Depth First Search. During DFS, 
    every visited node should be set as '0' to mark as visited node. Count the number of root nodes that trigger DFS, 
    this number would be the number of islands since each DFS starting at some root identifies an island.
    */
    int count = 0;
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length < 1)
            return 0;
        
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        
        for(int x = 0; x < m; x++){
            for(int y = 0; y < n; y++){
                if(grid[x][y] == '1'){
                    res++;
                    dfs(grid, x, y);
                }
            }
        }
        
        return res;
    }
    
    public void dfs(char[][] grid, int x, int y){
        int m = grid.length;
        int n = grid[0].length;
        
        if(x < 0 || y < 0 || x >= m || y >= n || grid[x][y] == '0')
            return;
        
        grid[x][y] = '0';
        dfs(grid, x - 1, y);
        dfs(grid, x + 1, y);
        dfs(grid, x, y - 1);
        dfs(grid, x, y + 1);
    }

}


/*
Time complexity : O(M×N) where MM is the number of rows and NN is the number of columns.
Space complexity : worst case O(M×N) in case that the grid map is filled with lands where DFS goes by M×N deep.

*/

