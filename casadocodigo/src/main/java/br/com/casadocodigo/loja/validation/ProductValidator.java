package br.com.casadocodigo.loja.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.casadocodigo.loja.models.Product;

/*Implementa a interface Validator para validar os campos dos formulários.
A implementação nos obriga a reescrever os dois métodos abaixo.*/
public class ProductValidator implements Validator {
	
	/*
	 * Esse método recebe a classe do objeto que está querendo ser validado e retorna se o 
	 * validador consegue lidar com ele. Essa é a forma como o Spring MVC controla qual 
	 * validação deve ser aplicada. Inclusive, é uma ótima maneira de separar regras de 
	 * validação em várias classes e deixar que o Spring MVC execute cada uma delas para você.
	 */
	@Override
	public boolean supports(Class<?> arg) {
		return Product.class.isAssignableFrom(arg);
	}
	
	/*
	 * O método validate recebe como argumento o objeto a ser validado, no caso especificado
	 * pelo parâmetro target e um outro objeto do tipo Errors, onde vamos guardando cada uma
	 * das falhas de validação. A classe ValidationUtils é um helper do Sprin Validation para
	 * realizar algumas validações básicas. Os métodos reject possuem a seguinte assinatura:
	 * 	
	 * 	public static void rejectIfEmpty(Errors errors, String field,
	 *		String errorCode) {....}
	 *
	 *	Error errors: objeto onde será guardado cada um dos erros de validação.
	 *	String field: representa o atributo do modelo que deve ser validado.
	 *	String errorCode: chave que será usada para buscarmos a mensagem relativa
	 *	a esse erro. 
	 */
	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "field.required");
		
		Product product = (Product) target;
		
		//Como a lógica de validação não existe na classe utilitária, utiliza-se o método rejectValue
		//para adicionar a mensagem na lista de erros.
		if (product.getPages() == 0)
			errors.rejectValue("pages", "field.required");
	}//validate()

}
