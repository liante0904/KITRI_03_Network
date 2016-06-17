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
		// Server ���� ������
		String _serverData = "������ �ִ� ������ �� �Դϴ�.";


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
		int _userPort = 60606;


		// Server Socket ����

		ServerSocket _userSS = null;
		try {
			_userSS = new ServerSocket();
			// Socket ������ �ʿ� ��� : IP, Port
			_userSS.bind(new InetSocketAddress(_ia.getHostAddress(), _userPort));

			// ������ ServerSocket Open
			boolean �۵�;
			�۵� = true;
			int count = 0;
			Socket _socket;
			InputStream _is;
			
			while(�۵�){

				_socket = _userSS.accept();
				count++;
				if(count >= 20){
					�۵� = false;
				}

				System.out.println(count + "��° connection");
				// Server -> Client = ���� ���� �ڷḦ Ŭ���̾�Ʈ�� ����
				// OutputStream �����͸� �ܺο� ���� ��Ŵ
				// Socket ��ü�� ���� OutputStream ����

				OutputStream _os = _socket.getOutputStream();
				// �Ϲ� Data�� byte ������ ��ȯ 
				//(Stream�� byte������ �����͸� �ۼ����ϱ� ����
				
				byte[] _bytes = null;
				_bytes = new byte[1000];
				// ������ OutputStream�� �ִ� ���� ��������
				_is = _socket.getInputStream(); 


				int _i = _is.read(_bytes);
				_serverData = new String(_bytes, 0, _i);
				
				System.out.println("�������� ���� ���� �� : " + _serverData);
				//�ٽ� �����ʿ� ���� ����
				
				_bytes = _serverData.getBytes();
				_os.write(_bytes); // OutputStream, (���ۿ� �����͸� ��ô)
				_os.flush(); //���ۿ� �ִ� ���� �����Ͽ� ���
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
