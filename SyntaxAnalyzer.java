//SyntaxAnalyzer.java
import java.util.*;
import javax.swing.*;
import java.util.regex.*;

public class SyntaxAnalyzer {
	// attributes
	private Stack<Token> tokenStack;
	private Token next;
	private static int currentLine;
	private Stack<String> errorReports;

	/* List of DataTypes 

		Source Code Delimiter
		Visible Keyword
		Gimmeh Keyword
		Increment
		Decrement
		Hard Typecast
		Variable Expression
		Variable Initialization
		Variable Assignment
		Boolean Unary
		Boolean End
		Different
		Operation Separator
		String Concatenate
		Switch Start
		Condition End
		If-else Keyword
		Case Keyword
		Default Keyword
		Break Keyword
		Integer Literal
		Float Literal
		String Literal
		String Delimiter
		Boolean Liteal
		Variable Identifier
		Newline
	*/

	// constructor
	public SyntaxAnalyzer(Stack<Token> tokenStack){
		Collections.reverse(tokenStack);
		this.tokenStack = tokenStack;
		this.currentLine = 1;
		this.errorReports = new Stack<String>();
		errorHandler();
	}

	public void errorHandler(){

		this.next = this.tokenStack.pop();

		System.out.println(codeChecker(this.next, this.tokenStack)? "Code will run" : "Cannot Interpret Due to Previous Errors");
	
	}



	/*
		<codeChecker> ::= <Source Code Delimiter>
		<Source Code Delimiter> ::= <Visible Keyword> | <Gimmeh Keyword> | <Declaration> | <Newline>
		<Newline> ::= <Visible Keyword> | <Gimmeh Keyword> | <Declaration> | <Newline>
		<Visible Keyword> ::= <ends with Literal> | <Variable Identifier> |  <Newline>
		<Variable Identifier1> ::= <an> <Variable Identifier> | <Variable Identifier>
		<Declaration> ::= <Variable Identifier2>
		<Variable Identifier2> ::= <Variable Initialization> | <Newline>
		<Variable Initialization> ::= <ends with Literal>
		<ends with Literal> ::= <Newline>
	*/
	
	private boolean codeChecker(Token next, Stack<Token> tokenStack){ // checks the start of the code
	
		if(next.getType().equals("Source Code Delimiter")){
	
			System.out.println("Code Start Successful");
	
			return programChecker(tokenStack.pop(), tokenStack);
	
		}else return false;
	}

	private boolean programChecker(Token next, Stack<Token> tokenStack){
		
		if(next.getType().equals("Visible Keyword")){

			System.out.println(next.getType()+" found");

			return visibleChecker(tokenStack.pop(), tokenStack);

		}else if(next.getType().equals("Gimmeh Keyword")){

			System.out.println(next.getType()+" found");
			
			return programChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Variable Declaration")){
		
			System.out.println(next.getType()+" found");
		
			return declarationChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Newline")){
		
			this.currentLine += 1;

			if(tokenStack.size() == 0)return false;
		
			return programChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Source Code Delimiter")){
		
			System.out.println("Code End Successfull");
		
			return true;
		
		}else{
		
			System.out.println("Error at line: "+this.currentLine+ " => " +next.getType() + " type is not valid.");
		
			return false;
		
		}
	}

	private boolean visibleChecker(Token next, Stack<Token> tokenStack){
		
		if(next.getType().equals("Variable Identifier")){
		
			System.out.println(next.getType()+" found");
		
			return visibleChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Integer Literal")){
		
			System.out.println(next.getType()+" found");
		
			return visibleChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Float Literal")){
		
			System.out.println(next.getType()+" found");
		
			return visibleChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("String Literal")){
		
			System.out.println(next.getType()+" found");
		
			return visibleChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Boolean Literal")){
		
			System.out.println(next.getType()+" found");
		
			return visibleChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Newline")){
		
			this.currentLine += 1;

			if(tokenStack.size() == 0)return false;
		
			return programChecker(tokenStack.pop(), tokenStack);
		
		}else{
		
			System.out.println("Error at line: "+this.currentLine+ " => " +next.getType() + " type is not valid.");
		
			return false;
		
		}
	}

	private boolean declarationChecker(Token next, Stack<Token> tokenStack){
		
		if(next.getType().equals("Variable Identifier")){
		
			System.out.println(next.getType() + " found");
		
			return declaration_variableIdentifierChecker(tokenStack.pop(), tokenStack);
		
		}else{
		
			System.out.println("Error at line: "+this.currentLine+ " => " +next.getType() + " type is not valid.");
		
			return false;
		
		}
	}

	private boolean declaration_variableIdentifierChecker(Token next, Stack<Token> tokenStack){

		if(next.getType().equals("Variable Initialization")){

			System.out.println(next.getType() + " found");

			return variableInitializationChecker(tokenStack.pop(), tokenStack);

		}else if(next.getType().equals("Newline")){

			this.currentLine += 1;

			if(tokenStack.size() == 0)return false;

			return programChecker(tokenStack.pop(), tokenStack);

		}else{
		
			System.out.println("Error at line: "+this.currentLine+ " => " +next.getType() + " type is not valid.");
		
			return false;
		
		}
	}

	private boolean variableInitializationChecker(Token next, Stack<Token> tokenStack){

		if(next.getType().equals("Variable Identifier")){
		
			System.out.println(next.getType()+" found");
		
			return programChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Integer Literal")){
		
			System.out.println(next.getType()+" found");
		
			return programChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Float Literal")){
		
			System.out.println(next.getType()+" found");
		
			return programChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("String Literal")){
		
			System.out.println(next.getType()+" found");
		
			return programChecker(tokenStack.pop(), tokenStack);
		
		}else if(next.getType().equals("Boolean Literal")){
		
			System.out.println(next.getType()+" found");
		
			return programChecker(tokenStack.pop(), tokenStack);
		
		}else{
		
			System.out.println("Error at line: "+this.currentLine+ " => " +next.getType() + " type is not valid.");
		
			return false;
		
		}

	}

}