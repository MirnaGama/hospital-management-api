create table consultations (

    id bigint not null auto_increment,
    patient_id bigint not null,
    doctor_id bigint not null,
    consultation_date datetime not null, 

    primary key(id),
    constraint fk_doctor_id foreign key(doctor_id) references doctors(id),
    constraint fk_patient_id foreign key(patient_id) references patients(id)
);

