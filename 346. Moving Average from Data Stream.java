//346. Moving Average from Data Stream
/*
Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

For example,
MovingAverage m = new MovingAverage(3);
m.next(1) = 1
m.next(10) = (1 + 10) / 2
m.next(3) = (1 + 10 + 3) / 3
m.next(5) = (10 + 3 + 5) / 3
*/

public class MovingAverage {
    Queue<Integer> queue = new LinkedList<Integer>();
    int k = 0;
    double sum = 0;
    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        k = size;
    }
    
    public double next(int val) {
        int qSize = queue.size();
        if(qSize < k)
        {
            queue.offer(val);
            qSize++;
            sum += val;
            return sum / qSize;
        }
        else
        {
            sum = sum - queue.poll() + val;
            queue.offer(val);
            return sum / k;
        }
    }
}
/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */