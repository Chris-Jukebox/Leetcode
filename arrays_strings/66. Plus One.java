class Solution {
    public int[] plusOne(int[] digits) {
        // 小学数学题
        int i = digits.length - 1;
        while(i >= 0)
        {
            if(digits[i] == 9){
                digits[i] = 0;
                i--;
            }
            else{
                digits[i] += 1;
                break;
            }
        }
        if(i < 0){
            int[] res = new int[digits.length + 1];
            for(int j = digits.length - 1; j >= 0; j--)
                res[j + 1] = digits[j];
            res[0] = 1;
            return res;
        }
        
        return digits;
    }
}

/* 
Time complexity : O(N) since it's not more than one pass along the input list.
Space complexity : O(1) when digits contains at least one not-nine digit, and O(N) otherwise.
*/
