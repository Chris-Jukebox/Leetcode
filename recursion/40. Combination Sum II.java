class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        if(candidates == null)
            return res;
        
        Arrays.sort(candidates);
        dfs(0, target, candidates, new ArrayList<Integer>(), res);
        return res;
    }
    
    public void dfs(int startIndex, int target, int[] candidates, List<Integer> curList, List<List<Integer>> res){
        
        if(target == 0){
            res.add(new ArrayList<Integer>(curList));
            return;
        }
        
        for(int i = startIndex; i < candidates.length; i++)
        {
            if(i != startIndex && candidates[i] == candidates[i - 1])
                continue;
            
            int cur = candidates[i];
            if(cur > target)
                break;
            
            curList.add(cur);
            dfs(i + 1, target - cur, candidates, curList, res);
            curList.remove(curList.size() - 1);
        }
    }
    
}

// Time Complexity: target t, minimal value m, number of candidate n
// -> O(n ^ (t/m + 1)

// Space Complexity: O(t/m)

