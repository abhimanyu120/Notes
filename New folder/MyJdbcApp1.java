package sachin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyJdbcApp1 {
	public static void main(String[] args) {
		Connection conn=null;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver successfully loaded!");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@//Sachin-PC:1521/orcl","advjavabatch","myscholars");
			System.out.println("Connection successfully opened!");
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("Select bookname,bookprice from allbooks");
			while(rs.next())
			{
				String bname=rs.getString(1);
				double amt=rs.getDouble(2);
				System.out.println(bname+"\t"+amt);
			}
		}
		catch(ClassNotFoundException cnf)
		{
			System.out.println("Sorry! Cannot load the driver");
			System.out.println(cnf.getMessage());
		}
		catch(SQLException ex)
		{
			System.out.println("Sorry! Problem with DB");
			System.out.println(ex.getMessage());
		}
		finally
		{
			if(conn!=null)
			{
				try
				{
					conn.close();
					System.out.println("Connection successfully closed!");
				}
				catch(SQLException ex)
				{
					System.out.println("Sorry! Problem in closing the conn");
					System.out.println(ex.getMessage());
				}
			}
		}
	}
}
