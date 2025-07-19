<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <fmt:setBundle basename="messages"/>
    <title><fmt:message key="login.title"/></title>
    <style>
        body { font-family: Arial; background-color: #f0f8ff; }
        .login-box { 
            width: 300px; padding: 20px; margin: 100px auto; 
            background: white; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        h2 { color: #0066cc; text-align: center; }
        input { width: 100%; padding: 8px; margin: 5px 0; }
        button { background: #0066cc; color: white; border: none; padding: 10px; width: 100%; }
        .error { color: red; text-align: center; }
    </style>
</head>
<body>
    <div class="login-box">
        <h2><fmt:message key="app.name"/></h2>
        <form action="login" method="post">
            <input type="text" name="email" placeholder="<fmt:message key="login.placeholder.username"/>" required>
            <input type="password" name="password" placeholder="<fmt:message key="login.placeholder.password"/>" required>
            <button type="submit"><fmt:message key="login.button.login"/></button>
        </form>
        <% if(request.getAttribute("error") != null) { %>
            <p class="error"><fmt:message key="${request.getAttribute("error")}"/></p>
        <% } %>
    </div>
</body>
</html>
