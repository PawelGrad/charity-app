<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<table class="table" style="width:100%">
    <tr>
        <th>Status</th>
        <th>Data odbioru</th>
        <th>Data utworzenia</th>
    </tr>
    <c:forEach items="${donations}" var="donation">
        <tr>
            <td><c:out value="${donation.delivered}"/></td>
            <c:if test="${donation.delivered=='true'}">
            <td><c:out value="${donation.deliveredTime}"/></td>
            </c:if>
            <c:if test="${donation.delivered=='false'}">
                <td></td>
            </c:if>
            <td><c:out value="${donation.creationTime}"/></td>
            <td>
                <form class="form-signin" action="/user/donations/details" method="post" >
                    <input hidden type="text" name ="id" class="form-control" required autofocus value="${donation.id}">
                    <input class="btn btn-md btn-success btn-block" type="submit" value="Details">
                </form>

            </td>
        </tr>
    </c:forEach>
</table>