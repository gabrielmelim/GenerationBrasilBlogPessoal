package com.helloworld.hello.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/pagina")
public class HelloController {

	@GetMapping("1")
	public String hello() 
	{
		return("Habilidade: Proatividade|Mentalidade: Persistencia");
	}	
	
	@GetMapping("2")
	public String hello2() 
	{
		return("Objetivo: Aprender springboot");
	}	
}
