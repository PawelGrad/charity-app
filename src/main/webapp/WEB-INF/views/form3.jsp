<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pl">
<head>


    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Document</title>

    <link rel="stylesheet" href="<c:url value="../resources/css/style.css"/>"/>
</head>
<body>
<header class="header--form-page">
    <%@include file="header.jsp" %>



    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                Oddaj rzeczy, których już nie chcesz<br />
                <span class="uppercase">potrzebującym</span>
            </h1>

            <div class="slogan--steps">
                <div class="slogan--steps-title">Wystarczą 4 proste kroki:</div>
                <ul class="slogan--steps-boxes">
                    <li>
                        <div><em>1</em><span>Wybierz rzeczy</span></div>
                    </li>
                    <li>
                        <div><em>2</em><span>Spakuj je w worki</span></div>
                    </li>
                    <li>
                        <div><em>3</em><span>Wybierz fundację</span></div>
                    </li>
                    <li>
                        <div><em>4</em><span>Zamów kuriera</span></div>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>

<section class="form--steps">
    <div class="form--steps-instructions">
        <div class="form--steps-container">
            <h3>Ważne!</h3>
            <p data-step="3" class="active">
                Wybierz jedną, do
                której trafi Twoja przesyłka.
            </p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>3</span>/4</div>
        <a name="form">
        <form:form method="post" modelAttribute="donation">
            <div hidden>
            <form:checkboxes path="categories" items="${categories}" itemLabel="name" />
            <form:input path="quantity" />

            </div>
                <!-- STEP 2 -->
            <div data-step="3" class="active">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>



                <c:forEach items="${institutions}" var="ins">

                <div class="form-group form-group--checkbox">
                    <label>
                        <input type="radio" name="organization" value="${ins.id}" checked="checked"/>
                        <span class="checkbox radio"></span>
                        <span class="description">
                  <div class="title">${ins.name}</div>
                  <div class="subtitle">${ins.descritpion}</div>
                </span>
                    </label>
                </div>
                </c:forEach>


                <div class="form-group form-group--buttons">
                    <input type="submit" class="btn prev-step" value="Wstecz" formaction="/user/form2#form">
                    <input type="submit" class="btn next-step" value="Dalej" formaction="/user/form4#form">
                </div>
            </div>
        </form:form>

    </div>
</section>

<footer>
    <%@include file="footer.jsp" %>
</footer>

<script src="js/app.js"></script>
</body>
</html>
