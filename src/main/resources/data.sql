insert into usuario
         (type, nome, cpf, data_nascimento, email, senha, is_premium, is_autenticado)
values
        ('Anunciante', 'nickolas', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
        ('Anunciante', 'Nicolau', '775.919.330-00','2002-10-17', 'nickolau.ng@outlook.com', '123', false,true),
        ('Anunciante', 'Pedro', '775.919.330-00','2002-10-17', 'pedro@outlook.com', '123', false,true),
        ('Anunciante', 'José', '775.919.330-00','2002-10-17', 'jose@outlook.com', '123', false,true),
        ('Anunciante', 'Kleber', '775.919.330-00','2002-10-17', 'kklbr@outlook.com', '123', false,true),
        ('Anunciante', 'Cabeçudo', '775.919.330-00','2002-10-17', 'cabeca@outlook.com', '123', true,true),
        ('Anunciante', 'Paulo', '775.919.330-00','2002-10-17', 'paulo@outlook.com', '123', true,true),
        ('Anunciante', 'Gabriel', '775.919.330-00','2002-10-17', 'gabriel@outlook.com', '123', true,true),
        ('Anunciante', 'Guilherme', '775.919.330-00','2002-10-17', 'guilherme@outlook.com', '123', true,true),
        ('Anunciante', 'Vinicius', '775.919.330-00','2002-10-17', 'vinicius@outlook.com', '123', true,true),
        ('Anunciante', 'Victor', '775.919.330-00','2002-10-17', 'victor@outlook.com', '123', true,true),
        ('Anunciante', 'Juan', '775.919.330-00', '2002-02-03', 'juan@gmail.com', '123', true,true);


insert into imovel ( disponibilidade) values ('Tanto faz');
insert into imovel (disponibilidade) values ('Urgente');
insert into imovel (disponibilidade) values ('Em breve');
insert into endereco (cep, estado, cidade, logradouro, numero,imovel_id)
values
('8521984731', 'SP', 'Ilha Bela', 'casa', '560',1),
('9876543218', 'SP', 'Campos de Jordão', 'casa', '560',2),
('8889995132', 'MT', 'Brasilia', 'casa', '560',3);
insert into anuncio (curtidas, descricao, preco, titulo, visualizacoes, anunciante_id, imovel_id) values (14, 'descricao', 5500, 'Sitio del Rei', 10, 6, 1);
insert into anuncio (curtidas, descricao, preco, titulo, visualizacoes, anunciante_id, imovel_id) values (71, 'descricao', 2500, 'Chacara Klabin', 10, 7, 2);
insert into anuncio (curtidas, descricao, preco, titulo, visualizacoes, anunciante_id, imovel_id) values (20, 'descricao', 7500, 'Chacara Emerindo Lima', 10, 8, 3);
insert into imagem (imovel_id, photo) values (1, 'https://images.pexels.com/photos/2468773/pexels-photo-2468773.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2');
insert into imagem (imovel_id, photo) values (2, 'https://images.pexels.com/photos/453201/pexels-photo-453201.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2');
insert into imagem (imovel_id, photo) values (3, 'https://images.pexels.com/photos/2468773/pexels-photo-2468773.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2');
--
--
--  "id": 0,
--  "nome": "string",
--  "dataNascimento": "2022-10-19",
--  "cpf": "string",
--  "email": "string",
--  "senha": "string",
--  "premium": true