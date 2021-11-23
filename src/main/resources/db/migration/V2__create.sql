/**
 * Author:  mouseasw@gmail.com
 * Created: Nov 23, 2021
 */

CREATE SEQUENCE foo_id;
CREATE TABLE IF NOT EXISTS public.foo
(
    id integer NOT NULL DEFAULT nextval('foo_id'::regclass),
    description character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT foo_pkey PRIMARY KEY (id),
    CONSTRAINT foo_description UNIQUE (description)
);

CREATE SEQUENCE bar_id;
CREATE TABLE IF NOT EXISTS public.bar
(
    id integer NOT NULL DEFAULT nextval('bar_id'::regclass),
    description character varying COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT bar_pkey PRIMARY KEY (id),
    CONSTRAINT bar_description UNIQUE (description)
);

CREATE SEQUENCE foo_bar_id;
CREATE TABLE IF NOT EXISTS public.foo_bar
(
    id integer NOT NULL DEFAULT nextval('foo_bar_id'::regclass),
    foo_id integer NOT NULL,
    bar_id integer NOT NULL,
    CONSTRAINT foo_bar_pkey PRIMARY KEY (id),
    CONSTRAINT foo_bar_foo_id_fkey FOREIGN KEY (foo_id)
        REFERENCES public.foo (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT foo_bar_bar_id_fkey FOREIGN KEY (bar_id)
        REFERENCES public.bar (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO foo (description) VALUES
('Book of Boba Fett'), ('The Mandalorian'), ('Rebels'), ('A New Hope');

INSERT INTO bar (description) VALUES
('Boba Fett'), ('Obi-Wan Kenobi'), ('Darth Vader'), ('Ezra Bridger');

INSERT INTO foo_bar (foo_id, bar_id) VALUES
((SELECT id FROM foo WHERE description = 'Book of Boba Fett'), (SELECT id FROM bar WHERE description = 'Boba Fett')),
((SELECT id FROM foo WHERE description = 'The Mandalorian'), (SELECT id FROM bar WHERE description = 'Boba Fett')),
((SELECT id FROM foo WHERE description = 'Rebels'), (SELECT id FROM bar WHERE description = 'Obi-Wan Kenobi')),
((SELECT id FROM foo WHERE description = 'Rebels'), (SELECT id FROM bar WHERE description = 'Darth Vader')),
((SELECT id FROM foo WHERE description = 'Rebels'), (SELECT id FROM bar WHERE description = 'Ezra Bridger')),
((SELECT id FROM foo WHERE description = 'A New Hope'), (SELECT id FROM bar WHERE description = 'Obi-Wan Kenobi')),
((SELECT id FROM foo WHERE description = 'A New Hope'), (SELECT id FROM bar WHERE description = 'Darth Vader'));