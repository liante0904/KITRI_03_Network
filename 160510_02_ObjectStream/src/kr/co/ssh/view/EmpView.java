package kr.co.ssh.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import kr.co.ssh.domain.EmpBean;

// EmpView Class => ȭ�� ���� ��� ��ü
public class EmpView {
	// 1. �⺻ �޴� ��� �κ�
	public void printMenu()
	{
		System.out.println("Emp �׽�Ʈ");
		System.out.println("���ϴ� �޴��� �Է��ϼ���");
		System.out.println("1. ��� �Է�");
		System.out.println("2. ��� ���");
		System.out.println("3. ���� ����");
		System.out.println("4. ���� ����");
		System.out.println("5. DB�� ��������");
		System.out.println("6. DB ��������");
		System.out.println("q. ���α׷� ����");
	}
	//�޴� �Է� �޼ҵ�
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
		// ��� �߰� �޴�
		System.out.println("�����ȣ�� �Է��ϼ���.");
		int empno = Integer.parseInt(inputMenu());
		System.out.println("����̸��� �Է��ϼ���.");
		String ename = inputMenu();
		System.out.println("�Ŵ��� ��ȣ�� �Է��ϼ���");
		int mgr = Integer.parseInt(inputMenu());
		
		//EmpBean�� ���� 3���� �� �Է�
		
		EmpBean eb= new EmpBean();
		eb.setEmpno(empno);
		eb.setEname(ename);
		eb.setMgr(mgr);
		
		return eb;
		
	}
	public void printEmp(EmpBean eb) {
		// �Էµ� EmpBean ���
		
		System.out.println("�Էµ� �����ȣ:"+ eb.getEmpno());
		System.out.println("�Էµ� ����̸�:"+ eb.getEname());
		System.out.println("�Էµ� �Ŵ�����ȣ:"+ eb.getMgr());
		
	}
	public void printEmpDB(List<EmpBean> emp) {
		
		System.out.println("DB�� ���");
		System.out.println("�����ȣ \t ����̸� \t �Ŵ�����ȣ");
		System.out.println("================================");
		for(int i = 0; i < emp.size(); i++)
		{
			System.out.println(emp.get(i).getEmpno()+"\t"+emp.get(i).getEname()+"\t"+emp.get(i).getMgr());
		}
	
	}
	
	
	
}
