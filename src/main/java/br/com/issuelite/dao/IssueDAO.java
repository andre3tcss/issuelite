package br.com.issuelite.dao;

import br.com.issuelite.model.Issue;
import br.com.issuelite.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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