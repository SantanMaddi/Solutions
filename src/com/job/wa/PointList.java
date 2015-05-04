package com.job.wa;

import java.util.ArrayList;

public class PointList extends ArrayList<Point> {
	
	public boolean remove(Point p){
		
		for(int i = 0; i < this.size(); i++){
			if(this.get(i).getRow() == p.getRow() && this.get(i).getColumn() == p.getColumn()){
				this.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void sortLastFour(){
		Point temp;
		for(int i = this.size() - 5; i < this.size(); i++){
			if(i < 0)
				continue;
			else{
				for(int j = i + 1; j < this.size(); j++)
					if(this.get(i).gethGoal() + this.get(i).getDistToGoal() > this.get(j).gethGoal() + this.get(j).getDistToGoal()){
						temp = new Point(this.get(i).getRow(), this.get(i).getColumn(), this.get(i).gethGoal(),
								this.get(i).getDistToGoal(), this.get(i).getParent(), this.get(i).isOpen());
						this.get(i).copy(this.get(j));
						this.get(j).copy(temp);
					}
			}
		}
	}
	
}
