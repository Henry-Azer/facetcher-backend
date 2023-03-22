-- liquibase formatted sql

-- changeset henry:20230314_create_cloud_file_table
CREATE SEQUENCE public.cloud_file_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.cloud_file
(
    id                BIGINT                 NOT NULL DEFAULT nextval('cloud_file_id_sequence'::regclass),
    file_name         CHARACTER VARYING(250) NOT NULL,
    bucket_name       CHARACTER VARYING(250) NOT NULL,
    cdn_code          CHARACTER VARYING(250) NOT NULL,
    file_url          CHARACTER VARYING(250) NOT NULL,

    created_date      TIMESTAMP              NOT NULL,
    modified_date     TIMESTAMP              NOT NULL,
    created_by        CHARACTER VARYING(100) NOT NULL,
    modified_by       CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted BOOLEAN                NOT NULL DEFAULT FALSE,

    CONSTRAINT cloud_file_pk PRIMARY KEY (id)

) TABLESPACE pg_default;


-- changeset henry:20230322_add_type_column_to_cloud_file_table
ALTER TABLE public.cloud_file
ADD COLUMN type CHARACTER VARYING(250) NOT NULL;

