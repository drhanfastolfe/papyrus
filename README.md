# Papyrus

## 0. Pre-proyecto

### Introducción

Para este proyecto se va a considerar el supuesto de una biblioteca pública que necesita una herramienta para su gestión interna y funcionamiento. Se trata de una biblioteca local que pretende agilizar y/o digitalizar las pequeñas transacciones o préstamos que tienen lugar allí.

Para satisfacer éstas necesidades se va a desarrollar una aplicación, que bien en realidad sería una aplicación de escritorio, pero para cumplir ciertos requisitos se realizará con una arquetectura web cliente-servidor.

### Objetivo

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

### Herramientas

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

### Planificación

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

## 1. Introducción

Papyrus es una herramienta de gestión interna para bibliotecas. Pretende agilizar y/o digitalizar toda las transacciones necesarias que tienen lugar en una biblioteca de libros.

Es una aplicación que en realidad no se habría desarrollado como una aplicación web pero por cumplir requisitos del proyecto y objetivos de aprendizaje se ha planteado cono un proyecto web con arquitectura cliente-servidor.

Papyrus se ha desarrollado de manera que se pueda extender su uso para una red de bibliotecas. Todo su desarrollo desde la base de datos, el modelo, el acceso a datos y la lógica de negocio está modularizada. Además, las tecnologías que se ha utilizado permiten la gestión eficiente, dado el caso, de una enorme cantidad de datos.

## 2. Funcionalidades

Para plantear el desarrollo de las funcionalidades se ha mantenido una perspectiva se han tenido en cuenta las perspectivas del administrador o gerente, del empleado y el lector. Esta visión ha permitido determinar diferentes casos de uso (historias de usuario) de la herramienta que se ha reflejado en las siguientes funcionalidades:

- Dashboard: consiste en un compendio de estádisticas de interés para una gestión más eficiente de la biblioteca. 
- Gestión de biblioteca: aquí se concentra toda la gestión de los libros que se encuentra bien estructura para su correcta persistencia en la base de datos.
- Gestión de socios: permite realizar la alta y baja de socios, además es posible actualizar la información cuando sea necesario. Además se genera una blacklist de socios que frecuentemente realizan devoluciones tardías.
- Gestión de préstamos: es el núcleo de la biblioteca, permite generar y finalizar préstamos de manera que se asocie correctamente con las demás funcionalidades. 
- Gestión de empleados: es una gestión que sólo puede realizar el administrador o gerente, permite dar de alta a nuevos empleados, con sus correspondiente roles, que de esta manera podrán usar la herramienta.
- Login: función para iniciar y cerrar sesión con contraseña encriptada.
- Buscadores: filtrado textual y más para todas las entidades que componen el proyecto.

## 3. Tecnologías 

La realización del proyecto ha sido un medio prácito eficaz para iniciarse y/o aprender las tecnologías que se han utilizado para su desarrollo.

- Base de datos:
    - PostgreSql
    - PgAdmin 4
- Desarrollo:
    - Spring Boot:
        - Spring Web
        - Spring Data Jpa (Hibernate)
        - Spring Security
        - Thymeleaf
    - HTML
    - CSS
    - JavaScript
    - Bootstrap 5
- Entorno:
    - Ubuntu 20.04
    - Apache Tomcat 9
    - Eclipse
    - Visual Studio Code
- Control de versiones:
    - Git
    - Github

## 4. Base de datos

El proyecto tiene un gran contenido de datos que ha provocado que se le tenga que dedicar gran parte del tiempo en crear un esquema lo más lógico y coherente posible que permita la implementación de mayores niveles de complejidad en futuras versiones.

### 4.1. Diagrama entidad relación:

![Diagrama entidad relación][der]

### 4.2. Esquema

