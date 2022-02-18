package assignmentCode;

public class WeekTwo {
	public static void main(String[] args) {
		int[] profits = {100, 180, 260, 310, 40, 535, 695};
		maxProfit(profits);
		
	}
	
	public static void maxProfit(int[] arr) {
		boolean buy = true; // initialize bool variable to know wjen to buy/sell gold
		int start=0;int end=0; // initialze two variables which records the start and end indexes
		int profit = 0; // variable to store profit
		int i=0; // variable to keep track of index of array
		if(arr.length ==1) {System.out.println("No Profit"); return;} // Handling base case i.e. there should be gold price of atleast 2 days
		
		while(i<arr.length-1) { // traverse array until we reach second last element
			while(buy && i<arr.length-1) { // if "buy" flag is +ve we buy gold 
					if(arr[i]<arr[i+1]) { // We buy only if current price will increase tomorrow
					start=i;
					buy = false; // Set flag to false to indicate that we need to find a selling point now
				}
				i++;
			}
			
			while((!buy)&& i<arr.length-1) { // if "buy" flag is -ve we sell the gold 
				if(arr[i]>arr[i+1]) { // We sell only if current price will decrease tomorrow
					end = i;
					buy = true;
					System.out.println("Day "+start+ " buy "+"Day "+end+ " sell"); // Print the buying and selling days
					profit += (arr[end]-arr[start]); // Store the profit
				}
				i++;
			}
		}
		if(buy==false) { //Handling base case i.e. when we have bought the gold but have not sold it even after reaching end of array
			System.out.println("Day "+start+ " buy "+"Day "+(arr.length-1)+ " sell"); // Sell the stock at end of the week.
			profit += (arr[arr.length-1]-arr[start]);
		}
		System.out.println("Maximum Profit = "+profit); // Displaying profit
	}
	 // Time Complexity: O(N) where N = no of days provided

}
