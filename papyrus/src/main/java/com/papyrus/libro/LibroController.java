package com.papyrus.libro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroController
{
	@Autowired
	private LibroService service;
	
	@GetMapping("/lista")
	public String vistaListaLibros(Model model)
	{
		List<Libro> listaLibros = service.listarTodo();
		model.addAttribute("listaLibros", listaLibros);
		
		return "libro/listaLibros";
	}

	@GetMapping("/form")
	public String vistaAgregarLibro(Model model)
	{
		model.addAttribute("libro", new Libro());

		return "libro/formLibro";
	}

	@PostMapping("/agregar")
	public String agregarLibro(Libro libro, Model model)
	{
		service.agregar(libro);
		
		return "redirect:/libro/lista";
	}
}
