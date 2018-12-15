package com.zda.arithmetic.linked_list;

/**
 * 有序列表
 * 　在有序链表中，数据是按照关键值有序排列的。
 * 一般在大多数需要使用有序数组的场合也可以使用有序链表。
 * 有序链表优于有序数组的地方是插入的速度（因为元素不需要移动），
 * 另外链表可以扩展到全部有效的使用内存，而数组只能局限于一个固定的大小中。
 */
public class OrderLinkedList {
    private Node head;

    private class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    public OrderLinkedList() {
        this.head = null;
    }

    //插入数据
    public void insert(int value) {
        Node node = new Node(value);
        Node current = head;
        Node pre = null;
        while (current != null && value > current.data) {
            pre = current;
            current = current.next;
        }
        if (pre == null) {
            head = node;
            head.next = current;
        } else {
            node.next = current;
            pre.next = node;
        }
    }

    //删除头节点
    public void deleteHead() {
        head = head.next;
    }

    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println("");
    }

    public static void main(String args[]){
        OrderLinkedList orderLinkedList=new OrderLinkedList();
        orderLinkedList.insert(2);

        orderLinkedList.display();
        orderLinkedList.insert(1);
        orderLinkedList.insert(3);
        orderLinkedList.insert(0);
        orderLinkedList.insert(5);
        orderLinkedList.insert(-1);
        orderLinkedList.display();

    }
}
