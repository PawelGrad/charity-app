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
            <p data-step="2" class="active">
                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy
                wiedzieć komu najlepiej je przekazać.
            </p>
        </div>
    </div>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>2</span>/4</div>
<a name="form"></a>
        <form:form id="form" method="post" modelAttribute="donation" action="/user/form3#form">
        <div hidden>
            <form:checkboxes path="categories" items="${categories}" itemLabel="name" />

        </div>
            <!-- STEP 2 -->
            <div data-step="2" class="active">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">

                    <label>
                        Liczba 60l worków:
                        <form:input path="quantity"/>

                    </label>
                    </form:form>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn btn-primary" id="submitButton">Dalej</button>
                </div>
            </div>


    </div>
</section>

<footer>
    <%@include file="footer.jsp" %>
</footer>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script>
    $(document).ready(function() {
        console.log("aaaa");
        document.getElementById("quantity").removeAttribute("type");
        document.getElementById("quantity").setAttribute("type","number");
        document.getElementById("quantity").setAttribute("min","1");
        document.getElementById("quantity").value = 1;


    });

    $("#submitButton").on("click", function(){
        if(document.getElementById("quantity").value >= 1 && Number.isInteger(parseInt(document.getElementById("quantity").value))) {
            document.getElementById("form").submit();
        }
    });
</script>


</body>
</html>
