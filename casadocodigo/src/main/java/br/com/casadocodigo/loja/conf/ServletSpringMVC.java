package br.com.casadocodigo.loja.conf;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	/*
	 * Quando as configurações devem estar disponíveis antes do Servlet do Spring MVC (como é o caso do filtro
	 * do Spring Security), elas devem ser declaradas no método getRootConfigClasses. Ele faz com que as classes
	 * sejam lidas e carregadas dentro de um Listener que é lido quando o servidor sobe. 
	 */
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppWebConfiguration.class, 	//Classe com as configurações da aplicação.
							 SecurityConfiguration.class, 	//Configurações de segurança.
							 JPAConfiguration.class,		//Configurações de acesso a base de dados.
							 AmazonConfiguration.class		//Configurações de acesso ao S3 Ninja.
							}; 
	}//getRootConfigClasses()
	
	/*Define quais classes devem ser carregadas na inicialização do Servlet Spring MVC. Geralmente, as classes
	carregadas aqui são as que definem as diversas configurações de funcionamento.*/
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ }; 
	}//getServletConfigClasses()
	
	/*
	 * Define o padrão de endereço que vai ser delegado para o Servlet do Spring MVC. 
	 * Equivalente a <url-mapping> no web.xml
	 * */
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}//getServletMappings()
	
	/**
	 * @param registration <code>Dynamic</code> Objeto responsável por registrar o objeto de
	 * de configuração do tipo <code>MultipartConfigElement</code>.
	 */
	@Override
	protected void customizeRegistration(Dynamic registration) {
		/*
		 * O construtor recebe uma String vazia indicando que o próprio servidor web vai decidir
		 * qual o local de armazenamento temporário dos arquivos.
		 */
		registration.setMultipartConfig(new MultipartConfigElement(""));
	}//customizeRegistration()
}//class ServletSpringMVC
