package br.com.casadocodigo.loja.controller;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.io.FileSaver;
import br.com.casadocodigo.loja.models.BookType;
import br.com.casadocodigo.loja.models.Product;

@Controller
@Transactional //Informa que os métodos da classe precisam de transação.
@RequestMapping("/produtos") //Anota o endereço base de todos os métodos da classe.
public class ProductsController {
	
/*	
 * Não utilizado pois estamos usando o validador do hibernate.
 * //Informa ao Spring MVC qual validador deve ser usado.
 *	@InitBinder
 *	protected void initBinder (WebDataBinder binder) {
 *		binder.setValidator(new ProductValidator());
 *	}//initBinder()
 */	
	@Autowired
	private FileSaver fileSaver;
	
	//Indica que deve ser injetada uma instância de ProductDAO.
	@Autowired
	private ProductDAO productDAO;
		
	@RequestMapping("/form")
	public ModelAndView form(Product product) {
	
		//Define no construtor para qual view será devolvida um objeto ModelAndView.
		ModelAndView modelAndView = new ModelAndView("products/form");
		modelAndView.addObject("bookTypes", BookType.values());
		
		return modelAndView;
	}//form()
	
	
	/*
	 * Faz com que o cache relacionado a lista de livros("books") tenha todos os seus valores retirados e seja
	 * recarregado, assim que uma nova entrada (allEntries) seja realizada no sistema.
	 */
	@CacheEvict(value = "books", allEntries = true)
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView save(MultipartFile summary ,@Valid Product product, 
							BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		//Utiliza o objeto bindingResult para verificar se existem erros de validação.
		if(bindingResult.hasErrors()) return form(product);
		
		String webPath = fileSaver.write("uploaded-images", summary);
		product.setSummaryPath(webPath);
		
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
	
	@RequestMapping(method = RequestMethod.GET)
	@Cacheable(value = "books")
	public ModelAndView list() {
		System.out.println("listando...");
		ModelAndView modelAndView = new ModelAndView("products/list");
		modelAndView.addObject("products", productDAO.list());
		return modelAndView;
	}//list()
	
	@RequestMapping("/{id}")
	public ModelAndView show(@PathVariable("id") Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("products/show");
		Product product = productDAO.find(id);
		modelAndView.addObject("product", product);
		
		return modelAndView;
	}//show()
}//class ProductsController
