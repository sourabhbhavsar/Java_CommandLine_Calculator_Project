package calculator;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.FileInputStream;
import java.io.IOException;
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

public class Main {

	// The log handle which can be used to print log messages at
	// different level like INFO, ERROR and DEBUG
	final static Logger logHandle = Logger.getLogger(Main.class);

	// Utility function to print the usage for the program.
	public static void printUsage()
	{
		System.out.println("********************************************************");
		System.out.println("Example Usage : ");
		System.out.println("Java Calculator.Main \"add(1,2)\"");
		System.out.println("OR");
		System.out.println("Java Calculator.Main \"let(a, 5, add(a, a))\"");
		System.out.println("********************************************************");
	}

	// The main driver of the program
	// evaluates the passed expression with the
	// help of calculator.

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initializeLogging();

		logHandle.trace("Entering " + Thread.currentThread().getStackTrace()[1].getMethodName());

		// If there are fewer than 1 arguments then 
		// flash the error and example usage.
		if(args.length < 1) 
		{        	
			logHandle.error("Incorect usage! No Command line arguements were passed.");
			System.out.println("Error: Incorrect Usage. No Command line arguements were passed");
			printUsage();
			System.exit(0);
		}

		logHandle.info("Received Command Line Arguments: " + args[0]);

		// Eliminate the whitespace from the expression.
		args[0] = args[0].replaceAll("\\s","");


        // Create the calculator and evaluate the expression string input.
        Calculator calculator = new Calculator();
        calculator.setExpressionInput(args[0]);

        logHandle.trace("Exiting " + Thread.currentThread().getStackTrace()[1].getMethodName());
	}

	// Initialize the logging properties using
	// the property file from the resource folder.
	private static void initializeLogging() {

		FileInputStream fis = null;
		try 
		{
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
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Encountered a problem in closing the input stream.");
			}
		}
	}

}
