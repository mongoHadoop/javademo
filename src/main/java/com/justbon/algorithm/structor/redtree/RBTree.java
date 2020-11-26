package com.justbon.algorithm.structor.redtree;

/**
 * @author ganli
 * @version 1.0
 * @file RBTree.java
 * @Modified By：
 * @date 2020-11-26 上午11:27
 * @description
 *
 * 红黑树特点
 * 1、每个节点不是红色就是黑色的；
 * 2、根节点总是黑色的；
 * 3、所有的叶节点都是是黑色的（红黑树的叶子节点都是空节点（NIL或者NULL））；
 * 4、如果节点是红色的，则它的子节点必须是黑色的（反之不一定）；
 * 5、从根节点到叶节点或空子节点的每条路径，必须包含相同数目的黑色节点（即相同的黑色高度）。
 * 需要注意：
 * 特性3中的叶子节点，是只为空(NIL或null)的节点。
 * 特性5，确保没有一条路径会比其他路径长出俩倍。因而，红黑树是相对是接近平衡的二叉树.
 * 红黑树的修正
 * 变色、左旋、右旋是红黑树在二叉树上的扩展操作,
 * 同时也是基于这三个操作才能遵守红黑树的五个特性。所以熟悉二叉树操作的同学只要掌握这红黑
 * 树这三个操作那么就能更加容易的理解进行红黑树的添加和删除之后怎么保证其平衡性,
 * 不熟悉二叉树的也可以先看看《二叉树知识点回忆以及整理》这片文章。
 */
public class RBTree <T extends Comparable<T>> {
    public RBNode<T> mRoot = null;
    // 根结点
    public static boolean RED = true;
    public static boolean BLACK = false;

    class RBNode <T extends Comparable<T>> {
        //颜色
        boolean color;
        //关键字（键值）
        T key;
        //左子节点
        RBNode<T> left;
        //右子节点
        RBNode<T> right;
        //父节点
        RBNode<T> parent;

        public RBNode(T key, boolean color, RBNode<T> parent, RBNode<T> left, RBNode<T> right) {
            this.key = key;
            this.color = color;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        public T getKey() {
            return key;
        }

        @Override
        public String toString() {
            return "" + key + (this.color == RED ? "RED" : "BLACK");
        }
    }


    /*************对红黑树节点x进行左旋操作 ******************
     * 左旋示意图：对节点x进行左旋
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
    public void leftRotate(RBNode<T> x) {
        if (x == null) return;
        //1. 将y的左子节点赋给x的右子节点,并将x赋给y左子节点的父节点(y左子节点非空时)
        RBNode<T> y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        //2. 将x的父节点p(非空时)赋给y的父节点，同时更新p的子节点为y(左或右)
        y.parent = x.parent;
        if (x.parent == null) {
            //mRoot是RBTree的根节点
            this.mRoot = y;
        } else {
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

    /*************对红黑树节点y进行右旋操作 ****************
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
     * 2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
     * 3. 将x的右子节点设为y，将y的父节点设为x
     */
    public void rightRotate(RBNode<T> y) {
        if (y == null) return;
        //1. 将x的右子节点赋给y的左子节点,并将y赋给x右子节点的父节点(x右子节点非空时)
        RBNode<T> x = y.left;
        y.left = x.right;
        if (x.right != null) {
            x.right.parent = y;
        }
        //2. 将y的父节点p(非空时)赋给x的父节点，同时更新p的子节点为x(左或右)
        x.parent = y.parent;
        if (y.parent == null) {
            this.mRoot = x;
        } else {
            if (y == y.parent.left) {
                y.parent.left = x;
            } else {
                y.parent.right = x;
            }
        }
        //3. 将x的右子节点设为y，将y的父节点设为x
        x.right = y;
        y.parent = x;
    }

    /*********************** 向红黑树中插入节点 **********************/
    public void insert(T key) {
        RBNode<T> node = new RBNode<>(key, BLACK, null, null, null);
        insert(node);
    }

    /**
     * 1、将节点插入到红黑树中，这个过程与二叉搜索树是一样的
     * 2、将插入的节点着色为"红色"；将插入的节点着色为红色，不会违背"特性5"！
     *    少违背了一条特性，意味着我们需要处理的情况越少。
     * 3、通过一系列的旋转或者着色等操作，使之重新成为一颗红黑树。
     * @param node 插入的节点
     */
    public void insert(RBNode<T> node) {
        //node的父节点
        RBNode<T> current = null;
        RBNode<T> x = mRoot;

        while (x != null) {
            current = x;
            int cmp = node.key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }
        //找到位置，将当前current作为node的父节点
        node.parent = current;
        //2. 接下来判断node是插在左子节点还是右子节点
        if (current != null) {
            int cmp = node.key.compareTo(current.key);
            if (cmp < 0) {
                current.left = node;
            } else {
                current.right = node;
            }
            node.color = RED;
            insertFixUp(node);
        } else {
            this.mRoot = node;
        }
    }