Aquí se puede consultar el esquema completo de la base de datos: [schema.sql](https://github.com/drhanfastolfe/papyrus/blob/master/db/schema.sql)

- **Distinción libro - ejemplar**: porque realmente un libro es un concepto abstracto, aunque cotidianamente utilizamos esa palabra para referirnos al objeto físico que en este caso sería el ejemplar. Para entenderlo mejor, hay que imaginar, por ejemplo, que lo que se desgasta por el uso es el ejemplar, no "EL" libro que concibió el autor en sus pensamientos.

- **Tabla préstamos**: es la tabla núcleo desde la que se puede acceder a la información de cualquier tabla. Lo más importante es que no acepta la eliminación de filas en cascada, para poder conservar un historial de los préstamos.

```sql
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
```

- **Relación editorial - ejemplar**: a raíz de lo explicado en la distinción libro - ejemplar, es importante saber que editorial prepara o imprime los ejemplares pertenecientes a un libro. Además un libro puede ser impreso por varias editoriales, lo que genera propiedades, como el número de páginas, únicas para cada ejemplar perteneciente a un determinado libro y editorial.

- **Enciptación**: las contraseñas de los empeleado estarán encriptadas independientemente de los roles que tengan asignados.

## 5. Desarrollo

*En este apartado se va a explicar, mediante el ejemplo simplificado un CRUD de la entidad libro, el código de la aplicación según la estrutura del proyecto*

Archivo pom de Maven que permite ver las depencencias del proyecto Java:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.4.5</version>
		<relativePath/>
	</parent>
	<groupId>com.papyrus</groupId>
	<artifactId>papyrus</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>papyrus</name>
	<description>Proyecto final de FP</description>
	<properties>
		<java.version>11</java.version>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
			<groupId>org.postgresql</groupId>
			<artifactId>postgresql</artifactId>
			<scope>runtime</scope>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<dependency>
			<groupId>nz.net.ultraq.thymeleaf</groupId>
			<artifactId>thymeleaf-layout-dialect</artifactId>
			<version>2.5.3</version>
		</dependency>
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-security</artifactId>
        </dependency>
		<dependency>
			<groupId>org.thymeleaf.extras</groupId>
			<artifactId>thymeleaf-extras-springsecurity5</artifactId>
		</dependency>
	</dependencies>
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>
</project>
```


### 5.1. Modelo

Los modelos son las representaciones en forma de **POJO** (Plain Old Java Object) de las tablas de la base de datos. Tener esta representación permite establecer las relaciones correspondientes entre todos los modelos. Para ello se utiliza el módulo **Spring Data JPA** que utiliza el **ORM Hibernate** (implementación de la especificación JPA de Java) para evitar contruir de manera directa consultas SQL extensas y tediosas.

- Modelo libro:

```java
@Entity
public class Libro 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String titulo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fecha_pub;
	private Integer seccion_id;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.ALL)
    private List<Ejemplar> listaEjemplares;

	@ManyToOne
	@JoinColumn(name = "seccion_id", insertable = false, updatable = false)
	private Seccion seccion;

	@ManyToMany
	@JoinTable
	(
		name = "libro_autor",
		joinColumns = @JoinColumn(name = "libro_id"),
		inverseJoinColumns = @JoinColumn(name = "autor_id")
	)
	private List<Autor> listaAutores;

	@ManyToMany
	@JoinTable
	(
		name = "libro_categoria",
		joinColumns = @JoinColumn(name = "libro_id"),
		inverseJoinColumns = @JoinColumn(name = "categoria_id")
	)
	private List<Categoria> listaCategorias;

	// Getters y setters correspondientes

	public String getEditorialesStr()
	{
		String editoriales = "";

		for(int i = 0; i < this.listaEjemplares.size(); i++)
		{
			if(!editoriales.contains(this.listaEjemplares.get(i).getEditorial().getNombre()))
			{
				editoriales += this.listaEjemplares.get(i).getEditorial().getNombre();
				
				if((i != this.listaEjemplares.size() - 1) && (!editoriales.contains(this.listaEjemplares.get(i + 1).getEditorial().getNombre())))
				{
					editoriales += ", ";
				}
			}
		}
		
		return editoriales;
	}

	public String getAutoresStr()
	{
		String autores = "";

		for(int i = 0; i < this.getListaAutores().size(); i++)
		{
			autores += this.getListaAutores().get(i).getNombre();
			
			if(i != this.getListaAutores().size() - 1)
			{
				autores += ", ";
			}
		}
		
		return autores;
	}

	public String getCategoriasStr()
	{
		String categorias = "";

		for(int i = 0; i < this.getListaCategorias().size(); i++)
		{
			categorias += this.getListaCategorias().get(i).getNombre();
			
			if(i != this.getListaCategorias().size() - 1)
			{
				categorias += ", ";
			}
		}
		
		return categorias;
	}

	public int getEjemplaresCount()
	{
		return this.getListaEjemplares().size();
	}

	public int getEjemplaresDisponiblesCount()
	{
		int count = 0;

		for(Ejemplar ejemplar : this.listaEjemplares)
		{
			if (ejemplar.disponible()) count++;
		}

		return count;
	}

	public int prestamosCount()
	{
		int prestamos = 0;

		for(Ejemplar ejemplar : this.getListaEjemplares())
		{
			prestamos += ejemplar.getListaPrestamos().size();
		}

		return prestamos;
	}
}
``` 

### 5.2. Repositorio

Ésta capa se compone principalmente al la interfaz que permite el acceso a datos y el manejo de los modelos. Esta función es posible gracias a Spring Boot JPA. Debido a que esta interfaz implementa de JpaRepository, integra todas las funcionalidades de ésta, de manera que permite relizar una gran variedad de consultas a la base de datos desde diferentes métodos.

- Repositorio de libro:

```java
@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>
{
    @Query("select l from Libro l where unaccent(lower(concat(l.titulo, ' ', (extract (year from l.fecha_pub))))) like %?1%")
	public List<Libro> search(String keyword);
}
```

### 5.3. Servicio

En este módulo se desarrolla toda la lógica de negocio. El servicio utiliza los métodos del repositorio para acceder a los datos y por otro lados los métodos de los modelos para resolver las necesidades del controlador.

- Servicio libro:

```java
@Service
public class LibroService
{
	@Autowired
	private LibroRepository repo;

