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
import java.util.List;

public class ListIssueServlet extends HttpServlet {

    private IssueDAO issueDAO = new IssueDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("usuarioLogado");

        try {
            List<Issue> issues = issueDAO.findAllByUserId(user.getId());

            req.setAttribute("issues", issues);

            RequestDispatcher dispatcher = req.getRequestDispatcher("listarIssues.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            throw new ServletException("Erro de banco de dados ao listar issues", e);
        }
    }
}