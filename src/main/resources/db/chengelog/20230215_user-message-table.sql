-- liquibase formatted sql

-- changeset henry:20230215_create_user_message_table
CREATE SEQUENCE public.user_message_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.user_message
(
    id                 BIGINT                 NOT NULL DEFAULT nextval('user_message_id_sequence'::regclass),
    user_id            BIGINT                 NOT NULL,
    title              CHARACTER VARYING(250) NOT NULL,
    description        TEXT                   NOT NULL,

    created_date       TIMESTAMP              NOT NULL,
    modified_date      TIMESTAMP              NOT NULL,
    created_by         CHARACTER VARYING(100) NOT NULL,
    modified_by        CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted  BOOLEAN                NOT NULL DEFAULT FALSE,

    CONSTRAINT user_message_pk PRIMARY KEY (id),
    CONSTRAINT user_message_user_id_fk FOREIGN KEY (user_id) REFERENCES public.user (id)

) TABLESPACE pg_default;
