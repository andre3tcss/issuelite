<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%-- editarIssue.jsp --%>
<jsp:include page="common/header.jsp" />

<h2>Editar Issue</h2>

<c:if test="${not empty issue}">
    <form action="<c:url value='/edit' />" method="POST" class="form-issue">

        <input type="hidden" name="id" value="<c:out value="${issue.id}" />">

        <div class="form-group">
            <label for="titulo">Título da Issue</label>
            <input type="text" id="titulo" name="titulo" value="<c:out value="${issue.titulo}" />" required>
        </div>
        <div class="form-group">
            <label for="descricao">Descrição</label>
            <textarea id="descricao" name="descricao" rows="4"><c:out value="${issue.descricao}" /></textarea>
        </div>
        <div class="form-group">
            <label for="tipo">Tipo</label>
            <select id="tipo" name="tipo">
                <option value="BUG" ${issue.tipo == 'BUG' ? 'selected' : ''}>Bug</option>
                <option value="MELHORIA" ${issue.tipo == 'MELHORIA' ? 'selected' : ''}>Melhoria</option>
                <option value="TAREFA" ${issue.tipo == 'TAREFA' ? 'selected' : ''}>Tarefa</option>
            </select>
        </div>
        <div class="form-group">
            <label for="status">Status</label>
                <%-- O formulário de Edição tem o campo Status (diferente do Criar) --%>
            <select id="status" name="status">
                <option value="ABERTO" ${issue.status == 'ABERTO' ? 'selected' : ''}>Aberto</option>
                <option value="EM_PROGRESSO" ${issue.status == 'EM_PROGRESSO' ? 'selected' : ''}>Em Progresso</option>
                <option value="FECHADO" ${issue.status == 'FECHADO' ? 'selected' : ''}>Fechado</option>
            </select>
        </div>
        <div class="form-group">
            <label for="projeto">Projeto</label>
            <input type="text" id="projeto" name="projeto" value="<c:out value="${issue.projeto}" />" required>
        </div>

        <div class="form-actions">
            <button type="submit" class="btn btn-primary">Salvar Alterações</button>
            <a href="<c:url value='/list' />" class="btn btn-secondary">Cancelar</a>
        </div>
    </form>
</c:if>

<c:if test="${empty issue}">
    <p class="error-message">A issue solicitada não foi encontrada ou você não tem permissão para editá-la.</p>
</c:if>

<jsp:include page="common/footer.jsp" />