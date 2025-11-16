<%@ page pageEncoding="UTF-8" %>
<%-- listarIssues.jsp --%>
<jsp:include page="common/header.jsp" />

<div class="dashboard-header">
    <h2>Meus Projetos / Issues</h2>
    <a href="#" class="btn btn-primary">+ Criar Nova Issue</a>
</div>

<table>
    <thead>
    <tr>
        <th>Título</th>
        <th>Status</th>
        <th>Projeto</th>
        <th>Ações</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="issue" items="${issues}">
        <tr>
            <td><c:out value="${issue.titulo}" /></td>
            <td><c:out value="${issue.status}" /></td>
            <td><c:out value="${issue.projeto}" /></td>
            <td class="actions">
                <a href="#" title="Editar">✏️</a>
                <a href="#" title="Excluir">🗑️</a>
            </td>
        </tr>
    </c:forEach>

    <c:if test="${empty issues}">
        <tr>
            <td colspan="4">Nenhuma issue encontrada. Crie uma nova!</td>
        </tr>
    </c:if>
    </tbody>
</table>

<jsp:include page="common/footer.jsp" />