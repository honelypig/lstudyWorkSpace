package com.zda.arithmetic.tree;
//https://www.cnblogs.com/ysocean/p/8032642.html

import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 　　①、路径：顺着节点的边从一个节点走到另一个节点，所经过的节点的顺序排列就称为“路径”。
 * <p>
 * 　　②、根：树顶端的节点称为根。一棵树只有一个根，如果要把一个节点和边的集合称为树，那么从根到其他任何一个节点都必须有且只有一条路径。A是根节点。
 * <p>
 * 　　③、父节点：若一个节点含有子节点，则这个节点称为其子节点的父节点；B是D的父节点。
 * <p>
 * 　　④、子节点：一个节点含有的子树的根节点称为该节点的子节点；D是B的子节点。
 * <p>
 * 　　⑤、兄弟节点：具有相同父节点的节点互称为兄弟节点；比如上图的D和E就互称为兄弟节点。
 * <p>
 * 　　⑥、叶节点：没有子节点的节点称为叶节点，也叫叶子节点，比如上图的A、E、F、G都是叶子节点。
 * <p>
 * 　　⑦、子树：每个节点都可以作为子树的根，它和它所有的子节点、子节点的子节点等都包含在子树中。
 * <p>
 * 　　⑧、节点的层次：从根开始定义，根为第一层，根的子节点为第二层，以此类推。
 * <p>
 * 　　⑨、深度：对于任意节点n,n的深度为从根到n的唯一路径长，根的深度为0；
 * <p>
 * 　　⑩、高度：对于任意节点n,n的高度为从n到一片树叶的最长路径长，所有树叶的高度为0；
 * <p>
 * 二叉排序树的性能取决于二叉树的层数：
 * 最好的情况是 O(logn)，存在于完全二叉排序树情况下，其访问性能近似于折半查找；
 * 最差时候会是 O(n)，比如插入的元素是有序的，生成的二叉排序树就是一个链表，这种情况下，需要遍历全部元素才行
 */


public class BinaryTree implements Tree {
    private Node root;

    @Override
    public Node find(int key) {
        Node current = root;
        while (current != null) {
            if (current.data > key) {
                current = current.rightChild;
            } else if (current.data < key) {
                current = current.leftChild;
            } else {
                return current;
            }
        }
        return null;
    }

