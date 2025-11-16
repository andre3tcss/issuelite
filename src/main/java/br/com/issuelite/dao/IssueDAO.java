package br.com.issuelite.dao;

import br.com.issuelite.model.Issue;
import br.com.issuelite.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IssueDAO {

    public List<Issue> findAllByUserId(int userId) throws SQLException {
        List<Issue> issues = new ArrayList<>();
        String sql = "SELECT * FROM ISSUE WHERE id_usuario_reportou = ? ORDER BY data_criacao DESC";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    issues.add(mapResultSetToIssue(rs));
                }
            }
        }
        return issues;
    }

    public boolean save(Issue issue) throws SQLException {
        String sql = "INSERT INTO ISSUE (titulo, descricao, tipo, status, id_usuario_reportou, projeto) " +
                "VALUES (?, ?, ?::TIPO_ISSUE, ?::STATUS_ISSUE, ?, ?)";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, issue.getTitulo());
            stmt.setString(2, issue.getDescricao());
            stmt.setString(3, issue.getTipo());
            stmt.setString(4, issue.getStatus());
            stmt.setInt(5, issue.getIdUsuarioReportou());
            stmt.setString(6, issue.getProjeto());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public Optional<Issue> findByIdAndUserId(int issueId, int userId) throws SQLException {
        String sql = "SELECT * FROM ISSUE WHERE id_issue = ? AND id_usuario_reportou = ?";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, issueId);
            stmt.setInt(2, userId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Usa o helper que já temos para mapear
                    return Optional.of(mapResultSetToIssue(rs));
                }
            }
        }
        return Optional.empty();
    }

    public boolean update(Issue issue) throws SQLException {
        String sql = "UPDATE ISSUE SET titulo = ?, descricao = ?, tipo = ?::TIPO_ISSUE, " +
                "status = ?::STATUS_ISSUE, projeto = ? " +
                "WHERE id_issue = ? AND id_usuario_reportou = ?"; // Garante que só o dono edite

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, issue.getTitulo());
            stmt.setString(2, issue.getDescricao());
            stmt.setString(3, issue.getTipo());
            stmt.setString(4, issue.getStatus());
            stmt.setString(5, issue.getProjeto());
            stmt.setInt(6, issue.getId());
            stmt.setInt(7, issue.getIdUsuarioReportou()); // Verificação de segurança

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    private Issue mapResultSetToIssue(ResultSet rs) throws SQLException {
        Issue issue = new Issue();
        issue.setId(rs.getInt("id_issue"));
        issue.setTitulo(rs.getString("titulo"));
        issue.setDescricao(rs.getString("descricao"));
        issue.setDataCriacao(rs.getTimestamp("data_criacao"));
        issue.setTipo(rs.getString("tipo"));
        issue.setStatus(rs.getString("status"));
        issue.setProjeto(rs.getString("projeto"));
        issue.setIdUsuarioReportou(rs.getInt("id_usuario_reportou"));
        return issue;
    }
}