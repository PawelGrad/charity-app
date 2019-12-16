<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

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
<table class="table" style="width:100%">
    <tr>
        <th>Data odbioru</th>
        <th>Data utworzenia</th>
        <th>Instytucja </th>
        <th>Ilosc paczek </th>
        <th>Dostarczony</th>
    </tr>
<tr>
    <td>${donation.pickUpDate} ${donation.pickUpTime}</td>
    <td><c:out value="${donation.creationTime}"/></td>
    <td>${donation.institution.name}</td>
    <td>${donation.quantity}</td>
    <td>${donation.delivered}</td>
    <td>
        <form class="form-signin" action="/user/donations/details/confirm" method="post" >
            <input hidden type="text" name ="id" class="form-control" required autofocus value="${donation.id}">
            <input class="btn btn-md btn-success btn-block" type="submit" value="Zmien status dostarczenia">
        </form>
    </td>
</tr>
</table>
</body>
</html>
