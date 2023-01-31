-- liquibase formatted sql

-- changeset henry:20230128_create_user_role_table
CREATE SEQUENCE public.user_role_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.user_role
(
    id                BIGINT                 NOT NULL DEFAULT nextval('user_role_id_sequence'::regclass),
    user_id           BIGINT                 NOT NULL,
    role_id           BIGINT                 NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    CONSTRAINT user_role_pk PRIMARY KEY (id),
    CONSTRAINT user_role_user_id_role_id_unq UNIQUE (user_id, role_id),
    CONSTRAINT user_role_user_id_fk FOREIGN KEY (user_id) REFERENCES public.user (id),
    CONSTRAINT user_role_role_id_fk FOREIGN KEY (role_id) REFERENCES public.role (id)

) TABLESPACE pg_default;

-- changeset henry:20230128_insert_user_role_into_user_role_table
INSERT INTO public.user_role (id, user_id, role_id, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES (nextval('role_id_sequence'::regclass), 1, 1, current_timestamp, current_timestamp, 'ADMIN', 'ADMIN', false);

-- changeset henry:20230128_insert_admin_role_into_user_role_table
INSERT INTO public.user_role (id, user_id, role_id, created_date, modified_date, created_by, modified_by, marked_as_deleted)
VALUES (nextval('role_id_sequence'::regclass), 2, 2, current_timestamp, current_timestamp, 'ADMIN', 'ADMIN', false);

