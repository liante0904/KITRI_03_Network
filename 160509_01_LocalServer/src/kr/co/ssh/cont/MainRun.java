package kr.co.ssh.cont;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainRun {
	public static void main(String[] args) {
		// Server 원본 데이터
		String _serverData = "서버에 있는 데이터 값 입니다.";


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
		int _userPort = 60606;


		// Server Socket 구성

		ServerSocket _userSS = null;
		try {
			_userSS = new ServerSocket();
			// Socket 구성시 필요 요소 : IP, Port
			_userSS.bind(new InetSocketAddress(_ia.getHostAddress(), _userPort));

			// 구성된 ServerSocket Open
			boolean 작동;
			작동 = true;
			int count = 0;
			Socket _socket;
			InputStream _is;
			
			while(작동){

				_socket = _userSS.accept();
				count++;
				if(count >= 20){
					작동 = false;
				}

				System.out.println(count + "번째 connection");
				// Server -> Client = 원본 서버 자료를 클라이언트에 전달
				// OutputStream 데이터를 외부에 전달 시킴
				// Socket 객체를 통해 OutputStream 연결

				OutputStream _os = _socket.getOutputStream();
				// 일반 Data를 byte 단위로 변환 
				//(Stream은 byte단위로 데이터를 송수신하기 때문
				
				byte[] _bytes = null;
				_bytes = new byte[1000];
				// 서버에 OutputStream에 있는 값을 가져오기
				_is = _socket.getInputStream(); 


				int _i = _is.read(_bytes);
				_serverData = new String(_bytes, 0, _i);
				
				System.out.println("서버에서 부터 받은 값 : " + _serverData);
				//다시 서버쪽에 값을 전달
				
				_bytes = _serverData.getBytes();
				_os.write(_bytes); // OutputStream, (버퍼에 데이터를 축척)
				_os.flush(); //버퍼에 있는 것을 전송하여 출력
				_is.close();
				_os.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				_userSS.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}





	}

}
