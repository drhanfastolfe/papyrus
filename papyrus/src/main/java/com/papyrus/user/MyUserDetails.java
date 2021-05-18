package com.papyrus.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.papyrus.empleado.Empleado;
import com.papyrus.rol.Rol;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class MyUserDetails implements UserDetails
{
    private Empleado empleado;

    public MyUserDetails(Empleado empleado)
    {
        this.empleado = empleado;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        Set<Rol> roles = empleado.getSetRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Rol rol : roles)
        {
            authorities.add(new SimpleGrantedAuthority(rol.getRol()));    
        }        

        return authorities;
    }

    @Override
    public String getPassword()
    {
        return empleado.getContrasenia();
    }

    @Override
    public String getUsername()
    {
        return empleado.getUsuario();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}