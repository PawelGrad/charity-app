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

<form:form method="post" modelAttribute="user" action="/changePassword/${token}">

    <div class="form-group form-group--inline">
        <label hidden> Email: <form:input path="enabled" /> </label>
        <label hidden> Email: <form:input path="id" /> </label>
        <label hidden> Email: <form:input path="authorities" /> </label>
        <label hidden> Email: <form:input path="tokens" /> </label>
        <label hidden> Email: <form:input path="email" /> </label>
        <label hidden> Email: <form:input path="firstName" /> </label>
        <label hidden> Email: <form:input path="lastName" /> </label>
    </div>

    <div class="form-group form-group--inline">
        <label> Haslo: <form:input path="password"/> </label>
    </div>

    <div class="form-group form-group--inline">
        <label> Haslo: <form:input path="passwordConfirmation"/> </label>
    </div>



    <div class="form-group form-group--buttons">
        <button type="submit" class="btn btn-primary">Edytuj</button>
    </div>
</form:form>
</body>
</html>
