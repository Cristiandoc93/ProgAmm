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
        <title>.:Bacheca di ${utente.nome}</title>
        <meta charset="UTF-8">
        <meta name="author" content="Cristian">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="persone world future amici social">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    
    <body>
        <div id="page">
            
        <div id="topbar">
        
        <c:set var="title" value="Bacheca Personale" scope="request"/>
        <jsp:include page="header.jsp"/>
        
        <c:set var="page" value="bacheca" scope="request"/>
        <jsp:include page="nav.jsp"/>
        
        </div>
        <jsp:include page="nesidebar.jsp"/>
        
        
           
            <div id="content">
                <!-- Utente non connesso -->
                <c:if test="${loggedOn != true}">
                    
                    <div id="errorProfile">
                        <p> Accesso negato </p>
                        <br/>
                        <p> Devi prima loggarti! </p>
                    </div>
                    <div id="content" class="nascosto">
                        
                    </div>
                    <div id="post" class="nascosto">
           
                    </div>
                    
                </c:if>
                
                <!-- Utente connesso -->
                
                <c:if test="${loggedOn == true}">
                  
                <div id="post">
                <c:forEach var="post" items="${posts}">    
                    <div class="posts">
                        <div class="author">
                            <img class="imgp" alt="Foto di ${utente.nome}" src="${utente.urlfotoprofilo}">
                            <h3>${utente.nome} ${utente.cognome}</h3>
                        </div>
                        
                        
                        <p> 
                            ${post.content}
                        </p>
                        
                    </div>
                </c:forEach>  
                </div>
                      
                </c:if>
                
            </div>
            
        </div>  
        <jsp:include page="footer.jsp"/>
             
    </body>

</html>
