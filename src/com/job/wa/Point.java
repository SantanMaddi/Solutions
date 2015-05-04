package com.job.wa;

public class Point {

private int row, column, hGoal, distToGoal, index;
private Point parent;
private boolean isOpen;
	
	public Point(){
		super();
	}
	
	public Point(int row, int column, boolean isOpen){
		super();
		this.row = row;
		this.column = column;
		this.isOpen = isOpen;
		this.hGoal = 0;
		this.distToGoal = 0;
	}
	
	public Point(int row, int column, boolean isOpen, int index){
		super();
		this.row = row;
		this.column = column;
		this.isOpen = isOpen;
		this.hGoal = 0;
		this.distToGoal = 0;
		this.index = index;
	}
	
	public Point(int row, int column, int hGoal, int distToGoal, Point parent, boolean isOpen){
		super();
		this.row = row;
		this.column = column; 
		this.hGoal = hGoal;
		this.distToGoal = distToGoal;
		this.parent = parent;
		this.isOpen = isOpen;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	
	public int gethGoal() {
		return hGoal;
	}

	public void sethGoal(int hGoal) {
		this.hGoal = hGoal;
	}
	
	public boolean isOpen() {
		return isOpen;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public int getDistToGoal() {
		return distToGoal;
	}

	public void setDistToGoal(int distToGoal) {
		this.distToGoal = distToGoal;
	}

	public Point getParent() {
		return parent;
	}

	public void setParent(Point parent) {
		this.parent = parent;
	}
	
	public void copy(Point p){
		this.row = p.getRow();
		this.column = p.getColumn(); 
		this.hGoal = p.gethGoal();
		this.distToGoal = p.getDistToGoal();
		this.parent = p.getParent();
		this.isOpen = p.isOpen();
	}
	
}
