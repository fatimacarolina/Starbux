-- Tried to use 'On Conflict''so that we wouldn't have them multiple times. But it kept on giving a syntax error.
-- Either way in a large environment such a change should warrant a PR to update the migrations first perhaps :)
-- However this also means the DB is wiped with every run since we had to cascade which is NOT cool.
delete from sold_topping;
delete from sold_drink;
delete from order_entity;
delete from drink;
delete from topping;

--insert into drink(name, price) values ('Black Coffee', 4.0) ON CONFLICT (name) DO NOTHING;
insert into drink(name, price) values ('Latte', 5.0);
insert into drink(name, price) values ('Mocha', 6.0);
insert into drink(name, price) values ('Tea', 3.0);

insert into topping(name, price) values ('Milk', 2.0);
insert into topping(name, price) values ('Hazelnut Syrup', 3.0);
insert into topping(name, price) values ('Chocolate Sauce', 5.0);
insert into topping(name, price) values ('Lemon', 2.0);

