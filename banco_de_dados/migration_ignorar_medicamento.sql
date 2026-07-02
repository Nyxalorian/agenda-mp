IF COL_LENGTH('Historico', 'motivo_ignorado') IS NULL
BEGIN
    ALTER TABLE Historico ADD motivo_ignorado VARCHAR(200) NULL;
END
GO

IF COL_LENGTH('Historico', 'data_hora_ignorado') IS NULL
BEGIN
    ALTER TABLE Historico ADD data_hora_ignorado DATETIME2 NULL;
END
GO
