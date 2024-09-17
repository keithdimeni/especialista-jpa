insert into produto (id, nome, preco, descricao) values (1, "Kindle", 499, "Conheça o novo Kindle, agora com mais desempenho");
insert into produto (id, nome, preco, descricao) value (3, "Samsung Galaxy S24 Ultra", 899, "Um smartphone para todas necessidades");

insert into cliente (id, nome) value (1, "Keith Dimeni");
insert into cliente (id, nome) value (2, "Augusto Dimeni");

insert into pedido (id, cliente_id, data_pedido, total, status) values (1, 1, sysdate(), 100.0, 'AGUARDANDO');
insert into item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) values (1, 1, 1, 5.0, 2);

insert into categoria (id, nome) values (1, "Games");
