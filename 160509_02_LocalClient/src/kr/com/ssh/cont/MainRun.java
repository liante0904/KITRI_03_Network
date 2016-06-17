package kr.com.ssh.cont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainRun {
	public static void main(String[] args){
		String _clientData = "클라이언트의 데이터입니다.";
		// Client Program
		// Socket API
		// IP 주소값 세팅
		InetAddress _ia = null;
		try {
			_ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(_ia.getHostName());
		System.out.println(_ia.getHostAddress());
		// Port번호 생성
		// 0~65535포트 번호 지정 가능
		// 60606 < 숫자
		int _userPort = 60600;

		// Socket API 서버에 접근할 수 있도록 구성
		// 서버 주소 및 포트값 지정
		String _serverIP = "192.168.14.171";
		int _serverPort = 60606;

		Socket _socket = null;
		try {
			//	접근하고자 하는 Server IP, Port 세팅
			//_socket = 	new Socket(_serverIP, _serverPort);
			_socket = new Socket();
			_socket.connect(new InetSocketAddress(_serverIP, _serverPort));
			System.out.println("서버 접속 성공");
			byte[] _bytes = null;
			_bytes = new byte[1000];
					
			OutputStream _os = _socket.getOutputStream();
			_bytes = _clientData.getBytes();
			_os.write(_bytes);
			_os.flush();
			// Client에서 부터 값 받아오기
			
			// InputStream
			InputStream _is = _socket.getInputStream();
			_clientData = new String(_bytes, 0, _is.read(_bytes));
			System.out.println(_clientData );
			_is.close();
			_os.close();
			_socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}
}
