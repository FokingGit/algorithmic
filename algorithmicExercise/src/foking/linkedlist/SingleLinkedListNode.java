package foking.linkedlist;

import java.util.HashMap;

/**
 * 单链表
 */
public class SingleLinkedListNode extends LinkedListNode {

    SingleLinkedListNode(int value) {
        super(value);
    }

    public static void main(String[] args) {
        SingleLinkedListNode linkedListNode1 = new SingleLinkedListNode(1);
//        SingleLinkedListNode linkedListNode2 = new SingleLinkedListNode(2);
//        SingleLinkedListNode linkedListNode3 = new SingleLinkedListNode(3);
//        SingleLinkedListNode linkedListNode4 = new SingleLinkedListNode(4);
//        SingleLinkedListNode linkedListNode5 = new SingleLinkedListNode(5);

//        linkedListNode1.next = linkedListNode2;
//        linkedListNode2.next = linkedListNode3;
//        linkedListNode3.next = linkedListNode4;
//        linkedListNode4.next = linkedListNode5;

        SingleLinkedListNode singleLinkedListNode = removeNthFromEnd3(linkedListNode1, 1);

        System.out.println(singleLinkedListNode);
    }

    /**
     * 删除指定节点
     * <p>
     * 链表至少包含两个节点。
     * 链表中所有节点的值都是唯一的。
     * 给定的节点为非末尾节点并且一定是链表中的一个有效节点。
     * 不要从你的函数中返回任何结果。
     *
     * @param node 要删除的节点
     */
    public void deleteListNode(SingleLinkedListNode node) {
        //注意不能直接将一个指针赋值过来，因为单链表无法追溯，直接替换的电话，上一个的节点的next指针就丢失了
        LinkedListNode tempeNode = node.next;
        node.next = tempeNode.next;
        node.value = tempeNode.value;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     *
     * @param head 头节点
     * @param n    第N个
     * @return
     */
    public SingleLinkedListNode removeNthFromEnd(SingleLinkedListNode head, int n) {
        HashMap<Integer, SingleLinkedListNode> map = new HashMap<>();
        int index = 0;
        map.put(index, head);
        SingleLinkedListNode tempNode = head;
        while (tempNode.next != null) {
            index++;
            tempNode = (SingleLinkedListNode) tempNode.next;
            map.put(index, tempNode);

        }

        if (n != 1 && n == map.size()) {
            //删除头节点,将第二个节点返回
            return map.get(1);
        }

        if (n == 1 && map.size() >= 2) {
            //删除尾节点
            map.get(map.size() - 2).next = null;
            return head;
        }

        if (n == 1 && map.size() == 1) {
            head = null;
            return head;
        }

        //倒数第N个节点就是Map中第size-N个节点
        SingleLinkedListNode needDeleteNode = map.get(map.size() - n);
        tempNode = (SingleLinkedListNode) needDeleteNode.next;
        needDeleteNode.next = tempNode.next;
        needDeleteNode.value = tempNode.value;


        return head;
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     *
     * @param head
     * @param n
     * @return
     */
    public SingleLinkedListNode removeNthFromEnd2(SingleLinkedListNode head, int n) {
        int listLength = 1;
        SingleLinkedListNode tempNode = head;
        while (tempNode.next != null) {
            listLength++;
            tempNode = (SingleLinkedListNode) tempNode.next;
        }

        //处理边界情况
        if (n == listLength) {
            //删除头节点
            if (n == 1) {
                //删除尾节点
                head = null;
                return null;
            } else {
                //删除头节点
                tempNode = head;
                head = null;
                return (SingleLinkedListNode) tempNode.next;
            }
        }


        tempNode = head;
        for (int i = 0; i < listLength - n; i++) {
            tempNode = (SingleLinkedListNode) tempNode.next;
        }

        //此时要删除的就是tempNode的下一个节点
        tempNode.next = tempNode.next.next;

        return head;
    }


    /**
     * 快慢指针：给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     *
     * @param head
     * @param n
     * @return
     */
    public static SingleLinkedListNode removeNthFromEnd3(SingleLinkedListNode head, int n) {
        SingleLinkedListNode dummy = new SingleLinkedListNode(0);
        dummy.next = head;
        SingleLinkedListNode quickListNode = dummy;
        //获取慢节点
        SingleLinkedListNode slowListNode = dummy;
        while (n + 1 > 1) {
            n--;
            quickListNode = (SingleLinkedListNode) quickListNode.next;
        }

        while (quickListNode.next != null) {
            quickListNode = (SingleLinkedListNode) quickListNode.next;
            slowListNode = (SingleLinkedListNode) slowListNode.next;
        }

        if (slowListNode.next == null) {
            head = null;
            return null;
        }

        slowListNode.next = slowListNode.next.next;

        return (SingleLinkedListNode) dummy.next;
    }

}
