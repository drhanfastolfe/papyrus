package com.papyrus.main;

import java.util.List;

import com.papyrus.autor.Autor;
import com.papyrus.autor.AutorService;
import com.papyrus.categoria.Categoria;
import com.papyrus.categoria.CategoriaService;
import com.papyrus.ejemplar.Ejemplar;
import com.papyrus.ejemplar.EjemplarService;
import com.papyrus.empleado.Empleado;
import com.papyrus.empleado.EmpleadoService;
import com.papyrus.libro.Libro;
import com.papyrus.libro.LibroService;
import com.papyrus.socio.Socio;
import com.papyrus.socio.SocioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController
{
    @Autowired
    private LibroService libroService;

    @Autowired
    private SocioService socioService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EjemplarService ejemplarService;

    @Autowired
    private AutorService autorService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public String index()
    {
        return "redirect:/dash";
    }

    @GetMapping("/login")
    public String login()
    {
        return "login";
    }

    @GetMapping("/dash")
    public String mostrarDash(Model model)
    {
        List<Libro> librosMasPrestados = libroService.librosMasPrestados();
        List<Socio> sociosMasActivos = socioService.sociosMasActivos();
        List<Libro> librosMasEjemplares = libroService.librosMasEjemplares();
        List<Empleado> empleadosMasActivos = empleadoService.empleadosMasActivos();
        List<Ejemplar> ejemplaresMasUsados = ejemplarService.ejemplaresMasUsados();
        List<Autor> autoresMasLeidos = autorService.autoresMasLeidos();
        List<Categoria> categoriasMasLeidos = categoriaService.categoriasMasLeidos();


        model.addAttribute("autoresMasLeidos", autoresMasLeidos);
        model.addAttribute("categoriasMasLeidos", categoriasMasLeidos);
        model.addAttribute("librosMasPrestados", librosMasPrestados);
        model.addAttribute("sociosMasActivos", sociosMasActivos);
        model.addAttribute("librosMasEjemplares", librosMasEjemplares);
        model.addAttribute("empleadosMasActivos", empleadosMasActivos);
        model.addAttribute("ejemplaresMasUsados", ejemplaresMasUsados);

        return "dash";
    }
}
