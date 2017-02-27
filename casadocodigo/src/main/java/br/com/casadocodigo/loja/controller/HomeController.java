package br.com.casadocodigo.loja.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index() {
		System.out.println("Carregando os produtos...");
		return "hello-world";
	}//index()
	
}//class HomeController
