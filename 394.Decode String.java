//394.Decode String
/*
Given an encoded string, return it's decoded string.

The encoding rule is: k[encoded_string], 
where the encoded_string inside the square brackets is being repeated exactly k times. 
Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. 
For example, there won't be input like 3a or 2[4].


Examples:

s = "3[a]2[bc]", return "aaabcbc".
s = "3[a2[c]]", return "accaccacc".
s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

*/

//吓死小编我以为是那道面经encode14xa的题...
//不过狗家好喜欢这种字符串的羞耻play

//基本想法：

// 遇到 '[' 把res push进去resStack 作为上一层
// 遇到 ']', resStack.pop() + countStack.pop * res
// 上一层 + 次数 * 当前层
public class Solution {
    public String decodeString(String s) {
        int len = s.length();
        // Count stack
        Stack<Integer> countStack = new Stack();
        // Previous string Stack
        Stack<String> resStack = new Stack();
        String res = "";
        int index = 0;
        while(index < len)
        {
        	char cur = s.charAt(index);
        	// 数字的情况
        	if(Character.isDigit(cur) == true)
        	{
        		int count = cur - '0';
        		char next = s.charAt(++index);
        		while(Character.isDigit(next) == true) {
        			count = count * 10 + next - '0';
        			index++;
        			next = s.charAt(index);
        		}
        		countStack.push(count);
        	}
        	// 左括号的情况
        	else if(cur == '[')
        	{
        		// store 当前上一层的内容进resStack
        		resStack.push(res);
        		res = "";
        		index++;
        	}
        	// 右括号 我们进行弹出操作
        	else if(cur == ']')
        	{
        		//首先弹出上一层的内容
        		StringBuilder temp = new StringBuilder(resStack.pop());
        		//弹出对当前括号里的内容（res）要重复的次数
        		int cnt = countStack.pop();
        		for(int i = 0; i < cnt; i++)
        			temp.append(res);
        		res = temp.toString();
        		index++;
        	}
        	else
        	{
        		res += cur;
        		index++;
        	}
        }
        return res;
    }
}