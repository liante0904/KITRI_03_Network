package kr.co.ssh.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import kr.co.ssh.domain.EmpBean;

// EmpView Class => 화면 구성 요소 객체
public class EmpView {
	// 1. 기본 메뉴 출력 부분
	public void printMenu()
	{
		System.out.println("Emp 테스트");
		System.out.println("원하는 메뉴를 입력하세요");
		System.out.println("1. 사원 입력");
		System.out.println("2. 사원 출력");
		System.out.println("3. 서버 시작");
		System.out.println("4. 서버 연결");
		System.out.println("5. DB값 가져오기");
		System.out.println("6. DB 서버접속");
		System.out.println("q. 프로그램 종료");
	}
	//메뉴 입력 메소드
	public String inputMenu()
	{
		InputStream is = System.in;
		Reader reader = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(reader);
		String inputMenu = null;
				try {
					inputMenu = br.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e
					
					.printStackTrace();
				}
				
				return inputMenu;
				
	}
	public EmpBean addEmp() {
		// 사원 추가 메뉴
		System.out.println("사원번호를 입력하세요.");
		int empno = Integer.parseInt(inputMenu());
		System.out.println("사원이름을 입력하세요.");
		String ename = inputMenu();
		System.out.println("매니저 번호를 입력하세요");
		int mgr = Integer.parseInt(inputMenu());
		
		//EmpBean을 통해 3가지 값 입력
		
		EmpBean eb= new EmpBean();
		eb.setEmpno(empno);
		eb.setEname(ename);
		eb.setMgr(mgr);
		
		return eb;
		
	}
	public void printEmp(EmpBean eb) {
		// 입력된 EmpBean 출력
		
		System.out.println("입력된 사원번호:"+ eb.getEmpno());
		System.out.println("입력된 사원이름:"+ eb.getEname());
		System.out.println("입력된 매니저번호:"+ eb.getMgr());
		
	}
	public void printEmpDB(List<EmpBean> emp) {
		
		System.out.println("DB값 출력");
		System.out.println("사원번호 \t 사원이름 \t 매니저번호");
		System.out.println("================================");
		for(int i = 0; i < emp.size(); i++)
		{
			System.out.println(emp.get(i).getEmpno()+"\t"+emp.get(i).getEname()+"\t"+emp.get(i).getMgr());
		}
	
	}
	
	
	
}
