package calculator; 

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;


/**
 * The following class will simulate the functioning of a command line calculator
 * Expressions can be passed through the command line arguments and the calculator
 * will evaluate the expression and will return the result
 * 
 * Example expressions and results are as follows
 * 
 * add(1,2) = 	3
 * add(1, mult(2, 3)) =	7
 * mult(add(2, 2), div(9, 3)) =	12
 * let(a, 5, add(a, a))	= 10
 * let(a, 5, let(b, mult(a, 10), add(b, a))) = 55
 * let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b)) = 40
 * 
 */
public class Calculator {

	// The log handle which can be used to print log messages at
	// different level like INFO, ERROR and DEBUG
	final static Logger logHandle = Logger.getLogger(Calculator.class);

	//ExpressionInput would store the input expression passed over the command line.
	private String expressionInput;

	// tokens will contains individual lexems after parsing the input expression
	private String[] tokens;
	// to store the ans of the evaluation of expression.
	private long answer;
	//pos will keep track of the index of the current token to be processed.
	private int pos;


	// let command will let the calculator create identifiers and 
	// this array will hold their evaluated values.
	private long[] identifiers;

	// Default Constructor
	public Calculator() {    	
		initializeLogHandle();
		logHandle.info("Initializing Calculator");
	}

	public String getExpressionInput() {
		return expressionInput;
	}


	public void setExpressionInput(String expressionInput) {
		this.expressionInput = expressionInput;
	}

	public void convertIdentifiersToSmallCase()
	{
		
	}
	
	
	// The main function that is responsible for evaluating an expression.
	public long evaluate() {
		logHandle.info("Entering evaluate method");

		// Validate the command line paramenters
		if(expressionInput == null || expressionInput.isEmpty()) {
			logHandle.error("Incorect usage! No Command line arguements were passed.");
			System.out.println("Incorrect Usage. No Command line arguements were passed");
			System.out.println("Example Usage : ");
			System.out.println("Java Calculator.Main \"add(1,2)\"");
			System.out.println("OR");
			System.out.println("Java Calculator.Main \"let(a, 5, add(a, a))\"");
			System.exit(0);
		}


		//split the expression input string to extract tokens
		tokens = expressionInput.split("[\\W]+");
		identifiers = new long[26];

		logHandle.info("Extracted the tokens[]: " + (Arrays.toString(tokens)));
		// process the extracted tokens and return the answer.
		answer = processToken();

		logHandle.info("Answer: " + answer);
		return answer;
	}




	// Method to process tokens
	// the add token should perform addition
	// the mult token should perform multiplication
	// the sub token should perform substraction
	// the div token should perform divsion
	// the let token should assign values to variables
	private long processToken() {

		logHandle.trace("Entering " + Thread.currentThread().getStackTrace()[1].getMethodName());

		long value = 0;
		String tokenStr = tokens[pos];


		if (tokenStr.equalsIgnoreCase("add"))
		{
			logHandle.info("Encountered Add");
			value = performAdd();
		}
		else if (tokenStr.equalsIgnoreCase("mult"))
		{
			logHandle.info("Encountered Mult");
			value = performMult();
		}
		else if (tokenStr.equalsIgnoreCase("sub"))
		{
			logHandle.info("Encountered sub");
			value = performSub();
		}
		else if (tokenStr.equalsIgnoreCase("div"))
		{
			logHandle.info("Encountered Div");
			value = performDiv();
		}
		else if (tokenStr.equalsIgnoreCase("let"))
		{
			logHandle.info("Encountered Let");
			value = performLet();
			pos++;
			value = processToken();
		}
		// this should not happen
		else 
		{
			char ch = tokenStr.charAt(0);
			// test if this identifier is already evaluated and if thst is the case
			// we can safely return it
			if(tokens[pos].length() == 1 && (ch >= 'a' && ch <= 'z')) {
				logHandle.info("Encountered preset value: " + identifiers[ch - 97]);
				return identifiers[ch - 97];
			}
			else {
				// This means we have found a literal value
				logHandle.info("Encountered Literal number " + tokens[pos]);

				value = Long.parseLong(tokens[pos]);
			}
		}

		logHandle.trace("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());

		return value;
	}


	// adds the two tokens by fetching their value from teh array of tokens by
	// incrementing the position pos
	private long performAdd() {

		logHandle.trace("Entering " + Thread.currentThread().getStackTrace()[1].getMethodName());

		// Extracting the operands for the add operation
		// by incrementing the position pos.
		pos++;
		long v1 = processToken();
		pos++;
		long v2 = processToken();

		// Perform the addition.
		long value = v1 + v2;

		logHandle.info("Evaluated Add resulting in the value: " + value);

		logHandle.trace("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());

		return value;
	}

	// subtracts the two tokens by fetching their value from the array of tokens by
	// incrementing the position pos
	private long performSub() {

		logHandle.trace("Entering " + Thread.currentThread().getStackTrace()[1].getMethodName());

		// Extracting the operands for the subtraction operation
		// by incrementing the position pos.    	
		pos++;
		long v1 = processToken();
		pos++;
		long v2 = processToken();
		long value = v1 - v2;

		logHandle.info("Evaluated Sub resulting in the value: " + value);

		logHandle.trace("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());

		return value;
	}


	// Multiply the two tokens by fetching their value from the array of tokens by
	// incrementing the position pos
	private long performMult() {

		logHandle.trace("Entering " + Thread.currentThread().getStackTrace()[1].getMethodName());

		// Extracting the operands for the mult operation
		// by incrementing the position pos.
		pos++;
		long v1 = processToken();
		pos++;
		long v2 = processToken();
		long value = v1 * v2;

		logHandle.info("Evaluated Mult resulting in the value: " + value);

		logHandle.trace("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());

		return value;
	}


	// Divide the two tokens by fetching their value from teh array of tokens by
	// incrementing the position pos
	private long performDiv() {

		logHandle.trace("Entering " + Thread.currentThread().getStackTrace()[1].getMethodName());

		// Extracting the operands for the division operation
		// by incrementing the position pos.
		pos++;
		long v1 = processToken();
		pos++;
		long v2 = processToken();
		long value = v1 / v2;

		logHandle.info("Evaluated Mult resulting in the value: " + value);

		logHandle.trace("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());

		return value;
	}

	// performLet method lets the calculator assign value to an identifier.
	private long performLet() {

		logHandle.trace("Entering " + Thread.currentThread().getStackTrace()[1].getMethodName());

		// Extracting the operands for the let operation
		// by incrementing the position pos.
		pos++;
		char val = tokens[pos].charAt(0);
		pos++;
		identifiers[val-97] = processToken();
		long value = processToken();

		logHandle.info("Evaluated Mult resulting in the value: " + value);

		logHandle.trace("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());

		return value;
	}


	// Initialize the log handler to be able to use logging 
	private void initializeLogHandle() {
		FileInputStream fis = null;
		try {
			Properties logProperties = new Properties();
			fis = new FileInputStream("src/main/resources/log4j.properties");
			logProperties.load(fis);

			PropertyConfigurator.configure(logProperties);
			//set the level to debug
			Logger.getRootLogger().setLevel(Level.ALL);
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Encountered a problem in initializing the Logger.");
		}
		finally
		{
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Encountered a problem in closing the input stream.");
			}
		}
	}
}
