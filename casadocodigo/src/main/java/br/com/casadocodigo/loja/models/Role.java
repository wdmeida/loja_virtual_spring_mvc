package br.com.casadocodigo.loja.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.security.core.GrantedAuthority;

/*
 * Define os perfis do usuário autenticado. Deve implementar a interface GrantedAuthority
 */
@Entity
public class Role implements GrantedAuthority {
	
	@Transient
	private static final long serialVersionUID = 1L;
	@Id
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return name;
	}

}//class Role
