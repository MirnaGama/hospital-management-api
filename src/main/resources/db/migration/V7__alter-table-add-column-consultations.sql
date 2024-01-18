alter table consultations add column canceled tinyint;
update consultations set canceled = 0;
alter table consultations add column reason_cancellation varchar(100);
