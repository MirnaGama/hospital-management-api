create table patients (

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(11) not null unique,
    telephone varchar(20) not null,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    zip_code varchar(9) not null,
    additional_details varchar(100),
    house_number varchar(20),
    state varchar(2) not null,
    city varchar(100) not null,
    _active tinyint not null,

    primary key(id)
);
