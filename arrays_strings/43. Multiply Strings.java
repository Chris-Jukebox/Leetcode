class Solution {
    public String multiply(String num1, String num2) {
        // 小学数学乘法题
        //        1 2 3   -> index[0, 1, 2] -i
        //  x       4 5   -> index[0, 1]    -j
        // _______________
        //          1 5   -> index[i + j], index[i + j + 1]
        //        1 0
        //      0 5
        //        1 2
        //      0 8
        //    0 4
        // _______________
        //index
        //    0 1 2 3 4 
        //    from right to left to calculate for index[] to consider carry-over  
        
        int m = num1.length();
        int n = num2.length();
        int[] pos = new int[m + n];
        
        for(int i = m - 1; i >= 0; i--){
            for(int j = n - 1; j >= 0; j--){
                int product = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                // adding previous product
                int sum = product + pos[i + j + 1];
                int quotient = sum / 10;
                int remainder = sum % 10;
                pos[i + j] += quotient;
                pos[i + j + 1] = remainder;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m + n; i++){
            // strip off leading zeros
            if(!(sb.length() == 0 && pos[i] == 0)){
                sb.append(pos[i]);
            }
        }
        // edge case: "0" 
        return sb.length() == 0 ? "0" : sb.toString();
    }
}
