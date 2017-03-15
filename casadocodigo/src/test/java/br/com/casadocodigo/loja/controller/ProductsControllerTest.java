package br.com.casadocodigo.loja.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import javax.servlet.Filter;
import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.casadocodigo.loja.conf.AmazonConfiguration;
import br.com.casadocodigo.loja.conf.AppWebConfiguration;
import br.com.casadocodigo.loja.conf.DataSourceConfigurationTest;
import br.com.casadocodigo.loja.conf.JPAConfiguration;
import br.com.casadocodigo.loja.conf.SecurityConfiguration;
import br.com.casadocodigo.loja.daos.ProductDAO;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration //Carrega os objetos necessários para uma aplicação web.
@ContextConfiguration(classes = {	AppWebConfiguration.class,
									JPAConfiguration.class,
									SecurityConfiguration.class,
									DataSourceConfigurationTest.class,
									AmazonConfiguration.class })
@ActiveProfiles("test")
public class ProductsControllerTest {

	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private Filter sprigSecurityFilterChain;
	
	//Representação do contexto do Spring para projetos web.
	@Autowired
	private WebApplicationContext wac;
	
	//Objeto responsável por simular requests, response, etc.
	private MockMvc mockMvc;
	
	//Cria o ambiente para execução dos testes, obtendo o contexto da aplicação.
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
									  .addFilters(sprigSecurityFilterChain) //Injeta o filtro responsável por interceptar requests.
									  .build();
	}//setup
	
	//Testa a listagem de produtos.
	@Test
	@Transactional
	public void shouldListAllBooksInTheHome() throws Exception {
		//Através do Mock, simula uma requisição get para a lisagem de produtos.
		this.mockMvc.perform(get("/produtos"))
					.andExpect(MockMvcResultMatchers.model().attributeExists("products")) //Verifica se foi criado o atributo "products".
					.andExpect(MockMvcResultMatchers.forwardedUrl("/WEB-INF/views/products/list.jsp")); //Verifica se o redirecionamento foi correto.
	}//shouldListAllBooksInTheHome()
	
	//Verifica se apenas usuários com perfil de ADMIN podem acessar o endereço de cadastro de produtos.
	@Test
	public void onlyAdminShouldAccessProductsForm() throws Exception {
		//Utiliza um objeto do tipo RequestPostProcessor para adicionar dados ao request antes dele ser processado.
		RequestPostProcessor processor = SecurityMockMvcRequestPostProcessors.user("comprador@gmail.com")
																			 .password("123456")
																			 .roles("COMPRADOR");
		
		this.mockMvc.perform(get("/produtos/form").with(processor))
					.andExpect(MockMvcResultMatchers.status().is(403));
	}//onlyAdminShouldAccessProductsForm()
}//class ProductsControllerTest
