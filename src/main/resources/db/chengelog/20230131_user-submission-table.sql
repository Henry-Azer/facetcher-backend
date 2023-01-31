-- liquibase formatted sql

-- changeset henry:20230131_create_user_submission_table
CREATE SEQUENCE public.user_submission_id_sequence
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

CREATE TABLE public.user_submission
(
    id                 BIGINT                 NOT NULL DEFAULT nextval('user_submission_id_sequence'::regclass),
    user_id            BIGINT                 NOT NULL,
    input_image_id     BIGINT                 NOT NULL,
    output_image_id    BIGINT                 NOT NULL,
    title              CHARACTER VARYING(250) NOT NULL,
    description        TEXT                   NOT NULL,
    trial_count        BIGINT                 NOT NULL,
    submission_date    TIMESTAMP              NOT NULL,
    submission_message CHARACTER VARYING(100) NOT NULL,

    created_date       TIMESTAMP              NOT NULL,
    modified_date      TIMESTAMP              NOT NULL,
    created_by         CHARACTER VARYING(100) NOT NULL,
    modified_by        CHARACTER VARYING(100) NOT NULL,
    marked_as_deleted  BOOLEAN                NOT NULL DEFAULT FALSE,

    CONSTRAINT user_submission_pk PRIMARY KEY (id),
    CONSTRAINT user_submission_user_id_fk FOREIGN KEY (user_id) REFERENCES public.user (id),
    CONSTRAINT user_submission_input_image_id_fk FOREIGN KEY (input_image_id) REFERENCES public.image (id),
    CONSTRAINT user_submission_output_image_id_fk FOREIGN KEY (output_image_id) REFERENCES public.image (id)

) TABLESPACE pg_default;
