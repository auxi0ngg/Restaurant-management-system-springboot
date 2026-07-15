create table carts
(
    id binary(16) default (uuid_to_bin(uuid())) primary key not null,
    created_at date default (curdate())
);

create table cart_items
(
    id       BIGINT auto_increment
        primary key,
    cart_id binary(16)        not null,
    item_id  BIGINT        not null,
    quantity int default 1 not null
);

alter table cart_items
    add constraint cart_items_menu_items_unique
        unique (item_id, cart_id);

alter table cart_items
    add constraint cart_items_menu_items_id_fk
        foreign key (item_id) references menu_items (id)
            on delete cascade;

alter table cart_items
    add constraint cart_items_carts_id_fk
        foreign key (cart_id) references carts (id)
            on delete cascade;