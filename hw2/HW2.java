
class HW2 {
 
	// Node structure containing power and coefficient of variable
	static class Node {
		public Node next;
		public double coeff;
		public int power;

		public Node(Node next, double coeff, int power) {
			this.coeff = coeff;
			this.next = next;
			this.power = power;
		}
	}
	
	//Function To Display The Linked list
	static void printList(Node ptr)
	{
		if (ptr == null) 
		{
			System.out.println();
			return;
		}
		else if (ptr.next != null)
		{
			while (ptr.next != null)
			{
				System.out.print(ptr.coeff + "x^" + ptr.power + " + ");
				ptr = ptr.next;
			}
		}
		System.out.println(ptr.coeff + "x^" + ptr.power);
	}
  
	// Create a node and return
	static Node createNode(double coeff, int power)
	{
		Node head = new Node(null, coeff, power);
		return head;
	}
  
  	// Function add a new node
	static Node addNode(Node head, double coeff, int power)
	{
		if(head != null) {
			Node current = head;
			Node toAdd = new Node(null, coeff, power);
			if(current.next != null) {
				while(power < current.next.power) {
					current = current.next;
					if(current.next == null) {
						break;
					}
				}
				toAdd.next = current.next;
				current.next = toAdd;
			} else {
				if(power < current.power) {
					current.next = toAdd;
				} else {
					toAdd.next = head;
					head = toAdd;
				}
			}
		} else {
			head = new Node(null,coeff,power);
		}
		return head;
	}
  
	static Node multiply(Node poly1, Node poly2)
	{	
		Node result = null;
		Node temp1 = poly1;
		Node temp2 = poly2;
		while (temp1 != null) {
			while(temp2 != null) {
				if(result != null) {
					result = HW2.add(result, new Node(null,temp1.coeff*temp2.coeff, temp1.power + temp2.power));
				} else {
					result = HW2.addNode(result, temp1.coeff*temp2.coeff, temp1.power + temp2.power);
				}
				temp2 = temp2.next;
			}
			temp2 = poly2;
			temp1 = temp1.next;
		}
		return result;
	}
  
	static Node add(Node poly1, Node poly2)
	{	
		Node temp1 = poly1.power >= poly2.power ? poly1:poly2;
		Node temp2 = poly1.power >= poly2.power ? poly2:poly1;
		Node result = null;
		while(temp1 != null) {
			boolean found = false;
			while(temp2 != null) {
				if(temp1.power == temp2.power) {
					result = HW2.addNode(result, temp1.coeff + temp2.coeff, temp1.power);
					found = true;
				}
				temp2 = temp2.next;
			}
			if(!found) {
				result = HW2.addNode(result, temp1.coeff, temp1.power);
			}
			temp2 = poly1.power >= poly2.power ? poly2:poly1;
			temp1 = temp1.next;
		}
		temp1 = poly1.power >= poly2.power ? poly1:poly2;
		temp2 = poly1.power >= poly2.power ? poly2:poly1;
		Node temp3 = result;
		while(temp2 != null) {
			boolean found = false;
			while(temp3 != null) {
				if(temp2.power == temp3.power) {
					found = true;
				}
				temp3 = temp3.next;
			}
			if(!found) {
				result = HW2.addNode(result,temp2.coeff,temp2.power);
			}
			temp3 = result;
			temp2 = temp2.next;
		}
		return result;
		
	}
  
}
