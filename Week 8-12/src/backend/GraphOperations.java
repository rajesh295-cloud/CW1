package backend;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GraphOperations {
	static int vertices;
    public static int adjacency_matrix[][];
    public ArrayList<Integer> allNodes;
    
    public GraphOperations(int v){
    	/*
    	 * Initialize this class with an required argument of vertex
    	 */
        vertices=v; //stores the arguemnt into object variable
        
        adjacency_matrix=new int[vertices][vertices]; //creates an vertices X vertices sized matrix
    }
	 
	public boolean addContact(int source, int destination) {
		/*
		 * This function add connection of two different nodes
		 */
		try {
			if(adjacency_matrix[destination][source]==1) { //Validate that destination is not itself a source of infection for current destination
				JOptionPane.showMessageDialog(null, "Patient can not be source of infection!");
				return false;
			}
			adjacency_matrix[source][destination] = 1; //if not, connection is made
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public boolean removeContact(int source, int destination) {
		try {
			if(adjacency_matrix[destination][source]==1) { //connection is removed by changing 1 to 0 in matrix
				JOptionPane.showMessageDialog(null, "Patient can not be source of infection!");
				return false;
			}
			adjacency_matrix[source][destination] = 0;
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean save() {
		//Write to matrix is called to write the current matrix into files
		if(FileOperations.writeToMatrix(adjacency_matrix)) {
			return true;
		}
		return false;
	}
	
	public ArrayList<Integer> adjacentNodes(int start) {
		//Function to get all adjacent nodes
		ArrayList<Integer> adja = new ArrayList<Integer>();
		for(int i=0;i<adjacency_matrix[start].length;i++) {
			if(adjacency_matrix[start][i]!=0 && i!=start) {
				adja.add(i);
			}
		}
		return adja;
	}
	
	public ArrayList<String[]> BFS(int source){
		/*
		 * this function returns all connected nodes along with its parent node in 2D matrix
		 * Also, all connected nodes are stored in BFS style
		 */
		ArrayList<String[]> arr = new ArrayList<String[]>();
		allNodes = new ArrayList<Integer>();
		String[] data;
		int j=0;
        boolean[] visited = new boolean[vertices]; // Visited array to so that a vertex is not visited more than once
        ArrayList<Integer> q = new ArrayList<>();
        q.add(source); // source vertex is visited at the beginning
 
        
        visited[source] = true; // Set source as visited
 
        int vis;
        while (!q.isEmpty())
        {
            vis = q.get(0); // Get next element from queue and store it in array
            q.remove(q.get(0));

            for(int i = 0; i < vertices; i++) // For every adjacent vertex to the current vertex
            {
                if (adjacency_matrix[vis][i] == 1 && (!visited[i]))
                {
                	data = new String[3];
                    q.add(i); // Push the adjacent node to the queue
                    visited[i] = true; // Set current node to visited
                    allNodes.add(i);
                    data[0] =  Integer.toString(vis);
                    data[1] =  Integer.toString(i);
                    arr.add(data);
                    
                }
            }
            j++;
        }
        return arr;
    }
	
	
}

