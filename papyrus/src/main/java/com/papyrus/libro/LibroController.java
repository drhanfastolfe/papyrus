package com.papyrus.libro;

import java.util.List;

import com.papyrus.seccion.Seccion;
import com.papyrus.seccion.SeccionService;

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

	@Autowired
	private SeccionService seccionService;
	
	@GetMapping("libros/lista")
	public String mostrarListaLibros(Model model)
	{
		List<Libro> listaLibros = libroService.findAll();
		model.addAttribute("listaLibros", listaLibros);
		
		return "libros/listaLibro";
	}
	
	@GetMapping("libros/insertar")
	public String mostrarInsertarLibro(Model model)
	{
		Libro libro = new Libro();
		List<Seccion> listaSecciones= seccionService.findAll();

		model.addAttribute("libro", libro);
		model.addAttribute("listaSecciones", listaSecciones);
		
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

		model.addAttribute("libro", libro);
		model.addAttribute("listaSecciones", listaSecciones);
		
		return "/libros/editarLibro";
	}

	@PostMapping("libros/editar/{id}")
	public String editarLibro(@PathVariable("id") Long id, Libro libro, Model model)
	{
		Libro libroBd = libroService.findById(id);
		
		libroBd.setTitulo(libro.getTitulo());
		libroBd.setFecha_pub(libro.getFecha_pub());
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

	@GetMapping("libros/configurar/{id}")
	public ModelAndView mostrarLibroPorId(Long id)
	{
		ModelAndView mav = new ModelAndView("libros/configurarLibro");
		Libro libro = libroService.findById(id);
		mav.addObject("libro", libro);

		return mav;
	}
}