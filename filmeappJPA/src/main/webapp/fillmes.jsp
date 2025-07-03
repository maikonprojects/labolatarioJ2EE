<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    <h1>Filmesss:</h1>
    <ul>
        <%
            java.util.List<String> filmes = (java.util.List<String>) request.getAttribute("filmes");
            for (String f : filmes) {
        %>
            <li><%= f %></li>
        <%
            }
        %>
    </ul>
    <a href="index.jsp">Voltar</a>
</body>
</html>
