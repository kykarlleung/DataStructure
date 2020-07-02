import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {
	
	int size = 0;
	TreeNode root;
	
	public BinarySearchTree() {
		root = null;
	}
	
	public void insert(T data){
		root = insertR(this.root, data);
	}
	
	public TreeNode<T> insertR(TreeNode root, T data){	
		if(root==null){
			root = new TreeNode<T>(data);
			this.size++;
			return root;
		}
			
		if(data.compareTo((T) root.val) < 0){
			root.left = insertR(root.left, data);
		}
		else if(data.compareTo((T) root.val) > 0){
			root.right = insertR(root.right, data);
		}
		return root;
	}
	
	public boolean isEmpty(){
		return this.size==0;
	}
	
	public void preOrder(){
		preOrderR(this.root);
		System.out.println();
	}
	
	public void preOrderR(TreeNode root){
		if(root==null)
			return;
		System.out.print(root.val + " ");
		preOrderR(root.left);
		preOrderR(root.right);
	}
	
	public void postOrder(){
		postOrderR(this.root);
		System.out.println();
	}
	
	public void postOrderR(TreeNode root){
		if(root==null)
			return;
		postOrderR(root.left);
		postOrderR(root.right);
		System.out.print(root.val + " ");
	}
	
	public void inOrder(){
		inOrderR(this.root);
		System.out.println();
	}
	
	public void inOrderR(TreeNode root){
		if(root==null)
			return;
		inOrderR(root.left);
		System.out.print(root.val + " ");
		inOrderR(root.right);
	}
	
	public T getMax(){
		TreeNode cur = root;
		if(cur==null)
			return null;
		while(cur.right != null){
			cur = cur.right;
		}
		return (T) cur.val;
	}
	
	public int getHeight(){
		return getHeightR(this.root);
	}
	
	public int getHeightR(TreeNode root){
		if(root==null)
			return 0;
		int left = 1 + getHeightR(root.left);
		int right = 1 + getHeightR(root.right);
		return Math.max(left, right);
	}
	
	public void BFS(){
		if(this.root==null)
			return;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(this.root);
		while(!q.isEmpty()){
			TreeNode temp = q.poll();
			System.out.print(temp.val + " ");
			if(temp.left!=null)
				q.offer(temp.left);
			if(temp.right!=null)
				q.offer(temp.right);
		}
		System.out.println();
	}
	
	public void DFSStack(){
		if(this.root==null)
			return;
		Stack<TreeNode> s = new Stack<>();
		s.push(this.root);
		while(!s.isEmpty()){
			TreeNode temp = s.pop();
			System.out.print(temp.val + " ");
			if(temp.right!=null)
				s.push(temp.right);
			if(temp.left!=null)
				s.push(temp.left);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) { 
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(); 
        System.out.println("isEmpty when Empty: " + tree.isEmpty());
        System.out.println("Max when empty: " + tree.getMax());
  
        /* Let us create following BST 
              50 
           /     \ 
          30      70 
         /  \    /  \ 
       20   40  60   80 
         			  \
         			  90 */
        tree.insert(50); 
        tree.insert(30); 
        tree.insert(20); 
        tree.insert(40); 
        tree.insert(70); 
        tree.insert(60); 
        tree.insert(80); 
        tree.insert(90);
  
        System.out.println("Tree size: " + tree.size);
        tree.inOrder(); //DFS
        tree.preOrder(); //DFS
        tree.postOrder(); //DFS
        System.out.println("Max: " + tree.getMax());
        System.out.println("Height: " + tree.getHeight());
        tree.BFS();
        tree.DFSStack(); //this is preOrder
    } 
}

