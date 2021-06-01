package com.papyrus.autor;

import java.util.ArrayList;
import java.util.List;

import com.papyrus.main.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AutorController
{
    @Autowired
    private AutorService autorService;

	@Autowired
	private MainService mainService;
	
    @GetMapping("autores/lista")
	public String mostrarListaAutores(Model model, String keyword)
	{
		List<Autor> listaAutores = new ArrayList<>();

		if(keyword != null)
		{
			keyword = mainService.normalizaStr(keyword);
			listaAutores = autorService.search(keyword);
		}
		else
		{
			listaAutores = autorService.findAll();
		}

		model.addAttribute("listaAutores", listaAutores);
		
		return "autores/listaAutor";
	}
    
    @GetMapping("autores/insertar")
	public String mostrarInsertarAutor(Model model)
	{
		Autor autor = new Autor();
		model.addAttribute("autor", autor);
		
		return "autores/insertarAutor";
	}
	
	@PostMapping("autores/insertar")
	public String insertarAutor(Autor autor)
	{
		autorService.save(autor);
		
		return "redirect:/autores/lista";
	}

	@GetMapping("autores/editar/{id}")
	public String mostrarEditarAutor(@PathVariable("id") Long id, Model model)
	{
		Autor autor = autorService.findById(id);
		model.addAttribute("autor", autor);
		
		return "/autores/editarAutor";
	}

	@PostMapping("autores/editar/{id}")
	public String editarAutor(@PathVariable("id") Long id, Autor autor, Model model)
	{
		Autor autorBd = autorService.findById(id);
		
        autorBd.setNombre(autor.getNombre());
		autorBd.setFecha_nac(autor.getFecha_nac()); //! realizar gestión de fechas

		autorService.save(autorBd);
		
		return "redirect:/autores/lista";
	}	

	@GetMapping("autores/eliminar/{id}")
	public String eliminarAutorPorId(@PathVariable("id") Long id, Model model)
	{
		autorService.deleteById(id);
		
		return "redirect:/autores/lista";
	}
}