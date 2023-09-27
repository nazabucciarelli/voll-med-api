CREATE TABLE patients(
    id bigint not null auto_increment,
    name varchar(100) not null,
    phone_number varchar(20) not null,
    email varchar(100) not null unique,
    dni varchar(10) not null unique,
    district varchar(100) not null,
    street varchar(100) not null,
    complement varchar(100),
    number varchar(20),
    city varchar(100) not null,
    primary key (id)
);