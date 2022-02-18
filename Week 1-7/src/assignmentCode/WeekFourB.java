package assignmentCode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class WeekFourB {
	public static void main(String[] args) {
		int[][] arr = {{0,1},{0,2},{2,3},{3,4}};
		System.out.println(getGreatestResturantValue("abaca", arr));
	}
	
	public static int getGreatestResturantValue(String resturants, int[][] vertices) {
		 //1. Build the Graph and Indegree array
	       ArrayList<ArrayList<Integer>> graph=new ArrayList<>();
	        int n=resturants.length(); //Get length of all resturants
	        char[] resturent=resturants.toCharArray(); //Break the resturants into character array
	        for(int i=0;i<n;i++) graph.add(i,new ArrayList<>()); //Populate empty arraylist into graph
	        int[] indegree=new int[n]; //Create array to store indegree values
	        for(int[] edge:vertices){ //Iterate over vertices and it into graph
	            int u=edge[0];
	            int v=edge[1];
	            indegree[v]++; //increament indegree of "v" i.e. the destination node
	            graph.get(u).add(v); // Add the edge to graph
	        }
	        

	        
	        //2. resturent count resturentValsoFar. resturentValsoFar[i][j] is the maximum count of j-th resturent from the ancester nodes to node i
	        int[][] resturentValsoFar=new int[n][26]; //Create a multi dim array of resturant.lenght X 26 size.
	        	//26 -> no.of alphabets in english dictionary
	        	// n -> total no.of resturants
	        
	        
	        //3.Khans Algorithm ( Iterative Topological Sort)
	        
	        // i) FInd the starting point
	        Queue<Integer> que=new ArrayDeque<>(); //Create an queue to Travel the nodes hierchially
	        for(int i=0;i<n;i++){ //Iterate over the indegree to find starting point i.e node with no dependencies
	            if(indegree[i]==0){ // Starting point is the one with no indegree i.e. no dependencies
	                que.add(i); //Add that node into queue
	                resturentValsoFar[i][resturent[i]-'a']=1; //Increament the occurence of that resturant by one
	            }
	        }
	        
	        
	        int res=0; //Stores current highest Occurence
	        int cnt=0; //Stores the count of nodes removed to trace for cycles
	        while(que.size()>0){
	            int node=que.remove(); //Remove The first element from queue
	            cnt++; //Increment Count
	            int max=getMax(resturentValsoFar[node]); //Get Highest Occurence of resturant up to that point
	            res = Math.max(res,max); //Find maxm between current greater and result greater and update if necessary
	            for(int ConnectedNode:graph.get(node)){ //Traverse graph and get array representing current vertice
	                // update the resturentValsoFar of next node
	                for(int i=0;i<26;i++){
	                    resturentValsoFar[ConnectedNode][i]=Math.max(resturentValsoFar[ConnectedNode][i],resturentValsoFar[node][i] + (resturent[ConnectedNode]-'a'==i?1:0));
	                    /*
	                     * Here, we traverse through all columns in resturant row and update the occurences for all character from previous node
	                     * Then we determine if current and previous resturant are same. If so, we increament that node by 1
	                     * Also, we decreace indegree by 1 for all connected node and add ir to queue if it's 0
	                     */
	                }
	                indegree[ConnectedNode]--;
	                if(indegree[ConnectedNode]==0){
	                    que.add(ConnectedNode);
	                }
	            }
	        }
	        //if seen!=n means cycle is there
	        return cnt==n?res:-1;
	    }
	    private static int getMax(int[] num){ //Finds maxm value up to that node
	        int max=num[0];
	        for(int n:num){
	            max=Math.max(n,max);
	        }
	        return max;
	    }
	}

// Overall Time Complexity: 
