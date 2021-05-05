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
	public String vistaFormLibro(Model model)
	{
		Libro libro = new Libro();
		model.addAttribute("libro", libro);

		return "libro/formLibro";
	}

	@PostMapping("/insertar")
	public String insertarLibro(Libro libro)
	{
		service.insertar(libro);
		
		return "redirect:/libro/lista";
	}
}
