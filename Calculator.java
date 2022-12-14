import java.util.Scanner;
import java.util.ArrayList;

public class Calculator {
	
	private float[] history = new float[50];
	private float memory;
	private float convertedResult;
	private String expression;
	private int historyCount = 0;
	private int evaluateCount = 0;
	private ArrayList<String> expressionArrayList = new ArrayList<String>();
	
	public Calculator() {
	}

	public float evaluate(String expression) {
		//initialise the number variables used during bracket expression calculation
		float numberOne = 0;
		float numberTwo = 0;

			//splits at space - checks that there are spaces between each thing
			String[] expressionArray = expression.split(" ");		

				//check number of elements is for BASIC CALCULATOR
				if (expressionArray.length == 3) {
			
					//VALIDATE
					//checks that the two number values are valid floats
					try {
						Float.parseFloat(expressionArray[0]);
						Float.parseFloat(expressionArray[2]);
					}
					
					//if not print invalid statement, set convertedResult to 0, and return float.min_value
					catch (Exception e) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}
					
					//checks a valid operator is used
					if(expressionArray[1].equals("/")|| expressionArray[1].equals("*") || expressionArray[1].equals("+") || expressionArray[1].equals("-")) {
					
					//if not print invalid statement, set convertedResult to 0, and return float.min_value
					} else {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

					//CALCULATE	
					//if divide by 0, print invalid statement, set convertedResult to 0, and return float.min_value
					if (expressionArray[1].equals("/") && expressionArray[2].equals("0")) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;

					//division: store parsed result in convertedResult
					} else if (expressionArray[1].equals("/")) {
						convertedResult = Float.parseFloat(expressionArray[0]) / Float.parseFloat(expressionArray[2]);
							
					//multiplication: store parsed result in convertedResult
					} else if (expressionArray[1].equals("*")) {
						convertedResult = Float.parseFloat(expressionArray[0]) * Float.parseFloat(expressionArray[2]);
				
					//addition: store parsed result in convertedResult
					} else if (expressionArray[1].equals("+")) {
						convertedResult = Float.parseFloat(expressionArray[0]) + Float.parseFloat(expressionArray[2]);
							
					//subtraction: store parsed result in convertedResult
					} else if (expressionArray[1].equals("-")) {
						convertedResult = Float.parseFloat(expressionArray[0]) - Float.parseFloat(expressionArray[2]);
						
					//if anything else, print invalid statement, set convertedResult to 0, and return float.min_value	
					} else {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					} 

				//check number of elements is for MEMORY CALCULATOR
				} else if (expressionArray.length == 2) {
					
					//VALIDATE
					//if the operator is invalid, print invalid statement, set convertedResult to 0, and return float.min_value
					if (!expressionArray[0].equals("/") && !expressionArray[0].equals("*") && !expressionArray[0].equals("+") && !expressionArray[0].equals("-")) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

					//test that the number input is valid (can it be parsed into float)
					try {
						Float.parseFloat(expressionArray[1]);
						
					//if not, print invalid statement, set convertedResult to 0, and return float.min_value
					} catch (Exception e) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

					//CALCULATE
					//if divide by 0, print invalid statement, set convertedResult to 0, and return float.min_value	
					if (expressionArray[0].equals("/") && expressionArray[1].equals("0")) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
						
					//division: using the saved memory, store parsed result in convertedResult
					} else if (expressionArray[0].equals("/")) {
						convertedResult = memory / Float.parseFloat(expressionArray[1]);
					
					//multiplication: using the saved memory, store parsed result in convertedResult
					} else if (expressionArray[0].equals("*")) {
						convertedResult = memory * Float.parseFloat(expressionArray[1]);
					
					//addition: using the saved memory, store parsed result in convertedResult
					} else if (expressionArray[0].equals("+")) {
						convertedResult = memory + Float.parseFloat(expressionArray[1]);
						
					//subtraction: using the saved memory, store parsed result in convertedResult
					} else if (expressionArray[0].equals("-")) {
						convertedResult = memory - Float.parseFloat(expressionArray[1]);
						
					//if anything else, print invalid statement, set convertedResult to 0, and return float.min_value
					} else {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

				//check number of elements is for BRACKETED CALCULATOR and it starts with an opening bracket
				} else if (expressionArray.length == 7 && expressionArray[0].startsWith("(")) {
					
					//VALIDATE
					//check if an expression contains correct brackets at the correct place
					if (expressionArray[0].startsWith("(") && expressionArray[2].endsWith(")") && expressionArray[4].startsWith("(") && expressionArray[6].endsWith(")")) {
					
						//check if each operator is valid
						if (expressionArray[1].equals("/") || expressionArray[1].equals("*") || expressionArray[1].equals("+") || expressionArray[1].equals("-")) {
						if (expressionArray[3].equals("/") || expressionArray[3].equals("*") || expressionArray[3].equals("+") || expressionArray[3].equals("-")) {
						if (expressionArray[5].equals("/") || expressionArray[5].equals("*") || expressionArray[5].equals("+") || expressionArray[5].equals("-")) {
						}
						}
						}
					
					//if anything else, print invalid statement, set convertedResult to 0, and return float.min_value
					} else {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}
						
					//find the brackets in the expression and remove them so the numbers can be parsed	
					expressionArray[0] = expressionArray[0].replace("(", "");
					expressionArray[2] = expressionArray[2].replace(")", "");
					expressionArray[4] = expressionArray[4].replace("(", "");
					expressionArray[6] = expressionArray[6].replace(")", "");
					
					//tests that you are now able to parse each of the values into a float
					try {
						Float.parseFloat(expressionArray[0]);
						Float.parseFloat(expressionArray[2]);
						Float.parseFloat(expressionArray[4]);
						Float.parseFloat(expressionArray[6]);
					
					//if anything else, print invalid statement, set convertedResult to 0, and return float.min_value
					} catch (Exception e) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

					//CALCULATE
					//CALCULATE BRACKET 1	
					//if divide by 0, print invalid statement, set convertedResult to 0, and return float.min_value	
					if (expressionArray[1].equals("/") && expressionArray[2].equals("0")) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					
					//division: store parsed result in numberOne	
					} else if (expressionArray[1].equals("/")) {
						numberOne = Float.parseFloat(expressionArray[0]) / Float.parseFloat(expressionArray[2]);
						//System.out.println(numberOne);
					
					//multiplication: store parsed result in numberOne
					} else if (expressionArray[1].equals("*")) {
						numberOne = Float.parseFloat(expressionArray[0]) * Float.parseFloat(expressionArray[2]);
						//System.out.println(numberOne);
					
					//addition: store parsed result in numberOne		
					} else if (expressionArray[1].equals("+")) {
						numberOne = Float.parseFloat(expressionArray[0]) + Float.parseFloat(expressionArray[2]);
						//System.out.println(numberOne);
					
					//subtraction: store parsed result in numberOne		
					} else if (expressionArray[1].equals("-")) {
						numberOne = Float.parseFloat(expressionArray[0]) - Float.parseFloat(expressionArray[2]);
						//System.out.println(numberOne);
							
					//if anything else, print invalid statement, set convertedResult to 0, and return float.min_value		
					} else {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

					//CALCULATE BRACKET 2
					//if divide by 0, print invalid statement, set convertedResult to 0, and return float.min_value
					if (expressionArray[4].equals("/") && expressionArray[6].equals("0")) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
						
					//division: store parsed result in numberTwo	
					} else if (expressionArray[5].equals("/")) {
						numberTwo = Float.parseFloat(expressionArray[4]) / Float.parseFloat(expressionArray[6]);
						//System.out.println(numberTwo);
						
					//multiplication: store parsed result in numberTwo	
					} else if (expressionArray[5].equals("*")) {
						numberTwo = Float.parseFloat(expressionArray[4]) * Float.parseFloat(expressionArray[6]);
						//System.out.println(numberTwo);
						
					//addition: store parsed result in numberTwo		
					} else if (expressionArray[5].equals("+")) {
						numberTwo = Float.parseFloat(expressionArray[4]) + Float.parseFloat(expressionArray[6]);
						//System.out.println(numberTwo);
						
					//subtraction: store parsed result in numberTwo		
					} else if (expressionArray[5].equals("-")) {
						numberTwo = Float.parseFloat(expressionArray[4]) - Float.parseFloat(expressionArray[6]);
						//System.out.println(numberTwo);
						
					//if anything else, print invalid statement, set convertedResult to 0, and return float.min_value	
					} else {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

					//FINAL CALCULATION: numberOne with numberTwo
					//if divide by 0, print invalid statement, set convertedResult to 0, and return float.min_value
					if (expressionArray[3].equals("/") && numberTwo == 0) {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
						
					//division: store in convertedResult
					} else if (expressionArray[3].equals("/")) {
						convertedResult = numberOne / numberTwo;
					
					//multiplication: store in convertedResult
					} else if (expressionArray[3].equals("*")) {
						convertedResult = numberOne * numberTwo;
					
					//addition: store in convertedResult
					} else if (expressionArray[3].equals("+")) {
						convertedResult = numberOne + numberTwo;
					
					//subtraction: store in convertedResult
					} else if (expressionArray[3].equals("-")) {
						convertedResult = numberOne - numberTwo;
						
					//if anything else, print invalid statement, set convertedResult to 0, and return float.min_value	
					} else {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

				//check the array length is greater than 4, for the ARBITRARY LENGTH CALCULATOR
				} else if (expressionArray.length > 4) {
					
					//initialise the variables used in the arbitrary length calculator
					int numberNum = 0;
					int operatorNum = 0;
					int a = 0;
					int b = 1;
					boolean valid = true;
					int preIndex = 0;
					int postIndex = 0;
					float preNumber = 0;
					float postNumber = 0;
					float result = 0;
					String stringResult;
					String valueTranslate;
					ArrayList<String> expressionArrayList = new ArrayList<String>();

					//VALIDATOR
					//if divide by 0, print invalid statement, set valid to false, set convertedResult to 0, and return float.min_value
					if (expression.contains("/ 0")) {
						System.out.println("Invalid input.");
						valid = false;
						convertedResult = 0;
						return Float.MIN_VALUE;
					}
					
					//test that the amount of numbers and operators in the array fit what is expected by the length
					try {
						numberNum = (expressionArray.length + 1) / 2;
						operatorNum = expressionArray.length - numberNum;
					
					//if anything else, print invalid statement, set valid to false, set convertedResult to 0, and return float.min_value
					} catch (Exception e) {
						System.out.println("Invalid input.");
						valid = false;
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

					//check that the numbers are in the correct place; a starts at 0
					while (a < expressionArray.length) {
						
						//test that a is able to be parsed to a float (is it a valid number)
						try {
							Float.parseFloat(expressionArray[a]);
							
						//if anything else, print invalid statement, set valid to 0, set convertedResult to 0, and return float.min_value	
						} catch (Exception e) {
							System.out.println("Invalid input.");
							valid = false;
							convertedResult = 0;
							return Float.MIN_VALUE;
						}
						
						//increment a by 2 so it skips the operators
						a = a + 2;
					}

					//check that the operators are in the correct place, b starts at 1
					while (b < expressionArray.length) {
						
						//if b equals a valid operator, set valid to 0
						if (expressionArray[b].equals("/") || expressionArray[b].equals("*") || expressionArray[b].equals("+") || expressionArray[b].equals("-")) {
							valid = true;
						
						//if anything else, print invalid statement, set valid to 0, set convertedResult to 0, and return float.min_value	
						} else {
							System.out.println("Invalid input.");
							valid = false;
							convertedResult = 0;
							return Float.MIN_VALUE;
						}
						
						//increment b by 2 so it skips the numbers
						b = b + 2;
					}

					//CALCULATOR
					//if boolean valid is true, do the calculation
					if (valid) {
						
						//save the values of the array into an arraylist
						for (int w = 0; w < expressionArray.length; w++) {
							
							valueTranslate = expressionArray[w];
							expressionArrayList.add(valueTranslate);
						}

						//DIVISION and MULTIPLICATION
						//for the amount of indexes in the array list, loop
						for (int f = 0; f < expressionArrayList.size(); f++) {
							
							//if f equals divide or multiply:
							if (expressionArrayList.get(f).equals("/") || expressionArrayList.get(f).equals("*")) {
								
								//find the indexes of the numbers either side of the operator
								preIndex = f - 1;
								postIndex = f + 1;
								
								//save the parsed float values within each of the above indexes
								preNumber = Float.parseFloat(expressionArrayList.get(preIndex));
								postNumber = Float.parseFloat(expressionArrayList.get(postIndex));

								//CALCULATE
								//divide: using the values found
								if (expressionArrayList.get(f).equals("/")) {
									result = preNumber / postNumber;
								
								//multiply: using the values found
								} else if (expressionArrayList.get(f).equals("*")) {
									result = preNumber * postNumber;
								}

								//save the float result as a string so it can be popped back into the arraylist
								stringResult = String.valueOf(result);
								
								//remove arraylist index f + 1
								expressionArrayList.remove(f + 1);
								
								//remove arraylist index f
								expressionArrayList.remove(f);
								
								//remove 2 values from the arraylist to reflect the removal of these indexes
								f = f - 2;
								
								//save result in f - 1
								expressionArrayList.set(preIndex, stringResult);
							}
						}
						
						//ADDITION and SUBTRACTION
						//for the amount of indexes in the array list, loop
						for (int f = 0; f < expressionArrayList.size(); f++) {
							
							//if f equals addition or subtraction
							if (expressionArrayList.get(f).equals("+") || expressionArrayList.get(f).equals("-")) {
								
								//find the indexes of the numbers either side of the operator
								preIndex = f - 1;
								postIndex = f + 1;
								
								//save the parsed float values within each of the above indexes
								preNumber = Float.parseFloat(expressionArrayList.get(preIndex));
								postNumber = Float.parseFloat(expressionArrayList.get(postIndex));
								
								//CALCULATE
								//add: using the values found
								if(expressionArrayList.get(f).equals("+")) {
									result = preNumber + postNumber;
									
								//subtract: using the values found	
								} else if (expressionArrayList.get(f).equals("-")) {
									result = preNumber - postNumber;
								}

								//save the float result as a string so it can be popped back into the arraylist
								stringResult = String.valueOf(result);
								
								//remove arraylist index f + 1
								expressionArrayList.remove(f + 1);
								
								//remove arraylist index f
								expressionArrayList.remove(f);
								
								//remove 2 values from the arraylist to reflect the removal of these indexes
								f = f - 2;
								
								//save result in f - 1
								expressionArrayList.set(preIndex, stringResult);
							}
						}

						//return result as a float through parsing: parse the arraylist result as a float
						convertedResult = Float.parseFloat(expressionArrayList.get(0));
					}
					
					//If anything else, print invalid statement, set convertedResult to 0, return float.min_value	
					} else {
						System.out.println("Invalid input.");
						convertedResult = 0;
						return Float.MIN_VALUE;
					}

			//add the convertedResult to the history array; increment historyCount and evaluateCount		
			history[historyCount] = convertedResult;
			historyCount ++;
			evaluateCount ++;
			
			//print and return the convertedResult
			System.out.println(convertedResult);
			return convertedResult;		
	}

	//get method that returns the current value
	public float getCurrentValue() {
		
		return convertedResult; 
		
	}

	//MEMORY and History methods 
	//when m is pressed, this is called from the main method to save the current result
	public void setMemoryValue(float memval) {
		
		memory = memval;
	
	}
	
	//when mr is pressed, this is called from the main method to print the value stored in memory
	public float getMemoryValue() {		
		
		System.out.println(memory);
		return memory;
	}

	//when c is pressed, this is called from the main method to set memory to 0
	public void clearMemory() {
		
		memory = 0;	
	}

	//returns history value at specified parameter
	public float getHistoryValue(int index) {
		
			return history[index];
	}
		
	
	//when h is pressed, this is called from the main method to print all the past results
	public float[] allHistory() {
		
		for (int j = 0; j < evaluateCount; j++) {
			
			System.out.print(history[j] + " ");
		}
		
		return history;
	}
	
	//MAIN METHOD
	public static void main (String[] args) {
		//initialise variables used in method
		Calculator calculatorObject = new Calculator();	
		String expression;
		
		//Prompt user to input the expression
		System.out.println("Enter expression:");
		
		//do this until x is pressed
		do {
			
			//get input (i was also very confused about all the conflicting annoucements about Scanner some saying its okay others saying its not)
			Scanner calculatorInput = new Scanner(System.in);
			
			expression = calculatorInput.nextLine();

			//switch to decide what to do with input
			switch(expression) {
			
					//if x, exit
					case "x":
						break;
						
					//if m, store the most recent calculator result; zero if invalid
					case "m":
						calculatorObject.setMemoryValue(calculatorObject.convertedResult);
						break;
			
					//mr print the stored memory value; zero if no value has been stored	
					case "mr":
						calculatorObject.getMemoryValue();
						break;
			
					//clear array; set to 0; call clearMemory
					case "c":
						calculatorObject.clearMemory();
						break;
					
					//print the history by calling allHistory
					case "h":
						calculatorObject.allHistory();
						break;	
					
					//if not above, run the evaluate method to work out calculation	
					default:
						calculatorObject.evaluate(expression);
						break;
			}
			
		//until the user is done, indicated by pressing x	
		} while (!expression.equals("x"));
	}
}




