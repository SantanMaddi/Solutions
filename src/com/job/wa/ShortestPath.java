package com.job.wa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ShortestPath {
	
	static int rows, columns;
	static Point start, goal;
	static Point[][] map;
	static int[][] distances;
	static ArrayList<Point> checkPoints = new ArrayList<Point>();
	static int minimumDistance = Integer.MAX_VALUE;
	static String path = "";
	
	public static void main(String args[]){

		String line;
		String[] firstLine;
		char[] words;

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		try {
			line = in.readLine();
			firstLine = line.split(" ");
			columns = Integer.parseInt(firstLine[0]);
			rows = Integer.parseInt(firstLine[1]);
			map = new Point[rows][columns];

			for(int i = 0; i < rows; i++){
				line = in.readLine();
				words = line.toCharArray();
				for(int j = 0; j < columns; j++){

					Point p = new Point();
					p.setRow(i);
					p.setColumn(j);

					if(words[j] == 'S'){
						start = new Point(i, j, true);
						p.setOpen(true);
					}
					else if(words[j] == 'G'){
						goal = new Point(i, j, true);
						p.setOpen(true);
					}
					else if(words[j] == '@'){
						Point c = new Point(i, j, true, checkPoints.size() + 1);
						checkPoints.add(c);
						p.setOpen(true);
					}
					else if(words[j] == '.')
						p.setOpen(true);
					else
						p.setOpen(false);
					
					map[i][j] = p;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		MapUtils m = new MapUtils();
		setHGoalToAllPoints();
		setDistanceToGoal();
		printDistances();
		perm2(checkPoints);
		System.out.println(minimumDistance + " " + path);
		
	}
	
	public static void perm2(ArrayList<Point> checkPoints) {
		int N = checkPoints.size();
		if(N > 0)
			perm2(checkPoints, N);
		else{
			int sum = start.getDistToGoal();
			if(sum < minimumDistance){
				minimumDistance = sum;
			}
		}
	}

	private static void perm2(ArrayList<Point> checkPoints, int n) {
		if (n == 1) {
			String newPath = "";
			int sum = 0;
			for(int i = 0, j = i + 1; j < checkPoints.size(); i++, j++){
				sum += distances[checkPoints.get(i).getIndex()][checkPoints.get(j).getIndex()];
				newPath += checkPoints.get(i).getIndex();
			}
			newPath += checkPoints.get(checkPoints.size() - 1).getIndex();
			sum += distances[0][checkPoints.get(0).getIndex()] + 
					checkPoints.get(checkPoints.size() - 1).getDistToGoal();
			if(sum < minimumDistance){
				minimumDistance = sum;
				path = newPath;
			}
			return;
		}
		for (int i = 0; i < n; i++) {
			swap(checkPoints, i, n-1);
			perm2(checkPoints, n-1);
			swap(checkPoints, i, n-1);
		}
	}
	
	private static void swap(ArrayList<Point> checkPoints, int i, int j) {
        Point p;
        p = checkPoints.get(i); checkPoints.set(i, checkPoints.get(j)); checkPoints.set(j, p);
    }
	
	public static void printDistances(){
		for(int i = 0; i < distances.length; i++){
			for(int j = 0; j < distances[i].length; j++)
				System.out.print(distances[i][j] +  " ");
			System.out.println();
		}
	}
	
	public static void setHGoalToAllPoints(){
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				if(map[i][j].isOpen())
					map[i][j].sethGoal(Math.abs(goal.getColumn() - j) + Math.abs(goal.getRow() - i));
			}
		}
	}
	
	public static void setDistanceToGoal(){
		distances = new int[checkPoints.size() + 2][checkPoints.size() + 2];
		MapUtils m = new MapUtils();
		distances[0][checkPoints.size() + 1] = m.FindDistance(map[start.getRow()][start.getColumn()], 
				map[goal.getRow()][goal.getColumn()], map);
		start.setDistToGoal(distances[0][checkPoints.size() + 1]);
		for(int i = 0; i < checkPoints.size(); i++){
			distances[0][i + 1] = m.FindDistance(map[start.getRow()][start.getColumn()], 
					checkPoints.get(i), map);
			distances[i + 1][checkPoints.size() + 1] = m.FindDistance(
					map[checkPoints.get(i).getRow()][checkPoints.get(i).getColumn()], 
					map[goal.getRow()][goal.getColumn()], map);
			checkPoints.get(i).setDistToGoal(distances[i + 1][checkPoints.size() + 1]);
		}
		
		for(int i = 0; i < checkPoints.size(); i++){
			for(int j = i + 1; j < checkPoints.size(); j++){
				distances[i + 1][j + 1] = m.FindDistance(checkPoints.get(i), checkPoints.get(j), map);
				distances[j + 1][i + 1] = distances[i + 1][j + 1];
			}
		}
	}
	
}
