package br.com.casadocodigo.loja.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
@Transactional //Informa que métodos da classe precisam de transação.
public class ProductsController {
	
	//Indica que deve ser injetada uma instância de ProductDAO.
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping("/produtos")
	public String save(Product product) {
		productDAO.save(product);
		return "products/ok";
	}//save()
	
	//Mapeia o formulário para que seja acessado via browser.
	@RequestMapping("/form")
	public ModelAndView form() {
		//Define no construtor para qual view será devolvida um objeto ModelAndView.
		ModelAndView modelAndView = new ModelAndView("products/form");
		
		//Seta os atributos e devolve o objeto.
		modelAndView.addObject("types", BookType.values());
		
		return modelAndView;
	}//form()
}//class ProductsController
