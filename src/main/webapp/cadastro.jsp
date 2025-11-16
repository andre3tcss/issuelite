<%-- cadastro.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cadastro - IssueLite</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>

<div class="login-container">
    <h1>Criar Conta</h1>

    <c:if test="${not empty errorMessage}">
        <div class="error-message">
                ${errorMessage}
        </div>
    </c:if>

    <form action="<c:url value='/cadastro' />" method="POST">
        <div class="form-group">
            <input type="text" id="nome" name="nome" placeholder="Nome Completo" required>
        </div>
        <div class="form-group">
            <input type="email" id="email" name="email" placeholder="Email" required>
        </div>
        <div class="form-group">
            <input type="password" id="senha" name="senha" placeholder="Senha" required>
        </div>
        <button type="submit" class="btn btn-primary">Cadastrar</button>
    </form>

    <div class="register-link">
        Já tem uma conta? <a href="<c:url value='/login' />">Faça o login</a>
    </div>
</div>

</body>
</html>