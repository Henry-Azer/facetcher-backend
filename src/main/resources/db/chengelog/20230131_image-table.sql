-- liquibase formatted sql

-- changeset henry:20230131_create_image_table
CREATE SEQUENCE public.image_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.image
(
    id                BIGINT                 NOT NULL DEFAULT nextval('image_id_sequence'::regclass),
    name              CHARACTER VARYING(100) NOT NULL,
    image             BIGINT                 NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    CONSTRAINT image_pk PRIMARY KEY (id)

) TABLESPACE pg_default;

-- changeset henry:20230305_modify_image_table_alter_image_url
ALTER TABLE public.image DROP COLUMN image;
ALTER TABLE public.image ADD COLUMN image_url CHARACTER VARYING(255) NOT NULL;