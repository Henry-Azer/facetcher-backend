-- liquibase formatted sql

-- changeset henry:20230124_create_role_table
CREATE SEQUENCE public.role_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.role
(
    id                BIGINT                 NOT NULL DEFAULT nextval('role_id_sequence'::regclass),
    name              CHARACTER VARYING(50)  NOT NULL,
    description       CHARACTER VARYING(500) NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    PRIMARY KEY (id),
    CONSTRAINT role_name_unq UNIQUE (name)

) TABLESPACE pg_default;

-- changeset henry:20230128_insert_user_role_into_role_table
INSERT INTO public.role (id, name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES (nextval('role_id_sequence'::regclass), 'USER',
        'Refers to the user who has set of tasks, responsibilities, and permissions assigned to a specific group of users within the project.',
        current_timestamp, current_timestamp, 'ADMIN', 'ADMIN', false);

-- changeset henry:20230128_insert_admin_role_into_role_table
INSERT INTO public.role (id, name, description, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES (nextval('role_id_sequence'::regclass), 'ADMIN',
        'Refers to the admin user who is responsible for managing and maintaining the project''s overall infrastructure and configuration. This includes tasks such as setting up user accounts and permissions, configuring project settings, managing project resources, and monitoring the project''s performance.',
        current_timestamp, current_timestamp, 'ADMIN', 'ADMIN', false);

