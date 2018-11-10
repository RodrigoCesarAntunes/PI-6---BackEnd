create database livraria_virtual;
use livraria_virtual;

CREATE USER 'rodrigo'@'localhost' IDENTIFIED BY 'Livraria-435';
GRANT ALL PRIVILEGES ON * . * TO 'rodrigo'@'localhost';
FLUSH PRIVILEGES;

create table usuario
(
	id  int primary key auto_increment,
    nome varchar(255),
    email varchar(255) unique key,
    senha varchar (255),
    documento varchar(255),
    data_nascimento date,
    isAdm varchar(6),
    isExcluido int(1) default 0
);

create table livro
(
	id  int primary key auto_increment,
    	nome varchar(255),
    	autor varchar(255),
    	tipo varchar(255),
	preco decimal(6,2),
	editora varchar(255),
	edicao int,
	isExcluido int(1) default 0
);

create table usuario_livros
(
	id int primary key auto_increment,
    livro_id int,
    usuario_email varchar(255),
    
    foreign key (livro_id) references livro(id),
    foreign key (usuario_email) references usuario(email)
)
