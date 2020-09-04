ass Solution {
    /*
    Backtracking with HashTable
    
    We could simply build a hashtable with all possible prefixes as keys 
    and the words that are associated with the prefix as the values in the table. 
    Later, given the prefix, we should be able to list all the words with the given prefix in constant time O(1).
    */
    public List<List<String>> wordSquares(String[] words) {
        Map<String, List<String>> prefixMap = buildPrefixMap(words);
        List<List<String>> res = new ArrayList<>();
        int n = words[0].length();
        
        for(String word : words){
            List<String> curWordSquares = new ArrayList<>();
            curWordSquares.add(word);
            dfs(1, n, curWordSquares, prefixMap, res);
        }
        
        return res;
    }
    
    public void dfs(int startIndex, int n, List<String> curWordSquares, Map<String, List<String>> prefixMap, List<List<String>> res){
        // output 
        if(startIndex == n){
            res.add(new ArrayList<String>(curWordSquares));
            return;
        }
        
        // construct prefix based on matrix symmetric
        StringBuilder prefix = new StringBuilder();
        for(String word: curWordSquares){
            prefix.append(word.charAt(startIndex));
        }
        
        // look for candidates in the prefix hashmap
        for(String candidate : prefixMap.getOrDefault(prefix.toString(), new ArrayList<>())){
            curWordSquares.add(candidate);
            dfs(startIndex + 1, n, curWordSquares, prefixMap, res);
            curWordSquares.remove(curWordSquares.size() - 1);
        }
    }
    
    public Map<String, List<String>> buildPrefixMap(String[] words){
        Map<String, List<String>> map = new HashMap<>();
        for(String word: words)
        {
            for(int i = 1; i < word.length(); i++){
                String prefix = word.substring(0, i);
                List<String> wordList = map.getOrDefault(prefix, new ArrayList<>());
                wordList.add(word);
                map.put(prefix, wordList);
            }
        }
        return map;
    }
    
}

// Time complexity: m is the word length, n is the number of word, -> O(26^m(this many of permutation for one set start word) * n)
// Space complexity: O(m^2 * n) -> worst case
