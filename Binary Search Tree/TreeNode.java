
public class TreeNode<T> {
	public T val;
	public TreeNode left, right;
	
	public TreeNode(){
		this(null, null, null);
	}
	
	public TreeNode(T val){
		this(val, null, null);
	}
	
	public TreeNode(T val, TreeNode left, TreeNode right){
		this.left = left;
		this.right = right;
		this.val = val;
	}
}
