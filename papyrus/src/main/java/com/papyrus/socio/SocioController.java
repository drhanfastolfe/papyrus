package com.papyrus.socio;

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
public class SocioController
{
    @Autowired
    private SocioService socioService;

	@Autowired
	private MainService mainService;

    @GetMapping("socios/lista")
	public String mostrarListaSocio(Model model, String keyword)
	{
		List<Socio> listaSocios = new ArrayList<>();
		
		if(keyword != null)
		{
			keyword = mainService.normalizaStr(keyword); 
			listaSocios = socioService.search(keyword);
		}
		else
		{
			listaSocios = socioService.findAll();
		}

		model.addAttribute("listaSocios", listaSocios);
		
		return "socios/listaSocio";
	}
	
	@GetMapping("socios/insertar")
	public String mostrarInsertarSocio(Model model)
	{
		Socio socio = new Socio();

		model.addAttribute("socio", socio);
		
		return "socios/insertarSocio";
	}
	
	@PostMapping("socios/insertar")
	public String insertarSocio(Socio socio)
	{
		socioService.save(socio);
		
		return "redirect:/socios/lista";
	}

	@GetMapping("socios/editar/{id}")
	public String mostrarEditarSocio(@PathVariable("id") Long id, Model model)
	{
		Socio socio = socioService.findById(id);

		model.addAttribute("socio", socio);
		
		return "/socios/editarSocio";
	}

	@PostMapping("socios/editar/{id}")
	public String editarSocio(@PathVariable("id") Long id, Socio socio, Model model)
	{
		Socio socioBd = socioService.findById(id);
		
		socioBd.setDni(socio.getDni());
        socioBd.setNombre(socio.getNombre());
        socioBd.setApellidos(socio.getApellidos());
		socioBd.setTelefono(socio.getTelefono());
        socioBd.setEmail(socio.getEmail());
        socioBd.setFecha_nac(socio.getFecha_nac());

		socioService.save(socioBd);
		
		return "redirect:/socios/lista";
	}	

	@GetMapping("socios/eliminar/{id}")
	public String eliminarSocioPorId(@PathVariable("id") Long id, Model model)
	{
		socioService.deleteById(id);
		
		return "redirect:/socios/lista";
	}
}