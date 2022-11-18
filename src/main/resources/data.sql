-- Insert de usuários
insert into usuario
         (type, nome, cpf, data_nascimento, email, senha, is_premium, is_autenticado)
values
        ('Anunciante', 'nickolas', '775.919.330-00','2002-10-17', 'nickolas.ng@outlook.com', '123', false,true),
        ('Cliente', 'Juan', '775.919.330-00', '2002-02-03', 'juan@gmail.com', '123', true,false),
('Anunciante', 'Vinicius', '9874322211', '2002-10-20', 'vinicius@hotmail.com','123', false, false);


-- Insert de endereços
insert into endereco (cep, bairro, cidade, estado, logradouro) values
('05181301', 'Jaraguá', 'São Paulo', 'SP1', 'Rua Titako Pinto'),
('05181302', 'Jaraguá', 'São Paulo', 'SP', 'Rua Titako Pinto'),
('05181303', 'Jaraguá', 'São Paulo', 'SP', 'Rua Titako Pinto');

-- Insert de imoveis
insert into imovel (ENDERECO_CEP) values ('05181301'), ('05181302');

-- Insert de anuncios
insert into anuncio (descricao, preco, titulo, anunciante_id, imovel_id) values ('descricao básica', 20.0, 'apartamento 2 portas', 1, 1);




--
--
--  "id": 0,
--  "nome": "string",
--  "dataNascimento": "2022-10-19",
--  "cpf": "string",
--  "email": "string",
--  "senha": "string",
--  "premium": true