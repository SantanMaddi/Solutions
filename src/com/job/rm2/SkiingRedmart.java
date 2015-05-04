package com.job.rm2;
/*
 * author = Santan Maddi
 * First Line of input = Rows Columns
 * Next Rows*Columns input values of Elevation points
 * 
 *   Output: [row column] Longest Path Starting Point
 *   		 Longest Path: xx Largest Drop: yy 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SkiingRedmart {

	static int rows, columns; 
	static int[][] Map; 
	static int[][] pathLength, pathTail;
	
	public static int traverse(int x, int y){ //Traverse through each point in the map
											  //to it's neighbor 
		if(pathLength[x][y] != -1)
			return pathLength[x][y];
		
		int[] incX, incY;
		incX = getInc(x, rows); 			  //Get the increments of row and column of the current position 
		incY = getInc(y, columns);  		  //if point is on the edge, the corner or other
		
		//Traverse through next column on the same row
		//if the elevation is less and current pathLength is less than 
		//length after traversal
		if((incY[0] != 0) && Map[x][y + incY[0]] < Map[x][y] && 
				pathLength[x][y] < (1 + traverse(x, y + incY[0])) ){
			pathLength[x][y] = (1 + traverse(x, y + incY[0]));
			pathTail[x][y] = pathTail[x][y + incY[0]];
		}
		
		//Traverse through next row on the same column
		//if the elevation is less and current pathLength is less than 
		//length after traversal
		if((incX[0] != 0) && Map[x + incX[0]][y] < Map[x][y] && 
				pathLength[x][y] < (1+ traverse(x + incX[0], y))){
			pathLength[x][y] = (1+ traverse(x + incX[0], y));
			pathTail[x][y] = pathTail[x + incX[0]][y];
		}
		
		//Traverse through previous column on the same row
		//if the elevation is less and current pathLength is less than 
		//length after traversal
		if((incY[1] != 0) && Map[x][y + incY[1]] < Map[x][y] && 
				pathLength[x][y] < (1 + traverse(x, y + incY[1]))){
			pathLength[x][y] = (1 + traverse(x, y + incY[1]));
			pathTail[x][y] = pathTail[x][y + incY[1]];
		}
		
		//Traverse through previous row on the same column
		//if the elevation is less and current pathLength is less than 
		//length after traversal
		if((incX[1] != 0) && Map[x + incX[1]][y] < Map[x][y] && 
				pathLength[x][y] < (1 + traverse(x + incX[1], y))){
			pathLength[x][y] = (1 + traverse(x + incX[1], y));
			pathTail[x][y] = pathTail[x + incX[1]][y];
		}
		
		//If no where to traverse
		if(pathLength[x][y] == -1){
			pathLength[x][y] = 1;
			pathTail[x][y] = Map[x][y];
		}
	
		return pathLength[x][y];
	}
	
	public static int[] getInc(int rowOrcolumn, int rowsOrcolumns){
		int[] inc = new int[2]; 
		if(rowOrcolumn == 0){
			inc[0] = 1;
			inc[1] = 0; 
		}
		else if(rowOrcolumn == rowsOrcolumns - 1){
			inc[0] = 0;
			inc[1] = -1;
		}
		else{
			inc[0] = 1;
			inc[1] = -1;
		}
		return inc;
	}
	
	public static void main(String args[]){
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			String line = in.readLine();
			String []arr = line.split(" ");
			rows = Integer.parseInt(arr[0]);
			columns = Integer.parseInt(arr[1]);
			Map = new int[rows][columns];//Elevation at each position on the map
			pathLength = new int[rows][columns]; //Path length for each elevation on the map
			pathTail = new int[rows][columns]; //Lowest elevation of each position after skiing
			
			for(int i = 0; i < rows; i++){ //Assign input elevation to each position on the map				
				line = in.readLine(); 		//And assign pathLength and pathTail for each position as -1 
				arr = line.split(" ");
				for(int j = 0; j < arr.length; j++){
					Map[i][j] = Integer.parseInt(arr[j]);
					pathLength[i][j] = -1;
					pathTail[i][j] = -1;
				}
			}
			
			for(int i = 0; i < rows; i++){
				for(int j = 0; j < columns; j++){
					traverse(i, j);
				}
			}
			
			int[] position = findMaxDepth(); //Find the position of the largest steep possible
			System.out.println("[" + position[0] + " " + position[1] + "]");
			System.out.println("Longest Path:" + pathLength[position[0]][position[1]] + " Largest drop:" 
			+ (Map[position[0]][position[1]] - pathTail[position[0]][position[1]]));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int[] findMaxDepth(){
		int[] pos = new int[2];
		
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				
				if(pathLength[i][j] > pathLength[pos[0]][pos[1]]){
					pos[0] = i;
					pos[1] = j;
				}
				else if ((pathLength[i][j] == pathLength[pos[0]][pos[1]]) && 
						(Map[i][j] - pathTail[i][j] > Map[pos[0]][pos[1]] - pathTail[pos[0]][pos[1]])){
					pos[0] = i;
					pos[1] = j;
				}
				else
					continue;
			}
		}
		return pos;
	}
	
}
