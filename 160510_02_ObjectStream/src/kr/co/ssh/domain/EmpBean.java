package kr.co.ssh.domain;

import java.io.Serializable;

import kr.co.ssh.model.EmpDao;

public class EmpBean extends Object implements Serializable  {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211184423751362296L;
	private int empno;
	private String ename;
	private int mgr;
	private String job;
	private String hiredate;
	private int sal;
	private int comm;
	private int deptno;
	
	
	public void setEmpno(int empno)
	{
		this.empno = empno;
	}
	
	public int getEmpno()
	{
		return empno;
	}
	public void setEname(String ename)
	{
		this.ename = ename;
	}
	public String getEname()
	{
		return ename;
	}

	public int getMgr() {
		return mgr;
	}

	public void setMgr(int mgr) {
		this.mgr = mgr;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getHiredate() {
		return hiredate;
	}

	public void setHiredate(String hiredate) {
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public int getComm() {
		return comm;
	}

	public void setComm(int comm) {
		this.comm = comm;
	}

	public int getDeptno() {
		return deptno;
	}

	@Override
	public String toString() {
		return "EmpBean [empno=" + empno + ", ename=" + ename + ", mgr=" + mgr + ", job=" + job + ", hiredate="
				+ hiredate + ", sal=" + sal + ", comm=" + comm + ", deptno=" + deptno + "]";
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	

	}

