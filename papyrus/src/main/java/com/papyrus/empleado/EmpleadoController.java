package com.papyrus.empleado;

import java.util.List;

import com.papyrus.rol.Rol;
import com.papyrus.rol.RolService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpleadoController
{
    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private RolService rolService;

    @GetMapping("empleados/lista")
	public String mostrarListaEmpleado(Model model)
	{
		List<Empleado> listaEmpleados = empleadoService.findAll();
		model.addAttribute("listaEmpleados", listaEmpleados);
		
		return "empleados/listaEmpleado";
	}
	
	@GetMapping("empleados/insertar")
	public String mostrarInsertarEmpleado(Model model)
	{
		Empleado empleado = new Empleado();
        List<Rol> listaRoles = rolService.findAll();
        
		model.addAttribute("empleado", empleado);
        model.addAttribute("listaRoles", listaRoles);
		
		return "empleados/insertarEmpleado";
	}
	
	@PostMapping("empleados/insertar")
	public String insertarEmpleado(Empleado empleado, Model model)
	{
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        String encodedPassword = passwordEncoder.encode(empleado.getContrasenia());
        empleado.setContrasenia(encodedPassword);
		
        empleadoService.save(empleado);
		
		return "redirect:/empleados/lista";
	}

	@GetMapping("empleados/editar/{id}")
	public String mostrarEditarEmpleado(@PathVariable("id") Long id, Model model)
	{
		Empleado empleado = empleadoService.findById(id);
        List<Rol> listaRoles = rolService.findAll();

		model.addAttribute("empleado", empleado);
        model.addAttribute("listaRoles", listaRoles);
		
		return "/empleados/editarEmpleado";
	}

	@PostMapping("empleados/editar/{id}")
	public String editarEmpleado(@PathVariable("id") Long id, Empleado empleado, Model model)
	{
		Empleado empleadoBd = empleadoService.findById(id);
		
		empleadoBd.setDni(empleado.getDni());
        empleadoBd.setNombre(empleado.getNombre());
        empleadoBd.setApellidos(empleado.getApellidos());
		empleadoBd.setTelefono(empleado.getTelefono());
        empleadoBd.setEmail(empleado.getEmail());
        empleadoBd.setFecha_nac(empleado.getFecha_nac());
        empleadoBd.setUsuario(empleado.getUsuario());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(4);
        String encodedPassword = passwordEncoder.encode(empleado.getContrasenia());
        empleadoBd.setContrasenia(encodedPassword);
    

		empleadoService.save(empleadoBd);
		
		return "redirect:/empleados/lista";
	}	

	@GetMapping("empleados/eliminar/{id}")
	public String eliminarEmpleadoPorId(@PathVariable("id") Long id, Model model)
	{
		empleadoService.deleteById(id);
		
		return "redirect:/empleados/lista";
	}
}
