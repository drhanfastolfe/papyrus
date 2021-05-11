BEGIN;

CREATE SCHEMA papyrus
    AUTHORIZATION admin;

CREATE TABLE papyrus.autor
(
    id serial NOT NULL,
    nombre character varying(100) NOT NULL,
    fecha_nac date,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.categoria
(
    id serial NOT NULL,
    nombre character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.seccion
(
    id integer NOT NULL,
    nombre character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.libro
(
    id serial NOT NULL,
    titulo character varying(100) NOT NULL,
    anio_pub integer,
    seccion_id serial NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.ejemplar
(
    id serial NOT NULL,
    estado character varying(50) NOT NULL,
    detalle_id serial NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.socio
(
    id serial NOT NULL,
    dni character varying(10) NOT NULL UNIQUE,
    nombre character varying(100) NOT NULL,
    apellidos character varying(100) NOT NULL,
    telefono character varying(9) NOT NULL UNIQUE,
    email character varying(50) NOT NULL UNIQUE,
    fecha_nac date,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.prestamo
(
    id serial NOT NULL,
    socio_id serial NOT NULL,
    ejemplar_id serial NOT NULL,
    empleado_id serial NOT NULL,
    fecha_inicio date NOT NULL,
    fecha_fin date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.libro_categoria
(
    libro_id serial NOT NULL,
    categoria_id serial NOT NULL,
    PRIMARY KEY (libro_id, categoria_id)
);

CREATE TABLE papyrus.empleado
(
    id serial NOT NULL,
    dni character varying(100) NOT NULL UNIQUE,
    nombre character varying(100) NOT NULL,
    apellidos character varying(100) NOT NULL,
    telefono character varying(9) NOT NULL UNIQUE,
    email character varying(50) NOT NULL UNIQUE,
    fecha_nac date,
    usuario character varying(50) NOT NULL UNIQUE,
    contrasenia character varying(20) NOT NULL,
    admin boolean NOT NULL DEFAULT 'false',
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.editorial
(
    id serial NOT NULL,
    nombre character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.detalle
(
    id serial NOT NULL,
    libro_id serial NOT NULL,
    editorial_id serial NOT NULL,
    edicion integer NOT NULL,
    isbn character varying(13) UNIQUE,
    paginas integer NOT NULL,
    fecha_imp date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.libro_autor
(
    libro_id serial NOT NULL,
    autor_id serial NOT NULL,
    PRIMARY KEY (libro_id, autor_id)
);

ALTER TABLE papyrus.prestamo
    ADD FOREIGN KEY (socio_id)
    REFERENCES papyrus.socio (id)
    NOT VALID;


ALTER TABLE papyrus.prestamo
    ADD FOREIGN KEY (ejemplar_id)
    REFERENCES papyrus.ejemplar (id)
    NOT VALID;

ALTER TABLE papyrus.prestamo
    ADD FOREIGN KEY (empleado_id)
    REFERENCES papyrus.empleado (id)
    NOT VALID;

ALTER TABLE papyrus.libro_categoria
    ADD FOREIGN KEY (libro_id)
    REFERENCES papyrus.libro (id)
    ON DELETE CASCADE
	NOT VALID;


ALTER TABLE papyrus.libro_categoria
    ADD FOREIGN KEY (categoria_id)
    REFERENCES papyrus.categoria (id)
	ON DELETE CASCADE
	NOT VALID;


ALTER TABLE papyrus.detalle
    ADD FOREIGN KEY (libro_id)
    REFERENCES papyrus.libro (id)
	ON DELETE CASCADE
    NOT VALID;


ALTER TABLE papyrus.detalle
    ADD FOREIGN KEY (editorial_id)
    REFERENCES papyrus.editorial (id)
	ON DELETE CASCADE
    NOT VALID;


ALTER TABLE papyrus.libro_autor
    ADD FOREIGN KEY (libro_id)
    REFERENCES papyrus.libro (id)
	ON DELETE CASCADE
    NOT VALID;


ALTER TABLE papyrus.libro_autor
    ADD FOREIGN KEY (autor_id)
    REFERENCES papyrus.autor (id)
	ON DELETE CASCADE
    NOT VALID;


ALTER TABLE papyrus.ejemplar
    ADD FOREIGN KEY (detalle_id)
    REFERENCES papyrus.detalle (id)
	ON DELETE CASCADE
    NOT VALID;

ALTER TABLE papyrus.libro
    ADD FOREIGN KEY (seccion_id)
    REFERENCES papyrus.seccion (id)
    NOT VALID;

END;