-- Table: public.book_details

-- DROP TABLE IF EXISTS public.book_details;

CREATE TABLE IF NOT EXISTS public.book_details
(
    book_id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    author character varying(255) COLLATE pg_catalog."default",
    category character varying(255) COLLATE pg_catalog."default",
    description character varying(255) COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    CONSTRAINT book_details_pkey PRIMARY KEY (book_id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book_details
    OWNER to postgres;


-- Table: public.library_details

-- DROP TABLE IF EXISTS public.library_details;

CREATE TABLE IF NOT EXISTS public.library_details
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    due_date timestamp(6) without time zone,
    is_returned boolean NOT NULL,
    issued_date timestamp(6) without time zone,
    user_id character varying(255) COLLATE pg_catalog."default",
    book_id bigint,
    CONSTRAINT library_details_pkey PRIMARY KEY (id),
    CONSTRAINT book_id FOREIGN KEY (book_id)
    REFERENCES public.book_details (book_id) MATCH SIMPLE
                          ON UPDATE NO ACTION
                          ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.library_details
    OWNER to postgres;

-- Table: public.notifications

-- DROP TABLE IF EXISTS public.notifications;

CREATE TABLE IF NOT EXISTS public.notifications
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    created_at timestamp(6) without time zone,
    is_read boolean NOT NULL,
    message character varying(255) COLLATE pg_catalog."default",
    user_id bigint,
    CONSTRAINT notifications_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.notifications
    OWNER to postgres;





