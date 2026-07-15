alter table order_items
    add constraint order_items_order_item_unique
        unique (item_id, order_id);

alter table order_items
    add constraint order_items_menu_items_id_fk
        foreign key (item_id) references menu_items (id)
            on delete cascade;

alter table order_items
    add constraint order_items_orders_id_fk
        foreign key (order_id) references orders (id)
            on delete cascade;