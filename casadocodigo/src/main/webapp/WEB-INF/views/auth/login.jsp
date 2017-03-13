<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport"
		content="width=device-width, initial-scale=1, maximum-scale=1">

		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" 
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" 
			crossorigin="anonymous">

		<!-- Optional theme -->
		<link rel="stylesheet" 
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" 
			integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" 
			crossorigin="anonymous">
		 
		 <!-- Custom form -->
		 <link rel="stylesheet" href="<c:url value="/resources/css/signin.css" />"/>
		 
		<title>Login</title>
	</head>
	
	<body>
		
		<div class="container">
		
			<form:form servletRelativeAction="/login" cssClass="form-signin">
				<h2>Login</h2>
				
				<label for="username" class="sr-only" >Email:</label>
				<input type='email' id="username" name="username" 
					   placeholder="Email" class="form-control" required="required" />
				
				<label for="password" class="sr-only">Password</label>
				<input type='password' id="password" name='password'
						placeholder="Password" class="form-control" required />
				
				
				<div class="checkbox">
					<label>
						<input type="checkbox" value="remember-me" />Remember me
					</label>
				</div>
				<button class="btn btn-lg btl-primary btn-block" name="submit" type="submit" >
					Sign in
				</button>
				
			</form:form>
		
		</div>
		<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    	<script type="text/javascript" 
    		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"/></script>
		
		<!-- Latest compiled and minified JavaScript -->
		<script type="text/javascript" 
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" 
			integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" 
			crossorigin="anonymous"></script>
	</body>
</html>