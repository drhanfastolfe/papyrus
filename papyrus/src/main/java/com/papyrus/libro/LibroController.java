package com.papyrus.libro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		return "libros/listaLibro";
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
		model.addAttribute("libro", libro);
		
		return "/libros/editarLibro";
	}

	@PostMapping("libros/editar/{id}")
	public String editarLibro(@PathVariable("id") Long id, Libro libro, Model model)
	{
		Libro libroBd = libroService.findById(id);

		libroBd.setTitulo(libro.getTitulo());
		libroBd.setAnio_pub(libro.getAnio_pub());
		libroBd.setSeccion_id(libro.getSeccion_id());

		libroService.save(libroBd);
		
		return "redirect:/libros/lista";
	}	

	@GetMapping("libros/eliminar/{id}")
	public String eliminarLibroPorId(@PathVariable("id") Long id, Model model)
	{
		libroService.deleteById(id);

		return "redirect:/libros/lista";
	}

	//* método prueba para listar libros por editorial
	@GetMapping("libros/prueba")
	public String prueba(Model model)
	{
		List<Libro> listaLibros = libroService.findByEditorialNombre("Debolsillo");

		model.addAttribute("listaLibros", listaLibros);

		return "libros/prueba";
	}
}