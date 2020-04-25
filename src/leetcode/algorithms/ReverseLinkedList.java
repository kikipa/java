package leetcode.algorithms;

public class ReverseLinkedList {

    public static void main(String[] args){
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
//		ListNode node2 = new ListNode(3);
//		ListNode node3 = new ListNode(3);
//		ListNode node4 = new ListNode(4);
//		ListNode node5 = new ListNode(5);
//		ListNode node6 = new ListNode(6);

//		node5.next = node6;
//		node4.next = node5;
//		node3.next = node4;
//		node2.next = node3;
//		node1.next = node2;
        node.next = node1;

        ReverseLinkedList rl = new ReverseLinkedList();

        node = rl.reverseList(node);

        while(node!=null){
            System.out.println(node.val);
            node = node.next;
        }

    }

    public ListNode reverseList(ListNode head) {

        if(head==null){
            return null;
        }

        if(head.next==null){
            return head;
        }

        ListNode tmp1 = null;
        ListNode tmp2 = null;
        while(head!=null&&head.next!=null){

            tmp2 = new ListNode(head.next.val);
            tmp2.next = head;

            head = head.next.next;

            tmp2.next.next.next = null;
            tmp2.next.next = tmp1;

            tmp1=tmp2;
        }

        if(head==null){
            head = tmp2;
        }
        else{
            head.next = tmp2;
        }


        return head;
    }

}

class ListNode {
    public int val;
    ListNode next;
    public ListNode(int x){
        this.val = x;
    }
}
