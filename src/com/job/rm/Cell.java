package com.job.rm;

public class Cell {
	
	private float val;
	private boolean inStack;
	private String expression;
	
	public Cell(){
		super();
		val = -1;
		inStack = false;
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}
	
	public boolean isInStack() {
		return inStack;
	}

	public void setInStack(boolean inStack) {
		this.inStack = inStack;
	}
}