	@Autowired
	private AutorService autorService;

	@Autowired
	private CategoriaService categoriaService;
	
	public List<Libro> findAll()
	{
		return repo.findAll();
	}
	
	public void save(Libro libro)
	{
		repo.save(libro);
	}
	
	public Libro findById(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void deleteById(Long id)
	{
		repo.deleteById(id);
	}

	public List<Libro> search(String keyword)
	{
		List<Libro> listaLibros = new ArrayList<>();
		Set<Libro> setLibros = new LinkedHashSet<>();

		setLibros.addAll(repo.search(keyword));

		for (Autor autor : autorService.search(keyword))
		{
			setLibros.addAll(autor.getListaLibros());	
		}

		for (Categoria categoria : categoriaService.search(keyword))
		{
			setLibros.addAll(categoria.getListaLibros());	
		}

		List<Libro> listaLibrosSet = new ArrayList<>(setLibros);
		listaLibros = listaLibrosSet;

		return listaLibros;
	}

	public List<Libro> librosMasPrestados()
	{
		List<Libro> librosMasPrestados = repo.findAll();

		Comparator<Libro> comparaPrestamos = (Libro l1, Libro l2) -> Integer.valueOf(l1.prestamosCount()).compareTo(Integer.valueOf(l2.prestamosCount())); 

		Collections.sort(librosMasPrestados, comparaPrestamos.reversed());

		if(librosMasPrestados.size() > 5)
		{
			librosMasPrestados = librosMasPrestados.subList(0, 5);
		}

		return librosMasPrestados;
	}

	public List<Libro> librosMasEjemplares()
	{
		List<Libro> librosMasEjemplares = findAll();

		Comparator<Libro> comparaEjemplares = (Libro l1, Libro l2) -> Integer.valueOf(l1.getListaEjemplares().size()).compareTo(Integer.valueOf(l2.getListaEjemplares().size())); 

		Collections.sort(librosMasEjemplares, comparaEjemplares.reversed());

		if(librosMasEjemplares.size() > 5)
		{
			librosMasEjemplares = librosMasEjemplares.subList(0, 5);
		}

		return librosMasEjemplares;
	}
}
```

### 5.4. Controlador

El controlador hace el papel de director de orquesta que según las rutas solicitadas y los métodos HTTP reponde con difetentes vistas. Utiliza los métodos del servicio. Contiene cierta lógica de tratamiento de los parámteros de la petición.

- Controlador libro:

```java
@Controller
public class LibroController
{
	@Autowired
	private LibroService libroService;

