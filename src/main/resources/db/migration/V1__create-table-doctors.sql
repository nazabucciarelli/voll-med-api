CREATE TABLE doctors(
    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    dni varchar(10) not null unique,
    phone_number varchar(15) not null,
    speciality varchar(50) not null,
    district varchar(100) not null,
    street varchar(100) not null,
    complement varchar(100),
    number varchar(20),
    city varchar(100) not null,
    primary key (id)
);