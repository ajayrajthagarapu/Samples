package com.mits.LoanApproval.Beans;

public class UserRoleVo {
	
	int roleid;
	String rolename;
	
	
	
	public UserRoleVo(int roleid, String rolename) {
		super();
		this.roleid = roleid;
		this.rolename = rolename;
	}


	public int getRoleid() {
		return roleid;
	}


	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}


	public String getRolename() {
		return rolename;
	}


	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	
	
	
	
	

}
