package com.papyrus.config;

import com.papyrus.user.UserDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    public UserDetailsService userDetailsService()
    {
        return new UserDetailsServiceImpl();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(4);
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider()
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
         
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
            .antMatchers("/").hasAnyAuthority("USER", "ADMIN")
            .antMatchers("/empleado").hasAuthority("ADMIN")
            .antMatchers(
                        "/libros/**", 
                        "/autores/**", 
                        "/categorias/**",
                        "/editoriales/**",
                        "/ejemplares/**",
                        "/prestamos/**",
                        "/socios/**").hasAnyAuthority("ADMIN", "USER")
            .anyRequest().authenticated()
            .and()
            .formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/libros/listaLibro").failureUrl("/login?error=true")
            .and()
            .logout().logoutSuccessUrl("/login?logout").permitAll()
            .and()
            .exceptionHandling().accessDeniedPage("/403")
            ;
    }

    // String[] resources = new String[]{"/include/**","/css/**","/icons/**","/img/**","/js/**","/layer/**"};

    // protected void configure(HttpSecurity http) throws Exception
    // {
    //     http
    //         .authorizeRequests()
	//         .antMatchers(resources).permitAll()  
	//         .antMatchers("/").permitAll()
	//         .antMatchers("/empleados*").hasRole("ADMIN")
	//         .antMatchers("/libros*", "/ejemplares*", "/autores*", "/categorias*", "/editoriales*").hasRole("ADMIN, USER")
    //             .anyRequest().authenticated()
    //             .and()
    //         .formLogin()
    //             .loginPage("/login")
    //             .permitAll()
    //             .defaultSuccessUrl("/libros/lista")
    //             .failureUrl("/login")
    //             .usernameParameter("usuario")
    //             .passwordParameter("contrasenia")
    //             .and()
    //         .logout()
    //             .permitAll()
    //             .logoutSuccessUrl("/login");
    // }
    
    // BCryptPasswordEncoder bCryptPasswordEncoder;
    // //Crea el encriptador de contraseñas	
    // @Bean
    // public BCryptPasswordEncoder passwordEncoder()
    // {
	// 	bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
    //     //El numero 4 representa que tan fuerte quieres la encriptacion.
    //     //Se puede en un rango entre 4 y 31. 
    //     //Si no pones un numero el programa utilizara uno aleatoriamente cada vez
    //     //que inicies la aplicacion, por lo cual tus contrasenas encriptadas no funcionaran bien
    //     return bCryptPasswordEncoder;
    // }
	
    // @Autowired
    // private EmpleadoService empleadoService;
	
    // //Registra el service para usuarios y el encriptador de contrasena
    // @Autowired
    // public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    // {
 
    //     // Setting Service to find User in the database.
    //     // And Setting PassswordEncoder
    //     auth.userDetailsService(empleadoService).passwordEncoder(passwordEncoder());     
    // }
}