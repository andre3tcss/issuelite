<%-- common/header.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>IssueLite</title>
    <link rel="stylesheet" href="<c:url value='/css/style.css' />">
</head>
<body>
<div class="main-container">
    <header class="navbar">
        <div class="navbar-brand">
            <a href="<c:url value='/list' />">🐜 IssueLite</a>
        </div>
        <c:if test="${not empty sessionScope.usuarioLogado}">
            <div class="user-info">
                <span>Olá, ${sessionScope.usuarioLogado.nome}!</span>
                <a href="<c:url value='/logout' />" class="btn-logout">Sair</a>
            </div>
        </c:if>
    </header>
    <main class="content">