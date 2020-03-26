CREATE TABLE pessoa (
 codigo serial PRIMARY KEY,
 nome varchar NOT NULL,
 ativo boolean NOT NULL,
 logradouro varchar,
 numero varchar,
 complemento varchar,
 bairro varchar,
 cep varchar,
 cidade varchar,
 estado varchar 
);

INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) values ('Douglas', true, 'Av Anhnaguera', '1116', null, 'Anicuns', '74433020','Goiânia','GO');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) values ('Diego', true, 'Av GOias', '1116', 456, 'Anicuns', '74433020','Goiânia','GO');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) values ('Pedro', true, 'Av Paranaiba', '78', null, 'Anicuns', '74433020','Goiânia','GO');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) values ('Joao', true, 'Av Tocantins', '64', null, 'Anicuns', '74433020','Goiânia','GO');
INSERT INTO pessoa (nome, ativo ) values ('Maria', true  );
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) values ('Jucelino', true, 'Rua Velha ', '587', null, 'Anicuns', '74433020','Goiânia','GO');
INSERT INTO pessoa (nome, ativo, logradouro, numero, complemento, bairro, cep, cidade, estado ) values ('Maluco', true, 'Av catalunha', '58', null, 'Anicuns', '74433020','Goiânia','GO');

