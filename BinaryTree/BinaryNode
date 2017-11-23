package 二叉树;

public class BinaryNode<T>{

    public T data;
    public BinaryNode<T> left,right;

    public BinaryNode(T data,BinaryNode<T> left,BinaryNode<T> right) {

        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BinaryNode(T data){

        this(data,null,null);
    }

    public BinaryNode(){

        this(null,null,null);
    }

    public boolean equals(Object obj){
//        if(this==obj)
//            return true;
//        if(!(obj instanceof BinaryNode<?>))
//            return false;
        return this==obj||obj instanceof BinaryNode&&this.data.equals(((BinaryNode<T>)obj).data);
    }
    public String toString(){

        return this.data.toString();
    }

    public boolean isLeaf(){

        return this.left==null&&this.right==null;
    }
}
