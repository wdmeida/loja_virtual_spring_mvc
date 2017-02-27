package br.com.casadocodigo.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.Product;

/*
 * Indica que essa classe, além de ser gerenciada pelo Spring, é
 * também responsável pelo acesso a dados. As sequintes annotations
 * podem ser usadas em uma classe gerenciada pelo container no Spring:
 * 	
 * •@Component : a semântica envolvida é que essa classe é um bean do
	Spring.
• 	@Respository : a classe é responsável pelo acesso a dados.
• 	@Controller : para indicar que essa classe interage com os requests
	vindos da web.
• 	@Service : para indicar que a classe representa um componente inti-
	mamente ligado a alguma regra de negócio do sistema.

 * 
*/
@Repository
public class ProductDAO {
	
	@PersistenceContext
	private EntityManager manager;
	
	public void save(Product product){
		manager.persist(product);
	}
}
