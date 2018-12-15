package com.zda.arithmetic.linked_list;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 单向链表，存放当前表头与表长度。以及node操作函数
 * 内置对象Node，node存放当前data与下一个节点node
 */
public class SingleLinkedList {
    private int size;
    private Node head;

    public SingleLinkedList() {
        this.size = 0;
        this.head = null;
    }

    private class Node {
        private Object data;
        private Node next;

        public Node(Object data) {
            this.data = data;
        }
    }

    public Object addHead(Object obj) {
        Node node = new Node(obj);
        if (size == 0) {
            head = node;
        } else {
            node.next = head;
            size++;
        }
        return obj;
    }

    public Object deleteHead() {
        Node node = head;
        head = node.next;
        size--;
        return node;
    }

    //查找指定元素，找到了返回节点Node，找不到返回null
    public Object findNode(Object obj) {
        Node current = head;
        int tempSize = size;
        while (tempSize > 0) {
            if (obj.equals(current.data)) {
                return current;
            } else
                current = current.next;
            tempSize--;
        }
        return null;
    }

    //删除指定的元素，删除成功返回true
    public boolean deleteNode(Object obj) {
        if (size == 0) return false;
        Node current = head;//当前指针
        Node previous = head;//前指针
        while (current.data != obj) {
            if (current.next == null) return false;
            else {
                previous = current;
                current = current.next;
            }
        }
        if (current == head) {
            head = current.next;
        } else {
            previous.next = current.next;
        }
        size--;
        return true;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        if (size > 0) {
            Node node = head;
            int tempSize = size;
            System.out.print("[");
            while (tempSize > 0) {
                if (node.next == null) System.out.println(node.data + "]");
                else System.out.println(node.data + "->");
                node = node.next;
                tempSize--;
            }
        } else if (size == 1) {
            System.out.println("[" + head.data + "]");
        } else {
            System.out.println("[]");
        }
    }
}
