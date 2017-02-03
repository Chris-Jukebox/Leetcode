//418. Sentence Screen Fitting

/*
Given a rows x cols screen and a sentence represented by a list of non-empty words, find how many times the given sentence can be fitted on the screen.

Note:

A word cannot be split into two lines.
The order of words in the sentence must remain unchanged.
Two consecutive words in a line must be separated by a single space.
Total words in the sentence won't exceed 100.
Length of each word is greater than 0 and won't exceed 10.
1 ≤ rows, cols ≤ 20,000.
*/

/*

Say sentence=["abc", "de", "f], rows=4, and cols=6.
The screen should look like

"abc de"
"f abc "
"de f  "
"abc de"


组织表示O(mn) runtime exceeds了
所以这又变成了找规律（排排队吃果果）的题目了
1）规律序列 String s = "abc de f "（f后面有空格）
基本想法是把矩阵->abc de f abc de f abc de f
这样我们用总长度／s.length() 来算出重复了多少遍了

2）恶心的部分是如何找到矩阵排列后的最后一行的下一行的起始位置
（即矩阵中字符长度）
然而要把矩阵字符串->正常字符串要++--
	a)abc de
	  f
	这种情况要补空格
	b)de f  
	  abc de
	这种情况要去掉f空格后能塞进去的abc的a 
	换句话说矩阵在这里强行补了空格	
*/

public class Solution {
	public int wordsTyping(String[] sentence, int rows, int cols) {
		// 规律序列
		String s = String.join(" ", sentence) + " ";
		int start = 0;
		int l = s.length();
		for(int i = 0; i < rows; i++)
		{
			start += cols;
			// 换行补空格的情况
			if(s.charAt(start % l) == ' ')
				start++;
			else
				// 没有放下的字符在上一行都补了空格
				while(start > 0 && s.charAt((start - 1) % l) != ' ')
					start--;
		}
		return start / s.length();
	}
}	


