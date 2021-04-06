# Pre-proyecto

## Introducción

Para este proyecto se va a considerar el supuesto de una biblioteca pública que necesita una herramienta para su gestión interna y funcionamiento. Se trata de una biblioteca local que pretende agilizar y/o digitalizar las pequeñas transacciones o préstamos que tienen lugar allí.

Para satisfacer éstas necesidades se va a desarrollar una aplicación, que bien en realidad sería una aplicación de escritorio, pero para cumplir ciertos requisitos se realizará con una arquetectura web cliente-servidor.

## Objetivo

Como ya he mencionado antes, el objetivo principal será proporcionar una plataforma donde el empleado público pueda todas las gestiones básicas con facilidad.

Es importante recordar que los objetivos pueden ir variando a medida que el proyecto avanza. Al fin y al cabo al ser un proyecto de clase se puede ir regulando la complejidad de éste.

Entrando más en detalle, Papyrus pretende cumplir con los siguientes objetivos:

- Insertar nuevos libros en la biblioteca
- Borrar libros de la biblioteca
- Modificar libros que ya se encuentren en la BBDD
- Marcar liros como no disponibles
- Ofrecer una categorización de los libros
- Gestión de socios
- Gestión de préstamos

## Herramientas

Igual que en el apartado anterior, las herramientas que se planean utilizar pueden variar en cuanto a capas superiores para aumentar la abstracción. Pero el núcleo de desarrollo será siempre constante.

A continuación se exponen las tecnologías del lado front-end:

- Núcleo: HTML, CSS, JavaScript, Sass y Bootstrap 
- Capa A: webpack y MVC
- Capa B: React

Para el back-end:

- Desarrollo: 
    - Núcleo: Java EE 8+ (JDBC, JSP, Servlets, MVC)
    - Capa A: Hibernate, Spring MVC
    - Capa B: Spring Rest, Thymeleaf
- BBDD: PostgreSQL y pgAdmin
- Servidor: Apache Tomcat 9+ y Ubuntu 20.04

La idea es separar el desarrollo en dos subproyectos, separar el lado cliente del servidor, conectados por peticiones HTTP. Aunque el desarrollo de ambas partes se realizará de manera simultánea para una mayor sinergia.

## Planificación

Es muy difícil determinar el tiempo que llevará desarrollar cada módulo del proyecto, por lo que no se relizará una planificación tradicional sobre los apartados del proyecto.

Sin embargo, se dividirá el proyecto de la siguiente manera:

- Abril:
    - Familiarizarse con las herramientas núcleo del proyecto
    - Realizar pequeñas prácticas (simulaciones) con el stack de tecnologías
    - Crear el modelo de datos más eficiente

- Mayo - Junio:
    - Preparar el entorno de desarrollo
    - Realizar el desarrollo en paralelo del front-end y el back-end

Por último, todo el desarrollo del proyecto se irá registrando en Github.
