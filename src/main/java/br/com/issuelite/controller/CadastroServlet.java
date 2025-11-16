package br.com.issuelite.controller;

import br.com.issuelite.dao.UserDAO;
import br.com.issuelite.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

public class CadastroServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("cadastro.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        if (nome == null || nome.isEmpty() || email == null || email.isEmpty() || senha == null || senha.isEmpty()) {
            req.setAttribute("errorMessage", "Todos os campos são obrigatórios.");
            RequestDispatcher dispatcher = req.getRequestDispatcher("cadastro.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        User user = new User();
        user.setNome(nome);
        user.setEmail(email);

        try {
            userDAO.save(user, senha);
            resp.sendRedirect(req.getContextPath() + "/login?success=true");
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")) {
                req.setAttribute("errorMessage", "Este email já está cadastrado.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("cadastro.jsp");
                dispatcher.forward(req, resp);
            } else {
                throw new ServletException("Erro no banco de dados ao cadastrar usuário", e);
            }
        }
    }
}