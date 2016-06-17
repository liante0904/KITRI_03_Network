package kr.co.ssh.cont;

import java.util.List;

import kr.co.ssh.domain.EmpBean;
import kr.co.ssh.model.EmpDao;
import kr.co.ssh.view.EmpView;

public class EmpMainRun {
	public static void main(String[] args) {

		EmpBean eb = new EmpBean();
		EmpView ev = new EmpView();
		
		String input = "";
		
		ServerStart ss = new ServerStart();

		do{
			// �޴� ����ϱ�.
			ev.printMenu();
			// �޴� �Է��ϱ�
			input = ev.inputMenu();
			// �ش� �޴��� ���� ó���ϱ�
			// 1 = ��� �߰� ����
			if(input.equals("1")) // ��� �Է� �޴� ���
			{
			eb	= ev.addEmp();
			} 
			else if (input.equals("2"))
			{
				// �Էµ� ��� ����ϱ�
				// eb => EmpView ��� ���� ��� �޼ҵ�
				// ev.printEmp(eb);
				
				ev.printEmp(eb);
					
			}
			else if (input.equals("3"))
			{
				// 3 = Server�� Socket�� �����ؼ� ���
				// Ŭ���̾�Ʈ���� �Է��� Emp ������ ����
				ss.startServer(eb);
			}
			else if (input.equals("4"))
			{
				// 4 = �ش� ������ �����Ͽ�
				// ���� Output Stream�� ���ؼ� �޾ƿ���
				// InputStream�� ���ؼ� �޾ƿ���
				System.out.println("���� �����Ǹ� �Է��ּ���. (ex: 192.168.14.XXX)");
				String Ser_ip = ev.inputMenu();
				System.out.println("���� ��Ʈ�� �Է��ּ���. (ex: 50000)");
				int Ser_port = Integer.parseInt(ev.inputMenu());
				eb = ss.conServer(Ser_ip, Ser_port);
				ev.printEmp(eb);
			}
			else if (input.equals("5"))
			{

				ss.startServerDB();
				//EmpDao edao = new EmpDao();
				//edao.getEmpAll();
			}
			else if (input.equals("6"))
			{
				System.out.println("���� �����Ǹ� �Է��ּ���. (ex: 192.168.14.XXX)");
				String Ser_ip = ev.inputMenu();
				System.out.println("���� ��Ʈ�� �Է��ּ���. (ex: 50000)");
				int Ser_port = Integer.parseInt(ev.inputMenu());

				List<EmpBean> emp = (List<EmpBean>) ss.conDBServer(Ser_ip, Ser_port);
				ev.printEmpDB(emp);
			}
				
			
		} while(!input.equals("q"));
		
		
	}
}
