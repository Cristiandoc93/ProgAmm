<%-- 
    Document   : nesidebar
    Created on : 16-apr-2017, 11.56.25
    Author     : Cristian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>




<div id="sidebar">
    <div id="persone" >
        
        <div id="myuser">
            Connesso come:<p class="user"> ${nome} </p>
        </div>
        
        
            
             <label for="ricerca">Barra di ricerca</label>
                         
             <input id="ricerca" type="text" value="">
             <button id="ricercabutton">Cerca</button>
          
        

        <div id="titlebar">
            <p>Persone online</p>
        </div>
        
        
        
        <c:if test="${loggedOn == true && bacError != true}">
            
        <div class="listperscerc">
            
                
                <c:if test="${utente.nome != null &&
                              utente.cognome != null &&
                              utente.urlfotoprofilo != null &&
                              utente.presentazione != null}">
                      <ul id="utenti">
                               <c:forEach var="utente" items="${utenti}">
                                    <li nome="utente">${utente.nome} ${utente.cognome}
                                        <a href="login.html?user=${utente.id}">
                                            
                                            bacheca
                                        </a>
                                    </li>
                               </c:forEach>
                      </ul> 
                    
                </c:if>
            
        </div>    
            
            
      
        </c:if>
        
    </div> 
</div>
        
