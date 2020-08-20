/* 3. Longest Substring Without Repeating Characters */
/* Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3 
Explanation: The answer is "abc", with the length of 3. 
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3. 
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/

/* my first attempt */
// Time complexity: O(n^2)
// Space complexity: O(min(m, n)), n is the length of given string, and m is the hashmap size(usually 26)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 1 || s == null)
            return 0;
        int res = 1;
        
        for(int i = 0; i < s.length(); i++){
            Map<Character, Integer> map = new HashMap<>();
            map.put(s.charAt(i), 1);
            int curLen = 1;
            int j = i + 1;
            while(j < s.length()){
                if(map.getOrDefault(s.charAt(j), 0) == 1)
                    break;
                
                map.put(s.charAt(j), 1);
                curLen++;
                j++;
            }
            res = Math.max(res, curLen);
        }
        return res;
    }
}

/* second attempt, use sliding window*/
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 1 || s == null)
            return 0;

        int res = 1;
        int i = 0;
        int j = 0;
        int n = s.length();
        Set<Character> set = new HashSet<>();

        while(i < n && j < n){
        	if(!set.contains(s.charAt(j))){
        		res = Math.max(res, j - i + 1);
        		set.add(s.charAt(j++));
        	}
        	else{
        		set.remove(s.charAt(i++));
        	}
        }
        return res;
    }
}
/* Time complexity: each character in the string could be visited twice at max, so O(n)
/* Space complexity: same as my first attempt, O(min(m, n)), 
n is the length of given string, 
and m is the hashmap size(usually 26)

/* third attempt, further optimization on second attempt, have a kv pair, <character-last index appears>,
so basically if [i, j] finds a replica j' then we can skip the [i, j'] part and updated i to j' + 1.
In this approach, each character in the string is visited only once */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s.length() < 1 || s == null)
            return 0;

        int res = 1;
        int i = 0;
        int j = 0;
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();


        while(i < n && j < n){
        	char cur = s.charAt(j);
        	if(map.containsKey(cur) && map.get(cur) >= i){
        		i = map.get(cur) + 1;
        	}
			map.put(cur, j++);
            // System.out.println(s.substring(i, j));
            // System.out.println("i: " + i + " j:" + j);
			res = Math.max(res, j - i);
        }
        return res;
    }
}












