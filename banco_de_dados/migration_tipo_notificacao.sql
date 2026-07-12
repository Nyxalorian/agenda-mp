ALTER TABLE Usuarios
ADD tipo_notificacao VARCHAR(20) NOT NULL
    CONSTRAINT DF_Usuarios_tipo_notificacao DEFAULT 'sistema';
GO

UPDATE Usuarios
SET tipo_notificacao = 'sistema'
WHERE tipo_notificacao IS NULL;
GO
