package com.mits.LoanApproval.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.mits.LoanApproval.Beans.UserMasterVo;
import com.mits.LoanApproval.Beans.UserRoleVo;
import com.mits.LoanApproval.DbUtil.DbUtil;

public class LoginDao {

	UserMasterVo obj;
	PreparedStatement pst = null;
	Statement st = null;
	ResultSet rs = null;
	Connection connection = null;
	boolean status = false;
	String roleName = null;
	ResultSet rs1 = null;
	int dbId;

	public boolean validate(UserMasterVo bean) {//checking whether userid and password are valid and returns TRUE if valid
         
		//getting the userId and Password from bean object
		String userid = bean.getUserid();
		String password = bean.getPassword();
		
        //validating the userId and password from TBLUSERMASTER_LOANAPPROVAL639 table
		try {
		
			connection = DbUtil.getConnection();
			String sql = "select userid,password from TBLUSERMASTER_LOANAPPROVAL639 where userid=? and password=?";
			pst = connection.prepareStatement(sql);
			pst.setString(1, userid);
			pst.setString(2, password);
			rs = pst.executeQuery();
			
			if(rs.next())
			{
				String dbuser = rs.getString("userid");
				String dbpass = rs.getString("password");

				if (dbuser.equals(userid) && dbpass.equals(password)) {
					status = true;
				}
			}
			
			
		}

		catch (Exception e)

		{

			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pst != null) {
					pst.close();
				}
				DbUtil.closeConnection(connection);
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return status;

	}

	public UserMasterVo getUserRolename(String userid) {//getting the ID  using UserId and from ID we are getting role name 

		try {
			connection = DbUtil.getConnection();
			String sql = "select ID from TBLUSERMASTER_LOANAPPROVAL639 where userid=? ";
			pst = connection.prepareStatement(sql);

			pst.setString(1, userid);
			rs = pst.executeQuery();
			dbId = 0;
			if (rs.next()) {

				dbId = rs.getInt("ID");
			}
			ArrayList<UserRoleVo> rolelist = new ArrayList<UserRoleVo>();
			
			if(dbId>0)
			{
				String sql1="select tblUserRoleMapping_loan639.RoleId ,tblRoleMaster_loanApproval639.RoleName from tblUserRoleMapping_loan639 ,tblRoleMaster_loanApproval639 "
						+ " where tblUserRoleMapping_loan639.roleid=tblRoleMaster_loanApproval639.roleid and tblUserRoleMapping_loan639.id="+dbId;
				
				Statement st = connection.createStatement();
				ResultSet rs1 = st.executeQuery(sql1);
				
				while(rs1.next())
				{
					int RoleId = rs1.getInt("roleid");
					String RoleName=rs1.getString("rolename");
					UserRoleVo uservo= new UserRoleVo(RoleId,RoleName);
					uservo.setRoleid(RoleId);
					uservo.setRolename(RoleName);
					
					rolelist.add(uservo);
				}
			}
			obj=new UserMasterVo();
			obj.setRolelist(rolelist);
		}

		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try {
				if (rs != null) {
					rs.close();
				}
					
				if (rs1 != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				DbUtil.closeConnection(connection);
			} catch (SQLException e) {

				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		return obj;
	}

}

