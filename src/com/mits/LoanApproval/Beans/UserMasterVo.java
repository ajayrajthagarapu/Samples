package com.mits.LoanApproval.Beans;

import java.util.ArrayList;

public class UserMasterVo {
	private int id;
	private String username;
	private String userid;
	private String password;
	private String email;
	private int isactive;
	
	private ArrayList<UserRoleVo> rolelist;
	

	public ArrayList<UserRoleVo> getRolelist() 
	{
		return rolelist;
	}


	public void setRolelist(ArrayList<UserRoleVo> rolelist) 
	{
		this.rolelist = rolelist;
	}


	public UserMasterVo()
	{
	
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public int getIsactive() {
		return isactive;
	}


	public void setIsactive(int isactive) {
		this.isactive = isactive;
	}
	

	
	

	
}
