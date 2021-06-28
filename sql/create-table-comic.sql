CREATE TABLE Comic (id int not null auto_increment, titulo VARCHAR(200),
       preco double, isbn varchar(17), descricao varchar(2000), dia_desconto varchar(20), 
       desconto_ativo boolean, online_purchased_price boolean, primary key(id));