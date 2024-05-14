<%--
  Created by IntelliJ IDEA.
  User: t4mar
  Date: 14/05/2024
  Time: 1:58 a. m.
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>eleccion</title>
</head>
<body>
<h1>eleccion de categoria</h1>
<form action="${pageContext.request.contextPath}/election" method="post">
    <div>
        <p><a href="${pageContext.request.contextPath}/index.html">volver</a></p>
    </div>
<ul>
    <li><a href="${pageContext.request.contextPath}/election1">carro</a></li>
    <li><a href="${pageContext.request.contextPath}/election2">moto</a></li>
</ul>
</form>
</body>
</html>
