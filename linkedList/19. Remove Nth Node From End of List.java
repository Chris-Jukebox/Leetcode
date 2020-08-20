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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // First we will add an auxiliary "dummy" node, which points to the list head. 
        // The "dummy" node is used to simplify some corner cases such as a list with only one node, 
        // or removing the head of the lis
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        // get the length
        int length = 0;
        ListNode cur = head;
        while(cur != null){
            length++;
            cur = cur.next;
        }
        
        // iterate to L - n - 1(index), relink nodes
        length -= n;
        cur = dummy;
        while(length > 0){
            length--;
            cur = cur.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    // one pass solution
public ListNode removeNthFromEnd(ListNode head, int n) {
    /*
    Instead of one pointer, we could use two pointers. The first pointer advances the list by n+1n+1 steps from the beginning, while the second pointer starts from the beginning of the list. Now, both pointers are exactly separated by nn nodes apart. We maintain this constant gap by advancing both pointers together until the first pointer arrives past the last node. The second pointer will be pointing at the nnth node counting from the last. We relink the next pointer of the node referenced by the second pointer to point to the node's next next node.
    */
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
    
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }
    
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }
}
