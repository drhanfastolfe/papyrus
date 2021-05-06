INSERT INTO papyrus.seccion(id, nombre)
	VALUES	(1, 'A'),(2, 'B'),(3, 'C'),(4, 'D'),(5, 'E'),
			(6, 'F'),(7, 'G'),(8, 'H'),(9, 'J'),(10, 'K');

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