import javax.swing.*;

public class Token {
	// attribute
	private String content;
	private String type;

	// constructor
	public Token(String content, String type) {
		this.content = content;
		this.type = type;
	}

	// methods
	public void show(){
		System.out.println(this.content + " => " + this.type);
	}

	public void show(JTextArea lexHolder, JTextArea claHolder){
		lexHolder.append(this.content + "\n");
		claHolder.append(this.type + "\n");
	}

	public String getContent(){
		return this.content;
	}

	public String getType(){
		return this.type;
	}
}