-- liquibase formatted sql

-- changeset henry:20230314_create_config_value_table
CREATE SEQUENCE public.config_value_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.config_value
(
    id                BIGINT                 NOT NULL DEFAULT nextval('config_value_id_sequence'::regclass),
    config_key        CHARACTER VARYING(250) NOT NULL,
    config_value      CHARACTER VARYING(250) NOT NULL,
    config_type       CHARACTER VARYING(250) NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    CONSTRAINT config_value_pk PRIMARY KEY (id)

) TABLESPACE pg_default;
