package com.zda.arithmetic.tree;

/**
     * https://www.cnblogs.com/ysocean/p/8004211.html
 * 红黑树在原有的二叉查找树基础上增加了如下几个要求：
 * <p>
 * 　　1.每个节点不是红色就是黑色的；
 * <p>
 * 　　2.根节点总是黑色的；
 * <p>
 * 　　3.如果节点是红色的，则它的子节点必须是黑色的（反之不一定）,(也就是从每个叶子到根的所有路径上不能有两个连续的红色节点)；
 * <p>
 * 　　4.从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）。
 * <p>
 * 　　从根节点到叶节点的路径上的黑色节点的数目称为黑色高度，规则 4 另一种表示就是从根到叶节点路径上的黑色高度必须相同。
 * <p>
 * 　　注意：新插入的节点颜色总是红色的，这是因为插入一个红色节点比插入一个黑色节点违背红-黑规则的可能性更小，
 * 原因是插入黑色节点总会改变黑色高度（违背规则4），但是插入红色节点只有一半的机会会违背规则3（因为父节点是黑色的没事，父节点是红色的就违背规则3）。
 * 另外违背规则3比违背规则4要更容易修正。当插入一个新的节点时，可能会破坏这种平衡性，那么红-黑树是如何修正的呢？
 */
public class RBTree<T extends Comparable<T>> {
    public RBNode<T> root;//根节点
    final private Boolean RED = true;
    final private Boolean BLACK = false;

    /*
     * 左旋示意图：对节点x进行左旋  (此时在p的左节点。也可以为右节点。是两种情况)
     *     p                       p
     *    /                       /
     *   x                       y
     *  / \                     / \
     * lx  y      ----->       x  ry
     *    / \                 / \
     *   ly ry               lx ly
     * 左旋做了三件事：
     * 1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
     * 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
     * 3. 将y的左子节点设为x，将x的父节点设为y
     */
    private void leftRotate(RBNode<T> x) {//x对应上面的x
        //1. 将y的左子节点赋给x的右子节点，并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        // 2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else {//如果x的parent不为空，即x不是root
            //判断x是在p的左还是右
            if (x == x.parent.left) {
                x.parent.left = y;
            } else {
                x.parent.right = y;
            }
        }
        //3. 将y的左子节点设为x，将x的父节点设为y
        y.left = x;
        x.parent = y;
    }

    /*
     * 右旋示意图：对节点y进行右旋
     *        p                   p
     *       /                   /
     *      y                   x
     *     / \                 / \
     *    x  ry   ----->      lx  y
     *   / \                     / \
     * lx  rx                   rx ry
     * 右旋做了三件事：
     * 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)（通过parent.left==y判断）
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    private void rightRotate(RBNode<T> y) {
        // 1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
        RBNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        //2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)（通过parent.left==y判断）
        x.parent = y.parent;
        if (y.parent == null) {
            this.root = x;
        } else {
            if (y.parent.left == y) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        //3. 将x的右子节点设为y，将y的父节点设为x
        x.right = y;
        y.parent = x;
    }

    public RBNode<T> find(T key) {
        RBNode<T> node = new RBNode<T>(RED, key, null, null, null);
        RBNode<T> current = root;
        while (current != null) {
            int cmp = node.key.compareTo(current.key);
            if (cmp > 0) {
                current = current.right;
            } else if (cmp < 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }

    public boolean insert(T data) {
        RBNode<T> node = new RBNode<T>(RED, data, null, null, null);
        return insert(node);
    }

    private boolean insert(RBNode<T> node) {
        RBNode<T> current = null;//表示最后node的父节点
        RBNode<T> x = this.root;//用来向下搜索
        //1.找到插入位置
        while (x != null) {
            current = x;
            int cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        node.parent = current;//找到了插入的位置，将当前current作为node的父节点
        //2.接下来判断node是左子节点还是右子节点
        if (current != null) {
            int cmp = node.key.compareTo(current.key);
            if (cmp < 0) {
                current.left = node;
            } else {
                current.right = node;
            }
        } else {
            this.root = node;
        }
        //3.利用旋转操作将其修正为一颗红黑树
        insertFixUp(node);
        return false;
    }

    //插入后可能会导致树的不平衡，insertFixUp(node) 方法里主要是分情况讨论，分析何时变色，何时左旋，何时右旋。
    private void insertFixUp(RBNode<T> node) {
        RBNode<T> parent, gparent;//定义父节点和祖父节点

        //需要修正的条件：父节点存在，且父节点的颜色是红色
        while (((parent = parentOf(node)) != null) && isRed(parent)) {
            gparent = parentOf(parent);//获得祖父节点

            //若父节点是祖父节点的左子节点，下面的else相反
            if (parent == gparent.left) {
                RBNode<T> uncle = gparent.right;//获得叔叔节点

                //case1:叔叔节点也是红色
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);//把父节点和叔叔节点涂黑
                    setBlack(gparent);
                    setRed(gparent);//把祖父节点涂红
                    node = gparent;//把位置放到祖父节点处
                    continue;//继续while循环，重新判断
                }

                //case2:叔叔节点是黑色，且当前节点是右子节点
                if (node == parent.right) {
                    leftRotate(parent);//从父节点出左旋
                    RBNode<T> tmp = parent;//然后将父节点和自己调换一下，为下面右旋做准备
                    parent = node;
                    node = tmp;
                }

                //case3:叔叔节点是黑色，且当前节点是左子节点
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            } else {//若父节点是祖父节点的右子节点，与上面的情况完全相反，本质是一样的
                RBNode<T> uncle = gparent.left;

                //case1:叔叔节点也是红色的
                if (uncle != null && isRed(uncle)) {
                    setBlack(parent);
                    setBlack(uncle);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }

                //case2:叔叔节点是黑色的，且当前节点是左子节点
                if (node == parent.left) {
                    rightRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }

                //case3:叔叔节点是黑色的，且当前节点是右子节点
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);
            }
        }
        setBlack(root);//将根节点设置为黑色
    }

    private RBNode<T> parentOf(RBNode<T> node) {
        if (node == null) return null;
        return node.parent;
    }

    private boolean isRed(RBNode<T> node) {
        return node.color;
    }

    private void setRed(RBNode<T> node) {
        root.color = true;
    }

    private void setBlack(RBNode<T> node) {
        root.color = false;
    }

    public void infixOrder(RBNode<T> current) {
        if (current != null) {
            infixOrder(current.left);
            System.out.println(current.key);
            infixOrder(current.right);
        }
    }

    public void preOrder(RBNode<T> current) {
        if (current != null) {
            System.out.println(current.key);
            preOrder(current.left);
            preOrder(current.right);
        }
    }

    public void postOrder(RBNode<T> current) {
        if (current != null) {
            postOrder(current.left);
            postOrder(current.right);
            System.out.println(current.key);
        }
    }

    public RBNode<T> findMax() {
        RBNode<T> current = root;
        RBNode<T> maxNode = root;
        while (current != null) {
            maxNode = current;
            current = current.right;
        }
        return maxNode;
    }

    public RBNode<T> findMin() {
        RBNode<T> current = root;
        RBNode<T> minNode = root;
        while (current != null) {
            minNode = current;
            current = current.left;
        }
        return minNode;
    }

    public boolean delete(T key) {
        return false;
    }

    public static void main(String args[]) {
        RBTree<Integer> rb = new RBTree<>();

    }
}
