
[![Build Status](https://travis-ci.org/sourabhbhavsar/synopsys_project.svg?branch=master)](https://travis-ci.org/sourabhbhavsar/synopsys_project)


# Java Command Line Calculator

## Functional Requirements
Write a calculator program in Java that evaluates expressions in a very simple integer expression language. The program takes an input on the command line, computes the result, and prints it to the console.

## Detailed Requirements
An expression is one of the of the following:
- Numbers: integers between Integer.MIN_VALUE and Integer.MAX_VALUE
- Variables: strings of characters, where each character is one of a-z, A-Z
- Arithmetic functions: add, sub, mult, div, each taking two arbitrary expressions as arguments.  In other words, each argument may be any of the expressions on this list.
- A “let” operator for assigning values to variables:
	let(<variable name>, <value expression>, <expression where variable is used>)
As with arithmetic functions, the value expression and the expression where the variable is used may be an arbitrary expression from this list. 


## How to Run the Program
An expression needs to be passed to the program in the form of a command line parameter. Following are the examples:
- java calculator.Main "add(1,2)"	
- java calculator.Main "add(1, mult(2, 3))"	
- java calculator.Main "mult(add(2, 2), div(9, 3))"	
- java calculator.Main "let(a, 5, add(a, a))"	
- java calculator.Main "let(a, 5, let(b, mult(a, 10), add(b, a)))"	
- java calculator.Main "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))"	


## Sample Input and output
- Sample 1 "add(1,2)"  =>  3	
- Sample 2 "add(1, mult(2, 3))"  =>  7	
- Sample 3 "mult(add(2, 2), div(9, 3))"  =>  12	
- Sample 4 "let(a, 5, add(a, a))"	  =>  10
- Sample 5 "let(a, 5, let(b, mult(a, 10), add(b, a)))"	 =>  55
- Sample 6 "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))"  =>  40	

## JUnit Test Cases
The project contains unit test cases for the provided 6 sample inputs and outputs and the class CalculatorTestSute implements the 6 jUnit test case located in the SRC/TEST/JAVA folder of the project under the test package.

## Assumptions
- Identifiers created in the let command can only be between a to z (small case). This gives total 26 variables at the maximum.
- It performs a cases insensitive calculation of expression. A and a are considered to be same variables.
- All the operations like Add, sub etc would be first converted to small case and then processed and hence it perfroms a case insensitive operation.

## Logging Level
The program is integrated with log4j and can log different level of loggings like:
- TRACE
- ERROR
- INFO
- WARN
- DEBUG

## Continuos Integration:
The repository is linked with Travis CI and can build automatically with push and commit to repositories and it's build status is shown at the top of the readMe file.


