/*第一次写道这样就试着运行，链表和Recursive不清楚 :-)
class Solution {
      ListNode res = new ListNode();
    public ListNode reverseList(ListNode head) {
      
        if (head == null){
            return null;
        }
        
        res.next = head;
        res = res.next;
        
        return reverseList(head.next);
    }
    
}

/*看完题解后：
迭代法:

class Solution {
      ListNode res = new ListNode();
    public ListNode reverseList(ListNode head) {
      
        if (head == null){
            return res;
        }
        
        ListNode next = head.nest;
        head.next = res;        
        return reverseList(next);
    }    
}