package br.com.casadocodigo.loja.controller;

import java.math.BigDecimal;
import java.util.concurrent.Callable;

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
	
	/*
	 * Retorna um objeto do tipo Callable, que existe desde o Java 5 e é um análogo ao objeto tipo Runnable, muito
	 * comum para quem usa Threads. A única diferença do Callable é que le permite dar um retorno, algo necessário
	 * já que precisamos informar para qual endereço vamos depois da integração. Ao retornar um Callable, o Spring
	 * MVC já vai criar e iniciar um contexto assíncrono em sua Servlet e liberar para que o Web Container (Tomcat)
	 * possa usar a Thread dele para atender novas requisições. Assim, quando recebermos a resposta da integração 
	 * com o módulo de pagamento, retornamos o endereço de redirect e o mesmo é informado ao Tomcat.
	 * È importante ressaltar que não estamos tratando a performance do sistema, provavelmente o tempo de requisição
	 * vai ser o mesmo. O que estamos tratando é a escalabilidade, tentanto manter o tempo médio de requisição mesmo
	 * que a aplicação sofra com um acesso acima do normal.
	 */
	@RequestMapping(value = "checkout", method = RequestMethod.POST)
	public Callable<String> checkout() {
		return () -> {
			BigDecimal total = shoppingCart.getTotal();
			
			String uriToPay = "http://book-payment.herokuapp.com/payment";
			
			try {
				String response = restTemplate.postForObject(uriToPay, new PaymentData(total), String.class);
				return "redirect:/payment/success";
			} catch (HttpClientErrorException e) {
				return "redirect:/payment/error";
			}
		};
	}//checkout()
}//class PaymentController
