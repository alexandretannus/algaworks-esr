ALTER TABLE restaurante ADD ativo BOOLEAN NOT NULL DEFAULT true;
UPDATE restaurante SET ativo = true;