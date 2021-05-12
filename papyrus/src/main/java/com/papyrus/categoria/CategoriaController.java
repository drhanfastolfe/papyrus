package com.papyrus.categoria;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoriaController
{
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("categorias/lista")
	public String mostrarListaCategoria(Model model)
	{
		List<Categoria> listaCategorias = categoriaService.findAll();
		model.addAttribute("listaCategorias", listaCategorias);
		
		return "categorias/listaCategoria";
	}
    
    @GetMapping("categorias/insertar")
	public String mostrarInsertarCategoria(Model model)
	{
		Categoria categoria = new Categoria();
		model.addAttribute("categoria", categoria);
		
		return "categorias/insertarCategoria";
	}
	
	@PostMapping("categorias/insertar")
	public String insertarCategoria(Categoria categoria)
	{
		categoriaService.save(categoria);
		
		return "redirect:/categorias/lista";
	}

	@GetMapping("categorias/editar/{id}")
	public String mostrarEditarCategoria(@PathVariable("id") Long id, Model model)
	{
		Categoria categoria = categoriaService.findById(id);
		model.addAttribute("categoria", categoria);
		
		return "/categorias/editarCategoria";
	}

	@PostMapping("categorias/editar/{id}")
	public String editarCategoria(@PathVariable("id") Long id, Categoria categoria, Model model)
	{
		Categoria categoriaBd = categoriaService.findById(id);
		
        categoriaBd.setNombre(categoria.getNombre());

		categoriaService.save(categoriaBd);
		
		return "redirect:/categorias/lista";
	}	

	@GetMapping("categorias/eliminar/{id}")
	public String eliminarCategoriaPorId(@PathVariable("id") Long id, Model model)
	{
		categoriaService.deleteById(id);
		
		return "redirect:/categorias/lista";
	}
}
