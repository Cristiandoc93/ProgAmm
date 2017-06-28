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
            Connesso come:<p class="user">  <img class="imgpro" alt="Foto di ${utente.nome}" src="${utente.urlfotoprofilo}">
                ${utente.nome} </p>
        </div>
        
        
            
             <label for="ricerca">Barra di ricerca</label>
                         
             <input id="ricerca" type="text" value="">
             <button id="ricercabutton">Cerca</button>
          
        

        <div class="titlebar">
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
                      
                       <div class="titlebar">
                             <p>Gruppi</p>
                       </div>
                       
                      <c:forEach var="gruppi" items="${gruppi}">
                      
                      
                          <p><a href="gruppo?group=${gruppi.gruppo_id}">
                                   ${gruppi.nome_gruppo}
                                  
                             </a></p>
                      </c:forEach>
                    
                </c:if>
            
        </div>    
            
            
      
        </c:if>
        
    </div> 
</div>
        
