ass Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(candidates == null)
            return res;
        
        Arrays.sort(candidates);
        dfs(0, target, candidates, new ArrayList<Integer>(), res);
        return res;
    }
    
    public void dfs(int startIndex, int target, int[] candidates, List<Integer> curList, List<List<Integer>> res){
        if(target == 0)
        {
            res.add(new ArrayList<Integer>(curList));
            return;
        }
        
        for(int i = startIndex; i < candidates.length; i++){
            // skip duplicate numbers after the first identical one
            if(i != 0 && candidates[i] == candidates[i - 1])
                continue;
            
            int cur = candidates[i];
            if(candidates[i] > target)
                break;
            
            curList.add(cur);
            dfs(i, target - cur, candidates, curList, res);
            curList.remove(curList.size() - 1);
        }
    }
    
}

/* Time complexity: The total number of steps during the backtracking would be the number of nodes in the tree.
Let NN be the number of candidates, T be the target value, and MM be the minimal value among the candidates.
The maximal depth of the tree, would be T / M, As we know, the maximal number of nodes in N-ary tree of O(N^(T/M + 1))
*/

/* Space complexity: The number of recursive calls can pile up T/M
 where we keep on adding the smallest element to the combination. 
 As a result, the space overhead of the recursion is O(T/M)
In addition, we keep a combination of numbers during the execution, which requires at most O(T/M) space as well.
