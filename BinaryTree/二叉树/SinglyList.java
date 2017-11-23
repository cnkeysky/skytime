package 二叉树;

public class SinglyList<T> {

	public Node<T> head; //头指针
	private int n = 0;  //计算链表长度

	public SinglyList(){ //构造空单链表

		this.head = new Node<T>();
	}

	public SinglyList(T[] values){  //构造单链表，由values提供元素

		this();   //构造空单链表
		Node<T> rear = this.head;
		for(int i=0;i<values.length;i++){

			rear.next = new Node<T>(values[i],null);
			rear = rear.next;
			n++; //链表长度
		}
	}

	public SinglyList(SinglyList<T> list){   //深拷贝

		this();
		Node<T> rear = this.head;//rear指向单链表最后一个结点
		for(Node<T> p = list.head.next; p!=null; p=p.next){

			rear.next = new Node<T>(p.data,null);
			rear = rear.next;
		}
		this.n = list.n;  //确定深拷贝的长度
	}

	public String toString(){

		String str = this.getClass().getName()+"(";
		for(Node<T> p = this.head.next; p!=null; p=p.next){

			str += p.data.toString();
			if(p.next!=null)
				str += ",";
		}
		return str+")";
	}

	public boolean equals(Object obj){

		if(this == obj)
			return true;
		if(!(obj instanceof SinglyList<?>))
			return false;
		Node<T> p1 = this.head.next;  //头结点的下一个结点
		Node<T> p2 = ((SinglyList<T>)obj).first();
		while(p1!=null&&p2!=null&&p1.data.equals(p2.data)){

			p1 = p1.next;
			p2 = p2.next;
		}
		return p1==null&&p2==null;
	}

	public boolean search(T data){

		for(Node<T> p = this.head.next; p!=null; p=p.next){

			if(data.equals(p.data))
				return true;
		}
		return false;
	}
	//判断是否是子集
	public boolean containsAll(SinglyList<T> list){

		boolean bl = false;
		//若给定链表长度>this.n则返回false
		if(this.n < list.n)
			return bl;
		//比较类型是否一致
		if(!(list instanceof SinglyList<?>))
			return bl;
		Node<T> p = list.head.next;
		while(p!=null){
			bl = this.search(p.data);  //判断list中的数据是否在this中
			if(bl == true)
				p = p.next;
			else
				return false;
		}
		return true;
	}

	public boolean isEmpty(){  //判断是否为空

		return this.head.next == null;
	}

	public Node<T> first(){  //返回单链表头结点的下一个结点

		return this.head.next;
	}

	public Node<T> last(){ //返回最后一个结点

		Node<T> p = this.head.next;
		while(p!=null&&p.next!=null)
			p = p.next;
		return p;
	}

	public boolean addAll(int i, SinglyList<T> list){

		if(list.isEmpty()==true||this.isEmpty()==true)
			return false;
		Node<T> p = this.first();
		Node<T> p1 = this.first();
		Node<T> p2 = this.last();
		Node<T> p3 = list.first();
		Node<T> p4 = list.last();
		if(i<0) {     //i<0头插入

			p4.next = p1;
			this.head = p;
		}
		else
		if(i>this.n) //i>长度，尾插入
			p2.next = p3;
		else{              //中间插入
			for(int j=0;j<i-1;j++)
				p = p.next;
			p4.next = p.next;
			p.next = p3;
		}
		this.n += list.n;
		return true;
	}

	public SinglyList<T> union(SinglyList<T> list){

		SinglyList<T> result = new SinglyList<T>(this); //深拷贝
		result.addAll(this.n+1,list); //尾插入
		return result;
	}

	public SinglyList<T> subList(int i, int n){

		if(i>this.n||(i+n-1)>this.n)
			return null;
		SinglyList<T> first = new SinglyList<T>(this);  //深拷贝
		Node<T> p = first.head.next;
		for(int j=0;j<i;j++)  //获取子链表头结点的下一个结点
			p = p.next;
		first.head.next = p;
		for(int j=1;j<n;j++) //获取子链表的尾结点
			p = p.next;
		p.next = null;
		return first;
	}

	public void replaceAll(SinglyList<T> pattern, SinglyList<T> list){

		SinglyList<T> result1 = new SinglyList<T>(list);//执行深拷贝
		SinglyList<T> thisResult = new SinglyList<T>(this);//执行深拷贝
		Node<T> p1;
		Node<T> p2;

		Node<T> p3 = this.head;
		Node<T> p4 = this.head;

		int m = 0; //替换的次数
		if(pattern.isEmpty()||list.isEmpty())
			throw new NullPointerException("pattern或list为空");
		if(this.equals(pattern)) { // this 与 pattern 相等
			this.head = result1.head;
			this.n += (pattern.n-list.n);

		}
		else {
			for(int i=0;i+pattern.n-1<thisResult.n;i++) {

				SinglyList<T> result = new SinglyList<T>(list);//执行深拷贝 **很重要**
				p1 = result.first();//获取list拷贝后的链表头结点的下一个结点
				p2 = result.last();  //获取list拷贝后的链表的尾结点

				if(thisResult.subList(i, pattern.n).equals(pattern)){ //判断thisResult中与pattern子链表


					for(int j=0;j<pattern.n+1;j++)
						p3 = p3.next;  //要匹配的尾结点

					p2.next = p3;
//						if(i==0)
//							this.head.next = p1;
//						else
					p4.next = p1; //p4的下一个结点为p1，即要替换的首结点
					p3 = p2; //匹配后的尾结点的下一个结点
					p4 = p2; //匹配后的尾结点的下一个结点
					m++;
					i += (pattern.n-1); //执行下一次匹配
				}else {
					p3 = p3.next;
					p4 = p4.next;
				}
			}
			this.n += m*(list.n-pattern.n);
		}
	}

	public Node<T> insert(int i,T x){

		if(x==null)
			throw new NullPointerException("x为空");
		Node<T> front = this.head;
		for(int j=0;front.next!=null&&j<i;j++)
			front = front.next;
		front.next = new Node<T>(x,front.next);
		return front.next;
	}

	public T get(int i){

		Node<T> p = this.head.next;
		for(int j=0;p!=null&&j<i;j++)
			p = p.next;
		return (i>=0&&p!=null)?p.data:null;
	}

	public T remove(int i){

		Node<T> front=this.head;
		for (int j=0;  front.next!=null && j<i;  j++)
			front = front.next;
		if (i>=0 && front.next!=null) {
			T old = front.next.data;
			front.next = front.next.next;
			return old;
		}
		return null;
	}
}

