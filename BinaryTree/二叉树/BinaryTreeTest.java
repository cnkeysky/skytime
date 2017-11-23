package 二叉树;

public class BinaryTreeTest {

    public static void main(String[] args) {

//        String[] prelist2 = {"A","B","C","D","E","F"};
        String[] prelist2 = {"A","B","D","H","E","I","J","C","F","K","G"};
        String[] inlist2  = {"D","H","B","I","E","J","A","F","K","C","G"};
//        String[] inlist2  = {"B","C","A","E","F","D"};

        BinaryTree<String> bit = new BinaryTree<String>(prelist2,inlist2);
        BinaryTree<String> bt = new BinaryTree<String>(bit);

        System.out.println(bt.toString());
        System.out.println(bit.toString());
        bt.inorder();
        bit.inorder();
        bit.inorderTraverse();
        bt.inorderTraverse();

    }
}

