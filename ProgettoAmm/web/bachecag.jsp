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
                
                <c:if test="${cancfinito != null}">
                <div id="post">
                          
                    
                    <div class="posts">
                        <div class="author">
                        <h3>Hai cancellato questo post!</h3>
                        </div>
                        
                            <p> ${postcance}</p>
                        
                    </div>
                    
                </div>
                 
                </c:if>
                
                
                 <%-- pagina di invio post --%>
                
                <c:if test="${invioPost != null}">
                <div id="post">
                          
                    
                    <div class="posts">
                    <form action="gruppo.html?group=${gruppo.gruppo_id}" method="post">
                        <p class="sumPost"> stai scrivendo nella bacheca di ${gruppo.nome_gruppo}</p>
                        <div id='summaryPost'>
                        <p> ${wrPost} </p>
                        <p> ${urlPost} </p>
                        </div>
                        <input type="text" hidden name="newpost" value="${content}"> 
                        <input type="url" hidden name="urlP" value="${url}">
                        <input type="submit" name="sendpost" value="Invia post">
                    </form>       
                    </div>
                    
                </div>
            
                </c:if>
                
                <%-- caso messaggio inviato ---%>
                
                <c:if test="${sendPostok != null}">
                <div id="post">
                          
                    
                    <div class="posts">
                    
                        <p class="sumPost">Hai scritto sulla bacheca di ${gruppo.nome_gruppo}</p>
                        
                       
                        </div>
                        
                    
                       
                    </div>
                    
                
            
                </c:if>
                
                
              
                
                <%-- bacheca gruppo --%>
                
                <c:if test="${loggedOn == true && invioPost == null && sendPostok == null && cancfinito == null}">
                   
                <c:if test="${okgroup == false}">
                <div id="presTop">
                    
                    ciao ${utente.nome}
                    <form action="gruppo.html?group=${gruppo.gruppo_id}" method="post">
                    <input type="submit" name="Iscrizione" value="Iscriviti">
                    </form>
                </div>
                </c:if>
                    
                    <c:if test="${okgroup == true}">
                <div id="presTop">
                    
                    Ciao, benvenuto in ${gruppo.nome_gruppo}
                    <c:if test="${gruppo.amm_gruppo.id == utente.id}">
                      
                     <form action="gruppo.html?group=${gruppo.gruppo_id}" method="post">
                    <input type="submit" name="Cancella" value="Cancella gruppo">
                    </form>
                   
                    </c:if>
                         
                    
                </div>
                    </c:if>
                    
                    <c:if test="${okgroup == true}">
                        
                        
                        <div id="newPost">
                                
                    <form action="gruppo.html?group=${gruppo.gruppo_id}" method="post">

                        <label for="newpost">Scrivi un nuovo post sulla bacheca di ${gruppo.nome_gruppo}</label>
                        <br/>

                      <textarea name="newpost" id="newpost"></textarea>
                      <label for="urlP"> Puoi anche aggiungere un allegato!</label>
                      <input name="urlP" id="urlP" type="url"/>
                      <br/> 
                      <input type="url" hidden name="urlP" value="${url}">
                      <input type="text" hidden name="newpost" value="${content}">
                      <input type="submit" name="inviapost" value="Scrivi">
                    </form>
                        </div>
                    </c:if>
                    
                
                <div id="post">
                   
                     <c:forEach var="par" items="${partecipanti}">   
                    ${par.utente_id.id}
                     </c:forEach>
                    
                <c:forEach var="postg" items="${postsg}">    
                    <div class="posts">
                        
                        
                        <div class="author">
                            <img class="imgp" alt="Foto di ${postg.autore_post_gruppo.nome}" src="${postg.autore_post_gruppo.urlfotoprofilo}">
                            <h3>${postg.autore_post_gruppo.nome} ${postg.autore_post_gruppo.cognome}</h3>
                          
                        </div>
                        
                        
                        <p> 
                            
                             ${postg.content}
                             
                            
                        </p>
                        
                         <c:if test="${amm != null}">
                           <form action="gruppo.html?group=${gruppo.gruppo_id}" method="post">
                                <input type="text" hidden name="gid" value="${postg.gruppo_id.gruppo_id}">
                                <input type="text" hidden name="contentg" value="${postg.content}">
                                <input type="submit" name="cancposts" value="Cancella post">
                            
                          
                            </form>
                        </c:if>
                        
                    </div>
                </c:forEach>  
                </div>
                      
                </c:if>
                
                
                </div>
            
        </div>  
        <jsp:include page="footer.jsp"/>
             
    </body>

</html>
