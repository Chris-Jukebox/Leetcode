/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/*
 * For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.
 
Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
*/
class Solution {
    int max = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        if(root == null)
            return root.val;
        
        max_gain(root);
        return max; 
    }
    
    // retun the sum of the better path
    public int max_gain(TreeNode node){
        // note case: root is the only node in the tree
        if(node == null)
            return 0;
        
        int left_sum = Math.max(0, max_gain(node.left));
        int right_sum = Math.max(0, max_gain(node.right));
        
        // the price to start a new path where `node` is a highest node
        int current_cost = left_sum + node.val + right_sum;
        
        // update max_sum if it's better to start a new path
        max = Math.max(max, current_cost);
        
        // return the max gain if continue the same path
        return node.val + Math.max(left_sum, right_sum);
    }
    /*
        Time complexity: O(N), where N is number of nodes, since we visit each node not more than 2 times.
        Space complexity: O(H), where HH is a tree height, to keep the recursion stack. 
        In the average case of balanced tree, the tree height H =logN, in the worst case of skewed tree, H = N
    */
    
}
