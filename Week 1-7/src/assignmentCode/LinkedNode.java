package assignmentCode;


public class LinkedNode {
	int data;
	LinkedNode next;
	LinkedNode tail;
	LinkedNode head;
	int size=0;
	
	LinkedNode(int data){
		this.data = data;
	}
	LinkedNode(){
	}
	
	
	public void addLinkedNode(int data) {
		LinkedNode temp = new LinkedNode(data); // Wrap integer to a LinkedNode
		if(head==null) { // Handling Base Case
			head = temp;
			tail = head;
		}
		else {tail.next = temp; //change next pointer of tail LinkedNode to new LinkedNode
			  tail = temp; //Update tail Pointer
		}
		size++;
	}
	
	public LinkedNode getHead() {
		return head; //Return reference of head LinkedNode
	}
	public LinkedNode getTail() {
		return tail; //Return reference of head LinkedNode
	}
	
	public static void printLinkedList(LinkedNode node) {
		LinkedNode curr = node; // Create and copy current head pointer to "Curr"
		while(curr!=null) { // Defining Exit condition
			System.out.print(curr.data +" "); // Printing integer data from curr LinkedNode
			curr = curr.next; //Moving to next LinkedNode
		}
		
	}
	
	public int elementAtIthPosition(int i) {
		if(i>size||i<1) {return Integer.MIN_VALUE;} //Handles the base conditions
		LinkedNode curr = head; // Store head reference into a variable
		for(int j=0;j<i-1;j++) { // loop until we reach i-1 position as index = pos - 1
			curr = curr.next; // Keep moving to next LinkedNode
		}
		return curr.data; // return data from specified LinkedNode
	}
	
	public void removeLinkedNode(LinkedNode node) {
		if(node==head) {
			LinkedNode temp = head.next;
			head.next=null;
			head = temp;
			size--;
		}
		else if(node.next==null) {
			node = null;
			size--;
		}
		else {
			node.data = node.next.data;
			node.next = node.next.next;
			size--;
		}
	}
	
}
