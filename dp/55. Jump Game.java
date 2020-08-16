enum Index {
    GOOD, BAD, UNKNOWN
}

// Top-down DP
class Solution {
    Index[] memo;
    
    public boolean canJump(int[] nums) {
        // Init the memoization array
        memo = new Index[nums.length];
        for(int i = 0; i < memo.length; i++)
            memo[i] = Index.UNKNOWN; 
        
        memo[memo.length - 1] = Index.GOOD;
        return canJumpFromPos(0, nums);
    }
    
    public boolean canJumpFromPos(int pos, int[] nums){
        if(memo[pos] != Index.UNKNOWN)
            return memo[pos] == Index.GOOD ? true : false;
        
        // recursively update the mem table
        int furthestJump = Math.min(nums.length - 1, pos + nums[pos]);
        for(int nextPos = pos + 1; nextPos <= furthestJump; nextPos++){
            if(canJumpFromPos(nextPos, nums)){
                memo[pos] = Index.GOOD;
                return true;
            }
        }
        
        memo[pos] = Index.BAD;
        return false;
    }
    
    // Time complexity : O(n^2) For every element in the array, say i, we are looking at the next nums[i] elements to its right aiming to find a GOOD index. nums[i] can be at most n, where n is the length of array nums

    //  O(2n) = O(n)O(2n)=O(n). First n originates from recursion. Second n comes from the usage of the memo table 
}

// _____________________________________
//
// Solution 2:

enum Index {
    GOOD, BAD, UNKNOWN
}

// Bottom-up no recursion DP
class Solution {
    Index[] memo;

    public boolean canJump(int[] nums) {
        // Init the memoization array
        memo = new Index[nums.length];
        for(int i = 0; i < memo.length; i++)
            memo[i] = Index.UNKNOWN;

        memo[memo.length - 1] = Index.GOOD;

        for(int i = nums.length - 2; i >= 0; i--){
            int furthestJump = Math.min(nums.length - 1, i + nums[i]);
            for(int j = i + 1; j <= furthestJump; j++){
                if(memo[j] == Index.GOOD){
                    memo[i] = Index.GOOD;
                    break;
                }
            }
        }

        return memo[0] == Index.GOOD;

    }

/* Time complexity : O(n^2)O(n
2
 ). For every element in the array, say i, we are looking at the next nums[i] elements to its right aiming to find a GOOD index. nums[i] can be at most nn, where nn is the length of array nums.

Space complexity : O(n)O(n). This comes from the usage of the memo table.*/
}
