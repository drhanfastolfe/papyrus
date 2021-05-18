package com.papyrus.user;

import com.papyrus.empleado.Empleado;
import com.papyrus.empleado.EmpleadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Empleado empleado = empleadoRepository.findByUsuario(username);

        if(empleado == null)
        {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        return new MyUserDetails(empleado);
    }
}
