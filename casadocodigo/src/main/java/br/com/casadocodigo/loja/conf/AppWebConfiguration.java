package br.com.casadocodigo.loja.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controller.HomeController;

/*
 * O objetivo dessa classe é expor para o Servlet do Spring MVC quais são as classes
 * que devem ser lidas e carregadas. A principal annotation aqui no momento é
 * @ComponentScan. Através dela indicamos quais pacotes deve ser lidos. Passamos como
 * parâmetro a classe HomeController para que o Spring leia o pacote dela. Dessa forma,
 * não passamos uma String e evitamos um possível erro de digitação.
 * 
 * @EnableWebMvc habilida as seguinte funcionalidades que serão utilizadas:
 * 	Conversão de objetos para XML;
 * 	Conversão de objetos para JSON;
 * 	Validação usando a especificação;
 * 	Suporte a geração de RSS.
 */
@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class})
public class AppWebConfiguration {
	
	//Annotation @Bean indica para o Spring que o retorno desse método deve ser registrado como 
	//um objeto gerenciado pelo container.
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		//Define o prefisso e sufixo que devem ser adicionados para qualquer caminho retornado 
		//pelos métodos do controller.
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}//internalResourceViewResolver()
	
}//class AppWebConfiguration
