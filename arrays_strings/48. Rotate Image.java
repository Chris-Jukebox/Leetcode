class Solution {
    public void rotate(int[][] matrix) {
        if(matrix.length < 1 || matrix[0].length < 1)
            return;
        
        int n = matrix.length;
        // transpose matrix first
        for(int i = 0; i < n; i++)
        {
            for(int j = i; j < n; j++){
                if(i != j){
                    swap(matrix, i, j);
                }
            }
        }
        
        // Then reverse each row
        for(int i = 0; i < n; i++){
            int l = 0; 
            int r = n - 1;
            while(l < r)
            {
                int temp = matrix[i][l];
                matrix[i][l] = matrix[i][r];
                matrix[i][r] = temp;
                l++;
                r--;
            }
        }
    }
    
    void swap(int[][] matrix, int i, int j){
        int temp = matrix[i][j];
        matrix[i][j] = matrix[j][i];
        matrix[j][i] = temp;
    }
}
