package assignmentCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class WeekThree {
	LinkedNode node = new LinkedNode();
	private boolean blast; // boolean to signal blast
	private int day=0; //to store result
	ArrayList<Integer> removed = new ArrayList<Integer>();

	
	public int blastJars(int[] p) {
		for(int i:p) { //iterate through array and add it to linked list
			node.addLinkedNode(i);
		}

		LinkedNode curr = node.getHead(); //get head of linkedlist
		LinkedNode nextItem = curr.next; // get next item in linked list
		blast = true; //initialize blast as true to enter into while loop
		
		while(blast) {
			blast=false; //declare blast as false to start the process
			boolean blastnow = false; // this variable tells if a jar has blasted in current iteration
			
			while(nextItem!=null){ //Ensures that we have iterated till end
				if(curr.data>nextItem.data) { //equivalent to arr[i]> arr[i+1]
					removed.add(curr.data); // remove the current node in O(1) time
					node.removeLinkedNode(curr);
					blast=true; //indicates that a jar has been blasted 
					blastnow=true;//indicates that a jar has been blasted in this iteration
				}
				if(!blastnow) {//indicates that a jar has been blasted and current value isn't required to be changed
				curr = nextItem;}
				nextItem = nextItem.next; //move to next node
				blastnow=false; //reset blast indicator for current iteration
			}
			
			System.out.println(removed);
			removed = new ArrayList<Integer>(); // reset removed arraylist
			if(blast) {day++;} // increament the day count
			curr = node.getHead(); //get the current head for new iteration incase the old head is removed
			nextItem = curr.next; // get next node
			
		}
		
		return day;

	}
	public static void main(String[] args) {
		WeekThree test = new WeekThree();
//		int[] heights = {2,3,8,7,9,4,1};
		int[] heights = {1, 7, 3, 9, 2};
		System.out.println("Days: "+test.blastJars(heights));
	}
	
		

}
