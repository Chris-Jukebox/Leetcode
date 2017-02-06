// 425. Word Squares
/*
Given a set of words (without duplicates), find all word squares you can build from them.

A sequence of words forms a valid word square if the kth row and column read the exact same string, where 0 ≤ k < max(numRows, numColumns).

For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads the same both horizontally and vertically.

b a l l
a r e a
l e a d
l a d y
Note:
There are at least 1 and at most 1000 words.
All words will have the exact same length.
Word length is at least 1 and at most 5.
Each word contains only lowercase English alphabet a-z.
*/

// 恶心死人了 不光要建trie 还要建个记prefix的trie

public class Solution {
    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();
        if(words == null || words.length < 1)
        	return res;
        int len = words[0].length();
        Trie trie = new Trie(words);
        List<String> resBuilder = new ArrayList<String>();
        for(String s : words) {
        	resBuilder.add(s);
        	search(trie, res, resBuilder, len);
        	resBuilder.remove(resBuilder.size() - 1);
        }
        return res;
    }

    void search(Trie trie, List<List<String>> res, List<String> resBuilder, int len) {
    	// return true
    	if(resBuilder.size() == len) {
    	    //高能预警 因为resBuilder是个reference会被最后情况 我们必须来个deep copy
    	    ArrayList<String> copy = new ArrayList<>();
    	    copy.addAll(resBuilder);
    		res.add(copy);
    		return;
    	}
    	// build prefix
    	int curLen = resBuilder.size();
    	StringBuilder prefix = new StringBuilder();
    	for(String s : resBuilder)
    		prefix.append(s.charAt(curLen));
    	// findByPrefix
    	List<String> startWith = trie.findByPrefix(prefix.toString());
    	for(String s : startWith) {
    		resBuilder.add(s);
    		search(trie, res, resBuilder, len);
    		// pup up current
    		resBuilder.remove(resBuilder.size() - 1);
    	}
    }


    class TrieNode {
    	List<String> startWith;
    	TrieNode[] children;

    	TrieNode() {
    		startWith = new ArrayList<>();
    		children = new TrieNode[26];
    	}

    	TrieNode get(char c){
    		int index = c - 'a';
    		if(this.children[index] != null)
    			return this.children[index];
    		else
    			return null;
    	}

    	TrieNode add(char c){
    		int index = c - 'a';
    		this.children[index] = new TrieNode();
    		return this.children[index];
    	}

    }

    class Trie {
    	TrieNode root;
        
    	Trie(String[] words)
    	{
    	    root = new TrieNode();
    		for(String s : words) {
    			TrieNode cur = root;
    			for(char c : s.toCharArray())
    			{
    				TrieNode next = new TrieNode();
    				if(cur.get(c) == null)
    					next = cur.add(c);
    				else 
    				    next = cur.get(c);
    				next.startWith.add(s);
    				cur = next;
    			}
    		}
    	}

    	List<String> findByPrefix(String prefix)
    	{
    		List<String> res = new ArrayList<>();
    		TrieNode cur = root;
    		for(char c : prefix.toCharArray())
    		{
    			TrieNode next = new TrieNode();
    			if(cur.get(c) == null)
    				return res;
    			next = cur.get(c);
    			cur = next;
    		}
    		// curNode startWith -> res
    		res.addAll(cur.startWith);
    		return res;
    	}

    }

}