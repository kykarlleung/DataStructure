import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
//https://www.geeksforgeeks.org/avl-tree-set-1-insertion/

class Node { 
    int val;
    int height; 
    Node left, right; 
    
    Node(){
    	this(0);
    }
    
    Node(int d) { 
        this.val = d; 
        this.height = 1; 
        this.left = null;
        this.right = null;
    } 
} 
  
public class AVLTree { 
  
    Node root; 
    
    AVLTree(){
    	this.root = null;
    }
    
    AVLTree(int i){
    	insert(i);
    }
    
    public int height(Node node) { 
        if (node == null) 
            return 0; 
        return node.height; 
    }
  
//    a, b, c are subtrees of the tree 
//    rooted with y (on the left side) or x (on 
//    the right side)          
//
//		x y are most important
//    
//        [y]                              [x]
//        / \     Right Rotation          /  \
//       x    c   - - - - - - - >        a    y 
//      / \       < - - - - - - -            / \
//     a   b      Left Rotation             b   c
    
    
    private Node leftRotate(Node x) {
        Node y = x.right;
        //Node a = x.left;
        Node b = y.left; 
        //Node c = y.right;
        
        y.left = x; 
        //y.right = c;   //**Already is, no need to perform this
        //x.left = a;	//**Already is, no need to perform this
        x.right = b; 
  
        x.height = Math.max(height(x.left), height(x.right)) + 1; 
        y.height = Math.max(height(y.left), height(y.right)) + 1; 
        
        return y; 
    } 

    private Node rightRotate(Node y) { 
        Node x = y.left; 
        Node b = x.right; 
        
        x.right = y; 
        y.left = b; 
  
        y.height = Math.max(height(y.left), height(y.right)) + 1; 
        x.height = Math.max(height(x.left), height(x.right)) + 1; 
        
        return x; 
    } 

    public int getBalance(Node node) { // result>1: left-heavy ||  result < -1: right-heavy
        if (node == null) 
            return 0; 
        return height(node.left) - height(node.right); 
    } 
    
    public void insert(int data){
    	this.root = insert(this.root, data);
    }
    
    public void insert(int[] data){
    	for(int i=0; i<data.length; i++){
    		root = insert(this.root, data[i]);
    	}
    }
  
    public Node insert(Node root, int data) { 
  
        // BST insertion
        if (root == null) {
        	return new Node(data);
        }
        if(data == root.val){ //No need to add duplicated Node
        	return root;
        }
        else if (data < root.val){
        	root.left = insert(root.left, data); 
        }
        else if (data > root.val){
        	root.right = insert(root.right, data); 
        }
            
  
        // Update height of this ancestor node
        
        root.height = Math.max(height(root.left), height(root.right)) + 1; 
  
        int balance = getBalance(root); 
  

        //4 Cases of Unbalanced
        //Left Left Case (Left Heavy)
//        z                                      y 
//       / \                                   /   \
//      y   T4      Right Rotate (z)          x      z
//     / \          - - - - - - - - ->      /  \    /  \ 
//    x   T3                               T1  T2  T3  T4
//   / \
// T1   T2
        
        if (balance > 1 && data < root.left.val) 			//Balance > 1 => Left-heavy  						(Left)
            root = rightRotate(root); 						//data < root.left.val => data goes to Left Subtree (Left Left)
  
        // Right Right Case (Right Heavy)
//         z                                y
//        /  \                            /   \ 
//       T1   y     Left Rotate(z)       z      x
//           /  \   - - - - - - - ->    / \    / \
//          T2   x                     T1  T2 T3  T4
//              / \
//            T3  T4
        else if (balance < -1 && data > root.right.val){	//Balance < -1 => Right-Heavy						  (Right)
        	root = leftRotate(root);						//data > root.right.val => data goes to Right Subtree (Right Right)
        }  
  
        // Left Right Case 
//         z                               z                           x
//        / \                            /   \                        /  \ 
//       y   T4  Left Rotate (y)        x    T4  Right Rotate(z)    y      z
//      / \      - - - - - - - - ->    /  \      - - - - - - - ->  / \    / \
//    T1   x                          y    T3                    T1  T2 T3  T4
//        / \                        / \
//      T2   T3                    T1   T2
        else if (balance > 1 && data > root.left.val) { 	//Balance > 1 (Left)
            root.left = leftRotate(root.left); 				//data > root.left.val (Left Right)
            root = rightRotate(root);
        } 
  
        // Right Left Case 
//         z                            z                            x
//        / \                          / \                          /  \ 
//      T1   y   Right Rotate (y)    T1   x      Left Rotate(z)   z      y
//          / \  - - - - - - - - ->     /  \   - - - - - - - ->  / \    / \
//         x   T4                      T2   y                  T1  T2  T3  T4
//        / \                              /  \
//      T2   T3                           T3   T4
        else if (balance < -1 && data < root.right.val) { 	//Balance < -1 (Right)
            root.right = rightRotate(root.right);			//data < root.right.val (Right Left)
            root = leftRotate(root);
        } 
        return root; //if balanced, no need to rotate
    } 
    
    
    public void remove(int data){
    	root = remove(this.root, data);
    }
    
