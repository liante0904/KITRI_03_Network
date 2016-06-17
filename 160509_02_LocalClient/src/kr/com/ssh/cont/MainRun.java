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
		String _clientData = "Ŭ���̾�Ʈ�� �������Դϴ�.";
		// Client Program
		// Socket API
		// IP �ּҰ� ����
		InetAddress _ia = null;
		try {
			_ia = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(_ia.getHostName());
		System.out.println(_ia.getHostAddress());
		// Port��ȣ ����
		// 0~65535��Ʈ ��ȣ ���� ����
		// 60606 < ����
		int _userPort = 60600;

		// Socket API ������ ������ �� �ֵ��� ����
		// ���� �ּ� �� ��Ʈ�� ����
		String _serverIP = "192.168.14.171";
		int _serverPort = 60606;

		Socket _socket = null;
		try {
			//	�����ϰ��� �ϴ� Server IP, Port ����
			//_socket = 	new Socket(_serverIP, _serverPort);
			_socket = new Socket();
			_socket.connect(new InetSocketAddress(_serverIP, _serverPort));
			System.out.println("���� ���� ����");
			byte[] _bytes = null;
			_bytes = new byte[1000];
					
			OutputStream _os = _socket.getOutputStream();
			_bytes = _clientData.getBytes();
			_os.write(_bytes);
			_os.flush();
			// Client���� ���� �� �޾ƿ���
			
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
