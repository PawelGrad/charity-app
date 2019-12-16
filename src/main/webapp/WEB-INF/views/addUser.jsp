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
    <link rel="stylesheet" href="<c:url value="resources/css/style.css"/>"/>

</head>
<body>

<form:form method="post" modelAttribute="admin" action="/admin/admins/add">


    <div class="form-group">
        <form:input path="email" placeholder="Email"/>
    </div>

    <div class="form-group">
        <form:input path="firstName" placeholder="Imie"/>
    </div>
    <div class="form-group">
        <form:input path="lastName" placeholder="Nazwisko"/>
    </div>

    <div class="form-group">
        <form:input path="password" type="password" placeholder="Hasło"/>
    </div>

    <div class="form-group">
        <form:input path="passwordConfirmation" type="password" placeholder="Powtórz hasło"/>
    </div>


    <div class="form-group form-group--buttons">
        <button type="submit" class="btn btn-primary">Dodaj</button>
    </div>
</form:form>
</body>
</html>
