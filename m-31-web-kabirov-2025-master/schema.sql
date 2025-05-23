create table student
(
    id         uuid not null primary key,
    name       text not null,
    email      text not null unique,
    password   text not null,
    group_name text null
);

create table discipline
(
    id         uuid not null primary key,
    name       text not null,
    teacher_id uuid null
);

create index on discipline
    using btree (teacher_id);

create table teacher
(
    id   uuid not null primary key,
    name text not null
);

create table student_2_discipline
(
    student_id    uuid not null,
    discipline_id uuid not null,
    primary key (student_id, discipline_id)
);
