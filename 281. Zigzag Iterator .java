// 281. Zigzag Iterator
/*
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. 
If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
*/

public class ZigzagIterator {
    ArrayList<Integer> v;
    int cur = 0;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        v = new ArrayList<Integer>();
        int m = v1.size();
        int n = v2.size();
        int len = Math.max(m, n);
        for(int i = 0; i < len; i++)
        {
            if(i < m)
                v.add(v1.get(i));
            if(i < n)
                v.add(v2.get(i));
        }
    }
    public int next() {
        int temp = v.get(cur);
        cur++;
        return temp;
    }

    public boolean hasNext() {
        int n = v.size();
        if(cur < n)
            return true;
        else return false;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */