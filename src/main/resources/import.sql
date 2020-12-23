INSERT INTO cozinha(id, nome) VALUES (1, 'Tailandesa');
INSERT INTO cozinha(id, nome) VALUES (2, 'Indiana');

INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ("Thai Food Express", 5.90, 1);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ("Thai Delivery", 1.85, 1);
INSERT INTO restaurante(nome, taxa_frete, cozinha_id) VALUES ("All Indian Curries", 9.00, 2);

INSERT INTO estado (id, nome) VALUES (1, 'Minas Gerais');
INSERT INTO estado (id, nome) VALUES (2, 'São Paulo');
INSERT INTO estado (id, nome) VALUES (3, 'Ceará');

INSERT INTO cidade (id, nome, estado_id) VALUES (1, 'Uberlândia', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (2, 'Belo Horizonte', 1);
INSERT INTO cidade (id, nome, estado_id) VALUES (3, 'São Paulo', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (4, 'Campinas', 2);
INSERT INTO cidade (id, nome, estado_id) VALUES (5, 'Fortaleza', 3);

INSERT INTO forma_pagamento (id, nome) VALUES (1, 'Cartão de crédito');
INSERT INTO forma_pagamento (id, nome) VALUES (2, 'Cartão de débito');
INSERT INTO forma_pagamento (id, nome) VALUES (3, 'Dinheiro');

INSERT INTO permissao (id, nome, descricao) VALUES (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
INSERT INTO permissao (id, nome, descricao) VALUES (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');