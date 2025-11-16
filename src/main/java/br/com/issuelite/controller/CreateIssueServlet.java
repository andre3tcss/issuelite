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

public class CreateIssueServlet extends HttpServlet {

    private IssueDAO issueDAO = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("criarIssue.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("usuarioLogado");

        String titulo = req.getParameter("titulo");
        String descricao = req.getParameter("descricao");
        String tipo = req.getParameter("tipo"); // BUG, MELHORIA, TAREFA
        String projeto = req.getParameter("projeto");

        Issue issue = new Issue();
        issue.setTitulo(titulo);
        issue.setDescricao(descricao);
        issue.setTipo(tipo);
        issue.setProjeto(projeto);
        issue.setStatus("ABERTO");
        issue.setIdUsuarioReportou(user.getId());

        try {
            issueDAO.save(issue);

            resp.sendRedirect(req.getContextPath() + "/list");

        } catch (SQLException e) {
            throw new ServletException("Erro de banco de dados ao salvar nova issue", e);
        }
    }
}