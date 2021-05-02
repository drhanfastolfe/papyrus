package com.papyrus.libro;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/libro")
public class LibroController
{
	@Autowired
	private LibroService service;
	
	@RequestMapping("/lista")
	public String listaLibros(Model model)
	{
		List<Libro> listaLibros = service.listAll();
		model.addAttribute("listaLibros", listaLibros);
		
		return "libro/listaLibros";
	}

	// todo
	@RequestMapping("/agregar")
	public String agregarLibro(Model model)
	{
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		
		return "index";
	}
}
