<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Home - [DASA] Desafio Java</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>     
    
</head>

<body class="body"> 
<div class="container">
    <header>
        <div class="row">
            <h1>Home - [DASA] Desafio Java</h1>
        </div>
    </header>        
    
    <section id="main" class="clearfix">    
        <div class="row">
            <h2>Obter Dados Populacionais</h2>
            <p><a href="/2010">Ano 2010</a> - Item 1.1</p><br />
            <p><a href="/2017">Ano 2017</a> - Item 1.2</p><br />
            
            <h2>Participações em Campanhas</h2>
            <p><a href="/participacao/2010">Ano 2010</a> - Item 2.1</p><br />
            <p><a href="/participacao/2016">Ano 2016</a> - Item 2.1</p><br />
            <p><a href="/newParticipacao">Notificar Participação</a> - Item 2</p><br />
            
            <h2>Proporção de Participação em Campanhas</h2>
            <p><a href="/proporcao/2010">Ano 2010</a> - Item 2.2</p><br />
            <p><a href="/proporcao/2016">Ano 2016</a> - Item 2.2</p><br />
            
        </div>
    </section>
</div>                          
</body>
</html>