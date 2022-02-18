package assignmentCode;

import java.util.Arrays;

public class WeekFour {
	
	public static void main(String[] args) {
		int[][] cordinates = {{2, 3}, {5, 8}, {3, 4}, {5, 7}, {3,4}};
		int[] target = {3,4};
		closestThreeCordinates(cordinates, target);
	}
	
	
	
	
	public static void closestThreeCordinates(int[][] arr, int[] target) {
		Double distance; // variable to hold distance between points
		Double rawDistance; // variable to store unprocessed distance (unsquared)
		Double[] res = new Double[arr.length]; // array to store all distances
		int greatest = 0; // variable to hold greatest distance
		
		for(int i=0;i<arr.length;i++) {
			rawDistance =  Math.pow( (arr[i][0]-target[0]),2) + Math.pow((arr[i][1]-target[1]), 2); // calculate square distance between points
			distance = Math.sqrt(rawDistance); // use distance formula sqrt{(x2-x1)**2 + (y2-y1)**2} to find actual distance
			res[i] = distance; // add calculated distance to array
			if (res[greatest]<res[i]) { // update result variable
				greatest = i;
			}
		}
		
		int first = greatest; //initialize all 3 variable to largest element's index
		int second = greatest;
		int third = greatest;
		for(int i=0; i<res.length;i++) { // Second for loop to traverse the result array and find 3 smallest distances
			if(res[i]<=res[first]) {
				third = second;
				second=first;
				first = i;
			}
			else if(res[i]<=res[second]) {
				third = second;
				second = i;
			}
			else if(res[i]<=res[third]) {
				third = i;
			}
		}
		System.out.println("First = "+Arrays.toString(arr[first])); // Print out the results
		System.out.println("Second = "+Arrays.toString(arr[second]));
		System.out.println("third = "+Arrays.toString(arr[third]));
		
	}

}

// Time Complexity: O(N)
// Space Complexity: O(N)
