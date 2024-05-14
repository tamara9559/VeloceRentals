<%--
  Created by IntelliJ IDEA.
  User: t4mar
  Date: 11/05/2024
  Time: 2:53 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Formulario de login</title>
</head>
<body>
<h1>Iniciar sesión</h1>
<form action="${pageContext.request.contextPath}/login" method="post">

    <div>
        <p><a href="${pageContext.request.contextPath}/index.html">volver</a></p>
    </div>
    <div>
        <label for="username">Username</label>
        <div>
            <input type="text" name="username" id="username">
        </div>
    </div>
    <div>
        <label for="password">Password</label>
        <div>
            <input type="password" name="password" id="password">
        </div>
    </div>
    <div>
        <input type="submit" value="Login">
    </div>
</form>
</body>
</html>
