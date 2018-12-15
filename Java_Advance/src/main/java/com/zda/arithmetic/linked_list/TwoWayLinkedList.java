package com.zda.arithmetic.linked_list;

/**
 * 双向链表
 */
public class TwoWayLinkedList {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        private Node pre;
        private Node next;
        private Object data;

        public Node(Object data) {
            this.data = data;
        }
    }

    public TwoWayLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public void addHead(Object data) {
        Node node = new Node(data);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            head.pre = node;
            node.next = head;
            head = node;
        }
        size++;
    }

    public void addTail(Object data) {
        Node node = new Node(data);
        if (size == 0) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        size++;
    }

    public Node deleteHead() {
        Node result = head;
        if (size != 0) {
            head = head.next;
            head.pre = null;
            size--;
        }
        return result;
    }
    public Node deleteTail() {
        Node result = tail;
        if (size != 0) {
            tail = tail.pre;
            tail.next = null;
            size--;
        }
        return result;
    }
    public int getSize(){
        return  size;
    }

    //判断链表是否为空
    public boolean isEmpty(){
        return size==0;
    }

    public void display(){
        Node current=head;
        if(size==0){
            System.out.println("[]");
        }else if(size==1){
            System.out.print("["+current.data+"]");
        }else{
            System.out.print("[");
          while (size>0){
              if(current.next==null){
                  System.out.print(current.data+"]");
              }else{
                  System.out.print(current.data+"->");
              }
              size--;
              current=current.next;
          }
        }
    }
    public static void main(String args[]){
        TwoWayLinkedList list=new TwoWayLinkedList();
        list.addTail("张三");
        list.addTail("李四");
        list.addTail("王五");
        list.addTail("赵六");
        list.display();
    }
}
