package com.papyrus.empleado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class EmpleadoController
{
    @Autowired
    private EmpleadoRepository empleadoRepository;

    
}
