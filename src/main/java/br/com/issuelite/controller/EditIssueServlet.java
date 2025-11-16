package br.com.issuelite.controller;

import br.com.issuelite.dao.IssueDAO;
import br.com.issuelite.model.Issue;
import br.com.issuelite.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class EditIssueServlet extends HttpServlet {

    private IssueDAO issueDAO = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int issueId = Integer.parseInt(req.getParameter("id"));
            User user = (User) req.getSession().getAttribute("usuarioLogado");

            Optional<Issue> issueOptional = issueDAO.findByIdAndUserId(issueId, user.getId());

            if (issueOptional.isPresent()) {
                req.setAttribute("issue", issueOptional.get());
            } else {

            }

            RequestDispatcher dispatcher = req.getRequestDispatcher("editarIssue.jsp");
            dispatcher.forward(req, resp);

        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID da Issue inválido.");
        } catch (SQLException e) {
            throw new ServletException("Erro de banco ao buscar issue para edição", e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("usuarioLogado");

        int issueId = Integer.parseInt(req.getParameter("id")); // Pega do campo <input type="hidden">
        String titulo = req.getParameter("titulo");
        String descricao = req.getParameter("descricao");
        String tipo = req.getParameter("tipo");
        String status = req.getParameter("status");
        String projeto = req.getParameter("projeto");

        Issue issue = new Issue();
        issue.setId(issueId);
        issue.setTitulo(titulo);
        issue.setDescricao(descricao);
        issue.setTipo(tipo);
        issue.setStatus(status);
        issue.setProjeto(projeto);
        issue.setIdUsuarioReportou(user.getId());

        try {
            issueDAO.update(issue);

            resp.sendRedirect(req.getContextPath() + "/list");
        } catch (SQLException e) {
            throw new ServletException("Erro de banco ao atualizar issue", e);
        }
    }
}