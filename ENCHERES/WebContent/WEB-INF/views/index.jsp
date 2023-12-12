<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html lang="fr">
<%@include file="templates/head.jspf"%>
<body class="m-2">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <c:choose>
            <c:when test="${not empty sessionScope.utilisateur}">
                <%@include file="templates/navconnecte.jspf"%>
            </c:when>
            <c:otherwise>
                <%@include file="templates/nav.jspf"%>
            </c:otherwise>
        </c:choose>
    </nav>
    
    <c:if test="${param.page == 'login' || page == 'login'}">
        <%@include file="pages/login.jspf"%>
    </c:if>
    <c:if test="${param.page == 'enregistrer' || page == 'enregistrer'}">
        <%@include file="pages/enregistrer.jspf"%>
    </c:if>
    <c:if test="${param.page == 'accueil' || page == 'accueil'}">
        <%@include file="pages/accueil.jspf"%>
    </c:if>
    <c:if test="${param.page == 'accueilconnecte' || page == 'accueilconnecte'}">
        <%@include file="pages/accueilconnecte.jspf"%>
    </c:if>
    <c:if test="${param.page == 'monprofil' || page == 'monprofil'}">
        <%@include file="pages/monprofil.jspf"%>
    </c:if>
    <c:if test="${param.page == 'profil' || page == 'profil'}">
        <%@include file="pages/profil.jspf"%>
    </c:if>
    <c:if test="${param.page == 'modifiermonprofil' || page == 'modifiermonprofil'}">
        <%@include file="pages/modifiermonprofil.jspf"%>
    </c:if>
    <c:if test="${param.page == 'articleenvente' || page == 'articleenvente'}">
        <%@include file="pages/articleenvente.jspf"%>
    </c:if>
    <c:if test="${param.page == 'vendreunarticle' || page == 'vendreunarticle'}">
        <%@include file="pages/vendreunarticle.jspf"%>
    </c:if>
    <c:if test="${param.page == 'erreur' || page == 'erreur'}">
        <%@include file="pages/erreur.jspf"%>
    </c:if>
    <c:if test="${param.page == 'succes' || page == 'succes'}">
        <%@include file="pages/succes.jspf"%>
    </c:if>
    <c:if test="${param.page == 'successuppression' || page == 'successuppression'}">
        <%@include file="pages/successuppression.jspf"%>
    </c:if>
    <c:if test="${param.page == 'motdepasseoublie' || page == 'motdepasseoublie'}">
        <%@include file="pages/nouveaumotdepasse.jspf"%>
    </c:if>
    
    <%@include file="templates/footer.jspf"%>  
</body>
</html>


