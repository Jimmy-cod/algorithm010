/*
第一次写道这样就试着运行，链表和Recursive不清楚 :-)
*/
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

//因为要达到末尾时才能够进行连接，所以要在出栈的时候（recursive 调用后）
class Solution {

    ListNode res = new ListNode();

    public ListNode reverseList(ListNode head) {
        helper(head);
        return res;
    }
    private ListNode helper(ListNode node){
        if(node == null || node.next == null){
            res = node;
            return node;
        }
        ListNode temp = helper(node.next);
        temp.next = node;
        node.next = null;
        return node;
    }
}

/*看完题解后：
1. 迭代法: 用 prev, curr, head 反转指针
prev = head;
curr = head.next;
curr.next = prev; //反转指针
head = curr;

定义两个指针： pre 和 cur ；pre 在前 cur 在后。
每次让 pre 的 next 指向 cur ，实现一次局部反转
局部反转完成之后，pre 和 cur 同时往前移动一个位置
循环上述过程，直至 pre 到达链表尾部

*/
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode cur = null, pre=head;
        while(pre!=null){
            ListNode tmp = pre.next;
            pre.next = cur;
            cur = pre;
            pre = tmp;
        }
        return cur;
    }
    //时间复杂度：O(n)O(n)
    //空间复杂度：O(1)O(1)
}

/* 2.递归
递归就是压栈，在递归调用前的code就是压栈前要执行的，在递归调用后的code就是出栈后执行的。
有没有一种方法/公式，知道是这段代码是放在递归前还是递归后
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        //terminator
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = reverseList(head.next); //这里会一直迭代直到触发terminator（走到链表最后），然后才执行下面语句
        head.next.next = head;
        head.next = null; //这里是为了断开连接
        return node;
    }
}

/* 递归 2
 return helper(nextNode, curr); //用value互换来达到指针反转
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        return helper(head, null);
    }

    private ListNode helper(ListNode curr, ListNode newList){
        if (curr == null){
            return newList;
        }
        ListNode nextNode = curr.next;
        curr.next = newList;
        return helper(nextNode, curr); //用参数互换来达到指针反转
    }
}