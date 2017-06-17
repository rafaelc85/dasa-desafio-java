<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Notificação de Participação</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>     
    
</head>

<body class="body"> 
<div class="container">
    <header>
        <div class="row">
            <h1>Notificação de Participação</h1>
        </div>
    </header>        
    
    <section id="main" class="clearfix">    
        <div class="row">
            <form:form method="POST" modelAttribute="participacao">
            <form:input type="hidden" path="id" id="id"/>                    
            <fieldset>   
              <br />
              <label for="campanha">Campanha:</label>
              <form:input path="campanha" id="campanha"/>
              <form:errors path="campanha" cssClass="error"/> 
              <br /><br />
              
              <label for="sexo">Sexo: </label>
              <select name="sexo">
                <option value="MASCULINO">Masculino</option>
                <option value="FEMININO">Feminino</option>
              </select>
              <form:errors path="sexo" cssClass="error"/>
              <br /><br />

              <label for="ano">Ano:</label>
              <form:input path="ano" id="ano" value=""/>
              <form:errors path="ano" cssClass="error"/>
              <br /><br />

              <button type="submit"/>Cadastrar</button>
            </fieldset>
            <br /><br />
            <p>Voltar para a <a href="<c:url value='/' />">Home</a></p>                
            </form:form>
        </div>
    </section>
</div>                          
</body>
</html>