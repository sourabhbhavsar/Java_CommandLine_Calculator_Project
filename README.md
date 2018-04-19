# synopsys_project
**Java Candidate Homework**:

## Functional Requirements*
Write a calculator program in Java that evaluates expressions in a very simple integer expression language. The program takes an input on the command line, computes the result, and prints it to the console.

## How to Run the Program
An expression needs to be passed to the program in the form of a command line parameter. Following are the examples:
- java calculator.Main "add(1,2)"	
- java calculator.Main "add(1, mult(2, 3))"	
- java calculator.Main "mult(add(2, 2), div(9, 3))"	
- java calculator.Main "let(a, 5, add(a, a))"	
- java calculator.Main "let(a, 5, let(b, mult(a, 10), add(b, a)))"	
- java calculator.Main "let(a, let(b, 10, add(b, b)), let(b, 20, add(a, b))"	


