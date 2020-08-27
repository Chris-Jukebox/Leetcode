class Solution {
    public int countNodes(TreeNode root) {
        return root != null ? 1 + countNodes(root.left) + countNodes(root.right) : 0;
    }    
}

// Time complexity : O(N).
// Space complexity : O(d) = O(logN) to keep the recursion stack, where d is a tree depth.

// Solution 2: Binary Search
class Solution {
    /*
    Take advantage of the fact this is a complete binary tree
    Note: In a complete binary tree every level, except possibly the last, is completely filled, 
    and all nodes in the last level are as far left as possible
    
    Return 0 if the tree is empty.
    Compute the tree depth d.
    Return 1 if d == 0.
    The number of nodes in all levels but the last one is 2^d - 1. The number of nodes in the last level could vary from 1 to 2^d
    Enumerate potential nodes from 0 to 2^d - 1 perform the binary search by the node index to check how many nodes are in the last level. 
    Use the function exists(idx, d, root) to check if the node with index idx exists.
    Use binary search to implement exists(idx, d, root) as well.
    Return 2^d - 1 + the number of nodes in the last level.
    */
     
    // Return tree depth in O(d) time
    public int computeDepth(TreeNode node){
        int d = 0;
        while(node.left != null) {
            node = node.left;
            d++;
        }
        return d;
    }
    
    // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
    // Return True if last level node idx exists. 
    // Binary search with O(d) complexity.
    public boolean exists(int idx, int d, TreeNode node){
        int left = 0;
        int right = (int)Math.pow(2, d) - 1;
        int pivot;
        
        for(int i = 0; i < d; i++){
            pivot = left + (right - left) / 2;
            if(idx <= pivot){
                node = node.left;
                right = pivot;
            }
            else{
                node = node.right;
                left = pivot + 1;
            }  
        }
        return node != null;
    }
  
    public int countNodes(TreeNode root) {
        // if the tree is empty
        if(root == null)
            return 0;
        
        // get the depth of the tree
        int d = computeDepth(root);
        if(d == 0)
            return 1;
        
        // Last level nodes are enumerated from 0 to 2**d - 1 (left -> right).
        // Perform binary search to check how many nodes exist.
        int left = 0;
        int right = (int)Math.pow(2, d) - 1;
        int pivot;
        while(left <= right){
            pivot = left + (right - left) / 2;
            if(exists(pivot, d, root))
                left = pivot + 1;
            else
                right = pivot - 1;
        }
        
        // The tree contains 2^d - 1 nodes on the first (d - 1) levels
        // and left nodes on the last level.
        return (int)Math.pow(2, d) - 1 + left; 
    }    
}

// Time complexity : O(d^2) = O(log^2N), where d is a tree depth.
// Space complexity : O(1).
