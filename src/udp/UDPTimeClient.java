package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

public class UDPTimeClient {

	private static final String SERVER_IP = "218.39.221.82";
	
	
	public static void main(String[] args) {
		
		DatagramSocket socket = null;
		try {
			
			socket = new DatagramSocket();
			System.out.print("지금시간은: ");
			String message = "connection ok";
			//4. 메세지 전송
			byte[] data = message.getBytes("UTF-8");
			DatagramPacket sendPacket = new DatagramPacket(data,data.length,new InetSocketAddress(SERVER_IP, UDPTimeServer.PORT));
			socket.send(sendPacket);
			
			//5.메세지 수신
			DatagramPacket receivePacket = new DatagramPacket(new byte[UDPTimeServer.BUFFER_SIZE],UDPTimeServer.BUFFER_SIZE);
			socket.receive(receivePacket);
			
			message = new String(receivePacket.getData(),"UTF-8");
			System.out.println(message);
		
		}catch(IOException e) {
			e.getStackTrace();
		}

		

	}

}
