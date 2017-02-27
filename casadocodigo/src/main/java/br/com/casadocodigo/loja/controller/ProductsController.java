package br.com.casadocodigo.loja.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
@Transactional //Informa que métodos da classe precisam de transação.
@RequestMapping("/produtos") //Anota o endereço base de todos os métodos da classe.
public class ProductsController {
	
/*	//Informa ao Spring MVC qual validador deve ser usado.
	@InitBinder
	protected void initBinder (WebDataBinder binder) {
		binder.setValidator(new ProductValidator());
	}//initBinder()
*/	
	//Indica que deve ser injetada uma instância de ProductDAO.
	@Autowired
	private ProductDAO productDAO;
		
	//Mapeia o formulário para que seja acessado via browser.
	@RequestMapping("/form")
	public ModelAndView form(Product product) {
		//Define no construtor para qual view será devolvida um objeto ModelAndView.
		ModelAndView modelAndView = new ModelAndView("products/form");
		//Seta os atributos e devolve o objeto.
		modelAndView.addObject("types", BookType.values());
		
		return modelAndView;
	}//form()
	
	//Salva um novo produto no banco de dados.
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(@Valid Product product, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		//Utiliza o objeto bindingResult para verificar se existem erros de validação.
		if(bindingResult.hasErrors()) return form(product);
		
		productDAO.save(product);
		/*
		 * Utiliza um objeto da interface RedirectAttributes para enviar uma mensagem
		 * ao usuário. Todo objeto adicionado nela, através do método addFlashAttributes,
		 * ficará disponível até o próximo request, sendo acessível de maneira simples 
		 * através de Expression Language: 
		 * 
		 * 		${sucesso }
		 */
		redirectAttributes.addFlashAttribute("sucesso", "Produto cadastrado com sucesso");
		return new ModelAndView("redirect:produtos");
	}//save()
	
	//Lista os produtos cadastrados no banco de dados.
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		
		return modelAndView;
	}//list
}//class ProductsController
