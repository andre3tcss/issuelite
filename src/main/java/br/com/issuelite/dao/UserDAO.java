package br.com.issuelite.dao;

import br.com.issuelite.model.User;
import br.com.issuelite.util.ConnectionFactory;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserDAO {

    public boolean save(User user, String senhaPura) throws SQLException {
        String sql = "INSERT INTO USUARIO (nome, email, senha_hash) VALUES (?, ?, ?)";

        String senhaHash = BCrypt.hashpw(senhaPura, BCrypt.gensalt());

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, senhaHash);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public Optional<User> findByEmailAndPassword(String email, String senhaPura) throws SQLException {
        String sql = "SELECT id_usuario, nome, email, senha_hash FROM USUARIO WHERE email = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String senhaHashDoBanco = rs.getString("senha_hash");

                    if (BCrypt.checkpw(senhaPura, senhaHashDoBanco)) {
                        User user = new User();
                        user.setId(rs.getInt("id_usuario"));
                        user.setNome(rs.getString("nome"));
                        user.setEmail(rs.getString("email"));
                        user.setSenhaHash(senhaHashDoBanco);
                        return Optional.of(user); // Retorna o usuário "empacotado"
                    }
                }
            }
        }
        return Optional.empty();
    }
}