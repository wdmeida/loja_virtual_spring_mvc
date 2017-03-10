package br.com.casadocodigo.loja.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import br.com.casadocodigo.loja.models.PaymentData;
import br.com.casadocodigo.loja.models.ShoppingCart;

@Controller
@RequestMapping("/payment")
public class PaymentController {
	
	@Autowired
	private ShoppingCart shoppingCart;
	/*
	 * Disponibiliza diversos métodos, que podem ser usados em diversos tipos de requisições.
	 * Para conseguir a injeção deste objeto, lembre-se de alterar a classe AppWebConfiguration
	 * para registar o bean no container do Spring.
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public String checkout() {
		BigDecimal total = shoppingCart.getTotal();
		
		String uriToPay = "http://book-payment.herokuapp.com/payment";
		
		try {
			String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
			return "redirect:/payment/success";
		} catch (HttpClientErrorException e) {
			return "redirect:/payment/error";
		}
	}//checkout()
}//class PaymentController
