<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="common/header.jsp" />

<h2>Criar Nova Issue</h2>

<form action="<c:url value='/create' />" method="POST" class="form-issue">
    <div class="form-group">
        <label for="titulo">Título da Issue</label>
        <input type="text" id="titulo" name="titulo" required>
    </div>
    <div class="form-group">
        <label for="descricao">Descrição</label>
        <textarea id="descricao" name="descricao" rows="4"></textarea>
    </div>
    <div class="form-group">
        <label for="tipo">Tipo</label>
        <select id="tipo" name="tipo">
            <option value="BUG">Bug</option>
            <option value="MELHORIA">Melhoria</option>
            <option value="TAREFA">Tarefa</option>
        </select>
    </div>
    <div class="form-group">
        <label for="projeto">Projeto</label>
        <input type="text" id="projeto" name="projeto" value="Site" required>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary">Salvar Issue</button>
        <a href="<c:url value='/list' />" class="btn btn-secondary">Cancelar</a>
    </div>
</form>

<jsp:include page="common/footer.jsp" />