package br.com.casadocodigo.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.com.casadocodigo.loja.models.SystemUser;

/*
 * Implementa a interface responsável por controlar o mecanismo de autenticação do Spring Security.
 */
@Repository
public class UserDAO implements UserDetailsService {

	@PersistenceContext
	private EntityManager em;
	
	/*
	 * Obtém as informações de um usuário e seus perfis de acesso. Note que a senha não é informada, pois está
	 * parte será gerenciada pelo proṕrio Spring Security.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			String jpql = "select u from SystemUser u where u.login = :login";
			List<SystemUser> users = em.createQuery(jpql,SystemUser.class).setParameter("login", username).getResultList();
			if(users.isEmpty()){
				throw new UsernameNotFoundException("O usuario "+ username + " não existe");
			}
			return users.get(0);
	}//loadUserByUsername()

}
