package com.job.wa2;

public class GreatestCommonDivisor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Divisor(56, 42));

	}
	
	public static int Divisor(int x, int y){
		if(x > y)
			return calculate(x, y);
		else
			return calculate(y, x);
	}
	
	public static int calculate(int x, int y){
		for(int i = y; i > 0; i--){
			if(x % i == 0 && y % i == 0)
				return i;
		}
		return 1;
	}

}
