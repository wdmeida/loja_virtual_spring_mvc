package br.com.casadocodigo.loja.conf;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletSpringMVC extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return null;
	}
	
	/*
	 * Retorna uma ou mais classes responsáveis por indicar quais outras classes
	 * devem ser lidas durante o carregamento da aplicação web.
	 */
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[]{ AppWebConfiguration.class,
							JPAConfiguration.class };
	}
	
	//Define o padrão de endereço que vai ser delegado para o Servlet do Spring MVC.
	//Equivalente a <url-mapping> no web.xml
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
}//class ServletSpringMVC
