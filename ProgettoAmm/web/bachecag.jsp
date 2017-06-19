<%-- 
    Document   : bachecag
    Created on : 15-giu-2017, 11.07.17
    Author     : Cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <title>.:Bacheca di ${gruppo.nome_gruppo}</title>
        <meta charset="UTF-8">
        <meta name="author" content="Cristian">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="persone world future amici social">
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    
    <body>
        <div id="page">
            
        <div id="topbar">
        
       
        <jsp:include page="header.jsp"/>
        
        <c:set var="page" value="bacheca" scope="request"/>
        <jsp:include page="nav.jsp"/>
        
        </div>
        <script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="js/javascript.js"></script>
        <jsp:include page="nesidebar.jsp"/>
        
             
           
            <div id="content">
                
              
                
                <%-- bacheca gruppo --%>
                
                <c:if test="${loggedOn == true && invioPost == null && sendPostok == null}">
                   
                <c:if test="${okgroup == false}">
                <div id="presTop">
                    
                    ciao Nome visitatore ---> tasto pr iscriversi 
                    <form action="gruppo?group=${gruppo.gruppo_id}" method="post">
                    <input type="submit" name="Iscrizione" value="Iscriviti">
                    </form>
                </div>
                </c:if>
                    
                    <c:if test="${okgroup == true}">
                <div id="presTop">
                    
                    Ciao, benvenuto in ${gruppo.nome_gruppo}
                    <c:if test="${gruppo.amm_gruppo.id == utente.id}">
                      
                     <form action="bacheca.html" method="post">
                    <input type="submit" name="Cancella" value="Cancella gruppo">
                    </form>
                   
                    </c:if>
                         
                    
                </div>
                </c:if>
                    
                    
                    
                
                <div id="post">
                    ${partecipa.utente_id.id}
                    ${gruppo.gruppo_id}
                  ${gruppo.amm_gruppo.nome} 
                   ${gruppo.amm_gruppo.id}
                    ${utente.id}
                    
                <c:forEach var="postg" items="${postsg}">    
                    <div class="posts">
                        
                        
                        <div class="author">
                            <img class="imgp" alt="Foto di ${postg.autore_post_gruppo.nome}" src="${postg.autore_post_gruppo.urlfotoprofilo}">
                            <h3>${postg.autore_post_gruppo.nome} ${postg.autore_post_gruppo.cognome}</h3>
                          
                        </div>
                        
                        
                        <p> 
                            
                             ${postg.content}
                            
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
