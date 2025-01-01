package com.lc.practice;

public class Solution3 {

    static class ListNode{
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        // Create a dummy node and point it to head
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // Initialize two pointers, slow and fast, both pointing to the dummy node
        ListNode slow = dummy;
        ListNode fast = dummy;

        // Move the fast pointer `n+1` steps ahead
        for (int i = 0; i <= n; i++) {
            if (fast == null) return head; // If n is larger than the length of the list
            fast = fast.next;
        }

        // Move both pointers until fast reaches the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the Nth node
        slow.next = slow.next.next;

        // Return the new head (which is dummy.next)
        return dummy.next;
    }

    // Helper method to print a linked list
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Example linked list: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        System.out.println("Original list:");
        printList(head);

        int n = 2; // Remove 2nd node from end
        head = removeNthFromEnd(head, 5);

        System.out.println("List after removing " + n + "th node from end:");
        printList(head);
    }
}
