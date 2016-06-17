import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server extends Thread{

	public void start()
	{
		String ser_ip = "localhost"; // 서버 ip
		int ser_port = 8888; // 포트 
		ServerSocket ss;
		
		try {
			ss = new ServerSocket(); // 1. ServerSocket 객체 생성
			ss.bind(new InetSocketAddress(ser_ip, ser_port)); // 1-2 바인딩
			Socket soc = new Socket(); // 2. Socket 객체 생성

			while(true){
				soc = ss.accept(); 		//  클라이언트의 요청을 받아, 소켓에 서버소켓 리턴
				System.out.println("클라이언트가 접속 하였습니다.");

				DataInputStream dis = new DataInputStream(soc.getInputStream());
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream());

				String imsg = ""; // 클라이언트가 전송한 메세지
				imsg =dis.readUTF();
				System.out.println(imsg);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public static void main(String[] args) {


		String ser_ip = "localhost"; // 서버 ip
		int ser_port = 8888; // 포트 
		ServerSocket ss;
		try {
			ss = new ServerSocket(); // 1. ServerSocket 객체 생성
			ss.bind(new InetSocketAddress(ser_ip, ser_port)); // 1-2 바인딩
			Socket soc = new Socket(); // 2. Socket 객체 생성

			Server server = new Server();
			server.run();
			while(true){
				soc = ss.accept(); 		//  클라이언트의 요청을 받아, 소켓에 서버소켓 리턴
				System.out.println("클라이언트가 접속 하였습니다.");

				DataInputStream dis = new DataInputStream(soc.getInputStream());
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream());

				Scanner sc =new Scanner(System.in);
				String imsg = "";
				imsg = sc.nextLine();

				dos.writeUTF(imsg);

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		



	}
}




