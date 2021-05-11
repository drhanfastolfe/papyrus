package com.papyrus.libro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LibroController
{
	@Autowired
	private LibroService libroService;
	
	@GetMapping("libros/lista")
	public String mostrarListaLibros(Model model)
	{
		List<Libro> listaLibros = libroService.findAll();
		model.addAttribute("listaLibros", listaLibros);
		
		return "libros/libroLista";
	}

	@GetMapping("libros/{id}")
	public ModelAndView mostrarLibroPorId(Long id)
	{
		ModelAndView mav = new ModelAndView("libros/detallesLibro");
		Libro libro = libroService.findById(id);
		mav.addObject("libro", libro);

		return mav;
	}

	@GetMapping("libros/insertar")
	public String mostrarInsertarLibro(Model model)
	{
		Libro libro = new Libro();
		model.addAttribute("libro", libro);

		return "libro/insertarLibro";
	}

	@PostMapping("libros/insertar")
	public String insertarLibro(Libro libro)
	{
		libroService.save(libro);
		
		return "redirect:/libros/listaLibro";
	}

	
	@GetMapping("libros/editar/{id}")
	public String mostrarEditarLibro(Long id, Model model)
	{
		Libro libro = libroService.findById(id);
		model.addAttribute("libro", libro);
		
		return "/libros/editarLibro";
	}

	@PostMapping("libros/editar/{id}")
	public String editarLibro(Long id, Libro libro, Model model)
	{
		libroService.save(libro);
		
		return "/libro/listaLibro";
	}	

	@GetMapping("libros/eliminar/{id}")
	public String eliminarLibroPorId(Long id, Model model)
	{
		libroService.deleteById(id);

		return "redirect:/libros/listaLibro";
	}

	//* método prueba para listar libros por editorial
	@GetMapping("libros/prueba")
	public String prueba(Model model)
	{
		List<Libro> listaLibros = libroService.findByEditorialNombre("Debolsillo");

		model.addAttribute("listaLibros", listaLibros);

		return "libro/prueba";
	}
}