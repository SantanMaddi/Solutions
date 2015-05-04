package com.job.vsa;

//Program to find the minimum positive number
//which is divisible by the given input number
//as well as 
//whose sum of digits are equal to the given 
//input number
public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(Solution(11));
		
	}

	public static int Solution(int n){
		int i = 2;
		if(n <= 2)
			return -1;
		else{
			while(true){
				if(checkSum(i * n, n))
					return i * n;
				i++;
			}
		}
	}
	
	public static boolean checkSum(int number, int n){
		int result = 0;
		while(!(number < 10)){
			result += number % 10; 
			number = number / 10;
		}
		result += number;
		if(result == n)
			return true;
		return false;
	}
}
