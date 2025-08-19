create table if not exists todo (
    id varchar(255) not null,
    title varchar(255) not null,
    description text,
    done boolean not null,
    created_on timestamp  not null default current_timestamp,
    updated_on timestamp default null,
    primary key (id)
);
