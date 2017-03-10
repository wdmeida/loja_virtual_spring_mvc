package br.com.casadocodigo.loja.conf;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}//getRootConfigClasses()
	
	/*
	 * Retorna uma ou mais classes responsáveis por indicar quais outras classes devem ser lidas 
	 * durante o carregamento da aplicação web.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ AppWebConfiguration.class, //Define as configurações da aplicação.
							JPAConfiguration.class,
							AmazonConfiguration.class }; //Define as configurações de acesso ao bd.
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
