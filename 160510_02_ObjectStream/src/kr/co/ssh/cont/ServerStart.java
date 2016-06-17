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
	// Server Socket 생성 메소드

	String Ser_ip = "192.168.14.171";
	int Ser_port = 50000;
	public void startServer(EmpBean eb)
	{
		ServerSocket ss = null;
		Socket socket = null;
		ObjectOutputStream oos = null;


		try {
			// Server 구축으로 클라이언트로 부터 요청 대기
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress(Ser_ip, Ser_port));
			System.out.println("서버 대기중");
			socket = ss.accept();
			oos = new ObjectOutputStream(socket.getOutputStream());
			// 클라이언트로 부터 요청이 오면, 자료 전송
			// EmpBean 자료 전달 할수 있도록 구성
			oos.writeObject(eb);
			oos.flush();
			System.out.println("oos 세팅 완료");
			oos.close();
			socket.close();
			ss.close();
			System.out.println("서버 소켓 종료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public EmpBean conServer(String Ser_ip, int Ser_port) {
		// Client가 서버에 접속하는 모듈
		Socket socket = new Socket();
		InetSocketAddress isa = new InetSocketAddress(Ser_ip, Ser_port);
		ObjectInputStream ois = null;
		try {
			// client Server Socket에 연결시킴
			socket.connect(isa);
			// Server OutputStream 전달된 자료
			// InputStream 통해서 받아오기

			ois = new ObjectInputStream(socket.getInputStream());
			EmpBean eb = (EmpBean)ois.readObject();
			ois.close();
			socket.close();

			return eb;
			// 서버로 값을 전달 받았다면 eb로 리턴하고

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
		// 서버로 값을 전달받지 못했다면 null 반환
	}


	public void startServerDB()
	{
		ServerSocket ss = null;
		Socket socket = null;
		ObjectOutputStream oos = null;


		try {
			// Server 구축으로 클라이언트로 부터 요청 대기
			ss = new ServerSocket();
			ss.bind(new InetSocketAddress(Ser_ip, Ser_port));
			System.out.println("서버 대기중");
			socket = ss.accept();
			oos = new ObjectOutputStream(socket.getOutputStream());


			//DB에서 부터 받아온 값을 입력한다.
	

			EmpDao ed = new EmpDao();
			List<EmpBean> emp = new ArrayList<EmpBean>();  
			emp = ed.getEmpAll();

			oos.writeObject(emp);
			oos.flush();
			System.out.println("oos 세팅 완료");
			oos.close();
			socket.close();
			ss.close();
			System.out.println("서버 소켓 종료");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		public List<EmpBean> conDBServer(String Ser_ip, int Ser_port) {
			// Client가 서버에 접속하는 모듈
			Socket socket = new Socket();
			InetSocketAddress isa = new InetSocketAddress(Ser_ip, Ser_port);
			ObjectInputStream ois = null;
			try {
				// client Server Socket에 연결시킴
				socket.connect(isa);
				// Server OutputStream 전달된 자료
				// InputStream 통해서 받아오기

				ois = new ObjectInputStream(socket.getInputStream());
				List<EmpBean> eb = (List<EmpBean>)ois.readObject();


				ois.close();
				socket.close();

				return eb;
				// 서버로 값을 전달 받았다면 eb로 리턴하고

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
			// 서버로 값을 전달받지 못했다면 null 반환
		}
	
}




