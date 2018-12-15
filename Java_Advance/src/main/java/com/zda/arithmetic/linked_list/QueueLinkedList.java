package com.zda.arithmetic.linked_list;

/**
 * 队列，使用双端列表实现队列
 */
public class QueueLinkedList {
    private DoublePointLinkedList dp;

    public QueueLinkedList() {
        dp = new DoublePointLinkedList();
    }

    public void insert(Object data) {
        dp.addHead(data);
    }

    public void delete() {
        dp.deleteHead();
    }

    public boolean isEmpty() {
        return dp.isEmpty();
    }

    public int getSize() {
        return dp.getSize();
    }

    public void display() {
        dp.display();
    }
}
