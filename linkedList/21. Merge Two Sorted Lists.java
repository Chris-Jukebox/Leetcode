tion for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(l1 != null && l2 != null){
            if(l1 != null && l2 != null){
                if(l1.val < l2.val){
                    cur.next = l1;
                    l1 = l1.next;
                }
                else{
                    cur.next = l2;
                    l2 = l2.next;
                }
                cur = cur.next;
            }
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }
}

/*
Time complexity : O(n + m)

Because exactly one of l1 and l2 is incremented on each loop iteration, 
the while loop runs for a number of iterations equal to the sum of the lengths of the two lists. 
All other work is constant, so the overall complexity is linear.

Space complexity : O(1)

The iterative approach only allocates a few pointers, so it has a constant overall memory footprint.
*/
