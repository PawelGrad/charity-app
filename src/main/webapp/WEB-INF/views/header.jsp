<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<li class="logged-user">
    <nav class="container container--70">
        <c:if test="${user == null}">
        <ul class="nav--actions">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
        </ul>
        </c:if>

    <c:if test="${user != null}">
        Witaj, ${user.firstName}!
    </c:if>

        <ul>
            <li><a href="/" class="btn btn--without-border active">Start</a></li>
            <li><a href="#how" class="btn btn--without-border">O co chodzi?</a></li>
            <li><a href="#about-us" class="btn btn--without-border">O nas</a></li>
            <li><a href="#organizations" class="btn btn--without-border">Fundacje i organizacje</a></li>
            <li><a href="#contact" class="btn btn--without-border">Kontakt</a></li>
        </ul>
    </nav>
</li>
