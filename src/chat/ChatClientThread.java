package chat;

import java.io.BufferedReader;
import java.io.IOException;


public class ChatClientThread extends Thread {
	private BufferedReader bufferedReader;
	
	public ChatClientThread(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	@Override
	public void run() {
		try {
			while(true) {
			System.out.println(bufferedReader.readLine());
			System.out.print(">>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
