<%-- 
    Document   : index
    Created on : May 14, 2021, 3:42:47 PM
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>RWS Client</title>
        <style>
            td, th {
                text-align: center;
                background-color: lavenderblush
            }
        </style>
    </head>
    <body>
        <h1>RWS Client</h1>

        <form action="DemoRS">

            <table border="1">
                <thead>
                    <tr>
                        <th>Directory : </th>
                        <th>Search line : </th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>
                            <input type="text" name="directory" value="" />
                        </td>
                        <td>
                            <input type="text" name="fragment" value="" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" value="Show directory" name="showDir" />
                        </td>
                        <td>
                            <input type="submit" value="Find file" name="findFile" />
                            <input type="checkbox" name="regexp" value="RE" /> Matches
                        </td>
                    </tr>
                </tbody>
            </table>
        </form>
        
        <%String answer = (String) request.getAttribute("response");
            if (answer != null) {
        %>

        <%=answer%>

        <% }%>





    </body>
</html>
