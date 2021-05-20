INSERT INTO papyrus.seccion(id, nombre)
	VALUES	(1, 'A'),
			(2, 'B'),
			(3, 'C'),
			(4, 'D'),
			(5, 'E'),
			(6, 'F'),
			(7, 'G'),
			(8, 'H'),
			(9, 'J'),
			(10, 'K');

INSERT INTO papyrus.libro(titulo, fecha_pub, seccion_id)
	VALUES 	('Bóvedas de acero', '1954-02-15', 4),
			('Historia del tiempo', '1988-04-23', 5),
			('El Hobbit', '1937-12-05', 7);

INSERT INTO papyrus.editorial(nombre)
	VALUES	('Debolsillo'),
			('Critica'),
			('Minotauro');

INSERT INTO papyrus.ejemplar(libro_id, editorial_id, edicion, isbn, paginas, estado, fecha_imp)
	VALUES	(1, 1, 1, '001-001-01-03', 423, 'Correcto', '2016-07-03'),
			(1, 1, 1, '001-001-01-02', 423, 'Regular', '2016-07-03'),
			(1, 1, 2, '001-001-02-05', 435, 'Impecable','2018-05-04'),
			(2, 1, 1, '002-001-01-01', 325, 'Deplorable', '2001-06-03'),
			(2, 2, 1, '002-002-01-04', 312, 'Bueno', '2002-05-05'),
			(2, 2, 1, '002-002-01-04', 312, 'Bueno', '2002-05-05'),
			(3, 1, 1, '003-001-01-03', 542, 'Correcto', '1985-12-21'),
			(3, 2, 1, '003-002-01-05', 542, 'Impecable', '1985-12-21'),
			(3, 3, 1, '003-003-01-02', 542, 'Regular', '1985-12-21');

INSERT INTO papyrus.autor(nombre, fecha_nac)
	VALUES	('J.R.R. Tolkien', '1892-01-03'),
			('Stephen Hawking', '1942-01-08'),
			('Isaac Asimov', '1920-01-02'),
			('Richard Dawkins', '1941-03-26');

INSERT INTO papyrus.libro_autor(libro_id, autor_id)
	VALUES	(1, 3),
			(2, 2),
			(3, 1);

INSERT INTO papyrus.categoria(nombre)
	VALUES	('Ciencia ficción'),
			('Divulgación'),
			('Fantasía'),
			('Ciencia'),
			('Policíaca'),
			('Historia'),
			('Romance');

INSERT INTO papyrus.libro_categoria(libro_id, categoria_id)
	VALUES	(1, 1),
			(2, 4),
			(1, 5),
			(3, 3),
			(2, 2);

INSERT INTO papyrus.empleado(dni, nombre, apellidos, telefono, email, fecha_nac, usuario, contrasenia)
	VALUES	('X11111111', 'Hamza', 'Mohammad', '601030106', 'hamzamohammad@gmail.com', '1995-04-23', 'admin', '$2a$04$WFK7lmh7XvOnXdjYC13o0.KIsI64g3KVEK3Au5PBAwDu9YLRak9Uu'),
			('22222222A', 'Antonio', 'Nuñez Calero', '612549315', 'antonionuñezcalero@gmail.com', '1995-04-19', 'antonionuñez95', '$2a$04$WFK7lmh7XvOnXdjYC13o0.KIsI64g3KVEK3Au5PBAwDu9YLRak9Uu'),
			('33333333B', 'Razvan', 'Stefan Raspopescu', '621564853', 'razvanstefan@gmail.com', '1991-08-02', 'razvanstefan91', '$2a$04$WFK7lmh7XvOnXdjYC13o0.KIsI64g3KVEK3Au5PBAwDu9YLRak9Uu');

INSERT INTO papyrus.rol(rol)
	VALUES	('ADMIN'),
			('USER');

INSERT INTO papyrus.empleado_rol(empleado_id, rol_id)
	VALUES	(1, 1),
			(1, 2),
			(2, 2),
			(3, 2);

INSERT INTO papyrus.socio(dni, nombre, apellidos, telefono, email, fecha_nac)
	VALUES	('44444444C', 'Estela', 'Morgado Guerrero', '621452546', 'estelamorgado@gmail.com', '1999-04-09'),
			('55555555D', 'Mario', 'Acuña Morado', '685492459', 'marioacuña@gmail.com', '1982-11-25'),
			('66666666E', 'Pablo', 'García Moreno', '698546548', 'pablogarcia@gmail.com', '1993-07-14');

INSERT INTO papyrus.prestamo(socio_id, ejemplar_id, empleado_id, fecha_inicio, fecha_fin, fecha_fin_real)
	VALUES	(1, 8, 3, '2021-04-09 12:53:42', '2021-04-16', '2021-04-15 11:45:14'),
			(1, 3, 2, '2021-04-15 12:14:51', '2021-05-10', '2021-05-10 10:15:21'),
			(2, 5, 1, '2021-05-01 20:15:32', '2021-05-15', '2021-05-19 09:05:57'),
			(3, 8, 3, '2021-04-16 14:45:34', '2021-04-22', '2021-04-22 19:34:15'),
			(3, 4, 2, '2021-04-24 20:06:52', '2021-05-10', '2021-05-10 18:58:12');

INSERT INTO papyrus.prestamo(socio_id, ejemplar_id, empleado_id, fecha_inicio, fecha_fin)
	VALUES	(1, 4, 2, '2021-05-18 13:02:56', '2021-05-29'),
			(2, 2, 3, '2021-05-14 18:41:59', '2021-06-03');