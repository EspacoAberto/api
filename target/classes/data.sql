insert into endereco (cep, estado, cidade, logradouro, numero)
values
('8521984731', 'SP', 'Ilha Bela', 'casa', '560'),
('9876543218', 'SP', 'Campos de Jordão', 'casa', '560'),
('8889995132', 'MT', 'Brasilia', 'casa', '560'),
('1234567891', 'SP', 'Sptech', 'Faculdade', '560'),
('2005268423', 'SP', 'minha casa', 'casa', '560');


insert into usuario
         (type, nome, cpf, data_nascimento, email, senha, is_premium, is_autenticado)
values
        ('Anunciante', 'nickolas', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
        ('Cliente', 'Juan', '775.919.330-00', '2002-02-03', 'juan@gmail.com', '123', true,false);

insert into anuncio (descricao, preco, titulo) values ('alo', 20, 'titulo');
--
--
--  "id": 0,
--  "nome": "string",
--  "dataNascimento": "2022-10-19",
--  "cpf": "string",
--  "email": "string",
--  "senha": "string",
--  "premium": true