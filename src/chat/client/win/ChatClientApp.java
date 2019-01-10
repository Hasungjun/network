package chat.client.win;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import chat.ChatClientThread;



public class ChatClientApp {
	private static final int PORT = 6666;
	private static final String SERVER_IP = "218.39.221.82";

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		String name = null;
		
	try {	
		scanner = new Scanner(System.in);
		socket = new Socket();
		
		socket.connect(new InetSocketAddress(SERVER_IP, PORT));
		
		BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( socket.getInputStream(), StandardCharsets.UTF_8 ) );
		PrintWriter	printWriter = new PrintWriter( new OutputStreamWriter( socket.getOutputStream(), StandardCharsets.UTF_8 ), true );
		
		
		
		while( true ) {
			
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();
			
			if (name.isEmpty() == false ) {	
				 printWriter.println( "join:" + name );
				 printWriter.flush();
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
		scanner.close();
		//////////////////////////////
		//join 처리					//
		//Response가 "join:ok" 이면	//
		//////////////////////////////
		ChatWindow cw = new ChatWindow(name,printWriter);
		
		//new ChatClientThread(cw).start();
		 new ChatClientThread(bufferedReader,cw).start();
		
		 
		 
		 
	}catch(Exception e) {
		System.out.println(e);
	}
	}

}
