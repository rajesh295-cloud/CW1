package assignmentCode;

public class WeekOne {
	
	public static void main(String[] args) {
		LinkedNode l1 = new LinkedNode();
		l1.addLinkedNode(100);
		l1.addLinkedNode(400);
		l1.addLinkedNode(-1000);
		l1.addLinkedNode(-500);
		
		LinkedNode l2 = new LinkedNode();
		l2.addLinkedNode(-300);
		l2.addLinkedNode(2000);
		l2.addLinkedNode(-500);
		
		LinkedNode l3 = mergeLinkedList(l1,l2);
		System.out.println("Printing Linked node");
		LinkedNode.printLinkedList(l3);
	}
	
	public static LinkedNode mergeLinkedList(LinkedNode l1, LinkedNode l2) {
		int sum=0; // Variable to Store and compare sum
		LinkedNode h1 = l1.head; //Storing head of l1 to h1
		LinkedNode h2 = l2.head; //Storing head of l1 to h1
		LinkedNode temp; // initialize an empty node
		
		LinkedNode tempTail = null; // create temp tail
		LinkedNode tempHead = null; // create temp head
		
		while(h1 != null || h2 != null) { // Loop until both lists are empty
			if(h1==null && h2!=null) { // If h1 is empty and h2 is not, assign node from l2 to temp
				temp = h2;
				h2 = h2.next;}
			
			else if(h2==null && h1!=null) { // If h2 is empty and h1 is not, assign node from l1 to temp
				temp = h1;
				h1 = h1.next;}
			
			else { //If neither one is empty, find greater among them and assign that node to temp
				if(h1.data>h2.data) { // and whichever is greater, increase its pointer by one
					temp = h1;
					h1 = h1.next;}
				else {
					temp = h2;
					h2 = h2.next;}
			}
			
			sum += temp.data; // Add the data part from temp node
			if(sum>0) { // if it meets our criteria of sum not being -ve, add that node to our linked list
				if(tempHead ==null) {tempTail = temp; tempHead=temp;} // if node is being added for first time i.e. linked list is empty
				else { // else add node to end of linked list
					tempTail.next = temp;
				}
				tempTail = temp; // declare newly added node as tail of our linked list
			}
			else { // if it does not meet our criteria of sum being +ve, stop the loop and return
				System.out.println("Merge Failed");
				System.out.println(-1);
				return new LinkedNode(-1);
				}	
			}
		return tempHead; // return head of newly formed merge linked list

	}
	// Time Complexity: O(M+N) where M and N are size of respective linked Lists
	// Space Complexity: O(M+N) 

}