    /**
     * 修改整插入node节点之后的红黑树
     * @param node
     */
    public void insertFixUp(RBNode<T> node) {
        //定义父节点和祖父节点
        RBNode<T> parent, gparent;
        //需要修整的条件：父节点存在，且父节点的颜色是红色
        while (((parent = node.parent) != null) && isRed(parent)) {
            //祖父节点
            gparent = parent.parent;
            //若父节点是祖父节点的左子节点
            if (parent == gparent.left) {
                //获取叔叔点点
                RBNode<T> uncle = gparent.right;
                //case1:叔叔节点是红色
                if (uncle != null && isRed(uncle)) {
                    //把父亲和叔叔节点涂黑色
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    //把祖父节点图红色
                    gparent.color = RED;
                    //将位置放到祖父节点
                    node = gparent;
                    //继续往上循环判断
                    continue;
                }

                //case2：叔叔节点是黑色，且当前节点是右子节点
                if (node == parent.right) {
                    //从父亲即诶单处左旋
                    leftRotate(parent);
                    //将父节点和自己调换一下，为右旋左准备
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //case3：叔叔节点是黑色，且当前节点是左子节点
                parent.color = BLACK;
                gparent.color = RED;
                rightRotate(gparent);
            } else {
                //若父亲节点是祖父节点的右子节点，与上面的完全相反，本质一样的
                RBNode<T> uncle = gparent.left;
                //case1:叔叔节点也是红色
                if (uncle != null & isRed(uncle)) {
                    parent.color = BLACK;
                    uncle.color = BLACK;
                    gparent.color = RED;
                    node = gparent;
                    continue;
                }

                //case2: 叔叔节点是黑色的，且当前节点是左子节点
                if (node == parent.left) {
                    rightRotate(parent);
                    RBNode<T> tmp = parent;
                    parent = node;
                    node = tmp;
                }
                //case3: 叔叔节点是黑色的，且当前节点是右子节点
                parent.color = BLACK;
                gparent.color = RED;
                leftRotate(gparent);
            }
        }
        //将根节点设置为黑色
        this.mRoot.color = BLACK;
    }
    private boolean isRed( RBNode<T> node) {
        return ((node!=null)&&(node.color==RED)) ? true : false;
    }
    private boolean isBlack( RBNode<T> node) {
        return !isRed(node);
    }
    /*********************** 删除红黑树中的节点 **********************/
    public void remove(T key) {
        RBNode<T> node;
        if ((node = search(mRoot, key)) != null) {
            remove(node);
        }
    }

    /*
     * (递归实现)查找"红黑树x"中键值为key的节点
     */
    private RBNode<T> search(RBNode<T> x, T key) {
        if (x==null){
            return x;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return search(x.left, key);
        }
        else if (cmp > 0){
            return search(x.right, key);
        } else{
            return x;
        }

    }


    /**
     * 1、被删除的节点没有儿子，即删除的是叶子节点。那么，直接删除该节点。
     * 2、被删除的节点只有一个儿子。那么直接删除该节点，并用该节点的唯一子节点顶替它的初始位置。
     * 3、被删除的节点有两个儿子。那么先找出它的后继节点（右孩子中的最小的，该孩子没有子节点或者只有一右孩子）。
     *    然后把"它的后继节点的内容"复制给"该节点的内容"；之后，删除"它的后继节点"。
     *    在这里后继节点相当与替身，在将后继节点的内容复制给"被删除节点"之后，再将后继节点删除。
     *    ------这样问题就转化为怎么删除后继即节点的问题？
     *    在"被删除节点"有两个非空子节点的情况下，它的后继节点不可能是双子都非空。
     *    注：后继节点为补充被删除的节点；
     *    即：意味着"要么没有儿子，要么只有一个儿子"。
     *    若没有儿子，则回归到（1）。
     *    若只有一个儿子，则回归到（2）。
     *
     * @param node  需要删除的节点
     */
    public void remove(RBNode<T> node) {
        RBNode<T> child, parent;
        boolean color;
        //1、删除的节点的左右孩子都不为空
        if ((node.left != null) && (node.right != null)) {
            //先找到被删除节点的后继节点，用它来取代被删除节点的位置
            RBNode<T> replace = node;
            //1).获取后继节点[右孩子中的最小]
            replace = replace.right;
            while (replace.left != null) {
                replace = replace.left;
            }
            //2).处理【后继节点的子节点】和【被删除节点的子节点】之间的关系
            if (node.parent != null) {
                //要删除的节点不是根节点
                if (node == node.parent.left) {
                    node.parent.left = replace;
                } else {
                    node.parent.right = replace;
                }
            } else {
                mRoot = replace;
            }

            //3).处理【后继节点的子节点】和【被删除节点的子节点】之间的关系
            //后继节点肯定不存在左子节点
            child = replace.right;
            parent = replace.parent;
            //保存后继节点的颜色
            color = replace.color;
            //后继节点是被删除的节点
            if (parent == node) {
                parent =replace;
            } else {
                if (child != null) {
                    child.parent = parent;
                }
                parent.left = child;
                replace.right = node.right;
                node.right.parent = replace;
            }
            replace.parent = node.parent;
            replace.color = node.color;
            replace.left = node.left;
            node.left.parent = replace;
            //4。如果移走的后继节点颜色是黑色，重新修正红黑树
            if (color == BLACK) {
                removeFixUp(child, parent);
            }
            node = null;
        } else {
            //被删除的节点是叶子节点，或者只有一个孩子。
            if (node.left != null) {
                child = node.left;
            } else {
                child = node.right;
            }
            parent = node.parent;
            //保存"取代节点"的颜色
            color = node.color;
            if (child != null) {
                child.parent = parent;
            }
            //"node节点"不是根节点
            if (parent != null) {
                if (parent.left == node) {
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } else {
                mRoot = child;
            }
            if (color == BLACK) {
                removeFixUp(child, parent);
            }
            node = null;
        }
    }
    /**
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     * 如果当前待删除节点是红色的，它被删除之后对当前树的特性不会造成任何破坏影响。
     * 而如果被删除的节点是黑色的，这就需要进行进一步的调整来保证后续的树结构满足要求。
     * 这里我们只修正删除的节点是黑色的情况：
     *
     * 调整思想：
     * 为了保证删除节点的父节点左右两边黑色节点数一致，需要重点关注父节点没删除的那一边节点是不是黑色。
     * 如果删除后父亲节点另一边比删除的一边黑色多，就要想办法搞到平衡。
     * 1、把父亲节点另一边（即删除节点的兄弟树）其中一个节点弄成红色，也少了一个黑色。
     * 2、或者把另一边多的节点（染成黑色）转过来一个
     *
     * 1）、当前节点是黑色的，且兄弟节点是红色的（那么父节点和兄弟节点的子节点肯定是黑色的）；
     * 2）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的两个子节点均为黑色的；
     * 3）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的左子节点是红色，右子节点时黑色的；
     * 4）、当前节点是黑色的，且兄弟节点是黑色的，且兄弟节点的右子节点是红色，左子节点任意颜色。
     *
     * 以上四种情况中，2，3，4都是（当前节点是黑色的，且兄弟节点是黑色的）的子集。
     *
     * 参数说明：
     * @param node 删除之后代替的节点（后序节点）
     * @param parent 后序节点的父节点
     */
    private void removeFixUp(RBNode<T> node, RBNode<T> parent) {
        RBNode<T> other;
        RBNode<T> root = mRoot;
        while ((node == null || node.color == BLACK) && node != root) {
            if (parent.left == node) {
                other = parent.right;
                if (other.color == RED) {
                    //case 1：x的兄弟w是红色的【对应状态1，将其转化为2，3，4】
                    other.color = BLACK;
                    parent.color = RED;
                    leftRotate(parent);
                    other = parent.right;
                }

                if ((other.left == null || other.left.color == BLACK)
                        && (other.right == null || other.right.color == BLACK)) {
                    //case 2：x的兄弟w是黑色，且w的两个孩子都是黑色的【对应状态2，利用调整思想1网树的根部做递归】
                    other.color = RED;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (other.right == null || other.right.color == BLACK) {
                        //case 3:x的兄弟w是黑色的，并且w的左孩子是红色的，右孩子是黑色的【对应状态3，调整到状态4】
                        other.left.color = BLACK;
                        other.color = RED;
                        rightRotate(other);
                        other = parent.right;
                    }
                    //case 4:x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色【对应状态4，利用调整思想2做调整】
                    other.color = parent.color;
                    parent.color = BLACK;
                    other.right.color = BLACK;
                    leftRotate(parent);
                    node = root;
                    break;
                }
            } else {
                other = parent.left;
                if (other.color == RED) {
                    //case 1:x的兄弟w是红色的
                    other.color = BLACK;
                    parent.color = RED;
                    rightRotate(parent);
                    other = parent.left;
                }

                if ((other.left == null || other.left.color == BLACK)
                        && (other.right == null || other.right.color == BLACK)) {
                    //case 2:x兄弟w是黑色，且w的两个孩子也都是黑色的
                    other.color = RED;
                    node = parent;
                    parent = node.parent;
                } else {
                    if (other.left == null || other.left.color == BLACK) {
                        //case 3:x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        other.right.color = BLACK;
                        other.color = RED;
                        leftRotate(other);
                        other = parent.left;
                    }
                    //case 4:x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    other.color = parent.color;
                    parent.color = BLACK;
                    other.left.color = BLACK;
                    rightRotate(parent);
                    node = root;
                    break;
                }
            }
        }
        if (node != null) {
            node.color = BLACK;
        }
    }
}
