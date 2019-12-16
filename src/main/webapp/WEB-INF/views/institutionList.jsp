<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<table class="table" style="width:100%">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Description</th>
    </tr>
    <c:forEach items="${institutions}" var="institution">
        <tr>
            <td><c:out value="${institution.id}"/></td>
            <td><c:out value="${institution.name}"/></td>
            <td><c:out value="${institution.descritpion}"/></td>
            <td><a href="/admin/institutions/edit/${institution.id}"><button>Edytuj</button></a></td>
            <td><a href="/admin/institutions/remove/${institution.id}"><button>Usuń</button></a></td>
        </tr>
    </c:forEach>
</table>
<a href="/admin/institutions/add"><button>New Institution</button></a>