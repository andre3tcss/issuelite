<%-- login.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login - IssueLite</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>

<div class="login-container">
    <img src="<c:url value='/img/logoIssueLite.png' />" alt="IssueLite Logo" class="login-logo">
    <h2>Login</h2>

    <c:if test="${not empty errorMessage}">
        <div class="error-message">
                ${errorMessage}
        </div>
    </c:if>

    <c:if test="${param.success == 'true'}">
        <div class="success-message">
            Cadastro realizado com sucesso! Faça o login.
        </div>
    </c:if>

    <form action="<c:url value='/login' />" method="POST">
        <div class="form-group">
            <input type="email" id="email" name="email" placeholder="Email" required>
        </div>
        <div class="form-group">
            <input type="password" id="senha" name="senha" placeholder="Senha" required>
        </div>
        <button type="submit" class="btn btn-primary">Entrar</button>
    </form>

    <div class="register-link">
        Não tem uma conta? <a href="<c:url value='/cadastro' />">Cadastre-se</a>
    </div>
</div>

</body>
</html>