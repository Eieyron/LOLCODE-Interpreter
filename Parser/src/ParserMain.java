// Main.java
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) {
		LexicalAnalyzer l = new LexicalAnalyzer(args[0]);

		Stack<Token> s = l.getStack();

		for(Token t: s){
			t.show();
		}
		/*Stack<Token> r = new Stack<Token>();
		while(!s.empty()){r.push(s.pop());}

		for(Token t : r){
			t.show();
		}*/
	}
}