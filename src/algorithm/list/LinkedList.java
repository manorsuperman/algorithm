package algorithm.list;


import java.util.Stack;

/**
 * Created by think on 2016/9/20.
 * <p>
 * <p>
 * 1. 求单链表中结点的个数
 * 2. 将单链表反转
 * 3. 查找单链表中的倒数第K个结点（k > 0）
 * 4. 查找单链表的中间结点
 * 5. 从尾到头打印单链表
 * 6. 已知两个单链表pHead1和pHead2各自有序，把它们合并成一个链表依然有序
 * 7. 判断一个单链表中是否有环
 * 8. 已知一个单链表中存在环，求进入环中的第一个节点
 * 9. 判断两个单链表是否相交
 * 10. 求两个单链表相交的第一个节点
 * 11. 给出一单链表头指针 head 和一节点指针 deletedNode，O(1)时间复杂度删除节点deletedNode
 * 12. 链表的冒泡排序
 * 13. 单链表的双冒泡排序
 * 快慢指针，新的链表
 */


public class LinkedList {
    static class Node {
        int num;
        Node next;
    }

    public static void main(String[] args) {
        Node head = null, head2 = null;
        for (int i = 1; i <= 5; i++) {
            head = addBack(head, i);
        }
        for (int i = 2; i <= 6; i++) {
            head2 = addBack(head2, i);
        }
        show(head);
        System.out.println("1. 求单链表中结点的个数");
        System.out.println("size=" + size(head));

        System.out.println("2. 将单链表反转");
        head = reverse(head);
        show(head);
        head = reverse(head);

        System.out.println("3. 查找单链表中的倒数第K个结点（k > 0）");
        Node kNode = getKNode(head, 0);
        System.out.println(kNode != null ? kNode.num : null);

        System.out.println("4. 查找单链表的中间结点");
        Node midNode = getMidNode(head);
        System.out.println(midNode != null ? midNode.num : null);

        System.out.println("5. 从尾到头打印单链表");
        reversePrint2(head);
        System.out.println();

        System.out.println("6. 已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序");
        Node head6 = null, head7 = null;
        for (int i = 1; i <= 10; i++) {
            head6 = addBack(head6, (int) (Math.random() * 100));
        }
        bubbleSort(head6);

        for (int i = 1; i <= 10; i++) {
            head7 = addBack(head7, (int) (Math.random() * 100));
        }
        bubbleSort(head7);
        merge(head6, head7);

        System.out.println("7. 判断一个单链表中是否有环");
        Node head3 = null;
        for (int i = 1; i <= 5; i++) {
            head3 = addBack(head3, i);
        }
        Node p = head3;
        while (p.next != null) {
            p = p.next;
        }

        p.next = head3.next;

        System.out.println(hasRing(head3));

        System.out.println("8. 已知一个单链表中存在环，求进入环中的第一个节点");
        System.out.println(getFirstRingNode(head3).num);
        System.out.println("9. 判断两个单链表是否相交");
        Node head4 = null, head5 = null, p4;
        for (int i = 2; i <= 6; i++) {
            head4 = addBack(head4, i);
        }
        p4 = head4;
        while (p4.next != null) {
            p4 = p4.next;
        }
        p4.next = head.next;
        for (int i = 2; i <= 6; i++) {
            head5 = addBack(head5, i);
        }
        p4 = head5;
        while (p4.next != null) {
            p4 = p4.next;
        }
        p4.next = head.next;
        System.out.println(isIntersect(head4, head5));

        System.out.println("10. 求两个单链表相交的第一个节点");
        System.out.println(firstIntersectNode(head4, head5).num);
        System.out.println("11. 给出一单链表头指针 head 和一节点指针 deletedNode，O(1)时间复杂度删除节点deletedNode");

        head = delete(head, head);
        show(head);
        System.out.println("12. 单链表的冒泡排序");
        Node head12 = null;
        for (int i = 1; i <= 10; i++) {
            head12 = addBack(head12, (int) (Math.random() * 100));
        }
        bubbleSort(head12);
        System.out.println("13. 单链表的双冒泡排序");
        Node head13 = null;
        for (int i = 1; i <= 10; i++) {
            head13 = addBack(head13, (int) (Math.random() * 100));
        }
        doubleBubblesort(head13, null);
        show(head13);
    }

    public static Node addBack(Node head, int num) {
        Node newNode = new Node();
        newNode.num = num;
        newNode.next = null;
        Node node = head;
        if (head == null) {
            head = newNode;
            return head;
        }
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;

        return head;
    }

