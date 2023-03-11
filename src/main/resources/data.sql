insert into usuario
         (type, nome, cpf, data_nascimento, email, senha, is_premium, is_autenticado)
values
        ('Anunciante', 'nickolas', '775.919.340-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
        ('Anunciante', 'Nicolau', '775.919.430-00','2002-10-17', 'nickolau.ng@outlook.com', '123', false,true),
        ('Anunciante', 'Pedro', '775.949.330-00','2002-10-17', 'pedro@outlook.com', '123', false,true),
        ('Anunciante', 'José', '745.919.330-00','2002-10-17', 'jose@outlook.com', '123', false,true),
        ('Anunciante', 'Kleber', '773.919.330-00','2002-10-17', 'kklbr@outlook.com', '123', false,true),
        ('Anunciante', 'Cabeçudo', '775.919.332-00','2002-10-17', 'cabeca@outlook.com', '123', true,true),
        ('Anunciante', 'Paulo', '775.919.310-00','2002-10-17', 'paulo@outlook.com', '123', true,true),
        ('Anunciante', 'Gabriel', '725.919.330-00','2002-10-17', 'gabriel@outlook.com', '123', true,true),
        ('Anunciante', 'Guilherme', '735.919.330-00','2002-10-17', 'guilherme@outlook.com', '123', true,true),
        ('Anunciante', 'Vinicius', '775.219.330-00','2002-10-17', 'vinicius@outlook.com', '123', true,true),
        ('Anunciante', 'Victor', '215.919.330-00','2002-10-17', 'victor@outlook.com', '123', true,true),
        ('Anunciante', 'Juan', '7753219.330-00', '2002-02-03', 'juan@gmail.com', '123', true,true);

insert into Carteira
(fk_usuario_id, saldo)
values
(1,100.0),
(2,100.0),
(3,100.0),
(4,100.0),
(5,100.0),
(6,100.0),
(7,100.0),
(8,100.0),
(9,100.0),
(10,100.0),
(11,100.0),
(12,100.0);

insert into imovel (qtd_quartos, qtd_banheiros)
values
(4, 2),
(7, 4),
(5, 3);

insert into endereco (cep, estado, cidade, logradouro, numero, imovel_id)
values
('8521984731', 'SP', 'Ilha Bela', 'casa', '1070',1),
('9876543218', 'SP', 'Campos do Jordão', '9621', '560',2),
('8889995132', 'MT', 'Brasilia', 'casa', '63',3);

insert into imagem (imovel_id, photo)
values
(1,
'https://images.pexels.com/photos/2468773/pexels-photo-2468773.jpeg?auto=compress&cs=tinysrgb&w=1280&h=720&dpr=2'),
(2,
'https://images.pexels.com/photos/453201/pexels-photo-453201.jpeg?auto=compress&cs=tinysrgb&w=1280&h=720&dpr=2'),
(3,
'https://images.pexels.com/photos/13894755/pexels-photo-13894755.jpeg?auto=compress&cs=tinysrgb&w=1280&h=720&dpr=2'),
(3,
'https://images.pexels.com/photos/235725/pexels-photo-235725.jpeg?auto=compress&cs=tinysrgb&w=1280&h=720&dpr=2'),
(3,
'https://images.pexels.com/photos/13867111/pexels-photo-13867111.jpeg?auto=compress&cs=tinysrgb&w=1280&h=720dpr=2');

insert into anuncio (
imovel_id,
curtidas,
descricao,
preco,
titulo,
visualizacoes,
anunciante_id,
disponibilidade)
values
(1,
14,
'Sitio del Rei é um lugar muito agradavel e calmo para você passar o final de semana  com a familia',
 5500,
 'Sitio del Rei',
 50,
 10,
 'Em breve');

insert into anuncio (
imovel_id,
curtidas,
descricao,
preco,
titulo,
visualizacoes,
anunciante_id,
disponibilidade)
values
(2,
75,
'Chácara Aparecida é o lugal perfeito para você que deseja fazer uma festa com o pessoal',
2660.50,
'Chácara Aparecida',
186,
11,
'Disponivel');

insert into anuncio (
imovel_id,
curtidas,
descricao,
preco,
titulo,
visualizacoes,
anunciante_id,
disponibilidade)
values
(3,
75,
'Chácara Bela Vista é uma maravilha para você que busca um lugar amplo',
1560.50,
'Chácara Bela Vista',
186,
11,
'Disponivel');