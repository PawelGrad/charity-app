<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<table class="table" style="width:100%">
    <tr>
        <th>ID</th>
        <th>Imie</th>
        <th>Nazwisko</th>
        <th>Email</th>
        <th>Enabled</th>
    </tr>
    <c:forEach items="${admins}" var="admin">
        <tr>
            <td><c:out value="${admin.id}"/></td>
            <td><c:out value="${admin.firstName}"/></td>
            <td><c:out value="${admin.lastName}"/></td>
            <td><c:out value="${admin.email}"/></td>
            <td><c:out value="${admin.enabled}"/></td>
            <td><a href="/admin/admins/edit/${admin.id}"><button>Edytuj</button></a></td>
            <td><a href="/admin/admins/remove/${admin.id}"><button>Usuń</button></a></td>
        </tr>
    </c:forEach>

</table>
<a href="/admin/admins/add"><button>New Admin</button></a>