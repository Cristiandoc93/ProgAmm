<%-- 
    Document   : bacheca
    Created on : 16-apr-2017, 11.21.25
    Author     : Cristian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <title>.:Bacheca:.</title>
        <meta charset="UTF-8">
        <meta name="author" content="Cristian">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="persone world future amici social">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    
    <body>
        <c:set var="title" value="Bacheca Personale" scope="request"/>
        <jsp:include page="header.jsp"/>
        
        <c:set var="page" value="bacheca" scope="request"/>
        <jsp:include page="nav.jsp"/>

            <div id="content">

              <div id="post">

                  <div class="posts">
                      <div class="author">
                          <img class="imgp" alt="Foto di Gino Pino" src="${UtentiReg.urlfotoprofilo}">
                          <h3>${UtentiReg.nome} ${UtentiReg.cognome}</h3>
                      </div>
                      <p> 
                          ${Posts.post}
                      </p>
                  </div>
              </div>

            </div>    
    
    </body>

</html>
