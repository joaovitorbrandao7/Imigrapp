create database db_imigrapp;
use db_imigrapp;

create table tb_usuario(
id_usuario int auto_increment,
nome varchar (255) not null,
email varchar (255) not null,
senha varchar (30) not null,
tipo_usuario varchar(30) not null,
idioma varchar (30) not null,
pais_origem varchar(30) not null,
primary key (id_usuario)
);

create table tb_tema(
id_tema int auto_increment,
nome_tema varchar(255) not null,
primary key (id_tema)
);

create table tb_postagem(
id_postagem int auto_increment,
post varchar (255) not null,
compartilhamento varchar(255),
comentario varchar(255),
interacao varchar(255),
fk_usuario int,
fk_tema int,
primary key(id_postagem),
foreign key (fk_tema) references tb_tema (id_tema),
foreign key (fk_usuario) references tb_usuario (id_usuario)
);

insert into tb_usuario (nome,email,senha,tipo_usuario,idioma,pais_origem) values
('João','joao@joao','1234','Ajudante','Ingles','Brasil');

insert into tb_tema (nome_tema) values ('Educação');

insert into tb_postagem (post, compartilhamento, comentario, interacao, fk_usuario, fk_tema) values
('A gatinha do marcos deu fuga a noite','2','3','Haha',1,1);

select * from tb_postagem;

