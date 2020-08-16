class Solution {
    public int maxArea(int[] height) {
        if(height.length < 1)
            return 0;
        
        // brute force
        int res = 0;
        for(int i = 0; i < height.length; i++){
            for(int j = i + 1; j < height.length; j++){
                int length = j - i;
                int width = Math.min(height[i], height[j]);
                int curArea = width * length;
                res = Math.max(curArea, res);
            }
        }
        return res;
    }
}

class Solution {
    public int maxArea(int[] height) {
        if(height.length < 1)
            return 0;

        // two pointer, left -> ____ <- right
        // math proof: the only bigger area than current
        // is to move the current shorter line to the next pointer in spite of width reduction
        // as we are always limited by the shortest
        int l = 0;
        int r = height.length - 1;
        int res = 0;

        while(l < r){
            res = Math.max(res, (r - l) * Math.min(height[l], height[r]));
            if(height[l] < height[r]){
                l++;
            }
            else
                r--;
        }
        return res;
    }

    // Time complexity: O(n), one pass;
    // Space complexity: O(1);
}
