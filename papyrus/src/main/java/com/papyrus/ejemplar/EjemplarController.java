package com.papyrus.ejemplar;

import java.util.List;

import com.papyrus.editorial.Editorial;
import com.papyrus.editorial.EditorialService;
import com.papyrus.libro.Libro;
import com.papyrus.libro.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EjemplarController
{
    @Autowired
    private EjemplarService ejemplarService;

    @Autowired
    private LibroService libroService;

    @Autowired
    private EditorialService editorialService;

    @GetMapping("ejemplares/lista")
    public String mostrarListaEjemplar(Model model)
    {
        List<Ejemplar> listaEjemplares = ejemplarService.findAll();
        model.addAttribute("listaEjemplares", listaEjemplares);

        return "ejemplares/listaEjemplar";
    }

    @GetMapping("ejemplares/insertar")
	public String mostrarInsertarEjemplar(Model model)
	{
		Ejemplar ejemplar = new Ejemplar();
        List<Libro> listaLibros = libroService.findAll();
        List<Editorial> listaEditoriales = editorialService.findAll();
        
		model.addAttribute("ejemplar", ejemplar);
        model.addAttribute("listaLibros", listaLibros);
        model.addAttribute("listaEditoriales", listaEditoriales);
		
		return "ejemplares/insertarEjemplar";
	}
	
	@PostMapping("ejemplares/insertar")
	public String insertarEjemplar(Ejemplar ejemplar)
	{
        ejemplarService.save(ejemplar);

		return "redirect:/ejemplares/lista";
	}

    @GetMapping("ejemplares/editar/{id}")
	public String mostrarEditarEjemplar(@PathVariable("id") Long id, Model model)
	{
		Ejemplar ejemplar = ejemplarService.findById(id);
		List<Libro> listaLibros = libroService.findAll();
        List<Editorial> listaEditoriales = editorialService.findAll();

		model.addAttribute("ejemplar", ejemplar);
        model.addAttribute("listaLibros", listaLibros);
        model.addAttribute("listaEditoriales", listaEditoriales);
		
		return "/ejemplares/editarEjemplar";
	}

	@PostMapping("ejemplares/editar/{id}")
	public String editarEjemplar(@PathVariable("id") Long id, Ejemplar ejemplar, Model model)
	{
		Ejemplar ejemplarBd = ejemplarService.findById(id);
		
		ejemplarBd.setLibro_id(ejemplar.getLibro_id());
		ejemplarBd.setEditorial_id(ejemplar.getEditorial_id());
        ejemplarBd.setEdicion(ejemplar.getEdicion());
        ejemplarBd.setIsbn(ejemplar.getIsbn());
        ejemplarBd.setPaginas(ejemplar.getPaginas());
        ejemplarBd.setEstado(ejemplar.getEstado());
        ejemplarBd.setFecha_imp(ejemplar.getFecha_imp());
        ejemplarBd.setFecha_ins(ejemplar.getFecha_ins());

		ejemplarService.save(ejemplarBd);
		
		return "redirect:/ejemplares/lista";
	}

    @GetMapping("ejemplares/eliminar/{id}")
	public String eliminarEjemplarPorId(@PathVariable("id") Long id, Model model)
	{
		ejemplarService.deleteById(id);
		
		return "redirect:/ejemplares/lista";
	}
}
