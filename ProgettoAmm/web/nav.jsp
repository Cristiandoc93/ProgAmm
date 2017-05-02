<%-- 
    Document   : nav
    Created on : 16-apr-2017, 11.46.34
    Author     : Cristian
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<nav>
    <ul>
        <li <c:if test="${page=='bacheca'}">class="active"</c:if>><a href="Bacheca">Bacheca</a></li>
        <li <c:if test="${page=='descrizione'}">class="active"</c:if>><a href="descrizione.html">Descrizione</a></li>
        <li <c:if test="${page=='login'}">class="active"</c:if>><a href="login.html">Login</a></li>
        <li <c:if test="${page=='profilo'}">class="active"</c:if>><a href="profilo.html">Profilo</a></li>
    </ul>
</nav>