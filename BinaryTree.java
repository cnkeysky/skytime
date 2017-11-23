package 二叉树;

public class BinaryTree<T> {

    public BinaryNode<T> root;  //根结点，结点结构为二叉链表
    private int tlen;
    //构造空二叉树
    public BinaryTree(){
        this.root=new BinaryNode<T>();
    }
    //拷贝构造，递归实现
//    public BinaryTree(BinaryTree<T> bitree){
//
//        this.root = copy(bitree.root);
//    }
    private BinaryNode<T> copy(BinaryNode<T> bitree){

        if(bitree==null)
            return null;
        BinaryNode<T> p = new BinaryNode<T>(bitree.data);
        p.left = copy(bitree.left);
        p.right = copy(bitree.right);
        return p;
    }
    //拷贝构造，非递归实现。本类的实现的想法来自以先根遍历二叉树的非递归方法。
    public BinaryTree(BinaryTree<T> p){

        this(); //构造空二叉树
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();
        LinkedStack<BinaryNode<T>> stack1 = new LinkedStack<BinaryNode<T>>();
        BinaryNode<T> p1 = p.root;
        BinaryNode<T> p2 = this.root;
        if(p1.left==null&&p1.right==null)
            p2.data = p1.data;
        else {
            while(p1!=null||!stack.isEmpty()) {
                if(p1!=null) {
                    p2.data = p1.data;
                    //结点入栈
                    stack1.push(p2);
                    stack.push(p1);
                    p1 = p1.left;
                    //p1结点不为空，构建新的结点
                    if (p1 != null) {
                        BinaryNode<T> p3 = new BinaryNode<T>();
                        //p2左结点为新的结点
                        p2.left = p3;
                        //p2指向左结点
                        p2 = p2.left;
                    }
                }else {
                    //结点出栈
                    p1 = stack.pop();
                    //指向右结点
                    p1 = p1.right;
                    p2 = stack1.pop();
                    //p1结点不为空，则构建新结点
                    if(p1!=null){
                        BinaryNode<T> p3 = new BinaryNode<T>();
                        p2.right = p3;
                        p2 = p2.right;
                    }
                }
            }
        }
    }

    //以先根构造二叉树
    public BinaryTree(T[] prelist){

        this.root = create(prelist);
    }
    private int i = 0;
    private BinaryNode<T> create(T[] prelist){

        BinaryNode<T> p = null;
        if(i<prelist.length){

            T elem = prelist[i];
            i++;
            if(elem!=null){

                p = new BinaryNode<T>(elem);
                p.left = create(prelist);
                p.right = create(prelist);
            }
        }
        return p;
    }
    //以先跟和中根序列构造二叉树
    public BinaryTree(T prelist[],T inlist[]){

        this.root = create(prelist,inlist,0,0,prelist.length);
        this.tlen = prelist.length;
    }
    private BinaryNode<T> create(T[] prelist, T[] inlist, int preStart, int inStart, int n){

//        System.out.print("prelist:");
//        print(prelist, preStart, n);
//        System.out.print(" inlist:");
//        print(inlist, inStart, n);
//        System.out.println();

        if (n<=0)
            return null;
        //根结点值
        T elem=prelist[preStart];
        //创建叶子结点
        BinaryNode<T> p=new BinaryNode<T>(elem);
        int i=0;
        //在中根序列中查找根值所在位置
        while (i<n && !elem.equals(inlist[inStart+i]))
            i++;
        //创建左子树
        p.left = create(prelist, inlist, preStart+1, inStart, i);
        p.right = create(prelist, inlist, preStart+i+1, inStart+i+1, n-1-i);
        return p;
    }
    private void print(T[] table, int start, int n) {

        for (int i=0; i<n; i++)
            System.out.print(table[start+i]);
    }

    //返回先根次序遍历二叉树所有结点的描述字符串
    public String toString() {

        return toString(this.root);
    }
    //返回先根次序遍历以p为根的子树描述字符串，递归算法
    private String toString(BinaryNode<T> p) {
        if (p==null)
            return "^";
        //递归调用
        return p.data.toString() +" " + toString(p.left) + toString(p.right);
    }
    //判断二叉树是否空
    public boolean isEmpty(){

        return this.root==null;
    }
    //求二叉树的结点数
    public int size(){

        return size(this.root);
    }
    private int size(BinaryNode<T> p){

        if(p==null)
            return 0;
        return 1+size(p.left)+size(p.right);
    }
    //求二叉树的高度,递归算法
    public int height(){

        return height(this.root);
    }
    private int height(BinaryNode<T> p){

        if(p==null)
            return 0;
        int lh = height(p.left);
        int rh = height(p.right);
        return (lh>rh)?lh+1:rh+1;
    }
    //以先根遍历二叉树，递归算法
    public void preorder(){

        preorder(this.root);
        System.out.println();
    }
    private void preorder(BinaryNode<T> p){

        if(p!=null){

            System.out.print(p.data.toString()+" ");
            preorder(p.left);
            preorder(p.right);
        }
    }
    //以中根遍历二叉树，递归算法
    public void inorder(){

        inorder(this.root);
        System.out.println();
    }
    private void inorder(BinaryNode<T> p){

        if(p!=null){
            inorder(p.left);
            System.out.print(p.data.toString()+" ");
            inorder(p.right);
        }
    }
    //以后根遍历二叉树，递归算法
    public void postorder(){
        postorder(this.root);
        System.out.println();
    }
    private void postorder(BinaryNode<T> p){

        if(p!=null){
            postorder(p.left);
            postorder(p.right);
            System.out.println(p.data.toString()+" ");
        }
    }

    //以先根遍历，非递归算法
    public void preorderTravese(){
        //创建空栈
        LinkedStack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();
        BinaryNode<T> p = this.root;
        //p非空或栈非空时
        while(p!=null||!stack.isEmpty()){

            if(p!=null){

                System.out.print(p.data+" ");
                //p结点入栈
                stack.push(p);
                //访问左子树
                p = p.left;
            }else{

                //p指向出站结点
                p = stack.pop();
                p = p.right;
            }
        }
        System.out.println();
    }
    //以中根遍历，非递归算法
    public void inorderTraverse(){

        LinkedStack<BinaryNode<T>> stack = new LinkedStack<BinaryNode<T>>();
        BinaryNode<T> p = this.root;
        while (p!=null || !stack.isEmpty())
            if (p!=null) {
                stack.push(p);
                p=p.left;
            }
            else {
                p=stack.pop();
                System.out.print(p.data+" ");
                p=p.right;
            }
        System.out.println();
    }
}
