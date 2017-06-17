<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Mensagens</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   

</head>
             
<body class="body"> 
<div class="container">
    <header>
        <div class="row">
            <h1>Mensagens</h1>
        </div>
    </header>        
    
    <section id="main" class="clearfix">    
        <h2>${mensagens}</h2>
	<br/>
	<br/>
	<p>Voltar para a <a href="<c:url value='/' />">Home</a></p> 
    </section>           
       
</div>              

</body>
</html>