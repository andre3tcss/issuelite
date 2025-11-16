package br.com.issuelite.controller;

import br.com.issuelite.dao.IssueDAO;
import br.com.issuelite.model.User;

// ATENÇÃO: Imports do Jakarta EE
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class DeleteIssueServlet extends HttpServlet {

    private IssueDAO issueDAO = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int issueId = Integer.parseInt(req.getParameter("id"));
            User user = (User) req.getSession().getAttribute("usuarioLogado");

            issueDAO.delete(issueId, user.getId());

            resp.sendRedirect(req.getContextPath() + "/list");

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da Issue inválido.");
        } catch (SQLException e) {
            throw new ServletException("Erro de banco de dados ao deletar issue", e);
        }
    }
}