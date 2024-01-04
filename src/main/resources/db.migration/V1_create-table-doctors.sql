create table doctors (

    id bigint not null auto_increment,
    name varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(6) not null unique,
    speciality varchar(100) not null,
    telephone varchar(20) not null,
    street varchar(100) not null,
    neighborhood varchar(100) not null,
    zipCode varchar(9) not null,
    additionalDetails varchar(100),
    houseNumber varchar(20),
    state varchar(2) not null,
    city varchar(100) not null,

    primary key(id)
);