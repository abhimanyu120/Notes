package sachin.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map;

public class WeekDayCount {
	public static void main(String[] args) {
		Connection conn=null;
		try
		{
			Class.forName("oracle.jdbc.OracleDriver");
			System.out.println("Driver successfully loaded!");
			conn=DriverManager.getConnection("jdbc:oracle:thin:@//Sachin-PC:1521/orcl","advjavabatch","myscholars");
			System.out.println("Connection successfully opened!");
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("Select hiredate from emp");
			SimpleDateFormat sdf=new SimpleDateFormat("EEEE");
			HashMap <String,Integer>hm=new HashMap<>();
			while(rs.next())
			{
			    java.util.Date hd=rs.getDate(1);
			    String str=sdf.format(hd);
			    if(hm.containsKey(str))
			    {
			    	int x=hm.get(str);
			    	x++;
			    	hm.put(str, x);
			    }
			    else
			    {
			    	hm.put(str, 1);
			    }
			    
			}
			Set <Map.Entry<String,Integer>>data=hm.entrySet();
			Iterator <Map.Entry<String,Integer>>it=data.iterator();
			while(it.hasNext())
			{
				Map.Entry<String, Integer>e=it.next();
				String weekdayname=e.getKey();
				Integer count=e.getValue();
				System.out.println(weekdayname+"\t\t"+count);
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
