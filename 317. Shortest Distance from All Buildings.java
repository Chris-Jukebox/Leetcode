//317. Shortest Distance from All Buildings
/*
You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. 
You can only move up, down, left and right. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

1 - 0 - 2 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

*/
//基本思路 
//1) 遍历地图 对于是楼（“1”）的情况 BFS记录所有0到它的距离 distance[m][n] 
//2) pruning 每个楼进行计算的时候 把能到的楼-1，比如本轮要找所有0的地方 下一轮我们就要开始找所有标记为-1的地方 visited[][] 
//3) 对于visited[][] == -楼数的地方 我们遍历算distance

public class Solution {
    public int shortestDistance(int[][] grid) {
    	int m = grid.length;
    	int n = grid[0].length;
    	// total distance record
    	int[][] total = grid;
    	// visitedTimesCount
    	int count = 0, res;
    	int[] delta = {0, 1, 0, -1, 0};
    	for(int i = 0; i < m; ++i) {
    		for(int j = 0; j < n; ++j)
    		{
    			if(grid[i][j]) == 1) {
					res = -1;
					// 对每个楼 计算所有0到它的距离
					int[][] dist = grid;
					Queue<Tuple> queue = new LinkedList<>();
					queue.offer(Tuple(i, j));
					while(q.size() > 0)
					{
						Tuple temp = queue.poll();
						// 上下左右 
						for(int d = 0; d < 4; d++)
						{
							int i = temp.x + delta[d];
							int j = temp.y + delta[d + 1];
							// void needs to be accessible by all previous buildings
							if(i >= 0 && i < m && j >=0 && j < n && grid[i][j] == count)
							{
								grid[i][j]--;
								dist[i][j] = dist[temp.x][temp.y] + 1;
								// 起始的时候用的是grid里面的1
								// 所以每次算距离多加了个1
								total[i][j] += dist[i][j] - 1;
								// 放回队列
								q.offer(new Tuple(i, j));
								if(res < 0 || res > total[i][j])
									res = total[i][j]; 
							}
						}
					}
					//对于本楼的所有通路检查已结束
					count--;
    			}
    		}
    	}
    	return res;
    }    
}

class Tuple {
	public int x;
	public int y;

	public Tuple(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}

