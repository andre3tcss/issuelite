-- DDL (Data Definition Language)

-- Remove tabelas existentes em ordem de dependência (para testes)
DROP TABLE IF EXISTS COMENTARIO;
DROP TABLE IF EXISTS ISSUE;
DROP TABLE IF EXISTS USUARIO;
DROP TYPE IF EXISTS TIPO_ISSUE;
DROP TYPE IF EXISTS STATUS_ISSUE;

CREATE TABLE USUARIO (
                         id_usuario SERIAL PRIMARY KEY,
                         nome VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         senha_hash VARCHAR(255) NOT NULL -- Hash BCrypt
);

CREATE TYPE TIPO_ISSUE AS ENUM ('BUG', 'MELHORIA', 'TAREFA');
CREATE TYPE STATUS_ISSUE AS ENUM ('ABERTO', 'EM_PROGRESSO', 'FECHADO');

CREATE TABLE ISSUE (
                       id_issue SERIAL PRIMARY KEY,
                       titulo VARCHAR(200) NOT NULL,
                       descricao TEXT,
                       data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                       tipo TIPO_ISSUE NOT NULL,
                       status STATUS_ISSUE NOT NULL DEFAULT 'ABERTO',

                       projeto VARCHAR(100) NOT NULL DEFAULT 'Geral',

                       id_usuario_reportou INT NOT NULL,

                       FOREIGN KEY (id_usuario_reportou) REFERENCES USUARIO(id_usuario) ON DELETE CASCADE
);


-- DML (Data Manipulation Language) - Dados de Exemplo

INSERT INTO USUARIO (nome, email, senha_hash)
VALUES ('Administrador', 'admin@issuelite.com', '$2a$10$3zZ.aYOBv.xBS8/i.N8oT.iG1.SGS3b9tDlnV.uFTiA3Sg3j1.f5C');

INSERT INTO ISSUE (titulo, descricao, tipo, status, id_usuario_reportou, projeto)
VALUES
    ('Erro na tela de login', 'O botão de "Esqueci minha senha" não funciona.', 'BUG', 'ABERTO', 1, 'Site'),
    ('Adicionar filtro de pesquisa', 'Implementar uma barra de busca na listagem de issues.', 'MELHORIA', 'ABERTO', 1, 'Site'),
    ('Melhoria no redirecionamento', 'Após o login, redirecionar para o dashboard, não para a home.', 'TAREFA', 'EM_PROGRESSO', 1, 'Site');