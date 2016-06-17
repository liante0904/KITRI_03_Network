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
		String ser_ip = "localhost"; // ���� ip
		int ser_port = 8888; // ��Ʈ 
		ServerSocket ss;
		
		try {
			ss = new ServerSocket(); // 1. ServerSocket ��ü ����
			ss.bind(new InetSocketAddress(ser_ip, ser_port)); // 1-2 ���ε�
			Socket soc = new Socket(); // 2. Socket ��ü ����

			while(true){
				soc = ss.accept(); 		//  Ŭ���̾�Ʈ�� ��û�� �޾�, ���Ͽ� �������� ����
				System.out.println("Ŭ���̾�Ʈ�� ���� �Ͽ����ϴ�.");

				DataInputStream dis = new DataInputStream(soc.getInputStream());
				DataOutputStream dos = new DataOutputStream(soc.getOutputStream());

				String imsg = ""; // Ŭ���̾�Ʈ�� ������ �޼���
				imsg =dis.readUTF();
				System.out.println(imsg);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}
	
	public static void main(String[] args) {


		String ser_ip = "localhost"; // ���� ip
		int ser_port = 8888; // ��Ʈ 
		ServerSocket ss;
		try {
			ss = new ServerSocket(); // 1. ServerSocket ��ü ����
			ss.bind(new InetSocketAddress(ser_ip, ser_port)); // 1-2 ���ε�
			Socket soc = new Socket(); // 2. Socket ��ü ����

			Server server = new Server();
			server.run();
			while(true){
				soc = ss.accept(); 		//  Ŭ���̾�Ʈ�� ��û�� �޾�, ���Ͽ� �������� ����
				System.out.println("Ŭ���̾�Ʈ�� ���� �Ͽ����ϴ�.");

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




