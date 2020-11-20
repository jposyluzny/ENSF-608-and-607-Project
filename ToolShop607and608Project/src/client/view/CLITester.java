package client.view;

import java.io.BufferedReader;

public class CLITester {

	private BufferedReader stdIn;
	
	public CLITester() {
		this.setStdIn(stdIn);
	}
	
	public void run() {
		
	}

	public BufferedReader getStdIn() {
		return stdIn;
	}

	public void setStdIn(BufferedReader stdIn) {
		this.stdIn = stdIn;
	}

}
