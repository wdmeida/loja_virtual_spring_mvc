<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>	
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>	
<header>
	
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
				data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a href="<c:url value="/produtos"/>" id="logo" class="navbar-brand">Loja</a>		
			</div>
			
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
				
					<li class="dropdown">
						<a href="#" class="dropdown-toogle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">
									Casa do Código<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="http://www.casadocodigo.com.br" class="list-group active">
									Home
								</a>
							</li>
							<li>
								<a href="/collections/livros-de-agile" class="list-group">
									<fmt:message key="navigation.category.agile"/> 
								</a>
							</li>
							<li>	
								<a href="/collections/livros-de-front-end" class="list-group">
									<fmt:message key="navigation.category.front"/>
								</a>
							</li>
							<li>
								<a href="/collections/livros-de-games" class="list-group">
									<fmt:message key="navigation.category.games"/>
								</a>
							</li>
							<li>
								<a href="/collections/livros-de-java" class="list-group">
									<fmt:message key="navigation.category.java"/>
								</a>
							</li>
							<li>
								<a href="/collections/livros-de-mobile" class="list-group">
									<fmt:message key="navigation.category.mobile"/>
								</a>
							</li>
							<li>
								<a href="/collections/livros-desenvolvimento-web" class="list-group"> 
									<fmt:message key="navigation.category.web"/>
								</a>
							</li>
							<li>
								<a href="/collections/outros" class="list-group"> 
									<fmt:message key="navigation.category.others"/>
								</a>
							</li>				
						</ul>
					</li>		

					<li>
						<a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">
							Sobre nós
						</a>
					</li>

					<li>
						<a href="/pages/perguntas-frequentes" rel="nofollow">
							Perguntas Frequentes
						</a>
					</li>
					
					<li class="active">
						<a href="${spring:mvcUrl('SCC#items').build()}" rel="nofollow">
							Seu carrinho (${shoppingCart.quantity})
						</a>
					</li>
					
				</ul>
				
				<ul class="nav navbar-nav navbar-right">
					<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li><a href="${spring:mvcUrl('PC#form').build()}">Cadastrar novo produto</a></li>
					</sec:authorize>
					<li class="dropdown">
						<a href="#" class="dropdown-toogle" data-toggle="dropdown" role="button"
							aria-haspopup="true" aria-expanded="false">
									Idioma<span class="caret"></span>
						</a>
						<ul class="dropdown-menu">
							<li class="active">
								<a href="<c:url value="/produtos?locale=pt"/>">
									Português
								</a>
							</li>
							<li>
								<a href="<c:url value="/produtos?locale=en_US"/>">
									Inglês
							</a>
						</li>
					</ul>
				</ul>

			</div>	
		</div>
	</nav>
	
</header>