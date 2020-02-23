create table sequence
(
    seq_name varchar(50) not null
        constraint sequence_pkey
            primary key,
    seq_count numeric(38)
);

INSERT INTO sequence (seq_name, seq_count) VALUES ('SEQ_GEN', 0);

create table person
(
    id bigint not null
        constraint person_pkey
            primary key,
    address varchar(255),
    name varchar(255)
);