	@Autowired
	private SeccionService seccionService;

	@Autowired
	private AutorService autorService;

	@Autowired
	private CategoriaService categoriaService;

	@Autowired
	private MainService mainService;
	
	@GetMapping("libros/lista")
	public String mostrarListaLibro(Model model, String keyword)
	{
		List<Libro> listaLibros = new ArrayList<>();

		if(keyword != null)
		{
			keyword = mainService.normalizaStr(keyword);
			listaLibros = libroService.search(keyword);
		}
		else
		{
			listaLibros = libroService.findAll();
		}

		model.addAttribute("listaLibros", listaLibros);
		model.addAttribute("count", listaLibros.size());
		
		return "libros/listaLibro";
	}
	
	@GetMapping("libros/insertar")
	public String mostrarInsertarLibro(Model model)
	{
		Libro libro = new Libro();
		List<Seccion> listaSecciones = seccionService.findAll();
		List<Autor> listaAutoresBd = autorService.findAll();
		List<Categoria> listaCategoriasBd = categoriaService.findAll();

		model.addAttribute("libro", libro);
		model.addAttribute("listaSecciones", listaSecciones);
		model.addAttribute("listaAutoresBd", listaAutoresBd);
		model.addAttribute("listaCategoriasBd", listaCategoriasBd);
		
		return "libros/insertarLibro";
	}
	
	@PostMapping("libros/insertar")
	public String insertarLibro(Libro libro)
	{
		libroService.save(libro);
		
		return "redirect:/libros/lista";
	}

	@GetMapping("libros/editar/{id}")
	public String mostrarEditarLibro(@PathVariable("id") Long id, Model model)
	{
		Libro libro = libroService.findById(id);
		List<Seccion> listaSecciones= seccionService.findAll();
		List<Autor> listaAutoresBd = autorService.findAll();
		List<Categoria> listaCategoriasBd = categoriaService.findAll();

		model.addAttribute("libro", libro);
		model.addAttribute("listaSecciones", listaSecciones);
		model.addAttribute("listaAutoresBd", listaAutoresBd);
		model.addAttribute("listaCategoriasBd", listaCategoriasBd);
		
		return "/libros/editarLibro";
	}

	@PostMapping("libros/editar/{id}")
	public String editarLibro(@PathVariable("id") Long id, Libro libro, Model model)
	{
		Libro libroBd = libroService.findById(id);
		
		libroBd.setTitulo(libro.getTitulo());
		libroBd.setFecha_pub(libro.getFecha_pub());
		libroBd.setSeccion_id(libro.getSeccion_id());
		libroBd.setListaAutores(libro.getListaAutores());
		libroBd.setListaCategorias(libro.getListaCategorias());
		
		libroService.save(libroBd);
		
		return "redirect:/libros/lista";
	}	

