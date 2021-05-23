package com.papyrus.rol;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolService
{
    @Autowired
    private RolRepository repo;
    
    public List<Rol> findAll()
    {
        return repo.findAll();
    }
}
