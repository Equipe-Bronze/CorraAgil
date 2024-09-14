-- Adicionando a coluna nomecompleto à tabela usuarios
ALTER TABLE usuarios ADD COLUMN nomecompleto VARCHAR(99) NOT NULL DEFAULT 'Nome não informado';
