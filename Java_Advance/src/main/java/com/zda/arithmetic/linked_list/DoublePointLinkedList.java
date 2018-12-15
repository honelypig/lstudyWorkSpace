package com.zda.arithmetic.linked_list;

/**
 * 双端链表(是指链表有一个首节点和一个尾节点。与双向链表不一样！双向链表是指node有previous与next)
 */
public class DoublePointLinkedList {
    private int size;
    private Node head;//首节点
    private Node tail;//尾节点

    public DoublePointLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    private class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    //链表头新增节点
    public void addHead(Object obj) {
        Node node = new Node(obj);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    //链表尾新增节点
    public void addTail(Object obj) {
        Node node = new Node(obj);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    //删除表头
    public boolean deleteHead() {
        if (size == 0) return false;
        else if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
        }
        size--;
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void display() {
        if (size > 0) {
            Node node = head;
            int tempSize = size;
            System.out.print("[");
            while (tempSize > 0) {
                if (node.next == null)
                    System.out.println(node.data + "]");
                else System.out.println(node.data + "->");
                node = node.next;
                tempSize=tempSize-1;
            }
        } else if (size == 1) {
            System.out.println("[" + head.data + "]");
        } else {
            System.out.println("[]");
        }
    }

}
