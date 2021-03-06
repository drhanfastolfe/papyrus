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
    nombre character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.seccion
(
    id integer NOT NULL,
    nombre character varying(3) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.libro
(
    id serial NOT NULL,
    titulo character varying(150) NOT NULL,
    fecha_pub date NOT NULL,
    seccion_id serial NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.ejemplar
(
    id serial NOT NULL,
    libro_id serial NOT NULL,
    editorial_id serial NOT NULL,
    edicion integer NOT NULL,
    isbn character varying(13) NOT NULL,
    paginas integer NOT NULL,
    estado character varying(11) NOT NULL,
    fecha_imp date NOT NULL,
    fecha_ins timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.socio
(
    id serial NOT NULL,
    dni character varying(9) NOT NULL UNIQUE,
    nombre character varying(50) NOT NULL,
    apellidos character varying(100) NOT NULL,
    telefono character varying(9) NOT NULL UNIQUE,
    email character varying(100) NOT NULL UNIQUE,
    fecha_nac date NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.prestamo
(
    id serial NOT NULL,
    socio_id serial,
    ejemplar_id serial,
    empleado_id serial,
    fecha_inicio timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    fecha_fin date NOT NULL,
    fecha_fin_real timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.libro_categoria
(
    libro_id serial NOT NULL,
    categoria_id serial NOT NULL,
    PRIMARY KEY (libro_id, categoria_id)
);

CREATE TABLE papyrus.editorial
(
    id serial NOT NULL,
    nombre character varying(100) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.libro_autor
(
    libro_id serial NOT NULL,
    autor_id serial NOT NULL,
    PRIMARY KEY (libro_id, autor_id)
);

CREATE TABLE papyrus.empleado
(
    id serial NOT NULL,
    dni character varying(9) NOT NULL UNIQUE,
    nombre character varying(50) NOT NULL,
    apellidos character varying(100) NOT NULL,
    telefono character varying(9) NOT NULL UNIQUE,
    email character varying(100) NOT NULL UNIQUE,
    fecha_nac date NOT NULL,
    usuario character varying(50) NOT NULL UNIQUE,
    contrasenia character varying(150) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.rol
(
    id serial NOT NULL,
    rol character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE papyrus.empleado_rol
(
    empleado_id serial NOT NULL,
    rol_id serial NOT NULL,
    PRIMARY KEY (empleado_id, rol_id)
);

ALTER TABLE papyrus.empleado_rol
    ADD FOREIGN KEY (empleado_id)
    REFERENCES papyrus.empleado (id)
    NOT VALID;

ALTER TABLE papyrus.empleado_rol
    ADD FOREIGN KEY (rol_id)
    REFERENCES papyrus.rol (id)
    NOT VALID;

ALTER TABLE papyrus.prestamo
    ADD FOREIGN KEY (socio_id)
    REFERENCES papyrus.socio (id)
	ON DELETE SET NULL
    NOT VALID;


ALTER TABLE papyrus.prestamo
    ADD FOREIGN KEY (ejemplar_id)
    REFERENCES papyrus.ejemplar (id)
	ON DELETE SET NULL
    NOT VALID;

ALTER TABLE papyrus.prestamo
    ADD FOREIGN KEY (empleado_id)
    REFERENCES papyrus.empleado (id)
	ON DELETE SET NULL
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


ALTER TABLE papyrus.ejemplar
    ADD FOREIGN KEY (libro_id)
    REFERENCES papyrus.libro (id)
	ON DELETE CASCADE
    NOT VALID;


ALTER TABLE papyrus.ejemplar
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

ALTER TABLE papyrus.libro
    ADD FOREIGN KEY (seccion_id)
    REFERENCES papyrus.seccion (id)
    NOT VALID;

END;