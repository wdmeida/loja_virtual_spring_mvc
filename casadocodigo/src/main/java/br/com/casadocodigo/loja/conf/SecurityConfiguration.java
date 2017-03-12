package br.com.casadocodigo.loja.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * Implementa a classe WebSecurityAdapter, que já possui uma infraestrutura pronta para 
 * fazer as configurações de segurança. A annotation @EnableWebSecurity deve ser colocada
 * em cima das classes de configuração do Spring security que vão controlar as regras de
 * acesso.
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * Lembrar da ordem das invocações, primeiro fazendo restrições e, depois, liberando
		 * todo o resto. O Spring Securitu verifica as restrições na ordem em que elas foram
		 * cadastradas. Caso adicione a regra que bloqueia tudo no início da configuração, todas
		 * as URLs ficarão bloqueadas.
		 */	
		http.authorizeRequests()
			.antMatchers("/produtos/form").hasRole("ADMIN")
			.antMatchers("/shopping/**").permitAll()
			.antMatchers(HttpMethod.POST, "/produtos").hasRole("ADMIN")
			.antMatchers("/produtos/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin();
	}//configure()
	
	@Autowired(required = true)
	private UserDetailsService users;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*Define o objeto que informa os detalhes de autenticação e força que as senhas
		sejam armazenadas usando BCrypt como método de criptografia.*/
		auth.userDetailsService(users)
			.passwordEncoder(new BCryptPasswordEncoder());
	}//configure()
}//class SecurityConfiguration
