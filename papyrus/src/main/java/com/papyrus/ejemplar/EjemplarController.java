package com.papyrus.ejemplar;

import java.util.List;

import com.papyrus.detalle.Detalle;
import com.papyrus.editorial.Editorial;
import com.papyrus.editorial.EditorialService;
import com.papyrus.libro.Libro;
import com.papyrus.libro.LibroService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        Detalle detalle = new Detalle();
        List<Libro> listaLibros = libroService.findAll();
        List<Editorial> listaEdtoriales = editorialService.findAll();
        
		model.addAttribute(ejemplar);
        model.addAttribute(detalle);
        model.addAttribute(listaLibros);
        model.addAttribute(listaEdtoriales);
		
		return "ejemplares/insertarEjemplar";
	}
	
	@PostMapping("ejemplares/insertar")
	public String insertarEjemplar(Ejemplar ejemplar)
	{
        

		return "redirect:/ejemplares/lista";
	}
}
