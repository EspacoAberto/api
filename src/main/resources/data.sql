insert into usuario
         (type, nome, cpf, data_nascimento, email, senha, is_premium, is_autenticado)
values
        ('Cliente', 'nickolas', '775.919.340-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
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
(1,535.56),
(2,1000.00),
(3,750.00),
(4,240.00),
(5,0),
(6,100.0),
(7,75.0),
(8,124.0),
(9,465.0),
(10,896.0),
(11,666.0),
(12,858.0);

insert into imovel (qtd_quartos, qtd_banheiros)
values
(4, 2),
(7, 4),
(5, 3),
(2, 1),
(3, 2);

insert into endereco (cep, estado, cidade, logradouro, numero, imovel_id)
values
('8521984731', 'SP', 'Ilha Bela', 'casa', '1070',1),
('9876543218', 'SP', 'Campos do Jordão', '9621', '560',2),
('8889995132', 'MT', 'Brasilia', 'casa', '63',3),
('58050280', 'PB', 'João Pessoa', 'casa', '758', 4),
('57052070', 'AL', 'Maceió', 'casa', '568', 5);

insert into imagem (imovel_id, photo)
values
(1,'https://images.pexels.com/photos/2468773/pexels-photo-2468773.jpeg?auto=compress&cs=tinysrgb&w=854&h=480&dpr=2'),
(2,'https://images.pexels.com/photos/453201/pexels-photo-453201.jpeg?auto=compress&cs=tinysrgb&w=854&h=480&dpr=2'),
(3,'https://images.pexels.com/photos/13894755/pexels-photo-13894755.jpeg?auto=compress&cs=tinysrgb&w=854&h=480&dpr=2'),
(3,'https://images.pexels.com/photos/235725/pexels-photo-235725.jpeg?auto=compress&cs=tinysrgb&w=854&h=480&dpr=2'),
(3,'https://images.pexels.com/photos/13867111/pexels-photo-13867111.jpeg?auto=compress&cs=tinysrgb&w=854&h=480dpr=2'),
(4, 'https://images.unsplash.com/photo-1600607687920-4e2a09cf159d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80'),
(4, 'https://images.unsplash.com/photo-1600607687939-ce8a6c25118c?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=853&q=80'),
(5, 'https://images.unsplash.com/photo-1567002260834-61d030a974d9?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80'),
(5, 'https://images.unsplash.com/photo-1601327708356-cd4e1c399e2e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=828&q=80'),
(5, 'https://images.unsplash.com/photo-1558036117-15d82a90b9b1?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=870&q=80');

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
 'Em breve'),
(2,
75,
'Chácara Aparecida é o lugal perfeito para você que deseja fazer uma festa com o pessoal',
2660.50,
'Chácara Aparecida',
186,
11,
'Disponivel'),
(3,
75,
'Chácara Bela Vista é uma maravilha para você que busca um lugar amplo',
1560.50,
'Chácara Bela Vista',
186,
11,
'Disponivel'),
(
4,
260,
'Lugar moderno e bom para qualquer tipo de publico.',
3560.50,
'Casa da alvorada',
530,
6,
'Em breve'
),
(
5,
165,
'Casa do interior com um design muito interessante.',
1253.00,
'Casa Campo Belo',
342,
7,
'Disponivel'

);

insert into agendamento (data_checkin, data_checkout, usuario_id, anuncio_id_anuncio)
values
('2023-05-01', '2023-05-06', 1, 1);