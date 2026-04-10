USE master;
GO

IF EXISTS (SELECT * FROM sys.databases WHERE name = 'bd_tccamp') 
    DROP DATABASE bd_tccamp;
GO

CREATE DATABASE bd_tccamp;
GO

USE bd_tccamp;
GO

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

CREATE TABLE Agenda
( 
   id INT IDENTITY,
   nome VARCHAR(100) NOT NULL,
   dosagem VARCHAR(100) NOT NULL,
   horario TIME NOT NULL,
   dataInicio SMALLDATETIME NOT NULL,
   dataFim SMALLDATETIME NOT NULL,
   observacoes VARCHAR(100) NOT NULL,
   usuarios_id INT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (usuarios_id) REFERENCES Usuarios (id)
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
   PRIMARY KEY (id),
   FOREIGN KEY (agenda_id) REFERENCES Agenda (id)
);
GO

CREATE TABLE Historico
(
   id INT IDENTITY,
   nome VARCHAR(100) NOT NULL,
   dosagem VARCHAR(100) NOT NULL,
   observacoes VARCHAR(100) NOT NULL,
   horario TIME NOT NULL,
   status VARCHAR(20) NOT NULL DEFAULT 'PENDENTE', -- PENDENTE, CONFIRMADO, IGNORADO
   data_confirmacao DATETIME NULL,
   medicamento_id INT NULL,
   agenda_id INT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (medicamento_id) REFERENCES Medicamento (id),
   FOREIGN KEY (agenda_id) REFERENCES Agenda (id)
);
GO

-- Dados iniciais
INSERT INTO Usuarios (nome, idade, email, senha)
VALUES 
('Fulano da Silva', 30, 'fulano@email.com.br', 'MTIzNDU2Nzg='),
('Beltrana de Souza', 25, 'beltrana@email.com.br', 'MTIzNDU2Nzg=');
GO

SELECT * FROM Usuarios;
GO
