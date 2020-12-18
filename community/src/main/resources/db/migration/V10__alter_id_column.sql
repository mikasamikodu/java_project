alter table comment modify id bigint auto_increment;
alter table comment modify commentor bigint;
alter table question modify id bigint auto_increment;
alter table question modify creator_id bigint;
alter table user modify id bigint auto_increment;