    public static void show(Node node) {
        while (node != null) {
//            System.out.println(node.num + " " + node+"  "+node.next);
            System.out.print(node.num + " ");
            node = node.next;
        }
        System.out.println();
    }

    /**
     * 1. 求单链表中结点的个数
     * <p>
     * 思路
     *
     * @param head
     * @return
     */
    public static int size(Node head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    /**
     * 2. 将单链表反转
     * <p>
     * 构建一个新的链表，依次将本链表的节点插入到新链表的最前端，即可完成链表的反转。
     *
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        Node p1 = head, p2;
        head = null;
        while (p1 != null) {
            p2 = p1;
            p1 = p1.next;

            //头插法
            p2.next = head;
            head = p2;
        }
        return head;
    }

    /**
     * 3. 查找单链表中的倒数第K个结点（k > 0）
     * 第一种解法是得到顺数的第 size+k-1 个节点，即为倒数的第K歌节点
     * 第二种解法是快慢指针,主要思路就是使用两个指针，先让前面的指针走到正向第k个结点，
     * 这样前后两个指针的距离差是k-1，之后前后两个指针一起向前走，前面的指针走到最后一个结点时，
     * 后面指针所指结点就是倒数第k个结点，下面采用这种解法。
     *
     * @param head
     * @return
     */
    public static Node getKNode(Node head, int k) {
        if (k <= 0 || head == null) {
            return null;
        }
        Node p2 = head, p1 = head;
        while (k-- > 1 && p1 != null) {
            p1 = p1.next;
        }
        // 说明k>size，因此返回null
        if (k > 1 || p1 == null) {
            return null;
        }
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    /**
     * 4. 查找单链表的中间结点
     * 采用快慢指针，p1每次走两步，p2每次走一步，奇数返回size/2+1，偶数返回size/2,
     * 注意链表为空，链表结点个数为1和2的情况。
     *
     * @param head
     * @return
     */
    public static Node getMidNode(Node head) {
        if (head == null) {
            return null;
        }
        Node p1 = head, p2 = head;
        while (p1.next != null) {
            if (p1 == null) {
                break;
            }
            p1 = p1.next.next;
            p2 = p2.next;

        }
        return p2;
    }

    /**
     * 5. 从尾到头打印单链表
     * 用栈
     *
     * @param node
     */
    public static void reversePrint(Node node) {
        Stack<Node> stack = new Stack<>();
        while (node != null) {
            stack.push(node);
            node = node.next;
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().num + " ");
        }

    }

    /**
     * 5. 从尾到头打印单链表
     * 递归
     *
     * @param node
     */
    public static void reversePrint2(Node node) {
        if (node != null) {
            reversePrint2(node.next);
            System.out.print(node.num + " ");
        }
    }

    /**
     * 6. 已知两个单链表pHead1 和pHead2 各自有序，把它们合并成一个链表依然有序
     * <p>
     * 类似于归并排序
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node merge(Node head1, Node head2) {
        Node p1 = head1, p2 = head2, head;
        if (head1.num < head2.num) {
            head = head1;
            p1 = p1.next;
        } else {
            head = head2;
            p2 = p2.next;
        }

        Node p = head;
        while (p1 != null && p2 != null) {
            if (p1.num <= p2.num) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
            } else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
            }
        }
        if (p1 != null) {
            p.next = p1;
        }
        if (p2 != null) {
            p.next = p2;
        }
        return head;
    }

    /**
     * 7. 判断一个单链表中是否有环
     * <p>
     * 这里也是用到两个指针。如果一个链表中有环，也就是说用一个指针去遍历，
     * 是永远走不到头的。因此，我们可以用两个指针去遍历，一个指针一次走两步，
     * 一个指针一次走一步，如果有环，两个指针肯定会在环中相遇。时间复杂度为O（n）
     *
     * @param head
     * @return
     */
    public static boolean hasRing(Node head) {
        Node p1 = head, p2 = head;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) {
                return true;
            }
        }
        return false;
    }

    /**
     * 8. 已知一个单链表中存在环，求进入环中的第一个节点
     * <p>
     * **解题思路**： 由上题可知，按照 p2 每次两步，p1 每次一步的方式走，发现 p2 和 p1 重合，
     * 确定了单向链表有环路了。接下来，让p2回到链表的头部，重新走，每次步长不是走2了，
     * 而是走1，那么当 p1 和 p2 再次相遇的时候，就是环路的入口了。
     * <p>
     * *为什么？**：假定起点到环入口点的距离为 a，p1 和 p2 的相交点M与环入口点的距离为b，
     * 环路的周长为L，当 p1 和 p2 第一次相遇的时候，假定 p1 走了 n 步。那么有：
     * <p>
     * p1走的路径： a+b ＝ n；
     * p2走的路径： a+b+k*L = 2*n； p2 比 p1 多走了k圈环路，总路程是p1的2倍
     * <p>
     * 根据上述公式可以得到 k*L=a+b=n显然，如果从相遇点M开始，p1 再走 n 步的话，
     * 还可以再回到相遇点，同时p2从头开始走的话，经过n步，也会达到相遇点M。
     * <p>
     * 显然在这个步骤当中 p1 和 p2 只有前 a 步走的路径不同，所以当 p1 和 p2 再次重合的时候，必然是在链表的环路入口点上。
     *
     * @param head
     * @return
     */
    public static Node getFirstRingNode(Node head) {
        Node p1 = head, p2 = head;
        while (p1 != null && p1.next != null) {
            p1 = p1.next.next;
            p2 = p2.next;
            if (p1 == p2) {
                p1 = head;
                while (p1 != p2) {
                    p1 = p1.next;
                    p2 = p2.next;
                }
                break;
            }
        }
        return p1;
    }

    /**
     * 9. 判断两个单链表是否相交
     * 如果两个链表相交，那么相交之后的节点应该相同，那么最后那个节点应该也相同
     *
     * @param head1
     * @param head2
     * @return
     */
    public static boolean isIntersect(Node head1, Node head2) {
        Node p1 = head1, p2 = head2;
        while (p1.next != null) {
            p1 = p1.next;
        }
        while (p2.next != null) {
            p2 = p2.next;
        }
        return p1 == p2;
    }

    /**
     * 10. 求两个单链表相交的第一个节点
     * 采用对齐的思想。计算两个链表的长度 L1 , L2，分别用两个指针 p1 , p2 指向两个链表的头，
     * 然后将较长链表的 p1（假设为 p1）向后移动L2 - L1个节点，然后再同时向后移动p1 , p2，
     * 直到 p1 = p2。相遇的点就是相交的第一个节点。
     *
     * @param head1
     * @param head2
     * @return
     */
    public static Node firstIntersectNode(Node head1, Node head2) {
        int len1 = size(head1);
        int len2 = size(head2);
        Node p1 = head1, p2 = head2;
        if (len1 > len2) {
            for (int i = 1; i < len1 - len2; i++) {
                p1 = p1.next;
            }
        } else {
            for (int i = 1; i < len2 - len1; i++) {
                p2 = p2.next;
            }
        }
        while (p1 != p2) {
            p1 = p1.next;
            p2 = p2.next;
        }

        return p1;
    }

    /**
     * 11. 给出一单链表头指针 head 和一节点指针 deletedNode，O(1)时间复杂度删除节点deletedNode
     *
     * 将deletedNode下一个节点的值复制给deletedNode节点，然后删除deletedNode节点，但是对于要删除的节点是最后一个节点的时候要做处理。
     *
     * @param head
     */
    public static Node delete(Node head, Node deletedNode) {
        if (deletedNode.next != null) {
            deletedNode.num = deletedNode.next.num;
            deletedNode.next = deletedNode.next.next;
        } else {
            if (head == deletedNode) {
                return null;
            }
            Node p = head;
            while (p.next != deletedNode) {
                p = p.next;
            }
            p.next = null;
        }
        return head;
    }

    /**
     * 12 单链表的冒泡排序
     *
     * 对于数组的冒泡排序是上层for循环控制次数，下次for循环控制距离，对于链表的冒泡排序而言，
     * 首先让tail指针为null，一次循环比较完之后，在等于最后一个节点，倒数第二个节点。。。就是通过tail指针控制循环比较的次数和距离。
     * @param head
     */
    public static void bubbleSort(Node head) {
        Node tail = null;
        Node p1;
        while (head != tail) {
            for (p1 = head; p1.next != tail; p1 = p1.next) {
                if (p1.num > p1.next.num) {
                    int temp = p1.num;
                    p1.num = p1.next.num;
                    p1.next.num = temp;
                }
            }
            tail = p1;
        }
        show(head);
    }

    /**
     * 13 单链表的双冒泡排序
     *
     * @param start
     * @param end
     */
    public static void doubleBubblesort(Node start, Node end) {
        if (start != end) {
            Node p1 = start;
            Node p2 = p1.next;
            while (p2 != end) {
                if (p2.num < start.num) {
                    p1 = p1.next;
                    int temp = p1.num;
                    p1.num = p2.num;
                    p2.num = temp;
                }
                p2 = p2.next;
            }
            int temp = p1.num;
            p1.num = start.num;
            start.num = temp;
            doubleBubblesort(start, p1);
            doubleBubblesort(p1.next, null);
        }

    }

}
