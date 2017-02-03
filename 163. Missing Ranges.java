//163. Missing Ranges
/*
Given a sorted integer array 
where the range of elements are in the inclusive range [lower, upper], return its missing ranges.
For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
*/

// TODO: INT_MAX LIMIT

class Solution {
public:
	void addRange(vector<string> &vec, int start, int end)
	{
		// array[i - 1] + 1 == array[i] - 1 
		if(start <= end)
			vec.push_back(to_string(start) + (start == end? "" : "->" + to_string(end)));
	}

    vector<string> findMissingRanges(vector<int>& nums, int lower, int upper) {
    	// store the result
    	vector<string> result;
    	int start = lower;
    	int end = lower;
    	for(int n: nums)
    	{
    		end = n - 1;
    		addRange(result, start, end);
    		start = n + 1;
    	}
    	// upper
    	addRange(result, start, upper);
    	return result;
	}
};
