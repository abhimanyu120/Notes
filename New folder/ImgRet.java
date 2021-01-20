package sachin.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ImgRet {
	public static void main(String[] args) {
		  Connection conn=null;
			try
			{
				Class.forName("oracle.jdbc.OracleDriver");
				System.out.println("Driver successfully loaded!");
				conn=DriverManager.getConnection("jdbc:oracle:thin:@//Sachin-PC:1521/orcl","advjavabatch","myscholars");
				System.out.println("Connection successfully opened!");
				Statement st=conn.createStatement();
				ResultSet rs=st.executeQuery("Select * from bolly_movies");
				File mydir=new File("d:/onlineadvjava/mydbpics");
				if(!mydir.exists())
				{
					if(!mydir.mkdir())
					{
						FolderNotCreatedException obj=new FolderNotCreatedException("Sorry! Cannot create the folder. App will terminate!");
						throw obj;
					}
				}
				
				while(rs.next())
				{
					String mname=rs.getString(1);
					Blob obj=rs.getBlob(2);
					byte[] arr=obj.getBytes(1, (int)obj.length());
					
					FileOutputStream fout=new FileOutputStream("d:/onlineadvjava/mydbpics/"+mname+".jpg");
					fout.write(arr);
					fout.close();
					System.out.println("Saving "+mname+".jpg. . .");
					
				}
				
				
				
			}
			catch(FolderNotCreatedException obj)
			{
				System.out.println(obj.getMessage());
			}
			catch(FileNotFoundException fnf)
			{
				System.out.println("Cannot open the file");
				System.out.println(fnf.getMessage());
			}
			catch(IOException ioe)
			{
				System.out.println("Cannot save the file");
				System.out.println(ioe.getMessage());
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
