package com.job.rm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Spreadsheet {

	private static int sheetWidth = 0, sheetHeight = 0;
	private static Cell[][] sheet;

	public static float calculate(int height, int width){
		float result = 0;
		if(sheet[height][width].isInStack()){
			System.exit(1);
		}
		sheet[height][width].setInStack(true);
		if(sheet[height][width].getVal() != -1){
			sheet[height][width].setInStack(false);
			return sheet[height][width].getVal();
		}

		LinkedList<Float> stack = new LinkedList<>(); 
		String[] elements = sheet[height][width].getExpression().split(" ");

		for(int i = 0; i < elements.length; i++){
			try{
				stack.push((Float.parseFloat(elements[i])));
			}catch(NumberFormatException e){
				if(elements[i].length() > 1){
					stack.push(calculate(convertToCellReference(elements[i].charAt(0)), 
							Integer.parseInt(elements[i].substring(1)) - 1));
				}
				else{
					float x = stack.pop();
					float y = stack.pop();
					switch(elements[i]){
					case "+":
						stack.push(y + x);
						break;
					case "-":
						stack.push(y - x);
						break;
					case "*":
						stack.push(y * x);
						break;
					case "/":
						stack.push(y / x);
						break;
					default:
						/* THROW EXCEPTION */
						System.exit(1);
					}
				}
			}
		}
		sheet[height][width].setInStack(false);
		result = stack.pop();
		if(!stack.isEmpty()){
			System.exit(1);
			return -1;
		}
		else
			return result;
	}

	private static int convertToCellReference(char row){
		int resultRow = ((int)row - 65);
		if( 0 <= resultRow && resultRow < 26)
			return resultRow;
		else{
			System.exit(1);
			return -1;
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String line;
		String attr[];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		try {
			line = in.readLine();
			attr = line.split(" ");
			sheetWidth = Integer.parseInt(attr[0]);
			sheetHeight = Integer.parseInt(attr[1]);

			sheet = new Cell[sheetHeight][sheetWidth];

			for(int i = 0; i < sheetHeight; i++){
				for(int j = 0; j < sheetWidth; j++){
					line = in.readLine();
					Cell temp = new Cell();
					temp.setExpression(line);
					sheet[i][j] = temp;
					try{
						sheet[i][j].setVal(Float.parseFloat(line));
					}catch(NumberFormatException e){

					}catch(NullPointerException e){
						System.exit(1);
					}
				}
			}
			System.out.println(sheetWidth + " " + sheetHeight);
			for(int i = 0; i < sheetHeight; i++){
				for(int j = 0; j < sheetWidth; j++){
					System.out.println(String.format("%.5f", calculate(i, j)));
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.exit(1);
			e.printStackTrace();
		}
	}
}
