// 340. Longest Substring with At Most K Distinct Characters
/*
Given a string, find the length of the longest substring T that contains at most k distinct characters.

For example, Given s = “eceba” and k = 2,

T is "ece" which its length is 3.
*/

// 双指针lr遍历数组 
// r一直往右挪 直到不符合k为止
// l一直往右挪 直到符合k为止
// int[128]数组记录当前每个字符串在这个k的范围内出现了几次
// 过程中记录当前有多少不同的字母 并记录下最大的情况
public class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(k >= s.length())
            return s.length();
        int[] cnt = new int[128];
        int l = 0, d = 0, res = k;
        for(int r = 0; r < s.length(); r++)
        {
            if(cnt[s.charAt(r)] == 0)
                d++;
            cnt[s.charAt(r)]++;
            while(d > k)
            {
                cnt[s.charAt(l)]--;
                if(cnt[s.charAt(l)] == 0)
                    d--;
                l++;
            }
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}

