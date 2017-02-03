// 298. Binary Tree Longest Consecutive Sequence
/*
Given a binary tree, find the length of the longest consecutive sequence path.

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.
   
   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

*/

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // bottom-up search, record current node longest length, return the longest consecutive length among the all 
public class Solution {
    int maxLength = 0;
    public int longestConsecutive(TreeNode root) {
        DFS(root);
        return maxLength;
    }
    private int DFS(TreeNode node)
    {
        if(node == null)
            return 0;
        int leftLen = DFS(node.left);
        int rightLen = DFS(node.right);
        int len = Math.max(node.left != null && node.val + 1 == node.left.val? leftLen : 0, 
                    node.right != null && node.val + 1 == node.right.val? rightLen : 0) + 1; 
        maxLength = Math.max(maxLength, len);
        return len;
    }
}