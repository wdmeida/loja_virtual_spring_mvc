package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controller.HomeController;
import br.com.casadocodigo.loja.daos.ProductDAO;

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
@ComponentScan(basePackageClasses={HomeController.class,
								   ProductDAO.class })
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
	
	/*
	 * Define onde o Spring deve buscar as mensagens a serem exibidas ao usuário em caso de erros,
	 * a codificação utilizada e o tempo que as mesmas devem ser recarregadas para a memória. Em 
	 * ambiente de desenvolvimento, isso evita que tenhamos que reiniciar o servidor a cada nova
	 * mensagem inserida.
	 * 
	 * Outro ponto importante, é que o nome do método deve ser messageSource. O Spring MVC vai procurar
	 * um Bean registrado com eesse nome para carregar as mensagens. Uma alternativa, para não ter que
	 * se preocupar com o nome é utilizar o atributo name da annottion @Bean:
	 * 
	 * 	@Bean(name="messageSource")
	 * 	public MessageSource loadBundle(){ ... }
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasename("/WEB-INF/messages");
		bundleMessageSource.setDefaultEncoding("UTF-8");
		bundleMessageSource.setCacheSeconds(1);
		return bundleMessageSource;
	}//messageSource()
	
	//O nome deve ser mvcConversionService pois é usado internamente pelo Spring MVC.
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}//mvcConversionService()
	
}//class AppWebConfiguration
