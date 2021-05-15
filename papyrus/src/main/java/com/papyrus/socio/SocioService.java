package com.papyrus.socio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioService
{
    @Autowired
	private SocioRepository repo;
	
	public List<Socio> findAll()
	{
		return repo.findAll();
	}
	
	public void save(Socio socio)
	{
		repo.save(socio);
	}
	
	public Socio findById(Long id)
	{
		return repo.findById(id).get();
	}
	
	public void deleteById(Long id)
	{
		repo.deleteById(id);
	} 
}
