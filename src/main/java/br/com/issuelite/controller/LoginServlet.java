package br.com.issuelite.controller;

import br.com.issuelite.dao.UserDAO;
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

public class LoginServlet extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        try {
            Optional<User> userOptional = userDAO.findByEmailAndPassword(email, senha);

            if (userOptional.isPresent()) {
                HttpSession session = req.getSession();
                session.setAttribute("usuarioLogado", userOptional.get());
                session.setMaxInactiveInterval(30 * 60);

                resp.sendRedirect(req.getContextPath() + "/list");
            } else {
                req.setAttribute("errorMessage", "Email ou senha inválidos.");
                RequestDispatcher dispatcher = req.getRequestDispatcher("login.jsp");
                dispatcher.forward(req, resp);
            }
        } catch (SQLException e) {
            throw new ServletException("Erro no banco de dados ao tentar logar", e);
        }
    }
}