package com.papyrus.prestamo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.papyrus.empleado.EmpleadoService;
import com.papyrus.main.MainService;
import com.papyrus.socio.SocioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PrestamoController
{
    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private SocioService socioService;

    @Autowired
    private MainService mainService;

    @GetMapping("prestamos/lista")
    public String mostrarListaPrestamo(Model model, Long idPrestamo, String keyword, String activo)
    {
        List<Prestamo> listaPrestamos = new ArrayList<>();
        boolean boolActivo = activo != null ? true : false; 
        
        if(idPrestamo != null)
        {
            listaPrestamos.add(prestamoService.findById(idPrestamo));
        }
        else if(keyword != null || boolActivo)
        {
            keyword = mainService.normalizaStr(keyword);
            listaPrestamos = prestamoService.search(keyword, boolActivo);
        }
        else
        {
            listaPrestamos = prestamoService.findAll(Sort.by(Sort.Direction.ASC, "id"));
        }

        model.addAttribute("listaPrestamos", listaPrestamos);
        model.addAttribute("count", listaPrestamos.size());
        
        return "prestamos/listaPrestamo";
    }

    @GetMapping("prestamos/insertar")
    public String mostrarInsertarPrestamo(Model model)
    {
        Prestamo prestamo = new Prestamo();

        model.addAttribute("prestamo", prestamo);

        return "prestamos/insertarPrestamo";
    }

    @PostMapping("prestamos/insertar")
    public String insertarPrestamo(Prestamo prestamo, String dni)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        prestamo.setEmpleado_id(empleadoService.findByUsuario(auth.getName()).getId());
        prestamo.setSocio_id(socioService.findByDni(dni).getId());

        prestamoService.save(prestamo);

        return "redirect:/prestamos/lista";
    }

    @GetMapping("prestamos/editar/{id}")
    public String mostrarEditarPrestamo(@PathVariable("id") Long id, Model model)
    { 
        Prestamo prestamo = prestamoService.findById(id);

        model.addAttribute("prestamo", prestamo);

        return "prestamos/editarPrestamo";
    }

    @PostMapping("prestamos/editar/{id}")
    public String editarPrestamo(@PathVariable Long id, Prestamo prestamo, String dni)
    {
        Prestamo prestamoBd = prestamoService.findById(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        prestamoBd.setEmpleado_id(empleadoService.findByUsuario(auth.getName()).getId());
        prestamoBd.setSocio_id(socioService.findByDni(dni).getId());
        prestamoBd.setEjemplar_id(prestamo.getEjemplar_id());
        // prestamoBd.setFecha_inicio(prestamo.getFecha_inicio());
        prestamoBd.setFecha_fin(prestamo.getFecha_fin());
        prestamoBd.setFecha_fin_real(prestamo.getFecha_fin_real());

        prestamoService.save(prestamoBd);

        return "redirect:/prestamos/lista";
    }

    @GetMapping("prestamos/eliminar/{id}")
    public String eliminarPrestamo(@PathVariable Long id)
    {
        prestamoService.deleteById(id);

        return "redirect:/prestamos/lista";
    }

    @GetMapping("prestamos/finalizar/{id}")
    public String finalizarPrestamo(@PathVariable Long id)
    {
        Prestamo prestamoBd = prestamoService.findById(id);

        prestamoBd.setFecha_fin_real(LocalDateTime.now());
        
        prestamoService.save(prestamoBd);

        return "redirect:/prestamos/lista";
    }
}
