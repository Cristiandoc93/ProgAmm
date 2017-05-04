<%-- 
    Document   : profiloPage
    Created on : 1-mag-2017, 18.31.34
    Author     : Cristian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    
    <head>
        <title>.:Profilo:.</title>
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
                
                <c:set var="page" value="profilo" scope="request"/>
                <jsp:include page="nav.jsp"/>
                
            </div>

            <jsp:include page="nesidebar.jsp"/>
            
            
            
                
            <div id="content">  
            
            <!--- utente non connesso -->
            
                <c:if test="${loggedOn != true}">
                    
                    <div id="errorProfile">
                        <p> Accesso negato </p>
                        <br/>
                        <p> Devi prima loggarti! </p>
                    </div>
                    
                    <div id="profileform" class="nascosto">
           
                    </div>
                    
                </c:if>
            
            <!--- utente connesso -->
                
                <c:if test="${empty modifica && loggedOn == true}">
                
                <div id="profileform">
                
                  <div id="titleform">
                  <p>Inserisci i tuoi dati</p>
                  </div>

                  <br/>
                  <form method="post" action="profilo.html">
                      <div class="labeltrick">
                      <label for="nome">Nome utente</label>
                      </div>
                      <input name="nome" id="nome" type="text"/>
                      <br/>

                      <div class="labeltrick">
                      <label for="cognome">Cognome utente</label>
                      </div>
                      <input name="cognome" id="cognome" type="text"/>
                      <br/>

                      <div class="labeltrick">
                      <label for="img"> Url Immagine profilo</label>
                      </div>
                      <input name="img" id="img" type="url"/>
                      <br/>

                      <div class="labeltrick">
                      <label for="present">Presentazione</label>
                      </div>
                      <textarea name="present" id="present"></textarea>
                      <br/>

                      <div class="labeltrick">
                      <label for="bday">Data di nascita</label>
                      </div>
                      <input name="bday" id="bday" type="date"/>
                      <br/>

                      <div class="labeltrick">
                      <label for="psw">Password</label>
                      </div>
                      <input name="psw" id="psw" type="password">
                      <br/>

                      <div class="labeltrick">
                      <label for="cpsw">Conferma Password</label>
                      </div>
                      <input name="cpsw" id="cpsw" type="password">
                      <br/>

                      <input type="submit" name="Conf" value="Invia">

                  </form>
                  
                    
                  
                </div>
                </c:if> 
            
            
            <!-- Modifica dati ----->
            
                <c:if test="${not empty modifica && loggedOn == true}">
                    
                    <div id="summary">
                        <p> Modifiche Effetuate! </p>
                        <br/>
                        <c:if test="${not empty name}">
                        <c:out value="nome: ${name}"/> 
                        </c:if>
                        <br/>
                        <c:if test="${not empty sirname}">
                        <c:out value="cognome: ${sirname}"/>
                        </c:if>
                        <br/>
                        <c:if test="${not empty image}">
                        <c:out value="img profilo: ${image}"/>
                        </c:if>
                        <br/>
                        <c:if test="${not empty presentazione}">
                        <c:out value="presentazione: ${presentazione}"/>
                        </c:if>
                        <br/>
                        <c:if test="${not empty birthday}">
                        <c:out value="data di nascita: ${birthday}"/>
                        </c:if>
                        <br/>
                        <c:if test="${not empty password}">
                        <c:out value="password: ${password}"/>
                        </c:if>
                        <br/>
                        
                    </div>
                    
                    <div id="profileform" class="nascosto">
           
                    </div>
                    
                </c:if>
            
            
            
                
            </div>
                   
        </div>
        
        <jsp:include page="footer.jsp"/>
    </body>
    
    
</html>
