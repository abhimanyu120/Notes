package sachin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsert {
	public static void main(String[] args) {
		Connection conn=null;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver successfully loaded!");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@//Sachin-PC:1521/orcl","advjavabatch","myscholars");
			System.out.println("Connection successfully opened!");
			PreparedStatement ps=conn.prepareStatement("insert into emp values(?,?,?,?)");
			Scanner kb=new Scanner(System.in);
			System.out.println("Enter empid:");
			int eid=kb.nextInt();
			System.out.println("Enter emp name:");
			kb.nextLine();
			String ename=kb.nextLine();
			System.out.println("Enter hiredate(dd/MM/yyyy):");
			String hdatestr=kb.nextLine();
			System.out.println("Enter salary:");
			double sal=kb.nextDouble();
			
			ps.setInt(1, eid);
			ps.setString(2, ename);
			
			SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
			java.util.Date d1=sdf.parse(hdatestr);
			long ms=d1.getTime();
			java.sql.Date d2=new java.sql.Date(ms);
			
			ps.setDate(3, d2);
			ps.setDouble(4, sal);
			
			int result=ps.executeUpdate();
			System.out.println("REc inserted:"+result);
			
			
		}
		catch(ParseException pe)
		{
			System.out.println("Error in date conversion");
			System.out.println(pe.getMessage());
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
