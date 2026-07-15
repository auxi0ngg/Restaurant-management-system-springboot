alter table orders
    add total_price DECIMAL(10, 2) not null;

alter table order_items
    add unit_price DECIMAL(10, 2) not null;

alter table order_items
    add total_price DECIMAL(10, 2) not null;