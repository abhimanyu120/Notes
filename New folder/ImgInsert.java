package sachin.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ImgInsert {
  public static void main(String[] args) {
	  Connection conn=null;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver successfully loaded!");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@//Sachin-PC:1521/orcl","advjavabatch","myscholars");
			System.out.println("Connection successfully opened!");
			PreparedStatement ps=conn.prepareStatement("insert into bolly_movies values(?,?)");
			Scanner kb=new Scanner(System.in);
			System.out.println("Enter path to the image:");
			String str=kb.nextLine();
			File f=new File(str);
			FileInputStream fin=new FileInputStream(str);
			String fname=f.getName();
			int dotpos=fname.lastIndexOf(".");
			String movname=fname.substring(0,dotpos);
			
			ps.setString(1, movname);
			ps.setBinaryStream(2, fin,(int)f.length());
			
			int res=ps.executeUpdate();
			System.out.println("Rec inserted:"+res);
			
			
			
			
		}
		catch(FileNotFoundException fnf)
		{
			System.out.println("Cannot open the file");
			System.out.println(fnf.getMessage());
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
