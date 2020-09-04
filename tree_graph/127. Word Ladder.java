class Solution {
    /*
    The problem boils down to finding the shortest path from a start node to a destination node, 
    if there exists one. Hence it can be solved using Breadth First Search approach.
    */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();
        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        Map<String, List<String>> dict = buildMap(wordList);
        
        // Queue for BFS
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        queue.add(new Pair(beginWord, 1));
        
        // track visited in case go into cycles
        Map<String, Boolean> visited = new HashMap<>();
        visited.put(beginWord, true);
        
        while (!queue.isEmpty()) {
            Pair<String, Integer> node = queue.poll();
            String word = node.getKey();
            int level = node.getValue();
            for(int i = 0; i < len; i++){
                String trans = word.substring(0, i) + '*' + word.substring(i + 1, len);
                for(String adjacentWord : dict.getOrDefault(trans, new ArrayList<>())){
                    if(adjacentWord.equals(endWord)){
                        return level + 1;
                    }
                    
                    if(!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        queue.add(new Pair(adjacentWord, level + 1));
                    }
                }
            }
        }
        return 0;
    }
    
    // build map <wildcard String, wordList>
    // e.g, <*ot, [hot, dot]>
    public Map<String, List<String>> buildMap(List<String> wordList){
        Map<String, List<String>> dict = new HashMap<>();
        int len = wordList.get(0).length();
        wordList.forEach(
            word -> {
                for(int i = 0; i < len; i++) {
                    String trans = word.substring(0, i) + '*' + word.substring(i + 1, len);
                    List<String> words = dict.getOrDefault(trans, new ArrayList<>());
                    words.add(word);
                    dict.put(trans, words);
                }
            }
        );
        return dict;
    }
}

// Time complexity: O(m(m combination of string wildcards) * m(substring operation) * n(number of string))
// Space complexity: O(m * n(key) * n(value))
