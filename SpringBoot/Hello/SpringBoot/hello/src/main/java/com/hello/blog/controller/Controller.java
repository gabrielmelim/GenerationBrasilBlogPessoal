package com.hello.blog.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/pagina")
public class Controller {

	@GetMapping("1")
	public String msg() 
	{
		return("Habilidade: Proatividade|Mentalidade: Persistencia");
	}	
	
	@GetMapping("2")
	public String msg2() 
	{
		return("Objetivo: Aprender springboot");
	}	
}
