package com.papyrus.editorial;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EditorialController
{
    @Autowired
    private EditorialService editorialService;

    @GetMapping("editoriales/lista")
	public String mostrarListaEditorial(Model model)
	{
		List<Editorial> listaEditoriales = editorialService.findAll();
		model.addAttribute("listaEditoriales", listaEditoriales);
		
		return "editoriales/listaEditorial";
	}
    
    @GetMapping("editoriales/insertar")
	public String mostrarInsertarEditorial(Model model)
	{
		Editorial editorial = new Editorial();
		model.addAttribute("editorial", editorial);
		
		return "editoriales/insertarEditorial";
	}
	
	@PostMapping("editoriales/insertar")
	public String insertarEditorial(Editorial editorial)
	{
		editorialService.save(editorial);
		
		return "redirect:/editoriales/lista";
	}

	@GetMapping("editoriales/editar/{id}")
	public String mostrarEditarEditorial(@PathVariable("id") Long id, Model model)
	{
		Editorial editorial = editorialService.findById(id);
		model.addAttribute("editorial", editorial);
		
		return "/editoriales/editarEditorial";
	}

	@PostMapping("editoriales/editar/{id}")
	public String editarEditorial(@PathVariable("id") Long id, Editorial editorial, Model model)
	{
		Editorial editorialBd = editorialService.findById(id);
		
        editorialBd.setNombre(editorial.getNombre());

		editorialService.save(editorialBd);
		
		return "redirect:/editoriales/lista";
	}	

	@GetMapping("editoriales/eliminar/{id}")
	public String eliminarEditorialPorId(@PathVariable("id") Long id, Model model)
	{
		editorialService.deleteById(id);
		
		return "redirect:/editoriales/lista";
	}
}
