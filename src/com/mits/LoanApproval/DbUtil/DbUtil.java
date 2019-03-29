package com.mits.LoanApproval.DbUtil;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbUtil {

	public static Connection getConnection() {

		Connection connection = null;

		try {

			InitialContext context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("mitsind639jndi");
			connection = ds.getConnection();

			} catch (SQLException e1) {
			e1.printStackTrace();
			} catch (NamingException e1) {
			e1.printStackTrace();
			}
		return connection;
		}

	public static void closeConnection(Connection connection) 
	{
		
		try {
			connection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
	}

}
