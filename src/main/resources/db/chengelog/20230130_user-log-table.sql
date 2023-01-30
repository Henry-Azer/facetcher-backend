-- liquibase formatted sql

-- changeset henry:20230124_create_user_log_table
CREATE SEQUENCE public.user_log_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.user_log
(
    id                BIGINT                 NOT NULL DEFAULT nextval('user_log_id_sequence'::regclass),
    user_id           BIGINT                 NOT NULL,
    log_status        CHARACTER VARYING(50)  NOT NULL,
    log_count         BIGINT                 NOT NULL,
    log_date          TIMESTAMP              NOT NULL,
    log_message       CHARACTER VARYING(100) NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    CONSTRAINT user_log_pk PRIMARY KEY (id),
    CONSTRAINT user_id_fk FOREIGN KEY (user_id) REFERENCES public.user (id)

) TABLESPACE pg_default;