    public Node remove(Node root, int data){
    	if(root==null)
    		return root;
    	if(data < root.val)
    		root.left = remove(root.left, data);
    	else if(data > root.val)
    		root.right = remove(root.right, data);
    	else{
    		//Case 1: No child
    		if(root.left==null && root.right==null){
    			root = null;
    		}
    		else if(root.left == null){ //Case 2: One Right Child
    			root = root.right;
    		}
    		else if(root.right == null){ //Case 3: One Left Child
    			root = root.left;
    		}
    		else{	//Case 4: Two Children
//				root.val = getMax(root.left);
//				root.left = removeR(root.left, root.val);
				// ---- or --- 
				root.val = getMin(root.right);
				root.right = remove(root.right, root.val);
    		}
    	}
    	if(root==null) return root;
    	
    	root.height = Math.max(height(root.left), height(root.right)) + 1;
    	
    	int balance = getBalance(root);
    	
    	//Left Left
    	if(balance > 1 && getBalance(root.left) >= 0){ //if(balance == 2 && getBalance(root.left) >= 0)
    		return rightRotate(root);
    	}
    	//Right Right
    	else if(balance < -1 && getBalance(root.right) <= 0){ //else if(balance == -2 && getBalance(root.right) <= 0)
    		return leftRotate(root);
    	}
    	//Left Right
    	else if(balance > 1 && getBalance(root.left) < 0){ //else if(balance == 2 && getBalance(root.left) == -1)
    		root.left = leftRotate(root.left);
    		return rightRotate(root);
    	}
    	//Right Left
    	else if(balance < -1 && getBalance(root.right) > 0){ //else if(balance == -2 && getBalance(root.right) == 1)   Also work
    		root.right = rightRotate(root.right);
    		return leftRotate(root);
    	}
    	return root;
    }
    
    public int getMin(Node root){
    	Node curr = root;
    	while(curr.left != null){
    		curr = curr.left;
    	}
    	return curr.val;
    }
    
	public void preOrder(){
		System.out.print("PreOrder:\t");
		preOrderR(this.root);
		System.out.println();
	}
	
	public void preOrderR(Node root){
		if(root==null)
			return;
		System.out.print(root.val + " ");
		preOrderR(root.left);
		preOrderR(root.right);
	}
	
	public void postOrder(){
		System.out.print("PostOrder:\t");
		postOrderR(this.root);
		System.out.println();
	}
	
	public void postOrderR(Node root){
		if(root==null)
			return;
		postOrderR(root.left);
		postOrderR(root.right);
		System.out.print(root.val + " ");
	}
	
	public void inOrder(){
		System.out.print("InOrder:\t");
		inOrderR(this.root);
		System.out.println();
	}
	
	public void inOrderR(Node root){
		if(root==null)
			return;
		inOrderR(root.left);
		System.out.print(root.val + " ");
		inOrderR(root.right);
	}
  
    public void BFS(){
    	System.out.print("BFS:\t\t");
		if(this.root==null)
			return;
		Queue<Node> q = new LinkedList<>();
		q.add(this.root);
		while(!q.isEmpty()){
			Node temp = q.poll();
			System.out.print(temp.val + " ");
			if(temp.left!=null)
				q.offer(temp.left);
			if(temp.right!=null)
				q.offer(temp.right);
		}
		System.out.println();
	}
    
    
    public static void main(String[] args) { 
    	
//          AVL Tree
//	           30 
// 		      /  \ 
//   	    20    40
//    	   /  \     \ 
//   	  10  25	50
    	
    	AVLTree avl = new AVLTree();
    	int[] arr = {10,20,30,40,50,25};
    	int[] arr2 = {11,21,31,41,51,26};
    	avl.insert(arr);
    	avl.insert(arr2);
//    	avl.remove(50);
    	avl.remove(40);
    	avl.remove(41);
    	
    	avl.preOrder();
    	avl.inOrder();
    	avl.postOrder();
    	avl.BFS();
    } 
} 