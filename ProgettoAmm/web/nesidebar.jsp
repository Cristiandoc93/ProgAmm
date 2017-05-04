<%-- 
    Document   : nesidebar
    Created on : 16-apr-2017, 11.56.25
    Author     : Cristian
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="sidebar">
    <div id="persone" >

        <div id="titlebar">
            <p>Persone online</p>
        </div>
        
        <c:if test="${loggedOn == true}">
        <div id="listpers">
            <c:forEach var="utenti" items="${utenti}">
                <c:if test="${utenti.nome != null}">
                    <p><a href="bacheca.html?user=${utenti.id}">${utenti.nome} ${utenti.cognome}</a></p>
                </c:if>
            </c:forEach>
        </div>
        </c:if>
        
    </div>
</div>