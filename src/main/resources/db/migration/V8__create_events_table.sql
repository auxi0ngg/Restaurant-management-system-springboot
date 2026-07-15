create table events
(
    id              BIGINT auto_increment
        primary key,
    name            VARCHAR(55) not null,
    date            DATE        not null,
    time            TIME        not null,
    status          VARCHAR(20) not null,
    tables_required int         not null,
    max_attendees   int         not null,
    description     LONGTEXT    not null,
    customer_id     BIGINT      not null,
    constraint event_customers_id_fk
        foreign key (customer_id) references customers (id)
            on delete cascade
);

create table customer_events
(
    customer_id BIGINT not null,
    event_id    BIGINT not null,
    constraint customer_events_pk
        primary key (event_id, customer_id),
    constraint customer_events_events_id_fk
        foreign key (customer_id) references events (id)
            on delete cascade,
    constraint customer_events_events_id_fk_2
        foreign key (event_id) references events (id)
            on delete cascade
);