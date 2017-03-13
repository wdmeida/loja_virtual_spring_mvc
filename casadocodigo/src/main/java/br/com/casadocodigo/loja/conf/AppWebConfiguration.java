package br.com.casadocodigo.loja.conf;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.google.common.cache.CacheBuilder;

import br.com.casadocodigo.loja.controller.HomeController;
import br.com.casadocodigo.loja.daos.ProductDAO;
import br.com.casadocodigo.loja.io.FileSaver;
import br.com.casadocodigo.loja.models.ShoppingCart;
import br.com.casadocodigo.loja.viewresolver.JsonViewResolver;

/*
 * O objetivo dessa classe é expor para o Servlet do Spring MVC quais são as classes que 
 * devem ser lidas e carregadas. A principal annotation aqui no momento é @ComponentScan.
 * Através dela indicamos quais pacotes deve ser lidos. Passamos como parâmetro a classe 
 * HomeController para que o Spring leia o pacote dela. Dessa forma, não passamos uma 
 * String e evitamos um possível erro de digitação.
 * 
 * @EnableWebMvc habilida as seguinte funcionalidades que serão utilizadas:
 * 	Conversão de objetos para XML;
 * 	Conversão de objetos para JSON;
 * 	Validação usando a especificação;
 * 	Suporte a geração de RSS.
 * 
 * @EnableCaching Habilita o uso do cache, para que o Spring possa começar a guardar 
 * retornos indicados com @Cacheable.
 */
@EnableWebMvc
@ComponentScan(basePackageClasses={HomeController.class,
								   ProductDAO.class,
								   FileSaver.class,
								   ShoppingCart.class })
@EnableCaching //
public class AppWebConfiguration extends WebMvcConfigurerAdapter {
	
	/*
	 * Annotation @Bean indica para o Spring que o retorno desse método deve ser registrado
	 * como um objeto gerenciado pelo container.
	*/
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		
		/*
		 * Define o prefixo e sufixo que devem ser adicionados para qualquer caminho 
		 * retornado pelos métodos do controller.
		 * */
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setExposedContextBeanNames("shoppingCart");
		return resolver;
	}//internalResourceViewResolver()
	
	/*
	 * Define onde o Spring deve buscar as mensagens a serem exibidas ao usuário em 
	 * caso de erros, a codificação utilizada e o tempo* que as mesmas devem ser 
	 * recarregadas para a memória. 
	 * *Em ambiente de desenvolvimento, isso evita que tenhamos que reiniciar o servidor 
	 * a cada nova  mensagem inserida.
	 * 
	 * Outro ponto importante, é que o nome do método deve ser messageSource. O Spring 
	 * MVC vai procurar um Bean registrado com este nome para carregar as mensagens. 
	 * Uma alternativa, para não ter que se preocupar com o nome é utilizar o atributo 
	 * name da annottion @Bean:
	 * 
	 * 	@Bean(name="messageSource")
	 * 	public MessageSource loadBundle(){ ... }
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource 
			bundleMessageSource = new ReloadableResourceBundleMessageSource();
		bundleMessageSource.setBasename("/WEB-INF/messages");
		bundleMessageSource.setDefaultEncoding("UTF-8");
		bundleMessageSource.setCacheSeconds(1);
		return bundleMessageSource;
	}//messageSource()
	
	/*
	 * O método mvcConversionService() registra um formato padrão para os tipos de dados
	 *  utilizada na aplicação. No exemplo abaixo, estamos definindo o formato de entrada
	 *  e saída de data no sistema. O método dever manter este nome (mvcConversionService()) 
	 *  pois é usado internamente pelo Spring MVC.
	 *  */
	@Bean
	public FormattingConversionService mvcConversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService(true);
		
		DateFormatterRegistrar registrar = new DateFormatterRegistrar();
		registrar.setFormatter(new DateFormatter("yyyy-MM-dd"));
		registrar.registerFormatters(conversionService);
		return conversionService;
	}//mvcConversionService()
	
	/*
	 * Define a implementação que fará o tratamento de upload provido pela especificação 
	 * de Servlets.
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}//multipartResolver()
	
	/*
	 * Disponibiliza uma implementação para que o Spring saiba como fazer requisições HTTP.
	 */
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}//restTemplate()
	
	/*
	 * Disponibiliza uma classe que seja responsável por efeticamente guardar os objetos
	 * que devem ser cacheados. A implementação mas simples é retornar uma instância da 
	 * classe ConcurrentMapCacheManager. Para projetos que exigem mais configurações 
	 * podemos utilizar uma biblioteca como a Guava, desenvolvida pelo Google com várias
	 * classes que podem ser úteis em qualquer projeto..
	 */
	@Bean
	public CacheManager cacheManager() {
		CacheBuilder<Object, Object> builder = CacheBuilder
														.newBuilder()
														.maximumSize(100)
														.expireAfterAccess(5, TimeUnit.MINUTES);
		GuavaCacheManager cacheManager = new GuavaCacheManager();
		cacheManager.setCacheBuilder(builder);
		return cacheManager;
	}//cacheManager()
	
	/*
	 * Define quais formatos o Spring MVC deve retornar em suas requisições.
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		//Cria uma lista e adiciona o ViewResolver que deseja retornar (JSON, XML e HTML).
		List<ViewResolver> resolvers = new ArrayList<>();
		//Trata páginas normais (JSP, HTML).
		resolvers.add(internalResourceViewResolver());
		//Trata JSON (deve ser implementado).
		resolvers.add(new JsonViewResolver());
		
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setViewResolvers(resolvers);
		resolver.setContentNegotiationManager(manager);
		return resolver;
	}//contentNegotiatingViewResolver()
	
	/*
	 * Interceptors funcionam como filtros, só que dentro do framework. No nosso caso, para
	 * o idioma, o LocaleChanceInterceptor verifica se foi usado o parâmetro locale na 
	 * requisição e, em caso positivo, ele efetua a troca do idioma.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LocaleChangeInterceptor());
	}//addInterceptors()
	
	/*
	 * Retorna uma implementação da interface LocaleResolver. Nesse caso, guardamos o idioma 
	 * preferido em um cookie. Poderiamos utilizar SessionLocaleResolver, para armazenar na
	 * sessão do usuário. Além disso, precisamos ensinar ao Spring que ele deve trocar o valor
	 * do idioma em função do parâmetro passado.
	 */
	@Bean
	public LocaleResolver localeResolver() {
		return new CookieLocaleResolver();
	}//localeResolver()
	
	/*
	 * Informa ao Spring que caso não resolva um endereço, delegue a tarefa para o Servlet 
	 * Container.
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}//configureDefaultServletHandling()
	
	//Define as configurações de acesso dos arquivos estáticos.
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("/resources/");
	}//addResourceHandlers()
}//class AppWebConfiguration
