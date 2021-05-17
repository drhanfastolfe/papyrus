package com.papyrus.empleado;

import java.util.ArrayList;
import java.util.List;

import com.papyrus.rol.Rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService implements UserDetailsService
{
    @Autowired
	private EmpleadoRepository repo;
	
	public List<Empleado> findAll()
	{
		return repo.findAll();
	}
	
	public void save(Empleado empleado)
	{
		repo.save(empleado);
	}
	
	public Empleado findById(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void deleteById(Long id)
	{
		repo.deleteById(id);
	}

	@Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException
    {
        Empleado appEmpleado = repo.findByUsuario(usuario).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
		
        //Mapear nuestra lista de Authority con la de spring security 
        List setRoles = new ArrayList<>();

        for (Rol rol: appEmpleado.getSetRoles()) 
        {
            // ROLE_USER, ROLE_ADMIN,..
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(rol.getRol());
            setRoles.add(grantedAuthority);
        }
		
        //Crear El objeto UserDetails que va a ir en sesion y retornarlo.
        UserDetails user = (UserDetails) new Empleado(appEmpleado.getUsuario(), appEmpleado.getContrasenia(), setRoles);
        
        return user;
    }
}
