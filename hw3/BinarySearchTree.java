public class BinarySearchTree
{
    Node root;

    // Node structure containing the subtrees
	static class Node
	{
		public int item;
		public Node left;
		public Node right;
		public int height;

		public Node(int item) {
			this.item = item;
			this.left = null;
			this.right = null;
			this.height = -1;
		}

		public int findHeight() {
			if(this.left == null && this.right == null) {
				this.height = 1;
			} else if (this.left != null && this.right == null) {
				this.height = 1 + this.left.findHeight();
			} else if (this.left == null && this.right != null) {
				this.height = 1 + this.right.findHeight();
			} else {
				this.height = 1 + Math.max(this.left.findHeight(), this.right.findHeight());
			}
			return this.height;
		}

		public int getHeight() {
			return this.height;
		}

		public boolean isBalanced() {
			if((this.left == null && this.right == null) || this == null) {
				return true;
			} else if (this.left == null && this.right != null) {
			    if(this.right.getHeight() > 1) {
					return false;
				} else {
					return true;
				}
			} else if (this.left != null && this.right == null) {
			    if(this.left.getHeight() > 1) {
					return false;
				} else {
					return true;
				}
			} else {
				if(Math.abs(this.left.getHeight()-this.right.getHeight()) <= 1) {
					return(this.left.isBalanced() && this.right.isBalanced());
				} else {
					return false;
				}
			}
		}

		public boolean compareTo(Node node) {
			if(this == null && node == null) {
				return true;
			}
			if((this.left == null && this.right == null) && (node.left == null && node.right == null)) {
				if(this.item == node.item) {
					return true;
				} else {
					return false;
				}
			} else if ((this.left == null && this.right != null) && (node.left == null && node.right != null)) {
				if(this.item == node.item) {
					return this.right.compareTo(node.right);
				} else {
					return false;
				}
			} else if ((this.left != null && this.right == null) && (node.left != null && node.right == null)) {
				if(this.item == node.item) {
					return this.left.compareTo(node.left);
				} else {
					return false;
				}
			} else if ((this.left != null && this.right != null) && (node.left != null && node.right != null)) {
				if(this.item == node.item) {
					return this.left.compareTo(node.left) && this.right.compareTo(node.right);
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	};

    // Constructor
    public BinarySearchTree()
    {
        this.root = null;
    }

    // Insert new item into the binary tree
    public void insert(int data)
    {
        Node cur = root;
		if(root == null) {
			root = new Node(data);
			return;
		} else {
			while(cur != null) {
				if(data < cur.item) {
					if(cur.left == null) {
						cur.left = new Node(data);
						return;
					} else {
						cur = cur.left;
					}	
				} else if (data > cur.item) {
					if(cur.right == null) {
						cur.right = new Node(data);
						return;
					} else {
						cur = cur.right;
					}				
				} else if (data == cur.item) {
					return;
				}
			}
		}
    }

    // Check if the tree is balanced or not
    public boolean isBalanced()
    {
        // Your code here
		root.findHeight();
		return this.root.isBalanced();
    }
	
	// Remove an item from the tree
	public void remove(int item)
	{
		Node cur = root;
		Node parent = root;
		if(root == null) {
			return;
		} else {
			while(true) {
				if(item < cur.item) {
					if(cur.left == null) {
						return;
					} else {
						parent = cur;
						cur = cur.left;
					}
				} else if (item > cur.item) {
					if(cur.right == null) {
						return;
					} else {
						parent = cur;
						cur = cur.right;
					}
				} else {
					if(cur.left == null && cur.right == null) {
						if(root.left == null && root.right == null) {
							root = null;
							return;
						} else {
							if(parent.left != null) {
								if(parent.left.item == item) {
									parent.left = null;
									return;
								}
							} 
							if (parent.right != null) {
								if(parent.right.item == item) {
									parent.right = null;
									return;
								}
							}
						}
					} else if (cur.left == null && cur.right != null) {
						Node subTreeRoot = cur;
						if(cur.right.left != null) {
							cur = cur.right;
							while(cur.left.left != null) {
								cur = cur.left;
							}
							subTreeRoot.item = cur.left.item;
							if(cur.left.right != null) {
								Node temp = cur.left.right;
								cur.left = temp;
								return;
							} else {
								cur.left = null;
								return;
							}
						} else {
							cur.item = cur.right.item;
							cur.right = cur.right.right;
							return;
						}
					} else {
						Node subTreeRoot = cur;
						if(cur.left.right != null) {
							cur = cur.left;
							while(cur.right.right != null) {
								cur = cur.right;
							}
							subTreeRoot.item = cur.right.item;
							if(cur.right.left != null) {
								Node temp = cur.right.left;
								cur.right = temp;
								return;
							} else {
								cur.right = null;
								return;
							}
						} else {
							cur.item = cur.left.item;
							cur.left = cur.left.left;
							return;
						}
					}
				}
			}
		}
		
	}
	
	// Compare two trees. Return true if both trees are same
	public boolean compareTo(BinarySearchTree tree)
	{
		return this.root.compareTo(tree.root);
	}
	
	// Given function to print the tree
	public void printInOrder(Node node)
	{
		if (node != null)
		{
			printInOrder(node.left);
			System.out.print(node.item + " ");
			printInOrder(node.right);
		}
    }
}
