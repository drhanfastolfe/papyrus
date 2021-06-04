package com.papyrus.libro;

import java.util.ArrayList;
import java.util.List;

import com.papyrus.autor.Autor;
import com.papyrus.autor.AutorService;
import com.papyrus.categoria.Categoria;
import com.papyrus.categoria.CategoriaService;
import com.papyrus.main.MainService;
import com.papyrus.seccion.Seccion;
import com.papyrus.seccion.SeccionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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