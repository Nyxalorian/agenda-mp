CREATE TABLE Usuarios
( 
   id INT IDENTITY,
   nome VARCHAR(100) NOT NULL,
   idade INT NOT NULL,
   comorbidade VARCHAR(100) NULL,
   email VARCHAR(100) NOT NULL,
   senha VARCHAR(100) NOT NULL,

   PRIMARY KEY (id)
);
GO

-- Inserir dados na tabela 'Usuarios'
INSERT INTO Usuarios (nome, email, senha, nivel_acesso, foto, data_cadastro, status_usuario)
VALUES 
('Fulano da Silva', 'fulano@email.com.br', 'MTIzNDU2Nzg=', 'ADMIN', NULL, GETDATE(), 'ATIVO'),
('Beltrana de S�', 'beltrana@email.com.br', 'MTIzNDU2Nzg=', 'USER', NULL, GETDATE(), 'ATIVO'),
('Sicrana de Oliveira', 'sicrana@email.com.br', 'MTIzNDU2Nzg=', 'USER', NULL, GETDATE(), 'INATIVO'),
('Ordnael Zurc', 'ordnael@email.com.br', 'MTIzNDU2Nzg=', 'USER', NULL, GETDATE(), 'TROCAR_SENHA');
GO

-- Verificar se os dados foram inseridos corretamente
SELECT * FROM Usuarios;
GO

-- Criar outras tabelas (Cliente, Cadastro, Login, etc.)
CREATE TABLE Cliente
( 
   id INT IDENTITY,
   nome VARCHAR(100) NOT NULL,
   email CHAR(100) NOT NULL,
   data_nascimento SMALLDATETIME NOT NULL,
   PRIMARY KEY (id)
);
GO

CREATE TABLE Agenda
( 
   id INT IDENTITY,
   nome VARCHAR(100) NOT NULL,
   dosagem VARCHAR(100) NOT NULL,
   horario TIME NOT NULL,
   data_inicio SMALLDATETIME NOT NULL,
   data_fim SMALLDATETIME NOT NULL,
   observacoes VARCHAR(100) NOT NULL,
   usuarios_id INT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (usuarios_id) REFERENCES Usuarios (id)
);
GO

CREATE TABLE Horario
( 
   id INT IDENTITY,
   data SMALLDATETIME NOT NULL,
   hora TIME NOT NULL,
   status VARCHAR(20) NOT NULL, -- ATIVO ou INATIVO
   PRIMARY KEY (id)
);
GO

CREATE TABLE Medicamento
( 
   id INT IDENTITY,
   nome VARCHAR(100) NOT NULL,
   descricao VARCHAR(100) NOT NULL,
   tipo VARCHAR(100) NOT NULL,
   complemento VARCHAR(50) NULL,
   data_cadastro SMALLDATETIME NOT NULL,
   status_medicamento VARCHAR(20) NOT NULL, -- ATIVO ou INATIVO
   agenda_id INT NULL,
   PRIMARY KEY (id)
);
GO

CREATE TABLE Historico
(
   id INT IDENTITY,
   nome VARCHAR(100) NOT NULL,
   dosagem VARCHAR(100) NOT NULL,
   observacoes VARCHAR(100) NOT NULL,
   horario TIME NOT NULL,
   medicamento_id INT NULL,
   agenda_id INT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (medicamento_id) REFERENCES Medicamento (id),
   FOREIGN KEY (agenda_id) REFERENCES Agenda (id)
);
GO

-- Opcional: Apagar a tabela 'Usuarios' ap�s verificar os dados
-- DROP TABLE Usuarios;
GO


SELECT * FROM Usuarios

SELECT * FROM Medicamento

SELECT * FROM Historico

DELETE FROM Usuarios
WHERE ID = 2;