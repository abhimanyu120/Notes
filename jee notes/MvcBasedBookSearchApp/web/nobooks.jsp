<%-- 
    Document   : nobooks
    Created on : 22 Oct, 2019, 8:35:09 PM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>No Books!</title>
    </head>
    <body>
        <h3>Sorry! Our database has no books of <%= request.getAttribute("subject").toString()%></h3>
    </body>
</html>
