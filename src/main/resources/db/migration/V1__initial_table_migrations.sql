create table users
(
    id       BIGINT auto_increment
        primary key,
    email    VARCHAR(255)                   not null,
    password VARCHAR(255)                   not null,
    role     VARCHAR(20) default 'CUSTOMER' not null
);

create table customers
(
    id         BIGINT auto_increment primary key ,
    first_name VARCHAR(55) not null,
    last_name  VARCHAR(55) not null,
    street_number VARCHAR(10) not null,
    street    VARCHAR(55) not null,
    postcode   VARCHAR(10)  not null
);

create table staff
(
    id         BIGINT auto_increment primary key,
    first_name VARCHAR(55) not null,
    last_name  VARCHAR(55) not null
);

create table bookings
(
    id BIGINT auto_increment primary key ,
    customer_id BIGINT not null,
    guests INT not null,
    booking_date DATE not null,
    start_time TIME not null,
    end_time TIME not null,
    time_slot_id BIGINT not null,
    status VARCHAR(10) default 'PENDING' not null,
    table_id BIGINT not null,
    created_at TIMESTAMP default CURRENT_TIMESTAMP
);

create table time_slots
(
    id  BIGINT auto_increment primary key,
    start_time  TIME not null
);

create table restaurant_tables
(
    id              BIGINT auto_increment primary key,
    capacity int                             not null
);

create table shifts
(
    id         BIGINT auto_increment primary key,
    start_time TIME not null,
    end_time TIME not null
);

create table staff_shifts
(
    id       BIGINT auto_increment
        primary key,
    staff_id BIGINT not null,
    shift_id BIGINT not null,
    date     DATE   not null,
    constraint staff_shifts_date_unique
        unique (date, shift_id, staff_id),
    constraint staff_shifts_shifts_id_fk
        foreign key (shift_id) references shifts (id),
    constraint staff_shifts_staff_id_fk
        foreign key (staff_id) references staff (id)
);

alter table bookings
    add constraint bookings_customers_id_fk
        foreign key (customer_id) references customers (id);

alter table bookings
    add constraint bookings_restaurant_tables_id_fk
        foreign key (table_id) references restaurant_tables (id);

alter table bookings
add constraint bookings_timeslots_id_fk
        foreign key (time_slot_id) references time_slots(id);

alter table bookings
    add constraint unique_table_date_time_customer
        unique (table_id, booking_date, start_time, customer_id);


alter table customers
    add constraint customers_users_id_fk
        foreign key (id) references users (id);

alter table staff
    add constraint staff_users_id_fk
        foreign key (id) references users (id);





















