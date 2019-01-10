package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class ChatClient {
	private static final int PORT = 6666;
	private static final String SERVER_IP = "218.39.221.82";
	
	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		try {
		   //1. 키보드 연결
			scanner = new Scanner(System.in);
		   //2. socket 생성
			socket = new Socket();
		   //3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, PORT));

		   //4. reader/writer 생성
			BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( socket.getInputStream(), StandardCharsets.UTF_8 ) );
			PrintWriter	printWriter = new PrintWriter( new OutputStreamWriter( socket.getOutputStream(), StandardCharsets.UTF_8 ), true );
				
		   //5. join 프로토콜
		   System.out.print("닉네임>>" );
		   String nickname = scanner.nextLine();
		   printWriter.println( "join:" + nickname );
		   printWriter.flush();

		   //6. ChatClientReceiveThread 시작
		   new ChatClientThread(bufferedReader).start();
		   //7. 키보드 입력 처리
		   while( true ) {
			      String input = scanner.nextLine();
			      if( "quit".equals( input ) == true ) {
			    	  printWriter.println( "quit:");
			          break;
			      } else {
			    	  printWriter.println( "message:" + input);
			      }
			   }
		} catch( IOException ex ) {
		       log( "error:" + ex );
		} finally {
		     scanner.close();
		     try {
		    	 if(socket != null)
				socket.close();
			} catch (IOException e) {
				  log( "error:" + e );
			}
		}

		
	}
	
	public static void log(String message) {
		System.out.println("[Client#" + Thread.currentThread().getId()  + "] " + message);
	}

}
