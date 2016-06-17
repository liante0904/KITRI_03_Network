package kr.co.ssh.cont;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import kr.co.ssh.domain.EmpBean;
import kr.co.ssh.model.EmpDao;

public class ServerStart {
	// Server Socket ���� �޼ҵ�

	String Ser_ip = "192.168.14.171";
	int Ser_port = 50000;
	public void startServer(EmpBean eb)
	{
		ServerSocket ss = null;
		Socket socket = null;
		ObjectOutputStream oos = null;


		try {
			// Server �������� Ŭ���̾�Ʈ�� ���� ��û ���
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress(Ser_ip, Ser_port));
			System.out.println("���� �����");
			socket = ss.accept();
			oos = new ObjectOutputStream(socket.getOutputStream());
			// Ŭ���̾�Ʈ�� ���� ��û�� ����, �ڷ� ����
			// EmpBean �ڷ� ���� �Ҽ� �ֵ��� ����
			oos.writeObject(eb);
			oos.flush();
			System.out.println("oos ���� �Ϸ�");
			oos.close();
			socket.close();
			ss.close();
			System.out.println("���� ���� ����");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public EmpBean conServer(String Ser_ip, int Ser_port) {
		// Client�� ������ �����ϴ� ���
		Socket socket = new Socket();
		InetSocketAddress isa = new InetSocketAddress(Ser_ip, Ser_port);
		ObjectInputStream ois = null;
		try {
			// client Server Socket�� �����Ŵ
			socket.connect(isa);
			// Server OutputStream ���޵� �ڷ�
			// InputStream ���ؼ� �޾ƿ���

			ois = new ObjectInputStream(socket.getInputStream());
			EmpBean eb = (EmpBean)ois.readObject();
			ois.close();
			socket.close();

			return eb;
			// ������ ���� ���� �޾Ҵٸ� eb�� �����ϰ�

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		// ������ ���� ���޹��� ���ߴٸ� null ��ȯ
	}


	public void startServerDB()
	{
		ServerSocket ss = null;
		Socket socket = null;
		ObjectOutputStream oos = null;


		try {
			// Server �������� Ŭ���̾�Ʈ�� ���� ��û ���
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress(Ser_ip, Ser_port));
			System.out.println("���� �����");
			socket = ss.accept();
			oos = new ObjectOutputStream(socket.getOutputStream());


			//DB���� ���� �޾ƿ� ���� �Է��Ѵ�.
	

			EmpDao ed = new EmpDao();
			List<EmpBean> emp = new ArrayList<EmpBean>();  
			emp = ed.getEmpAll();

			oos.writeObject(emp);
			oos.flush();
			System.out.println("oos ���� �Ϸ�");
			oos.close();
			socket.close();
			ss.close();
			System.out.println("���� ���� ����");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		public List<EmpBean> conDBServer(String Ser_ip, int Ser_port) {
			// Client�� ������ �����ϴ� ���
			Socket socket = new Socket();
			InetSocketAddress isa = new InetSocketAddress(Ser_ip, Ser_port);
			ObjectInputStream ois = null;
			try {
				// client Server Socket�� �����Ŵ
				socket.connect(isa);
				// Server OutputStream ���޵� �ڷ�
				// InputStream ���ؼ� �޾ƿ���

				ois = new ObjectInputStream(socket.getInputStream());
				List<EmpBean> eb = (List<EmpBean>)ois.readObject();


				ois.close();
				socket.close();

				return eb;
				// ������ ���� ���� �޾Ҵٸ� eb�� �����ϰ�

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
			// ������ ���� ���޹��� ���ߴٸ� null ��ȯ
		}
	
}