	@GetMapping("libros/eliminar/{id}")
	public String eliminarLibroPorId(@PathVariable("id") Long id, Model model)
	{
		libroService.deleteById(id);
		
		return "redirect:/libros/lista";
	}
}
```

### 5.5. Vistas

Esta capa se encarga contruir y visualizar la interfaz desde la que el usuario va a interactuar con la aplicación. Para ello se ha utilizado principalmente como motor de plantilla Thymeleaf y Bootstrap 5 para los estilos.

- Vista principal de libro:

```html
<body>
  <section layout:fragment="custom-content">
    <h3 class="mt-4">Lista de libros <a th:href="@{/libros/insertar}" class="btn btn-info btn-sm"><span data-feather="plus-square"></span></a></h3>
    <form th:action="@{/libros/lista}" method="get">
      <fieldset>
        <div class="container">
          <div class="row justify-content-center mt-4">
            <div class="col-3 m-2">
              <input type="text" class="form-control" name="keyword" placeholder="título, autor, año, categoría..."/>
            </div>
          </div>
          <div class="d-flex justify-content-center">
            <button class="btn btn-primary m-4" type="submit">Filtrar</button>
            <a th:href="@{/libros/lista}" class="btn btn-secondary m-4">Limpiar</a>
            <span th:text="${'TOTAL: ' + count}" class="m-4 fs-4 fw-bold"></span>
          </div>
        </div>
      </fieldset>
    </form>
    <div class="table-responsive">
      <table class="table table-striped table-sm">
        <thead>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Autor/es</th>
            <th>Fecha publicación</th>
            <th>Categoría/s</th>
            <th>Editorial/es</th>
            <th>Ejemplares totales</th>
            <th>Ejemplares disponibles</th>
            <th>Sección</th>
            <th>Acciones</th>
          </tr>
        </thead>
        <tbody>
          <tr th:each="libro : ${listaLibros}">
            <td th:text="${libro.id}"></td>
            <td th:text="${libro.titulo}"></td>
            <td th:text="${libro.getAutoresStr}"></td>
            <td th:text="${libro.fecha_pub}"></td>
            <td th:text="${libro.getCategoriasStr}"></td>
            <td th:text="${libro.getEditorialesStr}"></td>
            <td th:text="${libro.getEjemplaresCount}"></td>
            <td th:text="${libro.getEjemplaresDisponiblesCount}"></td>
            <td th:text="${libro.seccion.nombre}"></td>
            <td>
              <a th:href="@{/libros/editar/{id}(id=${libro.id})}" class="btn btn-warning btn-sm"><span data-feather="edit"></span></a>
              <a th:href="@{/libros/eliminar/{id}(id=${libro.id})}" class="btn btn-danger btn-sm"><span data-feather="trash-2"></span></a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </section>
</body>
```

- Vista para creción y editado de libro

```html
<body>
  <section layout:fragment="custom-content">
    <h3 class="mt-4">Insertar libro</h3>
    <form th:action="@{/libros/insertar}" th:object="${libro}" method="post">
      <fieldset>
        <div class="form-group">
          <label class="form-label mt-4">Selecciona autor/es o
            <a th:href="@{/auotres/insertar}" class="btn btn-info btn-sm">inserta uno nuevo</a></label>
          <select class="form-select" multiple="" th:field="*{listaAutores}">
            <option th:each="autor : ${listaAutoresBd}" th:value="${autor.id}" th:text="${autor.nombre}"></option>
          </select>
        </div>
        <div class="form-group">
          <label class="form-label mt-4">Selecciona categoría/s o
            <a th:href="@{/categorias/insertar}" class="btn btn-info btn-sm">inserta una nueva</a></label>
          <select class="form-select" multiple="" th:field="*{listaCategorias}">
            <option th:each="categoria : ${listaCategoriasBd}" th:value="${categoria.id}" th:text="${categoria.nombre}">
            </option>
          </select>
        </div>
        <div class="form-group">
          <label class="form-label mt-4">Título</label>
          <input type="text" class="form-control" th:field="*{titulo}" placeholder="" />
        </div>
        <div class="form-group">
          <label class="form-label mt-4">Fecha de publicación</label>
          <input type="date" class="form-control" th:field="*{fecha_pub}" placeholder="" />
        </div>
        <div class="form-group">
          <label class="form-label mt-4">Sección</label>
          <select class="form-select" th:field="*{seccion_id}">
            <option th:each="seccion : ${listaSecciones}" th:value="${seccion.id}" th:text="${seccion.nombre}"></option>
          </select>
        </div>
        <button type="submit" class="btn btn-primary mt-4">Insertar</button>
      </fieldset>
    </form>
  </section>
</body>
```

## 6. Futuras versiones

Como ya se ha explicado antes, la aplicación está desarrollada de manera que se le puedan añadir más capas de comlejidad y funcionalidades nuevas con facilidad.

Esta versión del proyecto es para una biblioteca de libros local, no muy grande. Pero se planea desarrollar un versión que pueda realizar la gestión de una red de bibliotecas a nivel nacional.

### 6.1. Nuevas funcionalidades

- Comunicación contínua entre bibliotecas
- Plataforma para socios
- Préstamos online con reserva
- Proporcionar una servicio avanzado de búsquedas
- Tratamiento inteligente de los datos

### 6.2. Tecnologías adicionales

- Pentaho Data Intagration (ETL)
- Elasticsearch
- Spring Rest
- React Native

### 6.3. Plantemiento

El proyecto se dividirá en tres partes principales:

- Tratamiento de datos:
    - Consistirá en la base datos actual con las nuevas tablas correspondientes
    - Un proceso de transformación de los datos desde la base de datos a archivos JSON para los bulks de Elasticsearch, es decir, un ETL. Se usará Pentaho para esta tarea.
- Motor de búsqueda:
    - En Elasticsearch la información se estructura en índices. Un índice es una colección de documentos que tienen cierta relación entre ellos. Un documento es una unidad básica de información que puede indexarse y que se expresa en formato JSON, puede tener un mapeo (atributos), una configuración de filtros, analizadores y sinónimos.
        - Index: una búsqueda en Elasticsearch nunca arroja el contenido como respuesta, sino el índice, en el cual se almacenan, ya preparados, todos los contenidos de todos los documentos. Es el llamado inverted index (índice invertido): para cada término de búsqueda se indica el lugar donde se puede encontrar dicho término.
        - Document: la salida para el índice son los documentos, en los cuales aparecen los datos. 
        - Field: un documento, a su vez, consta de varios campos.
    - Realizar el mapping: consiste en plantear la correcta estrutura de datos, según los intereses de búsqueda.
    - Aplicar analizadores: ciertos métodos de trasformación de los datos de la manera apropiada en tokens aplicando los filtros que mejor se adapten a las necsidades de los casos de búsquedas.
    - Creación de las queries de búsqueda de documentos según las historias de usuario posibles.
- Servicio rest:
    - Consistirá en transformar el módulo Spring Web en un servicio rest
    - Respuestas JSON
    - Compatibilidad con cualquier cliente, ya sea web o móvil
- Cliente
    - Un cliente oficial en formato web que tenga una plataforma adicional para socios
    - Cliente móvil sólo para socios gracias a React Native

## 7. Referencias

- [Píldoras Informáticas](https://www.pildorasinformaticas.es/)
- [Baeldung](https://www.baeldung.com/)
- [ZetCode](https://zetcode.com/)
- [JavaDesde0](https://javadesde0.com/)
- [Acodigo](http://acodigo.blogspot.com/p/spring.html)
- [Code Java](https://www.codejava.net/all-tutorials)
- [Spring](https://spring.io/)
- [Sprng Boot Tutorial](https://www.springboottutorial.com/)
- [Beginners Book](https://beginnersbook.com/)
- [Geeks for Geeks](https://www.geeksforgeeks.org/)

[der]: https://github.com/drhanfastolfe/papyrus/blob/master/db/erd.png