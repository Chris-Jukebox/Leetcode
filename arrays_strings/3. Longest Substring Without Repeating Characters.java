class Solution {
    public int lengthOfLongestSubstring(String s) {
        /* typical sliding window problem */
        /* scan string, checking [i, j] */

        if(s == null || s.length() < 1)
            return 0;
        
        // <key, value> -> <cur character, last occur index>
        Map<Character, Integer> dict = new HashMap<>();
        int res = 0;
    
        for(int i = 0, j = 0; j < s.length(); j++){
            char cur = s.charAt(j);
            if(dict.containsKey(cur)){
                int lastOccurIndex = dict.get(cur);
                // ignore [i, i']
                i = Math.max(i, lastOccurIndex + 1);
            }
            // calculate the len of [i', j]
            res = Math.max(res, j - i + 1);
            dict.put(cur, j);
        }
        return res;
    }
    
    // Time complexity: O(n), n is the length of the given string 
    // Space complexity: O(m), m is the size of the charset/alphabet in the given string 
}
