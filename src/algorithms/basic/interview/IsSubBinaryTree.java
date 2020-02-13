package algorithms.basic.interview;

/**
 * @description:输入两棵二叉树A,B,判断B是不是A的子结构（PS:约定空树不是任意一个树的子结构）
 * @author: za-hejin
 * @time: 2020/2/11 10:46
 */
public class IsSubBinaryTree {
    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        Node n7 = new Node(7);
        Node n8 = new Node(8);
        Node n9 = new Node(9);
        Node n10 = new Node(10);
        Node n11 = new Node(11);

        BinaryTree a = new BinaryTree(n1);
        BinaryTree b = new BinaryTree(n5);

        Node roota = a.root;
        Node rootb = b.root;

        roota.left = n2;
        roota.right = n3;
        roota.left.left = n4;
        roota.left.right = n5;
        roota.left.right.left = n6;
        roota.left.right.right = n7;
        roota.left.right.right.left = n10;
        roota.right.left = n8;
        roota.right.right = n9;

        rootb.left = n6;
        rootb.right = n7;
        rootb.right.left = n10;

        System.out.println(IsSubBinaryTree.isSubBinaryTree(a,b));

    }

    static class Node{
        int key;
        Node left;
        Node right;
        Node(int key){
            this.key = key;
        }
    }

    static class BinaryTree{
        Node root;

        BinaryTree(Node root){
            this.root = root;
        }

    }

    public static boolean isSubBinaryTree(BinaryTree a, BinaryTree b){
        if(a.root==null||b.root==null){
            return false;
        }
        if(a.root.key == b.root.key){
            return isSub(a.root,b.root);
        }else {
            return isSubBinaryTree(a.root.left,b.root)||isSubBinaryTree(a.root.right,b.root);
        }
    }

    private static boolean isSubBinaryTree(Node a, Node b) {
        if(a==null||b==null){
            return false;
        }
        if(a.key==b.key){
            return isSub(a,b);
        }else{
            return isSubBinaryTree(a.left,b)||isSubBinaryTree(a.right,b);
        }

    }

    private static boolean isSub(Node a, Node b) {
        if(a!=null&&b!=null){
            if(a.key==b.key){
                return isSub(a.left,b.left)&&isSub(a.right,b.right);
            }else {
                return false;
            }
        }else if(a==null&&b==null){
            return true;
        }else{
            return false;
        }
    }
}
