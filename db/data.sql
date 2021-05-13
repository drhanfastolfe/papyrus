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