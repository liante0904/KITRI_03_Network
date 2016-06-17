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
			// 메뉴 출력하기.
			ev.printMenu();
			// 메뉴 입력하기
			input = ev.inputMenu();
			// 해당 메뉴에 따라서 처리하기
			// 1 = 사원 추가 내용
			if(input.equals("1")) // 사원 입력 메뉴 출력
			{
			eb	= ev.addEmp();
			} 
			else if (input.equals("2"))
			{
				// 입력된 사원 출력하기
				// eb => EmpView 사원 정보 출력 메소드
				// ev.printEmp(eb);
				
				ev.printEmp(eb);
					
			}
			else if (input.equals("3"))
			{
				// 3 = Server로 Socket을 생성해서 대기
				// 클라이언트에게 입력한 Emp 정보를 제공
				ss.startServer(eb);
			}
			else if (input.equals("4"))
			{
				// 4 = 해당 서버에 접근하여
				// 서버 Output Stream을 통해서 받아오기
				// InputStream을 통해서 받아오기
				System.out.println("접속 아이피를 입력주세요. (ex: 192.168.14.XXX)");
				String Ser_ip = ev.inputMenu();
				System.out.println("접속 포트를 입력주세요. (ex: 50000)");
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
				System.out.println("접속 아이피를 입력주세요. (ex: 192.168.14.XXX)");
				String Ser_ip = ev.inputMenu();
				System.out.println("접속 포트를 입력주세요. (ex: 50000)");
				int Ser_port = Integer.parseInt(ev.inputMenu());

				List<EmpBean> emp = (List<EmpBean>) ss.conDBServer(Ser_ip, Ser_port);
				ev.printEmpDB(emp);
			}
				
			
		} while(!input.equals("q"));
		
		
	}
}
