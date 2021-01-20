<%-- 
    Document   : showbooks
    Created on : 22 Oct, 2019, 8:34:55 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show All Books</title>
    </head>
    <body>
        <h3>Following are books of the subject <%= request.getAttribute("subject").toString()%></h3>
        <%
                java.util.ArrayList<mvcbooksearch.Book> bookList=(java.util.ArrayList<mvcbooksearch.Book>)request.getAttribute("booklist");
                out.println("<table border='2'>");
                out.println("<tr><th>BookId</th><th>BookName</th><th>BookPrice</th></tr>");
                for(mvcbooksearch.Book b: bookList)
                {
                    out.println("<tr><td>"+b.getBookid()+"</td><td>"+b.getBookname()+"</td><td>"+b.getBookprice()+"</td></tr>");
                    
                }
                out.println("</table>");
        %>
        <h3><a href='index.html'>Search</a> another book!</h3>     
    </body>
</html>
