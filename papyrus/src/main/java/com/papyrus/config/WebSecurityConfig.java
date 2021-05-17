package com.papyrus.config;

import com.papyrus.empleado.EmpleadoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    String[] resources = new String[]{"/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"};

    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
	        .antMatchers(resources).permitAll()  
	        .antMatchers("/").permitAll()
	        .antMatchers("/empleados*").access("hasRole('ADMIN')")
	        .antMatchers("/libros*", "/ejemplares*", "/autores*", "/categorias*", "/editoriales*").access("hasRole('USER') or hasRole('ADMIN')")
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .defaultSuccessUrl("/libros/lista")
                .failureUrl("/login?error=true")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
            .logout()
                .permitAll()
                .logoutSuccessUrl("/login?logout");
    }
    
    BCryptPasswordEncoder bCryptPasswordEncoder;
    //Crea el encriptador de contraseñas	
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
		bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        //El numero 4 representa que tan fuerte quieres la encriptacion.
        //Se puede en un rango entre 4 y 31. 
        //Si no pones un numero el programa utilizara uno aleatoriamente cada vez
        //que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
        return bCryptPasswordEncoder;
    }
	
    @Autowired
    private EmpleadoService empleadoService;
	
    //Registra el service para usuarios y el encriptador de contrasena
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
 
        // Setting Service to find User in the database.
        // And Setting PassswordEncoder
        auth.userDetailsService(empleadoService).passwordEncoder(passwordEncoder());     
    }
}