create schema erp;

insert into userrole(id, role) values (1, 'ADMIN');
insert into userrole(id, role) values (2, 'USER');

insert into usuario (id, username, password, userrole)
values (1, 'user 1', 'passwd1', 'U');

insert into usuario (id, username, password, userrole)
values (2, 'user 2', 'passwd2', 'A');

insert into item (id, itemcode, description, price, state, creation_date, usuario_id)
values (1, 1, 'Item 1', 15, 'A', '2023-04-28', 1);

insert into item (id, itemcode, description, price, state, creation_date, usuario_id)
values (2, 2, 'Item 2', 55, 'A', '2022-01-31', 2);

insert into item (id, itemcode, description, price, state, creation_date, usuario_id)
values (3, 3, 'Item 3', 105, 'D', '2020-01-31', 2);

insert into supplier (id, name, country) values (1, 'Supplier 1', 'Spain');
insert into supplier (id, name, country) values (2, 'Supplier 2', 'England');
insert into supplier (id, name, country) values (3, 'Supplier 3', 'ITALY');

insert into item_supplier (supplier_id, item_id) values (1, 1);
insert into item_supplier (supplier_id, item_id) values (1, 2);
insert into item_supplier (supplier_id, item_id) values (2, 2);
insert into item_supplier (supplier_id, item_id) values (3, 3);

insert into pricereduction (id, reducedprice, startdate, enddate, itemcode)
values (1, 5, '2023-05-01', '2023-05-31', 1);
insert into pricereduction (id, reducedprice, startdate, enddate, itemcode)
values (2, 15, '2022-12-01', '2022-12-31', 2);
insert into pricereduction (id, reducedprice, startdate, enddate, itemcode)
values (3, 35, '2022-05-01', '2022-05-31', 2);