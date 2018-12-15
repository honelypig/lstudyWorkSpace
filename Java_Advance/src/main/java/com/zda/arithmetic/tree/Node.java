package com.zda.arithmetic.tree;

/**
 * 二叉树的节点
 * 有数据和左右节点
 */
public class Node {
     int  data;
     Node leftChild;
     Node rightChild;
    public Node(int  data){
        this.data=data;
        leftChild=null;
        rightChild=null;

    }
    public void display(){
        System.out.println(data);
    }
}
