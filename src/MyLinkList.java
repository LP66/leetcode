
/**
 * @description: 链表相关题
 * @author: 吕立屏
 */
public class MyLinkList {

}


class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}


/**
 * @decription: 141.环形链表，判断链表是否有环
 * @solution: 利用快慢指针，快指针每次多走一步，快慢相遇则说明链表有环
 * @difficulty: 简单，O(n), 这种方法相比利用HashSet胜在使用常量级别空间，且时间更快（虽然都是O(n)）
 * @url: https://leetcode-cn.com/problems/linked-list-cycle/
 * @date: 2020/5/6
 */
class Solution141 {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode quick = head.next.next, slow = head.next;
        while (quick != slow) {
            if (quick == null || quick.next == null) return false;
            quick = quick.next.next;
            slow = slow.next;
        }
        return true;
    }
}


/**
 * @decription: 21.合并两个有序链表，返回一个新的有序链表，要求都是原来的节点
 * @solution: 基本就是指针操作的细节，这里关键在于我们new的head，让current引用变量指向head
 *            再让他一直往下next帮忙"打江山"，到头来还是head的
 * @difficulty: 简单，O(n), 还可以去看看这题的递归写法，非常优美简洁
 * @url: https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * @date: 2020/5/6
 */
class Solution21 {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        // 关键指针，工具人
        ListNode current = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        if (l1 != null) current.next = l1;
        else current.next = l2;
        return head.next;
    }
}


/**
 * @decription: 19.删除链表倒数第n个节点
 * @solution: 让一个指针提前走n+1步，再让快慢指针一起同步走，
 *            快指针到达链尾时，慢指针便在倒数第n+1个节点，
 *            这时直接让慢指针引用去修改 next = next.next,
 *            其中哨兵节点dummy的引入是为了简化极端情况的处理，
 *            防止传入链表仅有一个元素，也防止删除的恰好是头节点
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * @date: 2020/5/16
 */
class Solution19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy, slow = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}


/**
 * @decription: 206.反转链表，经典🔥
 * @solution: 迭代，一次遍历，利用三个指针，缓存当前原先的后一个
 *            再让当前的后一个指向前一个，
 *            再让 当前 与 前一个 指针一起后移直到当前为null
 * @difficulty: 简单
 * @url: https://leetcode-cn.com/problems/reverse-linked-list/
 * @date: 2020/5/16
 */
class Solution206 {

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;   // 因为新链表尾节点为null
        ListNode curr = head;
        ListNode ogNext;    // 依靠它缓存原来的下一个来往后遍历
        while (curr != null) {
            ogNext = curr.next;
            // 后指向前
            curr.next = prev;
            // 一起后移
            prev = curr;
            curr = ogNext;
        }
        return prev;    // prev已到达原来尾节点，即新链表头节点
    }
}


/**
 * @decription: 2.两数相加，两条逆序存储数字的链表，每个节点一位数字，返回相同组织形式的两数之和
 * @solution: 类似题415字符串相加，注意一下用head节点定头，有current节点打江山把后续节点加进去
 * @difficulty: 中等
 * @url: https://leetcode-cn.com/problems/add-two-numbers/
 * @date: 2021/3/18
 */
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = new ListNode(0);
        ListNode current = head;
        while (l1 != null || l2 != null) {
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            int temp = n1 + n2 + carry;
            carry = temp / 10;
            current.next = new ListNode(temp % 10);
            current = current.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry == 1) {
            current.next = new ListNode(1);
        }
        return head.next;
    }
}