package com.job.wa;

import java.util.ArrayList;

public class MapUtils {

	private ArrayList<Point> closedList;
	private PointList openList;
	private int rows, columns;
	private Point[][] Map;
	private Point start, end;
	int travelledDistance;
	
	public int FindDistance(Point x, Point y, Point[][] map){
		
		/*System.out.println(map.length);
		System.out.println(map[map.length - 1].length);*/
		
		rows = map.length;
		columns = map[rows - 1].length;
		start = x; end = y;
//		Map = map;
		copyMap(map);
		openList = new PointList();
		travelledDistance = 0;
		closedList = new ArrayList<Point>();
		openList.add(x);
		for(int i = 0; i < openList.size(); i++)
			if(reachToGoal(openList.get(i)))
				break;
		return distanceToGoal(Map[end.getRow()][end.getColumn()]);
//		return distanceToGoal(end);
	}
	
	private void copyMap(Point[][] map){
		Point p;
		Map = new Point[map.length][map[0].length];
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[i].length; j++){
				p = new Point(i, j, Math.abs(end.getColumn() - j) + Math.abs(end.getRow() - i), 0, null, map[i][j].isOpen());
				Map[i][j] = p;
			}
		}
	}
	
	private int distanceToGoal(Point point){
		if(point.getParent().getRow() == start.getRow() && point.getParent().getColumn() == start.getColumn())
			return 1;
		else
			return distanceToGoal(point.getParent()) + 1;
	}
	
	private boolean reachToGoal(Point x){
		
		closedList.add(x);
//		openList.remove(x);
		
		ArrayList<Point> neighbours;
		
		neighbours = pointsAround(x);
		
		for(int i = 0; i < neighbours.size(); i++){
			if(neighbours.get(i).getColumn() == end.getColumn() && neighbours.get(i).getRow() == end.getRow()){
				/*neighbours.get(i).setParent(x);
				neighbours.get(i).setDistToGoal(x.getDistToGoal() + 1);*/
				Map[neighbours.get(i).getRow()][neighbours.get(i).getColumn()].setParent(x);
				end.setParent(x);
				return true;
			}
			else if(neighbours.get(i).isOpen() && !isInClosedList(neighbours.get(i)) && 
					((x.getDistToGoal() + 1) < neighbours.get(i).getDistToGoal() || neighbours.get(i).getDistToGoal() == 0)){
				/*neighbours.get(i).setParent(x);
				neighbours.get(i).setDistToGoal(x.getDistToGoal() + 1);*/
				Map[neighbours.get(i).getRow()][neighbours.get(i).getColumn()].setParent(x);
				Map[neighbours.get(i).getRow()][neighbours.get(i).getColumn()].setDistToGoal(x.getDistToGoal() + 1);
				openList.add(neighbours.get(i));
			}
		}
		openList.sortLastFour();
		return false;
	}
	
	/*private boolean pointExists(Point p){
		if((p.getRow() >=0 && p.getRow() < rows) 
				&& (p.getColumn() >= 0 && p.getColumn() < columns))
			return true;
		return false;
	}*/
	
	private ArrayList<Point> pointsAround(Point p){
//		Point[] points = new Point[4]();
		ArrayList<Point> points = new ArrayList<Point>();
		if(p.getRow() + 1 < rows)
			points.add(Map[p.getRow() + 1][p.getColumn()]);
		if(p.getColumn() + 1 < columns)
			points.add(Map[p.getRow()][p.getColumn() + 1]);
		if(p.getRow() - 1 >= 0)
			points.add(Map[p.getRow() - 1][p.getColumn()]);
		if(p.getColumn() - 1 >= 0)
			points.add(Map[p.getRow()][p.getColumn() - 1]);
		return points;
	}
	
	private boolean isInClosedList(Point p){
		for(int i = 0; i < closedList.size(); i++){
			 if(closedList.get(i).getRow() == p.getRow() && closedList.get(i).getColumn() == p.getColumn())
				 return true;
		}
		return false;
	}
}
