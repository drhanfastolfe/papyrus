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

INSERT INTO papyrus.libro(titulo, anio_pub, seccion_id)
	VALUES 	('Bóvedas de acero', 1954, 4),
			('Historia del tiempo', 1988, 5),
			('El Hobbit', 1937, 7);

INSERT INTO papyrus.ejemplar(estado, libro_id)
	VALUES	('bueno', 1),
			('correcto', 1),
			('impecable', 1),
			('bueno', 1),
			('bueno', 2),
			('muy bueno', 2),
			('correcto', 1),
			('bueno', 3),
			('regular', 3),
			('regular', 3);

INSERT INTO papyrus.editorial(nombre)
	VALUES	('Debolsillo'),
			('Critica'),
			('Minotauro');

INSERT INTO papyrus.libro_editorial(libro_id, editorial_id, isbn, paginas, fecha_imp)
	VALUES	(1, 1, '123-123-123-0', 423, '2016-07-03'),
			(2, 2, '123-123-123-1', 325, '2001-06-03'),
			(3, 3, '123-123-123-2', 512, '2014-05-05'),
			(2, 1, '123-123-123-4', 352, '2003-02-13');

INSERT INTO papyrus.autor(nombre)
	VALUES	('J.R.R. Tolkien'),
			('Stephen Hawking'),
			('Isaac Asimov'),
			('Richard Dawkins');

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