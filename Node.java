package 二叉树;

public class Node<T>{
	
	public T data;
	public Node<T> next;

	public Node(T data, Node<T> next){
	
		this.next = next;
		this.data = data;
	}

	public Node(){
	
		this(null,null);
	}
}
