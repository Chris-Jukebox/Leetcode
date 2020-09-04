class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if(s == null || s.length() < 1)
            return res;
        
        dfs(0, s, new ArrayList<String>(), res);
        return res;
    }
    
    public void dfs(int startIndex, String s, List<String> curList, List<List<String>> res){
        if(startIndex == s.length())
        {
            res.add(new ArrayList<String>(curList));
            return;
        }
        
        for(int i = startIndex; i < s.length(); i++){
            String cur = s.substring(startIndex, i + 1);
            if(!isPalindrome(cur))
                continue;
            
            curList.add(cur);
            dfs(i + 1, s, curList, res);
            curList.remove(curList.size() - 1);
        }
    }
    
    // check string is palindrome
    public boolean isPalindrome(String s){
        int l = 0;
        int r = s.length() - 1;
        while(l < r){
            if(s.charAt(l++) != s.charAt(r--))
                return false;
        }
        return true;
    }
    
}
// Time complexity: For every interval, we can cut it or not cut it, so there will be 2^(n - 1) ways to partition the string.
// For every partition way, we need to check if it is palindrome, which is O(n).
// So the time complexity is O(n * (2 ^ n))
// Space complexity: O(2 ^ n)
