// 308. Range Sum Query 2D - Mutable
/*
Given a 2D matrix matrix, 
find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) 
and lower right corner (row2, col2).
*/

/*
Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
*/

/*
Note :
The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2
*/

public class NumMatrix {
    int[][] next;
    public NumMatrix(int[][] matrix) {
        // array empty
        int m = matrix.length;
        if(m < 1)
            return;
        int n = matrix[0].length;
        if(n < 1)
            return;
        next = matrix;
        for(int i = 0; i < m; i++)
            for(int j = 1; j < n; j++)
                next[i][j] += next[i][j - 1];
    }
    public void update(int row, int col, int val) {
        int delta = val - next[row][col] + (col == 0 ? 0 : next[row][col - 1]);
        for(int i = col; i < next[0].length; i++)
            next[row][i] += delta;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for(int m = row1; m <= row2; m++)
            sum += next[m][col2] - (col1 == 0 ? 0: next[m][col1 - 1]);
        return sum;
    }
}

// Your NumMatrix object will be instantiated and called as such:
// NumMatrix numMatrix = new NumMatrix(matrix);
// numMatrix.sumRegion(0, 1, 2, 3);
// numMatrix.update(1, 1, 10);
// numMatrix.sumRegion(1, 2, 3, 4);