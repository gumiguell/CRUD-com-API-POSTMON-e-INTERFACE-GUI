create schema Shop

create table Shopping
(
	cep varchar(8) not null,
	numero int not null,
	nome varchar(50) not null,
	
	primary key (cep, numero)
)