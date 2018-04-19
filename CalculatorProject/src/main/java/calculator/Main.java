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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		initializeLogging();
        
        logHandle.info("Entering Main");
        
		System.out.println("Hello");
		
		logHandle.info("Exiting Main");
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
            Logger.getRootLogger().setLevel(Level.INFO);
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
