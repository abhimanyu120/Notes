/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mvcbooksearch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class BookModel {
    private static Connection conn;
    private static PreparedStatement ps;
    static
    {
        try
        {
                Class.forName("oracle.jdbc.OracleDriver");
                conn=DriverManager.getConnection("jdbc:oracle:thin:@//DESKTOP-4BFU781:1521/xe","newadvjavabatch","genious");
                ps=conn.prepareStatement("select bookid,bookname,bookprice from allbooks where subject=?");
        }
        catch(Exception ex)
        {
            System.out.println("Exception occureed in Model:"+ex);
            
        }
    }
    public static ArrayList<Book> getBooksBySubject(String subject)throws SQLException
    {
        ps.setString(1,subject);
        ArrayList<Book> booklist=new ArrayList<Book>();
        ResultSet rs=ps.executeQuery();
        while(rs.next())
        {
            Book b=new Book();
            b.setBookid(rs.getInt("bookid"));
            b.setBookname(rs.getString("bookname"));
            b.setBookprice(rs.getDouble("bookprice"));
            booklist.add(b);
        }
        return booklist;
    }
    public static void closeConnection()throws SQLException
    {
        if(conn!=null)
        {
            conn.close();
        }
    }
}

