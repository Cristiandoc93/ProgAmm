<%-- 
    Document   : filterjson
    Created on : 3-giu-2017, 13.21.56
    Author     : Cristian
--%>


<%@page contentType="application/json" pageEncoding="UTF-8"%>
<%@ taglib prefix="json" uri="http://www.atg.com/taglibs/json" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<json:array>
    <c:forEach var="utenti" items="${utenti}">
        <json:object>
            <json:property name="nome" value="${utenti.nome}"/>
            <json:property name="cognome" value="${utenti.cognome}"/>
            <json:property name="id" value="${utenti.id}"/>
            <%--<json:property name="email" value="${utente.email}"/>
            <json:property name="password" value="${utente.password}"/>
            <json:property name="urlFotoProfilo" value="${utente.urlFotoProfilo}"/>--%>
        </json:object>
    </c:forEach>
</json:array>