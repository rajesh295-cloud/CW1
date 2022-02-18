package assignmentCode;

public class WeekSeven {
	public static void main(String[] args) {
		int[] a ={1, 2, 3, 5};
		int[] b = {1, 4, 3};
		System.out.println(minimumManipulations(a, b));

		
	}
	public static int minimumManipulations(int[] a, int[] b) {
		// Here only 3 operations are allowed: insert, remove and replace i.e shifting is not allowed
		int count = 0; // i.e if same element is present in array but at different location, it must be replaced not shifted!
		int i = a.length<b.length?a.length:b.length; // Find minimum common number of elements in both array
        i--; // decrease i by one to convert position to index value
        while(i>=0) { // run a loop to check in which positions are two array different
        	if(a[i]!=b[i]) {count++;}
        	i--; // Decrease I as we loop forward
        }
        count += Math.abs(a.length-b.length); // Finally add extra length of both arrays as those elements need to be wither removed/inserted in list A
        return count; // return final count of operations
	}
	
		// Time Complexity: O(N) Where  N = minimum length between two arrays
	// Space Complexity: O(1) 

}
