package br.com.casadocodigo.loja.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/*
 * Implementa a interface UserDetails, reponsável por armazenar as informações do usuário e seus perfis de acesso 
 * que serão gerenciados pelo Spring Security.
 */
@Entity
public class SystemUser implements UserDetails {
	
	@Transient
	private static final long serialVersionUID = 1L;
	
	@Id
	private String login;
	private String password;
	private String name;
	//Define os perfis do usuário.
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Role> getRoles() {
		return roles;
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	//Retorna o password do usuário.
	@Override
	public String getPassword() {
		return password;
	}
	
	//Retorna os perfis do usuário autenticado.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles;
	}

	//Retorna o login do usuário.
	@Override
	public String getUsername() {
		return login;
	}
	
	//Os métodos que retornam boolean devem retornar true para que o controle de acesso seja ativado.
	//Podem ser modificados caso seja necessário um ajuste mais fino.
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}//class User