    /**
     * 　　 要插入节点，必须先找到插入的位置。
     * 与查找操作相似，由于二叉搜索树的特殊性，待插入的节点也需要从根节点开始进行比较，小于根节点则与根节点左子树比较
     * ，反之则与右子树比较，直到左子树为空或右子树为空，则插入到相应为空的位置，
     * 在比较的过程中要注意保存父节点的信息 及 待插入的位置是父节点的左子树还是右子树，才能插入到正确的位置。
     *
     * @param data
     * @return
     */
    @Override
    public boolean insert(int data) {
        Node newNode = new Node(data);
        if (root == null) {
            root = newNode;
            return true;
        } else {
            Node current = root;//当前的
            Node parentNode = null;//父节点
            while (current != null) {
                parentNode = current;
                if (current.data > data) {
                    current = current.leftChild;//每次循环判断左边是否为空
                    if (current == null) {//左子节点为空，直接将新值插入到该节点
                        parentNode.leftChild = newNode;
                        return true;
                    }
                } else {
                    current = current.rightChild;
                    if (current == null) {//右子节点为空，直接将新值插入到该节点
                        parentNode.rightChild = newNode;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * 　遍历树是根据一种特定的顺序访问树的每一个节点。比较常用的有前序遍历，中序遍历和后序遍历。而二叉搜索树最常用的是中序遍历。
     * <p>
     * 　　①、中序遍历:左子树——》根节点——》右子树
     * <p>
     * 　　②、前序遍历:根节点——》左子树——》右子树
     * <p>
     * 　　③、后序遍历:左子树——》右子树——》根节点
     *
     * @param current
     */
    //中序遍历
    @Override
    public void infixOrder(Node current) {
        if (current != null) {
            infixOrder(current.leftChild);
            System.out.print(current.data + " ");
            infixOrder(current.rightChild);
        }

    }

    //前序遍历
    @Override
    public void preOrder(Node current) {
        if (current != null) {
            System.out.print(current.data + " ");
            preOrder(current.leftChild);
            preOrder(current.rightChild);
        }
    }

    //后序遍历
    @Override
    public void postOrder(Node current) {
        if (current != null) {
            postOrder(current.leftChild);
            postOrder(current.rightChild);
            System.out.print(current.data + " ");
        }
    }

    /**
     * 层次遍历
     * 1.对于不为空的结点，先把该结点加入到队列中
     * 2.从队中拿出结点，如果该结点的左右结点不为空，就分别把左右结点加入到队列中
     * 3.重复以上操作直到队列为空
     *
     * @param node
     */
    public void levelOrder(Node node) {
        if (node == null) return;
        LinkedList<Node> list = new LinkedList<>();
        list.add(node);
        Node current = node;
        while (!list.isEmpty()) {
            current = list.poll();
            System.out.print(current.data);
            if (current.leftChild != null) list.add(current.leftChild);
            if (current.rightChild != null) list.add(current.rightChild);
        }
    }

    /**
     * ●某节点没有左孩子，则一定无右孩子
     * ●若某节点缺左或右孩子，则其所有后继一定无孩子
     *
     * @param node
     * @return
     */
    public boolean checkFull(Node node) {
        if (node == null) return true;
        //采用层次遍历
        LinkedList<Node> list = new LinkedList<>();
        list.add(node);
        Node current = node;
        while (!list.isEmpty()) {
            current = list.poll();
            Node left = current.leftChild;
            Node right = current.rightChild;

            if ((left == null && right != null) || (left != null && right == null)) {//当只有其中一个不为null时返回错
                return false;
            }
            if (left != null || right != null) {
                list.offer(left);
                list.offer(right);
            }
        }
        return true;
    }

    @Override
    public Node findMax() {
        //如果大就往右边找
        Node current = root;
        Node maxNode = current;
        while (current != null) {
            maxNode = current;
            current = current.rightChild;
        }
        return maxNode;
    }

    public Boolean checkSymmetry(Node root) {
        return isSymmetrical(root, root);
    }

    public Boolean isSymmetrical(Node r1, Node r2) {
        if (r1 == null && r2 == null) {
            return true;
        }
        if (r1 == null || r2 == null) {
            return false;
        }
        return (r1.data == r2.data) && isSymmetrical(r1.leftChild, r2.rightChild) && isSymmetrical(r1.rightChild, r2.leftChild);
    }

    public boolean isSameTree(Node p, Node q) {
        if ((p == null && q != null) || (p != null && q == null)) {
            return false;
        }
        if (p != null && q != null) {
            if (!(p.data == q.data)) {
                return false;
            }
        }
        if (p == null && q == null) {

        } else {
            if (!isSameTree(p.leftChild, q.leftChild)) {
                return false;
            }
            if (!isSameTree(p.rightChild, q.rightChild)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Node findMin() {
        //如果小就往左边找
        Node current = root;
        Node minNode = current;
        while (current != null) {
            minNode = current;
            current = current.leftChild;
        }
        return minNode;
    }

    @Override
    public int getMaxDepth(Node node) {
        if (node == null) {
            return 0;
        } else {
            int left = getMaxDepth(node.leftChild);
            int right = getMaxDepth(node.rightChild);
            return 1 + Math.max(left, right);
        }
    }

    @Override
    public boolean delete(int key) {
        Node current = root;
        Node parent = root;//用来记录被删除点的父亲节点，当删除时，只要将父亲节点的子节点指向被删的子节点。存在三种情况
        boolean isLeftChild = false;//记录当前删除的节点是左还是右
        //查找删除值，找不到直接返回false
        while (current.data != key) {
            parent = current;
            if (current.data > key) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }
        //如果当前节点没有子节点
        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
            return true;

            //当前节点有一个子节点，右子节点
        } else if (current.leftChild == null && current.rightChild != null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
            return true;
            //当前节点有一个子节点，左子节点
        } else if (current.leftChild != null && current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
            return true;
        } else {
            //当前节点存在两个子节点
            Node successor = getSuccessor(current);
            if (current == root) {
                successor = root;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }

            successor.leftChild = current.leftChild;
        }
        return false;

    }

    /**
     * 获取后继节点
     *
     * @param delNode
     * @return
     */
    public Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.rightChild;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.leftChild;
        }
        //后继节点不是删除节点的右子节点，将后继节点替换删除节点
        if (successor != delNode.rightChild) {
            successorParent.leftChild = successor.rightChild;
            successor.rightChild = delNode.rightChild;
        }

        return successor;
    }

    public static void main(String args[]) {
        BinaryTree t = new BinaryTree();
        t.insert(4);
        t.insert(2);
        t.insert(5);
        t.insert(1);
        t.insert(3);
        t.insert(6);
        t.preOrder(t.root);
        System.out.println();
        t.infixOrder(t.root);
        System.out.println();
        t.postOrder(t.root);
        System.out.println();
        System.out.println(t.getMaxDepth(t.root));
        t.levelOrder(t.root);
        System.out.println();
        System.out.println(t.checkFull(t.root));
        t.insert(4);
        System.out.println(t.checkFull(t.root));
    }
}
