class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 1)
            return;
        
        // 垃圾数学问题
        
        /*          i 
                        j      
            i - 1           X
        
        X___________________________
        
        */
        
        // find the first number i before the descending patdtern
        int i = nums.length - 1;
        while(i >= 1 && nums[i - 1] >= nums[i])
            i--;
        
        // find the first number j that is bigger than i - 1
        if(i >= 1){
            int j = nums.length - 1;
            while(j >= 0 && nums[j] <= nums[i - 1]){
                j--;
            }
            swap(nums, i - 1, j);
        }
        // reverse the numbers from i to the end
        // to make sure this is the next big number
        reverse(nums, i, nums.length - 1);
    }
    
    void swap(int nums[], int i, int j){
        int temp = nums[j];
        nums[j] = nums[i];
        nums[i] = temp;
    }
    
    void reverse(int nums[], int start, int end){
        while(start < end){
            swap(nums, start++, end--);
        }
    }
}
