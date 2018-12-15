package com.zda.arithmetic.linked_list;

/**
 * 栈
 */
public class StackSingleLink {
    private SingleLinkedList link;
    public StackSingleLink(){
        link = new SingleLinkedList();
    }
    public void push(Object obj){
        link.addHead(obj);
    }
    public Object pop(){
      return link.deleteHead();
    }
}
