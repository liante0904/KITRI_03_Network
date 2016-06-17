import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	
	public void start()
	{
		String ser_ip = "localhost"; // 서버 ip
		int ser_port = 8888; // 포트 
		ServerSocket ss;
		
		try {
			ss = new ServerSocket(); // 1. ServerSocket 객체 생성
			//ss.bind(new InetSocketAddress(ser_ip, ser_port)); // 1-2 바인딩
			Socket soc = new Socket(); // 2. Socket 객체 생성

			while(true){
				soc = ss.accept(); 		//  클라이언트의 요청을 받아, 소켓에 서버소켓 리턴
			

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


		String ser_ip = "localhost"; // 접속 ip
		int ser_port = 8888; 	// 접속 포트


		Socket soc = new Socket(); // 1. Socket 객체 생성
		try {
			soc.connect(new InetSocketAddress(ser_ip, ser_port));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 서버 접속 IP, Port
		System.out.println("채팅 서버 접속 성공!");

		DataOutputStream dos;
		try {
			dos = new DataOutputStream(soc.getOutputStream());
			Client client = new Client();
			client.start();
			
			while(true){
				Scanner sc =new Scanner(System.in);
				String imsg = "";
				imsg = sc.nextLine();

				dos.writeUTF(imsg);
				System.out.println(imsg);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	} 

}
