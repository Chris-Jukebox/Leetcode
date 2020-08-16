class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 和2sum是一个道理 注意怎样去重
        
        if(nums == null || nums.length < 3)
            return new ArrayList<>();
        
        // sort array first, O(nlogn)
        Arrays.sort(nums);
        
        List<List<Integer>> res = new ArrayList<>();
        // 检查负数就可以了
        for(int i = 0; i < nums.length && nums[i] <= 0; i++){            
            int cur = nums[i];
            // 去重1
            if(i == 0 || cur != nums[i - 1]){
                // set a target, basically the target = b + c
                int target = -1 * cur;
                int l = i + 1;
                int r = nums.length - 1;
                while(l < r){
                    int curSum = nums[l] + nums[r];
                    if(curSum == target){
                        // 注意有解时也要修改指针
                        res.add(new ArrayList<>(Arrays.asList(cur, nums[l++], nums[r--])));
                        // 去重2
                        while(l < r && nums[l] == nums[l - 1])
                            l++;
                    }
                    else if(curSum < target)
                        l++;
                    else
                        r--;
                }
            }
        }
        return res;
    }
    // Time complexity O(n ^ 2)
    // Space complexity, ignoring output, from O(log(n)) to O(n), depending on the implementation of the sorting algorithm.
}
