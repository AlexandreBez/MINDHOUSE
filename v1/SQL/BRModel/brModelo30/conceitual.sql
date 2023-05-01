CREATE TABLE empregado (
	id_empregado int auto_increment primary key,
	nome VARCHAR(150) not null,
	email VARCHAR(150) not null,
	senha VARCHAR(200) not null,
	imagem longblob
);

CREATE TABLE promocao (
	id_promocao int auto_increment primary key,
	nome_promocao VARCHAR(150) not null,
	porcentagem double not null,
	descricao VARCHAR(180) not null
);

CREATE TABLE livro (
	id_livro int auto_increment primary key,
	codLivro VARCHAR(80) not null,
	titulo VARCHAR(200) not null,
	autor VARCHAR(150) not null,
	valor decimal(10, 2) not null,
	editora VARCHAR(150) not null,
	status boolean not null,
	id_recibo INTEGER,
    constraint fk_reciboLiv foreign key(id_recibo) references recibo(id_recibo)
);

CREATE TABLE cliente (
	id_cliente INTEGER PRIMARY KEY auto_increment,
	nome VARCHAR(150) not null,
	cpf CHAR(11) not null,
	data_nascimento VARCHAR(10) not null,
	contato VARCHAR(20) not null,
	email VARCHAR(80),
	newsletter boolean,
	termo_de_contrato boolean not null
);

CREATE TABLE recibo (
	id_recibo INTEGER primary key auto_increment,
    id_cliente integer,
    id_promocao integer,
	data_aluguel VARCHAR(10),
	data_prevista_retorno VARCHAR(10),
	valor_aluguel DECIMAL(10),
	metodo_pagamento_aluguel VARCHAR(10),
	data_retorno VARCHAR(10),
	valor_multa DECIMAL(10),
	metodo_pagamento_multa VARCHAR(10),
	constraint fk_reciboCliente foreign key (id_cliente) references cliente(id_cliente),
	constraint fk_reciboPromocao foreign key (id_promocao) references promocao(id_promocao)
);