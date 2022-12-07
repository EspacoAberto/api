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
        ('Anunciante', 'Nicolau', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
        ('Anunciante', 'Pedro', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
        ('Anunciante', 'José', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
        ('Anunciante', 'Kleber', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
        ('Anunciante', 'Cabeçudo', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', true,true),
        ('Anunciante', 'Paulo', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', true,true),
        ('Anunciante', 'Gabriel', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', true,true),
        ('Anunciante', 'Guilherme', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', true,true),
        ('Anunciante', 'Vinicius', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', true,true),
        ('Anunciante', 'Victor', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', true,true),
        ('Cliente', 'Juan', '775.919.330-00', '2002-02-03', 'juan@gmail.com', '123', true,false);

insert into anuncio (descricao, preco, titulo) values ('alo', 20, 'titulo');
insert into imovel (nome, endereco_id, disponibilidade) values ('imovel1', 1, 'Tanto faz');
insert into imovel (nome, endereco_id, disponibilidade) values ('imovel2', 1, 'Urgente');
insert into imovel (nome, endereco_id, disponibilidade) values ('imovel3', 1, 'Em breve');
insert into acomodacao (descricao, imovel_id) values ('acomodacao', 1);
insert into anuncio (curtidas, descricao, preco, titulo, visualizacoes, anunciante_id, imovel_id) values (2, 'descricao', 5500, 'titulo', 10, 6, 1);
insert into anuncio (curtidas, descricao, preco, titulo, visualizacoes, anunciante_id, imovel_id) values (2, 'descricao', 2500, 'titulo', 10, 7, 2);
insert into anuncio (curtidas, descricao, preco, titulo, visualizacoes, anunciante_id, imovel_id) values (2, 'descricao', 7500, 'titulo', 10, 1, 3);
insert into imagem (imovel_id, photo, tipo_imagem) values (1, 'dsajkdsajkdadjla', '.jpg');
--
--
--  "id": 0,
--  "nome": "string",
--  "dataNascimento": "2022-10-19",
--  "cpf": "string",
--  "email": "string",
--  "senha": "string",
--  "premium": true